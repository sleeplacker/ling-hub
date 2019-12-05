package com.ling.learn1301.jar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceTest {
	public static void main(String[] args) throws IOException {
		/* 1. 读取二进制文件 */
		/* 相对路径-在同一目录 */
		InputStream in = new ResourceTest().getClass().getResourceAsStream("createJarFileCmd.txt");// ok
		/* 相对路径-在当前目录的子目录 */
		// InputStream in = new
		// ResourceTest().getClass().getResourceAsStream("subdir1/subdir2/about.txt");//ok
		/* 相对路径-在当前目录的子目录 */
		// InputStream in = new
		// ResourceTest().getClass().getResourceAsStream("subdir1\\subdir2\\about.txt");//ok
		/* "/"开头的是绝对路径 */
		// InputStream in = new
		// ResourceTest().getClass().getResourceAsStream("/subdir1/subdir2/about.txt");//
		// 资源不存在
		byte[] buf = new byte[1024];
		int cnt = 0;
		while ((cnt = in.read(buf)) > 0) {
			System.out.println(new String(buf, 0, cnt));
		}
		in.close();

		/* 2. 读取图片/音频 /视频文件 */
		URL img1 = new ResourceTest().getClass().getResource("resource/msg/Msg_zh_CN.properties");//当前class文件目录下开始找，找不到该文件
		URL img2 = new ResourceTest().getClass().getResource("/Chapter13/resource/msg/Msg_zh_CN.properties");
		URL img3 = new ResourceTest().getClass().getResource("I:/MyGitLib/repository/ling-hub/ROOT/CODE/Learn/JAVA/CORE_JAVA_I/Chapter13/resourcemsg/img1.jpg");
		System.out.println(img1);// 打印资源绝对路径，URL有效
		System.out.println(img2);// 打印资源绝对路径，URL有效
		System.out.println(img3);// 打印null，找不到资源文件
	}
}
