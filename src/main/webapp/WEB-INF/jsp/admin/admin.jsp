<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<!-- 显示地声明如果用ie浏览器的化，要用最新的版本的视图引擎来渲染页面 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>进销系统后台管理首页</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css">
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/static/lib/html5shiv/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath}/static/lib/respond/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/font/iconfont.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/admin.css">
</head>

<body>
	<div id="admin_top" class="container-fluid">
		<div class="row">
			<div class="navbar navbar-inverse navbar-static-top navbar-hed">
				<div class="navbar-header col-md-8">
					<span class="navbar-brand"><i
						class="iconfont icon-jinxiaocun"></i>进销系统后台管理页</span>
				</div>
				<div class="col-md-8 col-md-offset-8 login_info text-right">
					<i class="iconfont icon-ren"></i> ${username} <i
						class="iconfont icon-rili"></i> ${nowDate} <a class="pull-right"
						href="${pageContext.request.contextPath}/logout.html"><i class="iconfont icon-power-bold-01"></i></a>
				</div>
			</div>
		</div>
	</div>
	<div id="sidle_bar">
		<div class="sidlebar_title">
			<p>
				<span>导航模块 / </span> <span>Nav Module</span>
			</p>
		</div>
		<div class="sidlebar_content navbar-fixed-bottom">
			<a href="#collapse_system" data-toggle="collapse"><i
				class="iconfont icon-jia1"></i>系统设置</a>
			<ul id="collapse_system" class="collapse collapse_all">
				<li><a href=""
					data-iframesrc="${pageContext.request.contextPath}/admin/userManager.html?pageNum=1&pageSize=10"><i
						class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""
					data-iframesrc="${pageContext.request.contextPath}/admin/roleManager.html"><i
						class="iconfont icon-jian"></i>角色管理</a></li>
				<li><a href=""
					data-iframesrc="${pageContext.request.contextPath}/admin/resManager.html"><i
						class="iconfont icon-jian"></i>资源管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>系统信息管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>系统备份管理</a></li>
			</ul>
			<a href="#collapse_vedio" data-toggle="collapse"><i
				class="iconfont icon-jia1"></i>视频管理</a>
			<ul id="collapse_vedio" class="collapse collapse_all">
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
			</ul>
			<a href="#collapse_course" data-toggle="collapse"><i
				class="iconfont icon-jia1"></i>课程管理</a>
			<ul id="collapse_course" class="collapse collapse_all">
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
			</ul>
			<a href="#collapse_res" data-toggle="collapse"><i
				class="iconfont icon-jia1"></i>资料管理</a>
			<ul id="collapse_res" class="collapse collapse_all">
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
				<li><a href=""><i class="iconfont icon-jian"></i>用户管理</a></li>
			</ul>
		</div>
	</div>
	<div id="path_nav">
		<ol class="breadcrumb">
			<li><a href="admin.html">后台首页</a></li>
			<li style="color: #337ab7;">系统设置</li>
			<li class="active">系统信息</li>
		</ol>
	</div>
	<script
		src="${pageContext.request.contextPath}/static/lib/jquery/jquery.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script
		src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/admin.js"></script>
</body>
</html>
<iframe id="iframe-contant" class="navbar-fixed-bottom" frameborder="no"
	scrolling="auto" width="100%" height="100%" allowtransparency="true"
	src="${pageContext.request.contextPath}/static/html/admin_welcome.html"></iframe>