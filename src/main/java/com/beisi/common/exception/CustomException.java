package com.beisi.common.exception;


/**
 * 自定义异常
 * 
 */
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String msg;
	private int code = 500;
	
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public CustomException(String frdMessage) {
		super(createFriendlyErrMsg(frdMessage));
	}

	public CustomException(String msg,Throwable e) {
		super(msg,e);
	}

	public CustomException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	private static String createFriendlyErrMsg(String msgBody) {
		String prefixStr = "抱歉！";
		String suffixStr = "请稍后再试或与管理员联系！";
		StringBuffer friendlyErrMsg = new StringBuffer();
		friendlyErrMsg.append(prefixStr);
		friendlyErrMsg.append(msgBody);
		friendlyErrMsg.append(suffixStr);
		return friendlyErrMsg.toString();
	}

}
