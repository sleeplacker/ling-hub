package com.ling.learn0404.web;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 发送邮件
 *
 * ChapterII04_Network/com.ling.learn0404.web.SendMailTest.java
 *
 * author lingang
 *
 * createTime 2020-02-19 23:47:15
 *
 */
public class SendMailTest {
	public static void main(String[] args)
			throws UnknownHostException, IOException, AddressException, MessagingException {
		// 过去发送邮件的方式，现在看来没效果
		try (Socket s = new Socket("smtp.263.net", 25);
				PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"))) {
			out.print("HELO smtp.263.net\r\n");
			out.print("MAIL FROM: 1544650023@qq.com\r\n");
			out.print("RCPT TO: lingang@csii.com.cn\r\n");
			out.print("DATA\r\n");
			out.print("Subject: testLearn\r\n");
			out.print("\r\n");
			out.print("AAA\r\n");
			out.print("BBB\r\n");
			out.print("CCC\r\n");
			out.print(".\r\n");
			out.print("QUIT\r\n");
		}

		/*
		 * 引入javamail的aip来发送邮件
		 * 
		 * 感觉流程是：先登录一个邮箱服务器(需要验证用户名和密码)，然后就可以发邮件，发件方和收件方可以都不属于前面登录的邮箱服务器，
		 * 例如：登录的是263邮箱，但是发件人和收件人都是QQ邮箱用户
		 * 
		 */
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtps");
		props.setProperty("mail.smtps.auth", "true");
		props.setProperty("mail.smtps.host", "smtp.263.net");// 263邮箱的服务器地址，SMTP协议为25号端口
		props.setProperty("mail.smtps.user", "lingang@csii.com.cn");// 263邮箱用户名
		Session mailSession = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress("1361806970@qq.com"));// 发件人
		message.addRecipient(RecipientType.TO, new InternetAddress("1544650023@qq.com"));// 添加收件人列表
		message.setSubject("Learn_Test");
		message.setText("AAA\nBBB\nCCC");

		try (Transport transport = mailSession.getTransport()) {
			transport.connect(null, "0lglglglg0LG");// 密码是真密码，测试的时候再填
			transport.sendMessage(message, message.getAllRecipients());
		}

	}
}
