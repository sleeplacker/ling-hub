package com.ling.learn0308.xsl;

import java.nio.file.Paths;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * XSL转换
 *
 * ChapterII03/com.ling.learn0308.xsl.XSLTest.java
 *
 * author qiongqiong
 *
 * createTime 2020-02-16 13:12:24
 *
 */
public class XSLTest {
	public static void main(String[] args) throws TransformerFactoryConfigurationError, TransformerException {
		StreamSource htmlStyleSouce = new StreamSource(
				Paths.get("src/com/ling/learn0308/xsl/htmlStylesheet.xsl").toFile());
		Transformer t = TransformerFactory.newInstance().newTransformer(htmlStyleSouce);
		t.transform(new StreamSource(Paths.get("src/com/ling/learn0308/xsl/xslTest.xml").toFile()),
				new StreamResult(Paths.get("src/com/ling/learn0308/xsl/htmlResult.html").toFile()));
	}
}
