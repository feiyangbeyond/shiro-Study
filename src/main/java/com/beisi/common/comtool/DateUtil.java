package com.beisi.common.comtool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static Date StringToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (null != str && !"".equals(str.trim()))
			try {
				date = format.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return date;
	}

	public static Date StringToTime(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		if (null != str && !"".equals(str.trim()))
			try {
				date = format.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return date;
	}

	public static Date StringToTimeMillis(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = null;
		if (null != str && !"".equals(str.trim()))
			try {
				date = format.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return date;
	}

	public static String DateToString(Date date) {
		String str = "";
		if (null != date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			str = format.format(date);
		}
		return str;
	}

	public static String DateToFormat(Date date, String fmt) {
		String str = "";
		if (null != date) {
			SimpleDateFormat format = new SimpleDateFormat(fmt);
			str = format.format(date);
		}
		return str;
	}

	public static String TimeToString(Date date) {
		String str = "";
		if (null != date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			str = format.format(date);
		}
		return str;
	}

	public static String TimeMillisToString(Date date) {
		String str = "";
		if (null != date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			str = format.format(date);
		}
		return str;
	}

	public static int GetDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate;
		java.util.Date endDate;
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
			// System.out.println("相隔的天数="+day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int) day;
	}
	
	/** 
     * 日期格式字符串转换成时间戳 
     * @param date 字符串日期 
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
    
    /** 
     * 时间戳转换成日期格式字符串 
     * @param seconds 精确到秒的字符串 
     * @param formatStr 
     * @return 
     */  
    public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
    }  
    
    /** 
     * 取得当前时间戳（精确到秒） 
     * @return 
     */  
    public static String timeStamp(){  
        long time = System.currentTimeMillis();  
        String t = String.valueOf(time/1000);  
        return t;  
    }  
    
//    public static void main(String[] args) {//比较时间
//    	String beginTime = "2018-07-28 14:42:32";
//    	String endTime = "2018-07-29 12:26:32";
//    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	try {
//    		Date date1 = format.parse(beginTime);
//    		Date date2 = format.parse(endTime);
//    		
//    		long beginMillisecond = date1.getTime();
//    		long endMillisecond = date2.getTime();
//    		
//    		System.out.println(beginMillisecond > endMillisecond);
//    		
//    	} catch (ParseException e) {
//    		e.printStackTrace();
//    	}
//    	try {
//    		Date date1 = format.parse(beginTime);
//    		Date date2 = format.parse(endTime);
//    		boolean before = date1.before(date2);
//    		System.out.println(before);
//    	} catch (ParseException e) {
//    		e.printStackTrace();
//    	}
//		try {
//			Date date1 = format.parse(beginTime);
//			Date date2 = format.parse(endTime);
//			int compareTo = date1.compareTo(date2);
//			System.out.println(compareTo);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}
}
