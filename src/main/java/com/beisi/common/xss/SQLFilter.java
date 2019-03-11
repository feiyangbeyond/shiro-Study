package com.beisi.common.xss;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.beisi.common.exception.CustomException;


/**
 * SQL过滤 防止注入
 * 
 */
public class SQLFilter {

	/**
	 * 校验并替换字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String sqlValidate(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		
		String[] badStrs = {"master", "truncate", "insert", "delete", "update", "select", "declare", "alert", "drop"};
		String[] unStrs = {"'", "\"", ";", "\\"};
		
		//去掉特殊字符
		for (String key : unStrs) {
			str = StringUtils.replace(str, key, "");
		}		
		
		List<Integer> indx = getUpCase(str);//获取驼峰命名并加上下划线，并全部改为大写
		StringBuffer coulm = new StringBuffer();
		char[] chstr = str.toCharArray();
		for(int i = 0; i < chstr.length ; i++){
			coulm.append(chstr[i]);
			if(indx.contains(i+1)) {//当前位置需要加上"_"
				coulm.append("_");
			}
        }
		str = coulm.toString();
		// 统一为小写
		str = str.toLowerCase();
		//非法字符
		for (String key : badStrs) {
			if (str.indexOf(key) != -1) {
				throw new CustomException("包含非法字符");
			}
		}
		str = str.toUpperCase();
		return str;

	}
	
	private static List<Integer> getUpCase(String str){
        StringBuffer buffer = new StringBuffer();
        // 转为char数组
        char[] ch = str.toCharArray();
        List<Integer> indx = new ArrayList<Integer>();
        // 得到大写字母
        for(int i = 0; i < ch.length ; i++){
            if(ch[i] >= 'A' && ch[i] <= 'Z'){
                buffer.append(ch[i]);
                indx.add(i);
            }
        }
        return indx;
    }
	
//	public static void main(String[] args) {
//		String sqlValidate = sqlValidate("staNameUpOk");
//		System.out.println(sqlValidate);
//	}

}
