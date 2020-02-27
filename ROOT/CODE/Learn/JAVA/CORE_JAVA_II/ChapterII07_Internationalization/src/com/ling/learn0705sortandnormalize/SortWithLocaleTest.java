package com.ling.learn0705sortandnormalize;

import java.text.CollationKey;
import java.text.Collator;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Arrays;
import java.util.Locale;

/**
 * 与Locale相关的排序
 *
 * ChapterII07_Internationalization/com.ling.learn0705sortandnormalize.SortWithLocaleTest.java
 *
 * author lingang
 *
 * createTime 2020-02-26 12:54:29
 *
 */
public class SortWithLocaleTest {
	public static void main(String[] args) {

		String[] words = { "able", "America", "zebra", "Benz", "Ångström" };// Ångström是计量单位，1埃=10的-10次方
		// 1. 与Locale相关的排序
		// 默认排序方式：按UTF-16的编码值排序，即大写字母->小写字母->拉丁文字母
		// [America, Benz, able, zebra, Ångström]
		Arrays.sort(words);
		System.out.println(Arrays.toString(words));

		// 使用默认的排序器(这里相当于使用Locale.CHINA)：
		// 会按不区分大小写，也不区分是不是拉丁字母来排序
		// [able, America, Ångström, Benz, zebra]
		Collator coll = Collator.getInstance();
		Arrays.sort(words, coll);
		System.out.println(Arrays.toString(words));

		// 使用瑞典的Locale来排序：瑞典的Å在大小写字母之后
		// [able, America, Benz, zebra, Ångström]
		coll = Collator.getInstance(Locale.forLanguageTag("sv-SE"));
		Arrays.sort(words, coll);
		System.out.println(Arrays.toString(words));

		// 2. 排序强度设置
		System.out.println("\n2. 排序强度设置");
		words = new String[] { "Åbc", "Abc", "abc" };
		coll = Collator.getInstance(Locale.ENGLISH);
		Arrays.sort(words, coll);
		System.out.println("默认强度(这里等于TERTIARY，即最高强度)：" + Arrays.toString(words));// 默认强度=最高强度

		words = new String[] { "Åbc", "Abc", "abc" };
		coll.setStrength(Collator.PRIMARY);
		Arrays.sort(words, coll);
		System.out.println("首要强度(最低，不区分大小写，也不区分重音符)：" + Arrays.toString(words));// 首要强度(最低，不区分大小写，也不区分重音符)：

		words = new String[] { "Åbc", "Abc", "abc" };
		coll.setStrength(Collator.SECONDARY);
		Arrays.sort(words, coll);
		System.out.println("次要强度(中等，不区分大小写，区分重音符)：" + Arrays.toString(words));// 次要强度(中等，不区分大小写，区分重音符)：

		words = new String[] { "Åbc", "Abc", "abc" };
		coll.setStrength(Collator.TERTIARY);
		Arrays.sort(words, coll);
		System.out.println("再次强度(最高，区分大小写，也区分重音符)：" + Arrays.toString(words));// 再次强度(最高，区分大小写，也区分重音符)：

		System.out.println("\n3. 分组模式的支持");
		// 3. 分组模式，有些字符或字符序列在被描述成Unicode是，可以有多种方式
		// 例如：Å可以表示成A和 ̊ 的组合，ffi可以用\ufb03来表示，™可以用TM表示，
		// 注意：这里的Å跟A和 ̊ 的组合打印出来是一样的，算了
		words = new String[] { "Åbc", "A\u030abc", "\ufb03", "ffi", "™", "TM" };
		coll.setStrength(Collator.IDENTICAL);
		Arrays.sort(words, coll);
		System.out.println("默认情况(强度最高，区分组合环，也区分连字和商标符号)：" + printStrAndLength(words));// 默认情况(强度最高，区分组合环，也区分连字和商标符号)

		words = new String[] { "Åbc", "A\u030abc", "\ufb03", "ffi", "™", "TM" };
		coll.setDecomposition(Collator.NO_DECOMPOSITION);
		Arrays.sort(words, coll);
		System.out.println("不分解(强度最高，区分组合环，也区分连字和商标符号)：" + printStrAndLength(words));// 不分解(强度最高，区分组合环，也区分连字和商标符号)

		words = new String[] { "Åbc", "A\u030abc", "\ufb03", "ffi", "™", "TM" };
		coll.setDecomposition(Collator.CANONICAL_DECOMPOSITION);
		Arrays.sort(words, coll);
		System.out.println("部分分解(中等强度，不区分组合环，但区分连字和商标符号)：" + printStrAndLength(words));// 部分分解(中等强度，不区分组合环，但区分连字和商标符号)

		words = new String[] { "Åbc", "A\u030abc", "\ufb03", "ffi", "™", "TM" };
		coll.setDecomposition(Collator.FULL_DECOMPOSITION);
		Arrays.sort(words, coll);
		System.out.println("完全分解(强度最低，都不区分)：" + printStrAndLength(words));// 完全分解(强度最低，都不区分)

		// 4. 将字符按指定格式进行规范化
		System.out.println("\n4. 将字符按指定格式进行规范化");
		String unNormalizedStr = "Ångström";
		String nomalizedStr = Normalizer.normalize(unNormalizedStr, Form.NFD);// 使用规范分解格式，更多格式参考第323页的D、KD、C、KC
		System.out.println(unNormalizedStr.length());// 8
		System.out.println(nomalizedStr.length());// 10，可以看到规范化后的字符串长度变为10了

		// 5. 保存分解結果
		System.out.println("\n5. 保存分解结果");
		String strA = "Åbc";
		String strB = "A\u030abc";
		System.out.println(strA.length());
		System.out.println(strB.length());
		coll.setDecomposition(Collator.NO_DECOMPOSITION);
		CollationKey aKey = coll.getCollationKey(strA);// 将分解结果进行保存，避免每次比较都新建分解结果，造成开销过大
		if (aKey.compareTo(coll.getCollationKey(strB)) == 0) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}
		coll.setDecomposition(Collator.FULL_DECOMPOSITION);
		aKey = coll.getCollationKey(strA);
		if (aKey.compareTo(coll.getCollationKey(strB)) == 0) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}
	}

	// 打印数组和字符串长度
	private static String printStrAndLength(String[] str) {
		String printStr = "\n[";
		for (String s : str) {
			printStr = printStr + s.length() + ":" + s + ", ";
		}
		if (printStr.endsWith(", "))
			printStr = printStr.substring(0, printStr.length() - 2);
		return printStr + "]";
	}
}
