package com.ling.learn0505.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ling.learn0503.connecttodb.ConnectToDBTest;

/**
 * 预备语句
 *
 * ChapterII05_DB/com.ling.learn0505.query.PrepareStatementTest.java
 *
 * author lingang
 *
 * createTime 2020-02-22 16:16:20
 *
 */
public class PrepareStatementTest {
	public static void main(String[] args) throws SQLException {
		try (Connection conn = ConnectToDBTest.createConnection();
				// 创建语句
				Statement stat = conn.createStatement()) {
			stat.execute("CREATE TABLE CJ_USER (USER_NAME VARCHAR(20),USER_AGE INTEGER)");// 建表
			// 插入记录
			stat.execute("INSERT INTO CJ_USER VALUES('pengmz',60)");
			stat.execute("INSERT INTO CJ_USER VALUES('ling',20)");
			stat.execute("INSERT INTO CJ_USER VALUES('linzy',1)");

			// 创建预备查询语句
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM CJ_USER WHERE USER_NAME=?");
			ps.setString(1, "linzy");// 设置第1个？参数值
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				System.out.println(result.getString(1) + ", " + result.getInt(2));
			}

			// 修改查询参数值，可以重复使用预备查询语句
			ps.setString(1, "pengmz");
			result = ps.executeQuery();
			while (result.next()) {
				System.out.println(result.getString(1) + ", " + result.getInt(2));
			}

			// 使用预备查询语句更新记录
			ps = conn.prepareStatement("UPDATE CJ_USER SET USER_AGE=USER_AGE+1 WHERE USER_NAME=?");
			ps.setString(1, "ling");// 设置第1个？参数值
			System.out.println("更新条数：" + ps.executeUpdate());

			result = stat.executeQuery("SELECT * FROM CJ_USER WHERE USER_NAME='ling'");// 再次查询修改的记录
			while (result.next()) {
				System.out.println(result.getString(1) + ", " + result.getInt(2));
			}

			stat.execute("DROP TABLE CJ_USER");// 删表
		}
	}
}
