package com.beisi.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前端视图控制器
 */

@Controller
public class PageController {

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping({"","/","/index"})
	public String index(){
		return "index";
	}
	/**
	 * 404页面
	 * @return
	 */
	@RequestMapping("/404")
	public String error404() {    
		return "error/404";
	}
	
	/**
	 * 405页面
	 * @return
	 */
	@RequestMapping("/405")
	public String error405() {
		return "error/405";
	}
	
	/** 
	 * 500页面
	 * @return
	 */
	@RequestMapping("/500")
	public String error500() {
		return "error/500";
	}
	
	/**
	 * 自动匹配	测试
	 * @param module
	 * @param url
	 * @return
	 */
	@RequestMapping("modules/{module}/{url}")
	public String module(@PathVariable("module") String module, @PathVariable("url") String url){
		return "modules/" + module + "/" + url;
	}

}
