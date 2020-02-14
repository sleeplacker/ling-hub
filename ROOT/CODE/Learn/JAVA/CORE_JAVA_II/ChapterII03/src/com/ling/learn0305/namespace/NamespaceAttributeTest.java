package com.ling.learn0305.namespace;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * 命名空间-属性处理
 * 
 * 属性必须要带前缀才有命名空间，否则命名空间为null
 *
 * ChapterII03/com.ling.learn0305.namespace.NamespaceAttributeTest.java
 *
 * author lingang
 *
 * createTime 2020-02-14 14:45:32
 *
 */
public class NamespaceAttributeTest {

	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);// 忽略标签间的空白字符
		factory.setNamespaceAware(true);// 打开命名空间的支持

		DocumentBuilder builder = factory.newDocumentBuilder();

		/* 命名空间前缀格式 */
		File file = new File("src/com/ling/learn0305/namespace/namespace-attr.xml");
		Document doc = builder.parse(file);

		Node root = doc.getDocumentElement();

		for (int i = 0; i < root.getChildNodes().getLength(); ++i) {
			if (root.getChildNodes().item(i) instanceof Element) {

				if (root.getChildNodes().item(i).getNodeName().equals("sub:font")) {
					Node font = root.getChildNodes().item(i);
					for (int j = 0; j < font.getChildNodes().getLength(); ++j) {
						if (font.getChildNodes().item(j).getNodeName().equals("size")) {
							Node size = font.getChildNodes().item(j);
							NamedNodeMap attrs = size.getAttributes();
							for (int k = 0; k < attrs.getLength(); ++k) {
								System.out.println(
										"\n********/root/font/size/@" + attrs.item(k).getNodeName() + "********");
								System.out.println("带前缀属性名：" + attrs.item(k).getNodeName());
								System.out.println("命名空间：" + attrs.item(k).getNamespaceURI());
								System.out.println("不带前缀属性名：" + attrs.item(k).getLocalName());
							}

						}
					}
				} else if (root.getChildNodes().item(i).getNodeName().equals("color")) {
					Node color = root.getChildNodes().item(i);
					NamedNodeMap attrs = color.getAttributes();
					for (int k = 0; k < attrs.getLength(); ++k) {
						System.out.println("\n********/root/color/@" + attrs.item(k).getNodeName() + "********");
						System.out.println("带前缀属性名：" + attrs.item(k).getNodeName());
						System.out.println("命名空间：" + attrs.item(k).getNamespaceURI());
						System.out.println("不带前缀属性名：" + attrs.item(k).getLocalName());
					}
				}
			}
		}
	}
}
