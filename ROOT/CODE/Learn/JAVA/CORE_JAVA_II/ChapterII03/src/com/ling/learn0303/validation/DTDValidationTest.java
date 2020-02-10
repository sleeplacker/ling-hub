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
 * DTD验证
 * 
 * 1. xml在eclipse中验证成功，可能在dtd验证中失败，因为dtd要求xml中对应的每个标签都对应一个dtd的systemId
 * 
 * 2.
 * DTD验证中，setIgnoringElementContentWhitespace方法无效，结果中还是包含了标签间的空白字符，好像schema验证才支持该方法
 *
 * ChapterII03/com.ling.learn0303.validation.DTDValidationTest.java
 *
 * author lingang
 *
 * createTime 2020-02-11 02:53:47
 *
 */
public class DTDValidationTest {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);// 开启验证特性
		factory.setIgnoringElementContentWhitespace(true);// 忽略标签间的空白字符
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
		File file = new File("src/com/ling/learn0303/validation/validationTest2.xml");
		Document doc = builder.parse(file);// 创建DOM树
		Element root = doc.getDocumentElement();// 获取根节点
		NodeList child = root.getChildNodes();
		for (int i = 0; i < child.getLength(); ++i) {
			System.out.println(child.item(i).getNodeName());
		}

	}
}
