package com.ling.learn0306.saxparse;

import java.io.IOException;
import java.nio.file.Paths;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX解析器
 *
 * ChapterII03/com.ling.learn0306.saxparse.SAXParserTest.java
 *
 * author lingang
 *
 * createTime 2020-02-15 22:47:07
 *
 */
public class SAXParserTest {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);// 开启命名空间模式
		SAXParser parser = factory.newSAXParser();
		parser.parse(Paths.get("src/com/ling/learn0306/saxparse/saxtest1.xml").toFile(), new DefaultHandler() {

			@Override
			public void startDocument() throws SAXException {// 文档开始解析
				System.out.println("文档开始");
				super.startDocument();
			}

			@Override
			public void endDocument() throws SAXException {// 文档解析完成
				System.out.println("文档结束");
				super.endDocument();
			}

			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes)
					throws SAXException {// 元素开始解析
				System.out.print("开始标签：" + localName + " URI：" + uri + " attributes：");
				if (attributes != null) {
					for (int i = 0; i < attributes.getLength(); ++i) {
						System.out.print(attributes.getLocalName(i) + "=" + attributes.getValue(i));
					}
				}
				System.out.println();
				super.startElement(uri, localName, qName, attributes);
			}

			@Override
			public void endElement(String uri, String localName, String qName) throws SAXException {// 元素解析完成
				System.out.println("结束标签：" + localName);
				super.endElement(uri, localName, qName);
			}

			@Override
			public void characters(char[] ch, int start, int length) throws SAXException {// 遇到字符数据，包括元素间的空白字符和标签间的内容
				System.out.println("字符：" + new String(ch, start, length));
				super.characters(ch, start, length);
			}

		});
	}
}
