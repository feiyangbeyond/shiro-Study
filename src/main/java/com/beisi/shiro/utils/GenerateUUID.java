package com.beisi.shiro.utils;

import java.util.UUID;

public class GenerateUUID {

	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");//生成随机码
	}
	
//	public static void main(String[] args) {
//		System.out.println(getUUID());
//	}
}
