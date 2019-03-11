package com.beisi.common.util;

import java.util.HashMap;

/**
 * 返回值统一处理
 *
 */
public class Result extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Result() {
		put("code", 0);
	}
	
	/**
	 * 成功 无参 code=0
	 * @return
	 */
	public static Result success(){
		return new Result();
	}
	
	@Override
	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	/**
	 * 成功
	 * @param msg
	 * @return
	 */
	public static Result success(String msg){
		Result result = new Result();
		result.put("msg", msg);
		return result;
	}
	
	/**
	 * 成功
	 * @param map
	 * @return
	 */
	public static Result success(HashMap<String, Object> map){
		Result result = new Result();
		result.putAll(map);
		return result; 
	}
	
	
	/**
	 * 错误
	 * @param code
	 * @param msg
	 * @return
	 */
	public static Result error(int code,String msg){
		Result result = new Result();
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}
	
	/**
	 * 错误
	 * @param msg
	 * @return
	 */
	public static Result error(String msg){
		return error(500,msg);
	}
	
	/**
	 * 未知或者不需处理错误调用
	 * @return
	 */
	public static Result error(){
		return error(500,"未知，请联系管理员");
	}

}
