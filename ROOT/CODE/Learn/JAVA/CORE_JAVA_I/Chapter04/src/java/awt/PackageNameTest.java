package java.awt;

/**
 * 包名取值测试
 * 
 * 不允许自定义以java.开头的包名，运行会抛出异常： java.lang.SecurityException: Prohibited package name: java.awt
 *
 * Chapter4/java.awt.PackageNameTest.java
 *
 * author lingang
 *
 * createTime 2019-10-14 23:10:21
 *
 */
public class PackageNameTest {
	public static void main(String[] args) {
		Window window = new Window();
		window.warningString = "HeHeHe";
		System.out.println(window.warningString);
	}
}
