package com.ling.learn0504.statementresultset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ling.learn0503.connecttodb.ConnectToDBTest;

/**
 * 语句、结果集和异常
 *
 * ChapterII05_DB/com.ling.learn0504.statementresultset.StaementAndResult.java
 *
 * author lingang
 *
 * createTime 2020-02-22 06:22:30
 *
 */
public class StaementAndResult {
	public static void main(String[] args) {
		try (Connection conn = ConnectToDBTest.createConnection();
				// 创建语句
				Statement stat = conn.createStatement()) {
			// stat.execute("DROP TABLE CJ_USER");//建表前先删表
			/* executeUpdate方法可以用于建表、删表、插入、更新和删除记录，但是不能用于查询 */
			int updCnt = 0;
			updCnt = stat.executeUpdate("CREATE TABLE CJ_USER (USER_NAME VARCHAR(20))");// 建表
			System.out.println("建表：" + updCnt);// 建表时更新行数返回0
			updCnt = stat.executeUpdate("INSERT INTO CJ_USER VALUES('linzeyi')");// 插入记录，使用executeUpdate方法
			System.out.println("插入记录：" + updCnt);// 插入记录时更新行数返回1
			/* execute方法可以用于所有操作，返回boolean类型的值，当有结果集时，返回ture，否则返回false */
			boolean haveResultSet = stat.execute("INSERT INTO CJ_USER VALUES('ling')");// 插入记录，使用通用的execute方法
			System.out.println("插入记录是否有结果集：" + haveResultSet);
			stat.execute("INSERT INTO CJ_USER VALUES('pengmz')");// 插入记录
			/* executeQuery方法可以用于查询，返回结果集 */
			ResultSet result = stat.executeQuery("SELECT * FROM CJ_USER");
			// 当execute方法用于查询时，不管查询结果是否为空，都返回true
			// haveResultSet = stat.execute("SELECT * FROM CJ_USER WHERE
			// USER_NAME='LLL'");
			haveResultSet = stat.execute("SELECT * FROM CJ_USER WHERE");
			System.out.println("查询时是否有结果集：" + haveResultSet);
			result = stat.getResultSet();// execute查询语句后面调用getResultSet方法可以获得结果集
			// 遍历结果集
			while (result.next()) {
				System.out.println(result.getString(1));// 通过序号定位字段，从1开始
				System.out.println(result.getString("USER_NAME"));// 通过字段名定位字段
			}
			stat.execute("DROP TABLE CJ_USER");// 删表
			stat.execute("DROP TABLE CJ_USER");// 制造异常
		} catch (SQLException e) {
			System.out.println("标准化错误代码：" + e.getSQLState());
			System.out.println("供应商相关的错误代码：" + e.getErrorCode());
			for (Throwable t : e) {// 打印异常链
				System.out.println("T : " + t);
			}
		}
	}
}
