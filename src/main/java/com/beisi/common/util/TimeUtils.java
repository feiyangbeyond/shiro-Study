package com.beisi.common.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式化
 * 
 */
public class TimeUtils {

	public static boolean isNotNull(String str) {
		return str != null && !"".equals(str);
	}

	public static String toUTF8(String str) {
		try {
			return isNotNull(str) ? new String(str.getBytes("ISO-8859-1"), "UTF-8") : null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String getEncoding(String str) {
		String encode;
		encode = "UTF-16";
		try {
			if (str.equals(new String(str.getBytes(), encode))) {
				return encode;
			}
		} catch (Exception ex) {
			
		}
		encode = "ASCII";
		try {
			if (str.equals(new String(str.getBytes(), encode))) {
				return "字符串<< " + str + " >>中仅由数字和英文字母组成，无法识别其编码格式";
			}
		} catch (Exception ex) {
			
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(), encode))) {
				return encode;
			}
		} catch (Exception ex) {
			
		}
		encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(), encode))) {
				return encode;
			}
		} catch (Exception ex) {
			
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(), encode))) {
				return encode;
			}
		} catch (Exception ex) {
			
		}
		return "未识别编码格式";
	}

	/**
	 * 时间戳转换成日期格式字符串
	 * 
	 * @param seconds   精确到秒的字符串
	 * @param formatStr
	 * @return
	 */
	public static String timeSecondToDate(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty()) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	/**
	 * 日期格式字符串转换成时间戳
	 * 
	 * @param date   字符串日期
	 * @param format 如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String dateTimeToMills(String date_str, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return String.valueOf(sdf.parse(date_str).getTime() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 取得当前时间戳（精确到秒）
	 * 
	 * @return
	 */
	public static String getCurrentSecondMills() {
		long time = System.currentTimeMillis();
		String t = String.valueOf(time / 1000);
		return t;
	}
}
