package com.ling.learn0607.transformwitholdcode;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Instant和ZonedDateTime是新类，java.util.Date和java.util.GregorianCalendar类是旧类，但是他们可以相互转换
 * 
 * 更多和遗留代码的转换见第302页
 *
 * ChapterII06_DateAndTime/com.ling.learn0607.transformwitholdcode.OldCodeTranformTest.java
 *
 * author lingang
 *
 * createTime 2020-02-25 00:29:44
 *
 */
public class OldCodeTranformTest {
public static void main(String[] args) {
	Instant inst  = Instant.now();
	ZonedDateTime zdt = ZonedDateTime.now();
	Date date = new Date();
	
	System.out.println(date.toInstant());//Date转Instant
	System.out.println(Date.from(inst));//Instant转Date
	
	GregorianCalendar cal = new GregorianCalendar();
	System.out.println(GregorianCalendar.from(zdt));//ZonedDateTime转GregorianCalendar
	System.out.println(cal.toZonedDateTime());//GregorianCalendar转ZonedDateTime
}
}
