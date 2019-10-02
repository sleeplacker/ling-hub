package com.lg.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TEst {
	public static void main(String[] args) {
		String sttr = "HJD2048.COM0907IPX202H264.MP4";
		Pattern p1 = Pattern.compile("([a-zA-Z]{2,5}\\-[0-9]+[a-cA-C]{0,1})([\\s\\S]*)(\\.[0-9a-zA-Z]+)");
		Pattern p2 = Pattern.compile("([a-zA-Z]{2,5}_[0-9]+[a-cA-C]{0,1})([\\s\\S]*)(\\.[0-9a-zA-Z]+)");
		Pattern p3 = Pattern.compile("([a-zA-Z]{2,5}[0-9]+[a-cA-C]{0,1})([\\s\\S]*)(\\.[0-9a-zA-Z]+)");
//		Pattern p2 = Pattern.compile("([a-zA-Z]{2,5}\\-[0-9]+)([\\s\\S]*)(\\.[0-9a-zA-Z]+)");
		// Pattern p = Pattern.compile("[a-zA-Z]{2,5}\\-[0-9]+");
		Matcher mat1 = p1.matcher(sttr);
		Matcher mat2 = p2.matcher(sttr);
		Matcher mat3 = p3.matcher(sttr);
		if (mat1.find()) {
			System.out.println(mat1.group(1) + mat1.group(mat1.groupCount()));
		}
		if (mat2.find()) {
			System.out.println(mat2.group(1) + mat2.group(mat2.groupCount()));
		}
		if (mat3.find()) {
			System.out.println(mat3.group(1) + mat3.group(mat3.groupCount()));
		}
	}
}
