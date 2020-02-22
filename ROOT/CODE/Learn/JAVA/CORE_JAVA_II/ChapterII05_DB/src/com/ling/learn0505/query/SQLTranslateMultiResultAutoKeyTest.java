package com.ling.learn0505.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ling.learn0503.connecttodb.ConnectToDBTest;

/**
 * SQL转译、多结果集和自动生成的键
 *
 * ChapterII05_DB/com.ling.learn0505.query.SQLTranslateMultiResultAutoKeyTest.java
 *
 * author lingang
 *
 * createTime 2020-02-22 20:04:35
 *
 */
public class SQLTranslateMultiResultAutoKeyTest {
	public static void main(String[] args) throws SQLException {
		try (Connection conn = ConnectToDBTest.createConnection();
				// 创建语句
				Statement stat = conn.createStatement()) {
			stat.execute(
					"CREATE TABLE CJ_USER (USERID INTEGER, USER_NAME VARCHAR(20),ROLEID VARCHAR(2),BIRTHDAY DATE)");
			stat.execute("CREATE TABLE CJ_ROLE (ROLEID VARCHAR(2),ROLETYPE VARCHAR(20))");

			stat.execute("INSERT INTO CJ_USER VALUES(999,'pengmz','01',null)");
			stat.execute("INSERT INTO CJ_USER VALUES(998,'ling','02',null)");
			stat.execute("INSERT INTO CJ_ROLE VALUES('01','manager')");

			/* SQL转义 */
			// 关联查询，不指定左右连接，则有些表不存在的数据不会显示
			ResultSet rs = stat
					.executeQuery("SELECT A.USER_NAME,B.ROLETYPE FROM CJ_USER A,CJ_ROLE B WHERE A.ROLEID=B.ROLEID");
			while (rs.next()) {
				System.out.println(rs.getString(1) + ", " + rs.getString(2));
			}

			// 关联查询，左连接，不存在的数据会显示空
			// {oj ...}，其中oj表示函数名
			rs = stat.executeQuery(
					"SELECT A.USER_NAME,B.ROLETYPE FROM {oj CJ_USER A LEFT OUTER JOIN CJ_ROLE B ON A.ROLEID=B.ROLEID}");
			while (rs.next()) {
				System.out.println(rs.getString(1) + ", " + rs.getString(2));
			}

			/* 多结果集：对支持多结果集的数据库可以使用，DB2不知如何操作 */
			// 连续调用execute查询两次
			// System.out.println(stat.execute("select * from cj_user;select *
			// from cj_role"));
			// while (stat.getMoreResults()) {// 遍历多个结果集
			// System.out.println("RT");
			// rs = stat.getResultSet();
			// while (rs.next()) {
			// System.out.println(rs.getString(1) + ", " + rs.getString(2));
			// }
			// }

			/* 自動生成鍵：仍然有数据库直接的差别，测试失败 */
			stat.execute("INSERT INTO CJ_USER(USER_NAME,ROLEID) VALUES('linsc','01')", Statement.RETURN_GENERATED_KEYS);
			rs = stat.getGeneratedKeys();
			while (rs.next()) {
				int key = rs.getInt(1);
				System.out.println(key);
			}

//			stat.execute("DROP TABLE  CJ_USER");
//			stat.execute("DROP TABLE  CJ_ROLE");
		}
	}
}
