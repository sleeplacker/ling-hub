package com.ling.learn0503.connecttodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 连接到数据库
 *
 * ChapterII05_DB/com.ling.learn0503.connecttodb.ConnectToDBTest.java
 *
 * author lingang
 *
 * createTime 2020-02-21 20:33:16
 *
 */
public class ConnectToDBTest {
	// 驱动类名，可以有多个，冒号隔开
	private static String JDBC_DRIVERS = "oracle.jdbc.driver.OracleDriver:com.ibm.db2.jdbc.app.DB2Driver";
	private static String JDBC_URL = "jdbc:db2://localhost:50000/COREJAVA";
	private static String JDBC_USER = "corejava";
	private static String JDBC_PASSWORD = "corejava";

	public static void main(String[] args) throws SQLException {

		try (
				// 创建连接
				Connection conn = createConnection();
				// 创建语句
				Statement stat = conn.createStatement()) {
			// stat.execute("DROP TABLE CJ_USER");//建表前先删表
			stat.execute("CREATE TABLE CJ_USER (USER_NAME VARCHAR(20))");// 建表
			stat.execute("INSERT INTO CJ_USER VALUES('linzeyi')");// 插入记录
			try (ResultSet result = stat.executeQuery("SELECT * FROM CJ_USER")) {// 查询
				while (result.next()) {
					System.out.println(result.getString(1));
				}
			}
			stat.execute("DROP TABLE CJ_USER");// 删表
		}
	}

	public static Connection createConnection() throws SQLException {
		System.setProperty("jdbc.drivers", JDBC_DRIVERS);// 设置驱动类
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
}
