package com.ling.learn0305.namespace;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * 命名空间-前缀格式
 *
 * ChapterII03/com.ling.learn0305.namespace.NamespacePrefixTest.java
 *
 * author lingang
 *
 * createTime 2020-02-14 14:35:37
 *
 */
public class NamespacePrefixTest {

	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);// 忽略标签间的空白字符
		factory.setNamespaceAware(true);// 打开命名空间的支持

		DocumentBuilder builder = factory.newDocumentBuilder();

		/* 命名空间前缀格式 */
		File file = new File("src/com/ling/learn0305/namespace/namespace-prefix.xml");
		Document doc = builder.parse(file);

		Node root = doc.getDocumentElement();
		System.out.println("********/root********");
		System.out.println("带前缀标签名：" + root.getNodeName());
		System.out.println("命名空间：" + root.getNamespaceURI());
		System.out.println("不带前缀标签名：" + root.getLocalName());

		for (int i = 0; i < root.getChildNodes().getLength(); ++i) {
			if (root.getChildNodes().item(i) instanceof Element) {

				if (root.getChildNodes().item(i).getNodeName().equals("sub:font")) {
					Node font = root.getChildNodes().item(i);
					System.out.println("\n********/root/sub:font********");
					System.out.println("带前缀标签名：" + font.getNodeName());
					System.out.println("命名空间：" + font.getNamespaceURI());
					System.out.println("不带前缀标签名：" + font.getLocalName());

					for (int j = 0; j < font.getChildNodes().getLength(); ++j) {
						if (font.getChildNodes().item(j).getNodeName().equals("sub:style")) {
							Node style = font.getChildNodes().item(j);
							System.out.println("\n********/root/font/sub:style********");//带前缀的是namespace2
							System.out.println("带前缀标签名：" + style.getNodeName());
							System.out.println("命名空间：" + style.getNamespaceURI());
							System.out.println("不带前缀标签名：" + style.getLocalName());
						}
						if (font.getChildNodes().item(j).getNodeName().equals("size")) {
							Node style = font.getChildNodes().item(j);
							System.out.println("\n********/root/font/size********");//不带前缀的是namespace1
							System.out.println("带前缀标签名：" + style.getNodeName());
							System.out.println("命名空间：" + style.getNamespaceURI());
							System.out.println("不带前缀标签名：" + style.getLocalName());
						}
					}
				} else if (root.getChildNodes().item(i).getNodeName().equals("color")) {
					Node color = root.getChildNodes().item(i);
					System.out.println("\n********/root/color********");
					System.out.println("带前缀标签名：" + color.getNodeName());
					System.out.println("命名空间：" + color.getNamespaceURI());
					System.out.println("不带前缀标签名：" + color.getLocalName());
				}
			}
		}
	}
}
