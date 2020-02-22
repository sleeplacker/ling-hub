package com.ling.learn0506.resultrollandupdate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ling.learn0503.connecttodb.ConnectToDBTest;

/**
 * 结果集的滚动和更新
 *
 * ChapterII05_DB/com.ling.learn0506.resultrollandupdate.ResultSetRollAndUpdateTest.java
 *
 * author lingang
 *
 * createTime 2020-02-23 01:04:52
 *
 */
public class ResultSetRollAndUpdateTest {

	public static void main(String[] args) throws SQLException {
		try (Connection conn = ConnectToDBTest.createConnection()) {

			/*
			 * 1. 滚动的结果集 createStatement方法两个参数依次表示：滚动属性，更新属性
			 */
			Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// 可滚动，但不可更新
			ResultSet rs = stat.executeQuery("SELECT * FROM CJ_USER");

			// 首先遍历所有记录，看看数据
			while (rs.next()) {
				System.out.println(rs.getInt(1) + ", " + rs.getString(2));
			}

			if (ResultSet.TYPE_SCROLL_SENSITIVE == rs.getType() || ResultSet.TYPE_SCROLL_INSENSITIVE == rs.getType()) {
				// 先检查结果集是否支持滚动
				// 使用游标
				System.out.println(rs.previous());// true，因为上面遍历过，所以游标在最后，有前一个位置
				// 看看现在指向哪条记录
				System.out.println(rs.getInt(1) + ", " + rs.getString(2));// 是最后一条数据
				// relative方法定位相对位置
				rs.relative(-2);// 往回移动两个位置
				System.out.println(rs.getInt(1) + ", " + rs.getString(2));// 第3条数据
				rs.relative(1);// 往后移动1个位置
				System.out.println(rs.getInt(1) + ", " + rs.getString(2));// 第4条数据
				// absolute方法定位绝对位置
				rs.absolute(1);
				System.out.println(rs.getInt(1) + ", " + rs.getString(2));// 第1条数据
				System.out.println(rs.getRow());// 获取行号，这里的行号指的是结果集中的行号，而不是数据库中的行号
				// 移动到头尾位置
				rs.last();// last方法移动到最后一行，同理还有first，beforeFirst和afterLast3个方法
				System.out.println(rs.getInt(1) + ", " + rs.getString(2));// last方法移动到最后一行
				// 检查是否在头尾位置
				System.out.println(rs.isFirst());
				System.out.println(rs.isLast());
				System.out.println(rs.isBeforeFirst());
				System.out.println(rs.isAfterLast());
			}

			/* 2. 更新结果集：不是所有数据库都支持结果集更新，貌似DB2就不支持 */
			System.out.println("\n更新结果集：");
			Statement stat2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);// 可滚动，也不可更新(可滚动不是可更新的必要条件)
			ResultSet rs2 = stat2.executeQuery("SELECT * FROM CJ_USER");

			// 首先遍历所有记录，看看数据
			// while (rs2.next()) {
			// System.out.println(rs2.getInt(1) + ", " + rs2.getString(2));
			// }
			// if (ResultSet.CONCUR_UPDATABLE == rs2.getConcurrency()) {//
			// 首先检查结果集是否支持更新
			// rs2.previous();// 因为遍历过，游标在最后一个位置后面，所以先往前移
			rs2.next();
			System.out.println(rs2.getInt(1) + ", " + rs2.getString(2));// 查看原数据
			 rs2.updateString("UNAME", "linzeyi");// 开始更新结果集
			 rs2.updateRow();
			 System.out.println(rs2.getInt(1) + ", " + rs2.getString(2));//
			// 查看更新后的数据
			// }

		}
	}
}
