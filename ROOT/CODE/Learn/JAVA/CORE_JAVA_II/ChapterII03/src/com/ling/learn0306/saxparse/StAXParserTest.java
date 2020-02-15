package com.ling.learn0306.saxparse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * StAX解析器，相比SAX解析器，操作更简单，不需要重写DefaultHandler的各个方法
 *
 * ChapterII03/com.ling.learn0306.saxparse.StAXParserTest.java
 *
 * author lingang
 *
 * createTime 2020-02-15 23:44:37
 *
 */
public class StAXParserTest {
	public static void main(String[] args) throws XMLStreamException, IOException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);// 设置属性方式，这里的true其实是默认值
		XMLStreamReader parser = factory
				.createXMLStreamReader(Files.newInputStream(Paths.get("src/com/ling/learn0306/saxparse/saxtest1.xml")));
		while (parser.hasNext()) {
			int event = parser.next();
			switch (event) {
			case XMLStreamConstants.START_DOCUMENT:
				System.out.println("文档开始");
				break;
			case XMLStreamConstants.END_DOCUMENT:
				System.out.println("文档结束");
				break;
			case XMLStreamConstants.START_ELEMENT:
				System.out.print("开始标签：" + parser.getLocalName() + " 属性：");
				for (int i = 0; i < parser.getAttributeCount(); ++i) {
					System.out.print(parser.getAttributeLocalName(i) + "=" + parser.getAttributeValue(i));
				}
				System.out.println();
				break;
			case XMLStreamConstants.END_ELEMENT:
				System.out.println("结束标签：" + parser.getLocalName());
				break;
			case XMLStreamConstants.CHARACTERS:
				System.out.println("遇到字符："
						+ new String(parser.getTextCharacters(), parser.getTextStart(), parser.getTextLength()));
				break;

			default:
				break;
			}
		}
	}
}
