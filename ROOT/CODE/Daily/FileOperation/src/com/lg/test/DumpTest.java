package com.lg.test;

// IBMjdk java -Xms5m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=C:\Users\1\Desktop\ com.lg.test.DumpTest
public class DumpTest {
	public static void main(String[] args) {
		System.getProperties().forEach((k, v) -> {
			System.out.println(k + "=" + v);
		});
		byte[] bs = new byte[10 * 1024 * 1024];
		System.out.println(bs.toString());
	}

}
