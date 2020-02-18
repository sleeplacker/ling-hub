package com.ling.learn1302.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

/**
 * 首选项
 * 
 * java设置的首选项在Windows注册表中的位置：
 * 
 * 1.
 * 用户首选项：计算机\HKEY_USERS\S-1-5-21-1767795510-2769244601-1552810370-1001\Software\JavaSoft\Prefs
 * 2. 系统首选项：计算机\HKEY_LOCAL_MACHINE\SOFTWARE\JavaSoft\Prefs
 *
 * Chapter13/com.ling.learn1302.properties.PreferencesTest.java
 *
 * author lingang
 *
 * createTime 2019-12-06 14:52:07
 *
 */
public class PreferencesTest {
	public static void main(String[] args) throws BackingStoreException, ClassNotFoundException, FileNotFoundException,
			IOException, InvalidPreferencesFormatException {
		Preferences userroot = Preferences.userRoot();// 用户首选项
		/* 还能通过下面的方式创建首选项对象 */
		Preferences sysroot = Preferences.systemRoot();// 系统首选项
		/*
		 * 3. Preferences.systemRoot()方法只能生成根节点的首选项，要想创建子节点，要调用Preferences.
		 * userNodeForPackage方法，但是该方法只接收Class类型的参数，所以如果要建子节点，就需要建立一个package(
		 * 这个包的包名就是子节点路径，例如com.ling对应子节点/com/ling)然后在里面随便创建一个类，例如PreClass，
		 * 然后将PreClass.class传入Preferences.userNodeForPackage方法
		 */
		Preferences userrootFromPkg = Preferences.userNodeForPackage(new Object() {
		}.getClass().getEnclosingClass());// 用户首选项-节点位于当前类所在包位置
		Preferences sysrootFromPkg = Preferences.systemNodeForPackage(new Object() {
		}.getClass().getEnclosingClass());// 系统首选项-节点位于当前类所在包位置
		System.out.println(userroot);
		System.out.println(sysroot);
		System.out.println(userrootFromPkg);
		System.out.println(sysrootFromPkg);

		System.out.println(Arrays.toString(userroot.keys()));// Preferences的keys方法能得到首选项所有键的数组

		/* 4. 新增首选项-一个Preferences对象只能表示一个节点 */
		userroot.put("/", "root.aa");// 新增首选项属性，这里的/不代表根节点，只是一个字符串键值
		userroot.put("/com/xx", "prj.nmae");// 这里的/com/ling只是一个字符串键值，并没有建立新的节点/com/ling
		userroot.put("com.xx", "prj.time");
		userroot.remove("/");// 删除首选项属性
		userrootFromPkg.put("mypref", "whatever");
		sysroot.put("sys.root.dir", "/xx/xx/xx");

		/*
		 * 5. 导出首选项到文件
		 * 
		 * exportSubtree方法会导出当前节点和其所有子节点的配置，exportNode方法只会导出当前节点的配置
		 */
		userroot.exportSubtree(new FileOutputStream("resource/prefs/MyPreferences.xml"));
		userroot.exportNode(new FileOutputStream("resource/prefs/MyPreferences_only_this_node.xml"));

		/*
		 * 6. 导入首选项文件-这个功能可以让首选项配置能跨平台迁移
		 *
		 * 注意这个方法是Preferences的静态方法，这个方法将直接修改Windows的注册表而不需要创建任何Preferences对象
		 */
		Preferences.importPreferences(new FileInputStream("resource/prefs/MyPreferences.xml"));

		System.out.println(Arrays.toString(userroot.keys()));
	}
}
