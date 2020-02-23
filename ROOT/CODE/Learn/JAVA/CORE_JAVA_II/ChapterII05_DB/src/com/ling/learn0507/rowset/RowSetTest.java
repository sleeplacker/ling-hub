package com.ling.learn0507.rowset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import com.ling.learn0503.connecttodb.ConnectToDBTest;

/**
 * 行集：行集是为了解决普通的可滚动结果集在交互过程中必须保持数据库连接的问题，使用行集时可以断开数据库连接
 * 
 * 行集有这几种：CachedRowSet，WebRowSet，FilteredRowSet，JoinRowSet和JdbcRowSet，说明见270页，本例使用CachedRowSet
 *
 * ChapterII05_DB/com.ling.learn0507.rowset.RowSetTest.java
 *
 * author lingang
 *
 * createTime 2020-02-23 16:51:53
 *
 */
public class RowSetTest {
	public static void main(String[] args) throws SQLException {

		/**
		 * 测试使用普通结果集时：如果断开连接后才遍历结果集会抛出异常： com.ibm.db2.jcc.am.SqlException:
		 * [jcc][t4][10120][10898][4.21.29] 操作无效：已关闭 result set。
		 * ERRORCODE=-4470, SQLSTATE=null
		 */
		// testResultSet();

		// 测试行集的特性
		testCachedRowSet();

	}

	// 测试ResultSet
	@SuppressWarnings("unused")
	private static void testResultSet() throws SQLException {
		Connection conn = ConnectToDBTest.createConnection();
		// 使用普通结果集填充CachedRowSet
		Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// 可滚动，但不可更新
		ResultSet rs = stat.executeQuery("SELECT * FROM CJ_USER");
		conn.close();
		while (rs.next()) {
			System.out.println(rs.getInt(1) + ", " + rs.getString(2));
		}
	}

	// 测试CachedRowSet
	private static void testCachedRowSet() throws SQLException {
		Connection conn = ConnectToDBTest.createConnection();
		// 使用普通结果集填充CachedRowSet
		Statement stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);// 可滚动，但不可更新
		ResultSet rs = stat.executeQuery("SELECT * FROM CJ_USER");
		// 构建行集
		RowSetFactory factory = RowSetProvider.newFactory();
		CachedRowSet crs = factory.createCachedRowSet();
		crs.populate(rs);
		// 断开连击
		conn.close();
		// 断开连接后通过行集遍历查询结果没问题
		while (crs.next()) {
			System.out.println(crs.getInt(1) + ", " + crs.getString(2));
		}

		// 可以在行集上设置数据库连接
		System.out.println("\n在行集上设置数据库连接：");
		crs.setUrl("jdbc:db2://localhost:50000/COREJAVA");
		crs.setUsername("corejava");
		crs.setPassword("corejava");
		// 设置查询语句和参数
		crs.setCommand("SELECT * FROM CJ_USER WHERE UNAME LIKE ?");
		crs.setString(1, "lin%");
		// 设置每页的大小
		crs.setPageSize(3);
		// 将查询结果填充到行集中
		crs.execute();// 这个方法会建立数据库连接，执行查询操作，填充行集，最后断开连击
		int pageIndex = 0;
		do {
			System.out.println("第" + (++pageIndex) + "页：");
			while (crs.next()) {
				System.out.println(crs.getInt(1) + ", " + crs.getString(2));
				/**
				 * 使用行集时，DB2数据库也可以更新成功
				 */
				crs.updateString(2, "linNewName");
				crs.updateRow();
				crs.acceptChanges();// 将对行集的修改更新到数据库
			}
		} while (crs.nextPage());
	}
}
