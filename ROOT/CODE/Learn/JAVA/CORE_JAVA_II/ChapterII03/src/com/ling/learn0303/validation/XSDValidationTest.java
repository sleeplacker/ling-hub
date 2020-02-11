package com.ling.learn0303.validation;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * 使用XML Schema验证
 * 
 * 确实使用schema验证方式，setIgnoringElementContentWhitespace参数设置起作用了
 *
 * ChapterII03/com.ling.learn0303.validation.XSDValidationTest.java
 *
 * author lingang
 *
 * createTime 2020-02-12 01:12:04
 *
 */
public class XSDValidationTest {
	public static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	public static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);// 开启验证特性
		factory.setIgnoringElementContentWhitespace(true);// 忽略标签间的空白字符

		// 下面两行是xml schema验证要多加的
		factory.setNamespaceAware(true);// 打开命名空间的支持
		factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);// 设置固定参数

		DocumentBuilder builder = factory.newDocumentBuilder();
		/*
		 * 如果使用的是DOM解析器，并且想要支持PUBLIC标识符，
		 * 需要像下面一样执行setEntityResolver操作并重写resolveEntity方法，这里用的是SYSTEM标识符，
		 * 所以其实不需要下面操作
		 */
		builder.setEntityResolver(new EntityResolver() {

			@Override
			public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
				// TODO Auto-generated method stub
				System.out.println("pub:" + publicId);
				System.out.println("sys:" + systemId);
				return null;// 使用默认行为
			}
		});

		/* 如果需要，可以设置致命错误捕捉器，否则按照默认行为，验证失败时不会抛错，只会警告 */
		builder.setErrorHandler(new ErrorHandler() {

			@Override
			public void warning(SAXParseException exception) throws SAXException {
				System.out.println("WARRING: " + exception);
			}

			@Override
			public void fatalError(SAXParseException exception) throws SAXException {
				System.out.println("FATALERROR: " + exception);
			}

			@Override
			public void error(SAXParseException exception) throws SAXException {
				System.out.println("ERROR: " + exception);
				throw exception;// 验证失败时抛错
			}
		});
		File file = new File("src/com/ling/learn0303/validation/validationTest03.xml");
		Document doc = builder.parse(file);// 创建DOM树
		Element root = doc.getDocumentElement();// 获取根节点
		NodeList child = root.getChildNodes();
		for (int i = 0; i < child.getLength(); ++i) {
			System.out.println(child.item(i).getNodeName());
		}

	}
}
