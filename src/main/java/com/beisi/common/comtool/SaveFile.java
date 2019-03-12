package com.beisi.common.comtool;

import java.io.File;

public class SaveFile {

	public static void CreatePath(String path) {
		File dirFile = null;
		try {
			dirFile = new File(path);
			if (!(dirFile.exists()) && !(dirFile.isDirectory())) {
				dirFile.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
}
