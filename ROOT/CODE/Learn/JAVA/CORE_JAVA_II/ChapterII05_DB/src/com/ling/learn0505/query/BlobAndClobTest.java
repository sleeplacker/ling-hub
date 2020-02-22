package com.ling.learn0505.query;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ling.learn0503.connecttodb.ConnectToDBTest;

/**
 * Blob和Clob操作
 *
 * ChapterII05_DB/com.ling.learn0505.query.BlobAndClobTest.java
 *
 * author lingang
 *
 * createTime 2020-02-22 16:18:55
 *
 */
public class BlobAndClobTest {
	public static void main(String[] args) throws SQLException, IOException {
		try (Connection conn = ConnectToDBTest.createConnection();
				// 创建语句
				Statement stat = conn.createStatement()) {
			stat.execute(
					"CREATE TABLE CJ_USER (USER_NAME VARCHAR(20),USER_AGE INTEGER,PICTURE BLOB(1048576),DESCRIPTION CLOB(1048576))");// 建表
			/* 插入BLOB和CLOB */
			// 组装Blob类型数据
			Blob blobData = conn.createBlob();
			blobData.setBytes(1, Files.readAllBytes(Paths.get("src/com/ling/learn0505/query/head.png")));
			// 组装Clob类型数据
			Clob clobData = conn.createClob();
			BufferedReader breader = new BufferedReader(
					Files.newBufferedReader(Paths.get("src/com/ling/learn0505/query/bigCharFile.txt")));
			BufferedWriter bwriter = new BufferedWriter(clobData.setCharacterStream(1));
			String line = null;
			int len = 0;
			while ((line = breader.readLine()) != null) {
				len += line.length();
				bwriter.write(line);
			}
			System.out.println(len);// 文件实际长度
			System.out.println(clobData.length());// clobData长度为只有8192，原因不明
			// 插入BLOB类型数据
			PreparedStatement ps = conn.prepareStatement("INSERT INTO CJ_USER VALUES('linzeyi', 2, ?, ?)");
			ps.setBlob(1, blobData);
			ps.setClob(2, clobData);
			System.out.println("插入成功条数：" + ps.executeUpdate());

			// 读取BOLB和CLOB
			ps = conn.prepareStatement("SELECT * FROM CJ_USER");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				int age = rs.getInt(2);
				Blob headImg = rs.getBlob(3);
				InputStream getIn = headImg.getBinaryStream();// 读取Blob中的数据
				Path newFile = Paths.get("src/com/ling/learn0505/query/qryImg.png");
				if (Files.exists(newFile))
					Files.delete(newFile);
				Files.copy(getIn, newFile);// 将输入流写到文件
				Clob description = rs.getClob(4);
				System.out.println();

				System.out.println("Name = " + name);
				System.out.println("Age = " + age);
				System.out.println("HerdImg length = " + headImg.length() + " Content at file qryImg.png");
				System.out.println("Description length = " + description.length() + "Content of first 100 chars : "
						+ description.getSubString(1, 100));
			}

			stat.execute("DROP TABLE CJ_USER");// 删表
		}
	}
}
