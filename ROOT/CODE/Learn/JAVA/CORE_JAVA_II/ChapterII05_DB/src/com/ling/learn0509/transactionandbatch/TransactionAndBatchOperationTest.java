package com.ling.learn0509.transactionandbatch;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Arrays;

import com.ling.learn0503.connecttodb.ConnectToDBTest;

/**
 * 事务和批处理
 *
 * ChapterII05_DB/com.ling.learn0509.transactionandbatch.TransactionAndBatchOperationTest.java
 *
 * author lingang
 *
 * createTime 2020-02-23 19:48:26
 *
 */
public class TransactionAndBatchOperationTest {
	public static void main(String[] args) throws SQLException {
		testBatchUpdate();// 批处理测试
		testTransaction();// 事务测试
	}

	// 批处理测试
	private static void testBatchUpdate() throws SQLException {
		Connection conn = ConnectToDBTest.createConnection();
		/*
		 * 1. 批处理：通常批处理操作应该放到一个事务中，这里就不放了
		 * 
		 * 批处理可以批量执行INSERT、UPDATE、DELETE、CREATE TABLE和DROP
		 * TABLE语句，但是SELECT语句不能执行，否则会抛出异常
		 */
		// 首先检查数据库是否支持批处理操作
		DatabaseMetaData dbMeta = conn.getMetaData();
		System.out.println("DB2是否支持批处理：" + dbMeta.supportsBatchUpdates());// DB2支持
		if (dbMeta.supportsBatchUpdates()) {
			// 这里使用批处理语句，一次插入多条数据
			Statement stat = conn.createStatement();
			stat.addBatch("INSERT INTO CJ_USER VALUES(101,'ling')");
			stat.addBatch("INSERT INTO CJ_USER VALUES(102,'l02')");
			stat.addBatch("INSERT INTO CJ_USER VALUES(103,'l03')");
			stat.addBatch("UPDATE CJ_USER SET UNAME='XX' WHERE UNAME='ABC'");
			int[] batch_upd = stat.executeBatch();// 批量更新返回每条语句更新记录条数的数组
			System.out.println(Arrays.toString(batch_upd));
		}
		conn.close();
	}

	// 事务测试
	private static void testTransaction() throws SQLException {
		Connection conn = ConnectToDBTest.createConnection();
		/* 2. 事务 */
		System.out.println(conn.getAutoCommit());// 默认情况下，是自动提交的
		conn.setAutoCommit(false);// 关闭自动提交，才能使用事务
		DatabaseMetaData dbMeta = conn.getMetaData();
		System.out.println("DB2是否支持事务：" + dbMeta.supportsTransactions());// DB2支持事务
		System.out.println("DB2是否支持事务保存点：" + dbMeta.supportsSavepoints());// DB2支持事务
		Statement stat = conn.createStatement();
		Savepoint sp = null;
		try {
			stat.executeUpdate("INSERT INTO CJ_USER VALUES(201,'11111')");
			stat.executeUpdate("INSERT INTO CJ_USER VALUES(202,'22222')");
			sp = conn.setSavepoint();// 设置保存点
			sp = conn.setSavepoint("SavePoint3");// 也可以设置具名保存点
			stat.executeUpdate("INSERT INTO CJ_USER VALUES(203,'33333')");
			if (System.currentTimeMillis() % 2 == 0) {// 制造异常
				System.out.println("制造异常成功");
				throw new SQLException();
			}
			stat.executeUpdate("INSERT INTO CJ_USER VALUES(204,'44444')");
			conn.commit();// 调用了commit方法才会更新数据库
		} catch (SQLException se) {
			// 当出现SQL异常时回滚，如果设置了保存点，则回滚保存点以后的操作(回滚后也需要commit才能更新到数据库)，否则回滚前一次提交以后的所有操作
			if (sp == null) {
				conn.rollback();
			} else {
				conn.rollback(sp);
				conn.commit();
				conn.releaseSavepoint(sp);
				conn.close();
			}
		}

	}
}
