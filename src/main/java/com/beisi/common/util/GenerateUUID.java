package com.beisi.common.util;

import java.util.Random;
import java.util.UUID;
/**
 * 获取UUID
 * 
 */
public class GenerateUUID {

	//生成随机码
	public static String getUUID() {
		String s = UUID.randomUUID().toString().replaceAll("-", "");
//		return s.replaceAll("-", "");
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}
	
	//随机生成N位长度的随机字符串
	public static String fixedLengthGUID(int num) {
		String uuid = getUUID();
		String s = "";
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			s += uuid.charAt(random.nextInt(uuid.length()));
		}
		return s;
	}
	
	//随机生成N个uuid
	public static String[] getGUUDs(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}
	
	public static void main(String[] args) {
		System.out.println(getUUID());
		System.out.println(fixedLengthGUID(2));
		String[] ss = getGUUDs(5);
		for(int i=0;i<ss.length;i++) {
			System.out.println(ss[i]);
		}
	}
}
