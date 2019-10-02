package com.lg.test;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRename {
	private static final String DIR = "G:\\大明王朝1566";

	public static void main(String[] args) {
		List<String> list = FileUtil.getAllFileName(DIR, ".srt");
		for(String s : list) {
			File f = new File(DIR+File.separator+s);
			if(s.length()>11){
				s = s.replaceAll(s.substring(0,11), "");
				s = s.replaceAll(s.substring(3,s.length()-7), "");
			}
			
			File newFile = new File(DIR+File.separator+s);
			System.out.println(DIR+File.separator+s);
			System.out.println(f.renameTo(newFile));
		}
	}
}
