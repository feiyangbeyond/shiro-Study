package com.beisi.common.comtool;

import javax.servlet.http.HttpServletRequest;

public class PageInclude {

	public static String BaiDuMapInfo() {
		//String key = "B0eDMGBhYBvGGodNxxSLptFUszg6whes"; // Treeyarn Key
		String key = "YjkB1M1zpO9I55OGGAvhzzQ5hQPyPfg6"; // WHH Key
		String apk = "<script type=\"text/javascript\" src=\"http://api.map.baidu.com/api?v=2.0&ak=" + key + "\"></script>";
		return apk;
	}

	public static String InsdepFile(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		String meta = "<meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'>" + 
					  "<meta http-equiv='Content-Language' content='zh-CN'>" + 
				      "<meta HTTP-EQUIV='Content-Type' CONTENT='text/html; charset=utf-8'>" + 
					  "<link rel='shortcut icon' href='" + basePath + "insdep/themes/insdep/logo.ico'>";
		String css = "<link href='" + basePath + "insdep/themes/insdep/easyui.css' rel='stylesheet' charset='utf-8' type='text/css'>" + 
					 "<link href='" + basePath + "insdep/themes/insdep/default_theme.css' rel='stylesheet' charset='utf-8'  type='text/css'>" + 
				     "<link href='" + basePath + "insdep/themes/insdep/icon.css' rel='stylesheet' charset='utf-8'  type='text/css'>";
		String js = "<script type='text/javascript' src='" + basePath + "insdep/jquery.min.js'></script>" + 
				    "<script type='text/javascript' src='" + basePath + "insdep/plugin/ajaxupload.js'></script>" + 
				    "<script type='text/javascript' src='" + basePath + "insdep/jquery.easyui.min.js'></script>" + 
				    "<script type='text/javascript' src='" + basePath + "insdep/themes/insdep/jquery.insdep-extend.min.js'></script>";
					
		return meta + css + js;
	}
}
