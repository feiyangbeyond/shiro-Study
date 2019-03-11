package com.beisi.common.exception;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.crypto.IllegalBlockSizeException;
import javax.servlet.jsp.JspException;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beisi.common.util.Result;


/**
 * 全局异常处理
 *
 */
@ControllerAdvice
public class SysExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public Map<String, Object> handleSystemException(CustomException e){
		Result result = new Result();
		result.put("code", e.getCode());
		result.put("msg", e.getMessage());
		return result;
	}
	
	@ExceptionHandler(DuplicateKeyException.class)
	@ResponseBody
	public Map<String, Object> handleDuplicateKeyException(DuplicateKeyException e){
		e.printStackTrace();
		logger.error("数据库记录重复添加："+e.getMessage());
		return Result.error("数据库已存在该记录："+e.getMessage());
	}
	
	@ExceptionHandler(AuthorizationException.class)
	@ResponseBody
	public Map<String, Object> handleAuthorizationException(AuthorizationException e){
		e.printStackTrace();
		logger.error("权限不足");
		return Result.error("权限不足，联系管理员");
	}
	
	@ExceptionHandler(NoSuchMethodException.class)
	@ResponseBody
	public Map<String, Object> handleNoSuchMethodException(NoSuchMethodException e){
		e.printStackTrace();
		logger.error("方法未找到！");
		return Result.error("方法未找到！");
	}
	
	@ExceptionHandler(NumberFormatException.class)
	@ResponseBody
	public Map<String, Object> handleNumberFormatException(NumberFormatException e){
		e.printStackTrace();
		logger.error("字符串转换为数字异常！");
		return Result.error("字符串转换为数字异常！");
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	@ResponseBody
	public Map<String, Object> handleFileNotFoundException(FileNotFoundException e){
		e.printStackTrace();
		logger.error("文件未找到！");
		return Result.error("文件未找到！");
	}
	
	@ExceptionHandler(EOFException.class)
	@ResponseBody
	public Map<String, Object> handleEOFException(EOFException e){
		e.printStackTrace();
		logger.error("文件异常结束！");
		return Result.error("文件异常结束！");
	}
	
	@ExceptionHandler(DataAccessException.class)
	@ResponseBody
	public Map<String, Object> handleDataAccessException(DataAccessException e){
		e.printStackTrace();
		logger.error("数据库操作失败！");
		return Result.error("数据库操作失败！");
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public Map<String, Object> handleNullPointerException(NullPointerException e){
		e.printStackTrace();
		logger.error("空指针，调用了未经初始化或者是不存在的对象！");
		return Result.error("空指针，调用了未经初始化或者是不存在的对象！");
	}
	
	@ExceptionHandler(IOException.class)
	@ResponseBody
	public Map<String, Object> handleIOException(IOException e){
		e.printStackTrace();
		logger.error("IO读写异常！");
		return Result.error("IO读写异常！");
	}
	
	@ExceptionHandler(ClassNotFoundException.class)
	@ResponseBody
	public Map<String, Object> handleClassNotFoundException(ClassNotFoundException e){
		e.printStackTrace();
		logger.error("指定的类不存在！");
		return Result.error("指定的类不存在！");
	}
	
	@ExceptionHandler(ArithmeticException.class)
	@ResponseBody
	public Map<String, Object> handleArithmeticException(ArithmeticException e){
		e.printStackTrace();
		logger.error("数学运算异常！");
		return Result.error("数学运算异常！");
	}
	
	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	@ResponseBody
	public Map<String, Object> handleArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e){
		e.printStackTrace();
		logger.error("数组下标越界！");
		return Result.error("数组下标越界！");
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public Map<String, Object> handleIllegalArgumentException(IllegalArgumentException e){
		e.printStackTrace();
		logger.error("调用方法的参数错误！");
		return Result.error("调用方法的参数错误！");
	}
	
	@ExceptionHandler(ClassCastException.class)
	@ResponseBody
	public Map<String, Object> handleClassCastException(ClassCastException e){
		e.printStackTrace();
		logger.error("类型强制转换错误！");
		return Result.error("类型强制转换错误！");
	}
	
	@ExceptionHandler(SecurityException.class)
	@ResponseBody
	public Map<String, Object> handleSecurityException(SecurityException e){
		e.printStackTrace();
		logger.error("违背安全原则异常：");
		return Result.error("违背安全原则异常！");
	}
	
	@ExceptionHandler(SQLException.class)
	@ResponseBody
	public Map<String, Object> handleSQLException(SQLException e){
		e.printStackTrace();
		logger.error("操作数据库异常！");
		return Result.error("操作数据库异常！");
	}
	
	@ExceptionHandler(NoSuchMethodError.class)
	@ResponseBody
	public Map<String, Object> handleNoSuchMethodError(NoSuchMethodError e){
		e.printStackTrace();
		logger.error("调用了未定义的方法！");
		return Result.error("调用了未定义的方法！");
	}
	
	@ExceptionHandler(InternalError.class)
	@ResponseBody
	public Map<String, Object> handleInternalError(InternalError e){
		e.printStackTrace();
		logger.error("Java虚拟机发生了内部错误！");
		return Result.error("Java虚拟机发生了内部错误！");
	}
	
	@ExceptionHandler(IllegalBlockSizeException.class)
	@ResponseBody
	public Map<String, Object> handleIllegalBlockSizeException(IllegalBlockSizeException e){
		e.printStackTrace();
		logger.error("数据权限保护，您无权操作该数据！");
		return Result.error("数据权限保护，您无权操作该数据！");
	}
	
	@ExceptionHandler(JspException.class)
	@ResponseBody
	public Map<String, Object> handleJspException(JspException e){
		e.printStackTrace();
		logger.error("未找到相应页面！");
		return Result.error("未找到相应页面！");
	}
}
