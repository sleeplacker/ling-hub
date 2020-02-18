package com.ling.learn0307.createxml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * 使用StAX方式创建XML
 * 
 * 优点：操作方便，生成大文档不那么耗内存资源
 * 
 * 缺点：不支持xml格式缩进，只能通过写入换行符和空格来缩进；对写出顺序有严格要求：必须先写属性，再写元素中的文本
 *
 * ChapterII03/com.ling.learn0307.createxml.CreateXMLUseStAXTest.java
 *
 * author lingang
 *
 * createTime 2020-02-16 01:17:16
 *
 */
public class CreateXMLUseStAXTest {
	public static void main(String[] args) throws XMLStreamException, IOException {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLStreamWriter writer = factory.createXMLStreamWriter(
				Files.newOutputStream(Paths.get("src/com/ling/learn0307/createxml/createXMLUseSAX.xml")));
		writer.writeStartDocument();// 产生XML文件头
		writer.writeStartElement("root");// 写元素
		writer.writeDefaultNamespace("http://www.lg.com.ns");// 为上面的元素写命名空间
		writer.writeStartElement("font");
		writer.writeNamespace("sub", "http://www.lg.com.ns_sub");// 为上面的元素写带前缀的命名空间
		writer.writeStartElement("sub:name");
		writer.writeCharacters("Helvetica");// 写文本到元素中
		writer.writeEndElement();// name元素结束
		writer.writeStartElement("size");
		writer.writeAttribute("unit", "px");// 写元素属性
		writer.writeCharacters("35");// 写文本到元素中
		writer.writeEndElement();// size元素结束
		writer.writeEndElement();// font元素结束
		writer.writeEmptyElement("color");// 写<color value="blue"/>格式的元素
		writer.writeAttribute("value", "blue");// 为上面的元素写属性
		writer.writeEndElement();// root元素结束
		writer.writeEndDocument();// XML文件结束
	}
}
