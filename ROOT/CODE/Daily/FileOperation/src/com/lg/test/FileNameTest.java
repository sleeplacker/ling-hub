package com.lg.test;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameTest {
	private static final String SRC_DIR = "K:\\ssoo\\SSSOO\\SS";
	private static final String DES_DIR = "K:\\ssoo\\SSSOO";

	public static void main(String[] args) {
		// 文件名正则
		Pattern p1 = Pattern.compile("([a-zA-Z]{2,5}\\-[0-9]{3,4}[a-dA-D1-4]{0,1})([\\s\\S]*)(\\.[0-9a-zA-Z]+)");
		Pattern p2 = Pattern.compile("([a-zA-Z]{2,5}_[0-9]{3,4}[a-dA-D1-4]{0,1})([\\s\\S]*)(\\.[0-9a-zA-Z]+)");
		Pattern p3 = Pattern.compile("([a-zA-Z]{2,5}[0-9]{3,4}[a-dA-D1-4]{0,1})([\\s\\S]*)(\\.[0-9a-zA-Z]+)");

		List<String> list = FileUtil.getAllAbsolutePath(SRC_DIR);
		String companyS[] = { "ABP", "IPZ", "IPX", "SNIS", "SSNI", "IPTD", "MIAE", "MIDE", "MIGD", "RBD", "LXVS",
				"TAAK", "PRED", "DASD", "BAZX", "AQMB", "HND", "JUY", "SHKD", "ARM", "CJOD", "DJSK", "ATFB", "BBAN",
				"BOBB", "CESD", "DVAJ", "EKW", "EYAN", "CEAD", "KAWD", "VENU", "OKSN", "ONEZ","ZEX" ,"STAR","AVOP","MXGS","CHN","PPT","SIRO","SRS","EBOD","NTRD","GVG","TYOD","MEYD"};
		int i = 0;
		if (list != null && !list.isEmpty()) {
			for (String absName : list) {
				if (absName.endsWith(".avi") || absName.endsWith(".mp4") || absName.endsWith(".rmvb")
						|| absName.endsWith(".mkv") || absName.endsWith(".wmv") || absName.endsWith(".iso")
						|| absName.endsWith(".AVI") || absName.endsWith(".MP4") || absName.endsWith(".RMVB")
						|| absName.endsWith(".MKV") || absName.endsWith(".WMV") || absName.endsWith(".ISO")) {
					File f = new File(absName);
					if (f.exists() && f.length() >= 209715200) {
						String strs[] = absName.split("\\\\");
						String name = strs[strs.length - 1];

						Matcher mat1 = p1.matcher(name);
						Matcher mat2 = p2.matcher(name);
						Matcher mat3 = p3.matcher(name);

						String tempName = "";

						if (mat1.find()) {
							tempName = mat1.group(1) + mat1.group(mat1.groupCount());
						}
						if (mat2.find()) {
							tempName = mat2.group(1) + mat2.group(mat2.groupCount());
						}
						if (mat3.find()) {
							tempName = mat3.group(1) + mat3.group(mat3.groupCount());
						}

						// 名字统一转为大写
						name = name.toUpperCase();

						for (String comp : companyS) {
							tempName = tempName.toUpperCase();
							if (tempName.contains(comp)) {
								name = tempName.substring(tempName.indexOf(comp));
							}
							if(name.contains(comp)) {
								name = name.substring(name.indexOf(comp));
							}
						}

						// 去掉-和_
						name = name.replace("-", "").replace("_", "").replace("H264", "");

						File file = new File(absName);
						if (file.exists()) {
							File des = new File(DES_DIR + "/" + name);
							if (des.exists()) {
								while (des.exists()) {
									des = new File(DES_DIR + "/1_" + des.getName());
								}
							}
							System.out.println(++i + ". " + absName + " Size : " + f.length());
							file.renameTo(des);
						}

					}

				}
			}
		}

	}
}
