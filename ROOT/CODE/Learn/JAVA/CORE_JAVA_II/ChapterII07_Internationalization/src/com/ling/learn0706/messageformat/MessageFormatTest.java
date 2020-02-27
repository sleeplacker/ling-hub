package com.ling.learn0706.messageformat;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 消息格式化
 *
 * ChapterII07_Internationalization/com.ling.learn0706.messageformat.MessageFormatTest.java
 *
 * author lingang
 *
 * createTime 2020-02-27 11:18:49
 *
 */
public class MessageFormatTest {
	public static void main(String[] args) {
		// 使用占位符格式化消息，MessageFormat.format方法的第二个参数开始是占位符，占位符序号从0开始
		String msg = "在 {2}, 一场 {0} 毁坏了 {1} 间房子并造成 {3} 的损失";
		String formatedMsg = MessageFormat.format(msg, "飓风", 99, new GregorianCalendar(1999, 0, 1).getTime(), 10.0e8);
		System.out.println(formatedMsg);

		// 为占位符提供格式可以进一步完善格式，格式为：{占位符序号, 类型, 风格}，关于类型和风格的各种取值参考第328页面
		msg = "在 {2,date,long}, 一场 {0} 毁坏了 {1} 间房子并造成 {3,number,currency} 的损失";
		formatedMsg = MessageFormat.format(msg, "飓风", 99, new GregorianCalendar(1999, 0, 1).getTime(), 10.0e8);
		System.out.println(formatedMsg);

		// 对于日期类型，风格可以采用yyyy-MM-dd这种模式，参见SimpleDateFormat类
		// 对于数字类型，风格可以采用￥,##0这种模式，参见DecimalFormat类
		msg = "在 {2,date,yyyy/MM/dd}, 一场 {0} 毁坏了 {1} 间房子并造成 {3,number,￥##,###.000} 的损失";
		formatedMsg = MessageFormat.format(msg, "飓风", 99, new GregorianCalendar(1999, 0, 1).getTime(), 10.0e8);
		System.out.println(formatedMsg);

		// 默认情况MessageFormat使用的是默认的Locale，可以设置Locale
		msg = "在 {2,date,long}, 一场 {0} 毁坏了 {1} 间房子并造成 {3,number,currency} 的损失";
		MessageFormat mf = new MessageFormat(msg, Locale.US);
		// 注意下面format中的参数必须是一个Object[]，如果用了多个参数，可能会用到format的其他重载方法版本
		formatedMsg = mf.format(new Object[] { "飓风", 99, new GregorianCalendar(1999, 0, 1).getTime(), 10.0e8 });
		System.out.println(formatedMsg);
	}
}
