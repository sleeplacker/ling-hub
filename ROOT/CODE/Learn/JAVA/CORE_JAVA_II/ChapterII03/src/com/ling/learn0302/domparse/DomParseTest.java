package com.ling.learn0302.domparse;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * DOM解析：DOM解析会将XML文档解析成树结构，所以对大文件的解析比较浪费内存，而SAX解析没这个问题，但是得不到文档整体结构
 *
 * ChapterII03/com.ling.learn0302.domparse.DomParseTest.java
 *
 * author lingang
 *
 * createTime 2020-02-07 22:29:31
 *
 */
public class DomParseTest {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		File file = new File("src/com/ling/learn0302/domparse/test.xml");
		Document doc = builder.parse(file);// 创建DOM树
		Element root = doc.getDocumentElement();// 获取根节点
		NodeList children = root.getChildNodes();// 获取节点的所有子节点
		// 下面的结果打印为：
		// #text
		// font
		// #text
		// #text表示两个标签之间(即root标签和text标签之间)的空白字符
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			System.out.println(child.getNodeName());
		}

		// 对叶子节点直接使用child.getFirstChild()方法，而不必遍历NodeList列表
		Node font = children.item(1);
		children = font.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child instanceof Element) {
				Element echild = (Element) child;
				Text textNode = (Text) echild.getFirstChild();
				String content = textNode.getData().trim();
				System.out.print(echild.getTagName() + "=" + content);

				// 获取节点属性
				NamedNodeMap attrs = echild.getAttributes();
				if (attrs.getLength() > 0) {
					for (int j = 0; j < attrs.getLength(); ++j) {
						Node attr = attrs.item(j);
						System.out.print(" " + attr.getNodeName() + ": " + attr.getNodeValue());
					}
				}
				System.out.println();
			}
		}

		// 其他方法
		Element child1 = (Element) root.getFirstChild();
		child1.getNextSibling();// 获取下一个兄弟节点，可以和getFirstChild方法以前使用来遍历孩子节点列表，而不需要使用NodeList
		child1.getParentNode();// 获取父节点
	}
}
