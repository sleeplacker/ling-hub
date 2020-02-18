package com.ling.learn0307.createxml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

/**
 * 使用DOM树的方式创建XML
 * 
 * 优点：xml格式化可以控制
 * 
 * 缺点：操作麻烦，写大文档时浪费内存
 *
 * ChapterII03/com.ling.learn0307.createxml.CreateXMLUseDOMTest.java
 *
 * author lingang
 *
 * createTime 2020-02-16 00:20:22
 *
 */
public class CreateXMLUseDOMTest {
	public static void main(String[] args) throws ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException, IOException {
		Document doc = createXMLNoNamespace();// 创建不带命名空间的DOM树
		writeXMLFile(doc, "src/com/ling/learn0307/createxml/createXMLUseDOMTest.xml");// 将DOM树写出到文件
		writeXMLFileLSS(doc, "src/com/ling/learn0307/createxml/createXMLUseDOMTestLSS.xml");// 使用LSS方式将DOM树写出到文件

		doc = createXMLWithNamespace();// 创建带命名空间的DOM树
		writeXMLFile(doc, "src/com/ling/learn0307/createxml/createXMLUseDOMTestWithNS.xml");
	}

	/**
	 * 创建不带命名空间的xml
	 * 
	 * @return
	 * 
	 * @throws ParserConfigurationException
	 */
	private static Document createXMLNoNamespace() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		// 创建DOM文档
		Document doc = builder.newDocument();
		// 创建元素
		Element root = doc.createElement("root");
		Element font = doc.createElement("font");
		Element name = doc.createElement("name");
		Element size = doc.createElement("size");
		size.setAttribute("unit", "px");// 设置元素属性
		// 创建文本内容
		Text name_text = doc.createTextNode("Helvetica");
		Text size_text = doc.createTextNode("33");

		// 构建DOM树
		doc.appendChild(root);
		root.appendChild(font);
		font.appendChild(name);
		name.appendChild(name_text);
		font.appendChild(size);
		size.appendChild(size_text);
		return doc;
	}

	/**
	 * 创建带命名空间的xml
	 * 
	 * @return
	 * 
	 * @throws ParserConfigurationException
	 */
	private static Document createXMLWithNamespace() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);// 添加命名空间支持
		DocumentBuilder builder = factory.newDocumentBuilder();
		// 创建DOM文档
		Document doc = builder.newDocument();
		// 创建元素
		Element root = doc.createElementNS("http://www.lg.com/ns1", "root");// 无前缀命名空间
		Element font = doc.createElementNS("http://www.lg.com/ns2", "sub:font");// 带前缀命名空间
		Element name = doc.createElement("name");
		Element size = doc.createElement("sub:size");
		Element color = doc.createElement("color");
		size.setAttribute("sub:unit", "px");// 给属性名称加sub前缀，xml解析时属性就属于http://www.lg.com/ns2命名空间
		// 创建文本内容
		Text name_text = doc.createTextNode("Helvetica");
		Text size_text = doc.createTextNode("33");
		Text color_text = doc.createTextNode("Yellow");

		// 构建DOM树
		doc.appendChild(root);
		root.appendChild(font);
		font.appendChild(name);
		name.appendChild(name_text);
		font.appendChild(size);
		size.appendChild(size_text);
		root.appendChild(color);
		color.appendChild(color_text);
		return doc;
	}

	/**
	 * 将DOM树写出到输出流
	 * 
	 * @param doc
	 * @param path
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	private static void writeXMLFile(Document doc, String path)
			throws TransformerFactoryConfigurationError, TransformerException {

		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		// 设置DOCTYPE
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "sysid");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "pubid");

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");// 标签是否换行
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");// 在换行的前提下，缩进2个空格

		transformer.transform(new DOMSource(doc), new StreamResult(Paths.get(path).toFile()));
	}

	/**
	 * 使用LSS方式写出DOM树
	 * 
	 * @param doc
	 * @param path
	 * @throws IOException
	 */
	private static void writeXMLFileLSS(Document doc, String path) throws IOException {
		DOMImplementation impl = doc.getImplementation();
		DOMImplementationLS implLS = (DOMImplementationLS) impl.getFeature("LS", "3.0");
		LSSerializer ser = implLS.createLSSerializer();

		ser.getDomConfig().setParameter("format-pretty-print", true);// 换行并缩进
		// 将xml写出到字符串
		String str = ser.writeToString(doc);
		System.out.println(str);
		LSOutput out = implLS.createLSOutput();
		out.setEncoding("UTF-8");
		out.setByteStream(Files.newOutputStream(Paths.get(path)));
		ser.write(doc, out);
	}
}
