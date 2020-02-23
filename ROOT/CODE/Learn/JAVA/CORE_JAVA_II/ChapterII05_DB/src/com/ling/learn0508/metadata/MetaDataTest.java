package com.ling.learn0508.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.ling.learn0503.connecttodb.ConnectToDBTest;

/**
 * 元数据，分为三类：
 * 
 * 一、关于数据库的元数据
 * 
 * 二、关于结果集的元数据
 * 
 * 三、关于预备语句参数的元数据
 *
 * ChapterII05_DB/com.ling.learn0508.metadata.MetaDataTest.java
 *
 * author lingang
 *
 * createTime 2020-02-23 18:13:50
 *
 */
public class MetaDataTest {
	public static void main(String[] args) throws SQLException {
		Connection conn = ConnectToDBTest.createConnection();
		/* 一、数据库的元数据 */
		DatabaseMetaData dbMeta = conn.getMetaData();
		/* 1. 查看数据库所有表的信息，下面getTables方法的第2个参数对应DB2的schema，最后一个参数指定要返回哪写类型的表 */
		ResultSet tables = dbMeta.getTables(null, "COREJAVA", null, new String[] { "TABLE" });
		int tableIndex = 0;
		while (tables.next()) {
			System.out.println("表" + (++tableIndex));
			// 每个表的元数据属性有5个
			String t_cat = tables.getString(1);// 表目录，TABLE_CAT
			System.out.println("\t表目录：" + t_cat);
			String t_sch = tables.getString(2);// 表模式，TABLE_SCHEM
			System.out.println("\t表模式：" + t_sch);
			String t_name = tables.getString(3);// 表名称，TABLE_NAME
			System.out.println("\t表名称：" + t_name);
			String t_type = tables.getString(4);// 表类型，TABLE_TYPE
			System.out.println("\t表类型：" + t_type);
			String t_remarks = tables.getString(5);// 表注释，REMARKS
			System.out.println("\t表注释：" + t_remarks);
		}

		/* 2. 其他数据库信息，还有更多信息看DatabaseMetaData类的API */
		System.out.println(dbMeta.getJDBCMajorVersion());// 4，JDBC驱动程序主版本号
		System.out.println(dbMeta.getJDBCMinorVersion());// 1，JDBC驱动程序次版本号
		System.out.println(dbMeta.getMaxConnections());// 0，可同时连接到数据库的最大并发连接数，0表示无限制或不可知
		System.out.println(dbMeta.getMaxStatements());// 0，单个数据库连接允许同时打开的最大并发语句数，0表示无限制或不可知
		//下面语句可以检查数据库是否支持结果集滚动
		System.out.println(dbMeta.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
		System.out.println(dbMeta.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
		System.out.println(dbMeta.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
		//下面语句可以检查数据库是否支持结果集更新
		System.out.println(
				dbMeta.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY));
		System.out.println(
				dbMeta.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE));
		System.out.println(
				dbMeta.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE));
		System.out.println(
				dbMeta.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE));
		System.out.println(
				dbMeta.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE));
		System.out.println(
				dbMeta.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE));

		/* 二、结果集的元数据 */
		ResultSet rs = conn.createStatement().executeQuery("SELECT USERID AS uid, UNAME AS name FROM CJ_USER");
		ResultSetMetaData rsMeta = rs.getMetaData();
		/* 下面操作可以查看表结构，包括：字段名称和字段大小等，更多信息看ResultSetMetaData类的API */
		for (int i = 1; i <= rsMeta.getColumnCount(); ++i) {
			System.out.println("第" + i + "列的最大宽度：" + rsMeta.getColumnDisplaySize(i));// 列宽
			System.out.println("第" + i + "列的建议名称：" + rsMeta.getColumnLabel(i));// 列建议名称，即AS后面的名称
			System.out.println("第" + i + "列名称：" + rsMeta.getColumnName(i));// 列字段名，即数据库中的字段名
		}

		/* 三、预备语句参数的元数据，和结果集的元数据使用同一个类ResultSetMetaData，操作也和结果集差不多 */
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM CJ_USER WHERE UNAME=?");
		ResultSetMetaData psMeta = ps.getMetaData();
		System.out.println(psMeta.getColumnTypeName(1));// 获取参数类型，结果集也能获取
		System.out.println(psMeta.getColumnTypeName(2));

		conn.close();
	}
}
