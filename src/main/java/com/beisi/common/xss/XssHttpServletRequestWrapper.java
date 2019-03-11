package com.beisi.common.xss;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;


/**
 * XSS过滤处理
 * 
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	//没被包装器处理过的HttpServletRequest对象（保留）
	private HttpServletRequest orgRequest;
	
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.orgRequest = request;
	}

	
	@Override  
    public String getParameter(String name) {  
        String value = super.getParameter(xssEncode(name));  
        if (value != null) {  
            value = xssEncode(value);  
        }  
        return value;  
    } 
	
	@Override
	public String[] getParameterValues(String name) {
		String[] parameters = super.getParameterValues(name);
		if (parameters == null || parameters.length == 0) {
			return null;
		}

		for (int i = 0; i < parameters.length; i++) {
			parameters[i] = xssEncode(parameters[i]);
		}
		return parameters;
	}



	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = new LinkedHashMap<>();
		Map<String, String[]> parameters = super.getParameterMap();
		for (String key : parameters.keySet()) {
			String[] values = parameters.get(key);
			for (int i = 0; i < values.length; i++) {
				values[i] = xssEncode(values[i]);
			}
			map.put(key, values);
		}
		return map;
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(xssEncode(name));
		if (StringUtils.isNotBlank(value)) {
			value = xssEncode(value);
		}
		return value;
	}
	
	

	/**
	 * 使用StringEscapeUtils
	 * @param string
	 * @return
	 */
	private String xssEncode(String str) {
		return StringEscapeUtils.escapeHtml(str);
	}
	
	
	/**
	 * 将容易引起xss漏洞的半角字符直接替换成全角字符
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String htmlEncode(String str) {
        if (str == null || "".equals(str)) {  
            return str;  
        }  
        StringBuilder sb = new StringBuilder(str.length() + 16);  
        for (int i = 0; i < str.length(); i++) {  
            char c = str.charAt(i);  
            switch (c) {  
            case '>':  
                sb.append('＞');//全角大于号  
                break;  
            case '<':  
                sb.append('＜');//全角小于号  
                break;  
            case '\'':  
                sb.append('‘');//全角单引号  
                break;  
            case '\"':  
                sb.append('“');//全角双引号  
                break;  
            case '&':  
                sb.append('＆');//全角  
                break;  
            case '\\':  
                sb.append('＼');//全角斜线  
                break;  
            case '#':  
                sb.append('＃');//全角井号  
                break;  
            default:  
                sb.append(c);  
                break;  
            }  
        }  
        return sb.toString();  
    }  
	
	
	 /**转码规则:
     * 对每一个字符进行检查，转换字符。
     * @param c
     * @return
     */
    @SuppressWarnings("unused")
	private static String htmlEncode(char c) {
        switch (c) {
            case '&':
                return "&amp;";
            case '<':
                return "&lt;";
            case '>':
                return "&gt;";
            case '"':
                return "&quot;";
            case ' ':
                return "&nbsp;";
            default:
                return c + "";
        }
    }

	
	/**
	 * 获取最原始的request
	 * @param request
	 * @return
	 */
	public static HttpServletRequest getOrgRequest(HttpServletRequest request){
		if (request instanceof XssHttpServletRequestWrapper) {
			return ((XssHttpServletRequestWrapper) request).getOrgRequest();
		}
		
		return request;
	}
	
	public HttpServletRequest getOrgRequest() {
		return orgRequest;
	}

}
