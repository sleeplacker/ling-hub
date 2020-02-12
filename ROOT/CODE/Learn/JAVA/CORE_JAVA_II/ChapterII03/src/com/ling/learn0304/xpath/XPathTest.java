package com.ling.learn0304.xpath;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * XPath定位
 *
 * ChapterII03/com.ling.learn0304.xpath.XPathTest.java
 *
 * author lingang
 *
 * createTime 2020-02-12 14:13:35
 *
 */
public class XPathTest {
	public static void main(String[] args)
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
		/* 先获得元素节点 */
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse("src/com/ling/learn0304/xpath/xpathtest.xml");

		/* 创建XPath对象 */
		XPathFactory xpFactory = XPathFactory.newInstance();
		XPath path = xpFactory.newXPath();

		/* 定位信息 */
		// 定位标签内容，如果标签有连续多个相同的，则取第一个
		String age1 = path.evaluate("/root/row/column", doc);
		System.out.println(age1);
		// 定位标签内容，对于有多个连续相同标签的情况，指定取第几个，标号从1开始
		String sex1 = path.evaluate("/root/row/column[2]", doc);
		System.out.println(sex1);
		// 定位标签属性
		String name1 = path.evaluate("/root/row/@name", doc);
		System.out.println(name1);
		// 定位标签属性，多个相同标签时，可指定标号
		String name2 = path.evaluate("/root/row[2]/@name", doc);
		System.out.println(name2);
		// 定位标签属性，指定多个标号
		String type2_2 = path.evaluate("/root/row[2]/column[2]/@type", doc);
		System.out.println(type2_2);
		// 计算子元素数量
		System.out.println(path.evaluate("count(/root/row)", doc));
		// 指定计算类型-返回NodeList
		NodeList nl = (NodeList) path.evaluate("/root/row", doc, XPathConstants.NODESET);
		for (int i = 0; i < nl.getLength(); ++i) {
			System.out.println(nl.item(i).getNodeName());
		}
		// 从子节点开始定位
		Node row2 = nl.item(1);
		String age2 = path.evaluate("column", row2);// 从子节点开始定位是，不能表达式开始不能写/
		System.out.println(age2);
	}
}
