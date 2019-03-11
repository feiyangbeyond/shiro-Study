<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page trimDirectiveWhitespaces="true" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String version = "0.0.1";
%>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

		<base href="<%=basePath%>">
		<title>后台管理</title>

		<!-- Bootstrap 3.3.7 -->
		<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css?ver=<%=version%>">
		<!-- Font Awesome -->
		<link rel="stylesheet" href="<%=basePath%>static/css/font-awesome.min.css?ver=<%=version%>">
		<link rel="stylesheet" href="<%=basePath%>static/alifonts/iconfont.css?ver=<%=version%>">
		<!-- Theme style -->
		<link rel="stylesheet" href="<%=basePath%>static/css/AdminLTE.min.css?ver=<%=version%>">
		<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
		<link rel="stylesheet" href="<%=basePath%>static/css/_all-skins.min.css?ver=<%=version%>">
		<!-- Owner css -->
		<link rel="stylesheet" href="<%=basePath%>static/css/index.css?ver=<%=version%>">
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
		<script type="text/javascript">
			var basePath = "<%=basePath%>";
		</script>
		<!-- 避免页面嵌套 -->
		<script>
			if(self != top) {
				top.location.href = self.location.href;
			}
		</script>
	</head>

	<body class="hold-transition skin-blue fixed sidebar-mini">
		<div class="wrapper">
			<header class="main-header">

				<!-- Logo -->
				<a class="logo">
					<!-- mini logo for sidebar mini 50x50 pixels -->
					<span class="logo-mini"><b>ZPC</b></span>
					<!-- logo for regular state and mobile devices -->
					<span class="logo-lg"><b>ZPC</b>后台管理</span>
				</a>

				<!-- Header Navbar: style can be found in header.less -->
				<nav class="navbar navbar-static-top">
					<!-- Sidebar toggle button-->
					<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
						<span class="sr-only">Toggle navigation</span>
					</a>
					<div class="navbar-custom-menu">
						<ul class="nav navbar-nav">
							<li class="dropdown setting-menu">
								<a class="dropdown-toggle" data-toggle="dropdown">
									<i class="iconfont icon-yonghu">&nbsp;${sysUser.cUsrName}</i>
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li>
										<ul class="menu">
											<li><a href="#" data-toggle="modal" data-target="#modal-password">修改密码</a></li>
											<li><a href="sys/logout" >退出</a></li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
					</div>
				</nav>
			</header>
			<!-- Left side column. contains the logo and sidebar -->
			<aside class="main-sidebar">

				<!-- sidebar: style can be found in sidebar.less -->
				<section class="sidebar">

					<!-- sidebar menu: : style can be found in sidebar.less -->
					<ul class="sidebar-menu" data-widget="tree">
						<li class="header">菜单</li>

						<!-- Jsp方式添加菜单 -->
						<c:import url="menutree.jsp" />

					</ul>
					<!-- /.sidebar-menu -->
				</section>
				<!-- /.sidebar -->
			</aside>

			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<ol class="breadcrumb">
						<li>
							<a><i class="iconfont icon-shouye_"></i> 首页</a>
						</li>
						<li class="active" id="navTitle">Welcome</li>
					</ol>
				</section>

				<!-- Main content -->
				<section class="content">
					<iframe id="contentFrame" name="contentFrame" src="index" onload="loadFrame(this)"></iframe>
				</section>
				<!-- /.content -->

			</div>
			<!-- /.content-wrapper -->

			<!-- Main Footer -->
			<footer class="main-footer navbar-fixed-bottom">
				<!-- To the right -->
				<div class="pull-right hidden-xs">可添加一些相关信息</div>
				<!-- Default to the left -->
				<strong>Copyright &copy; 2018 <a>Company</a>.
				</strong> All rights reserved.
			</footer>

		</div>
		<!-- ./wrapper -->


		<!-- 修改密码弹窗 -->
		<div class="modal fade" id="modal-password">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
						<h4 class="modal-title">修改密码</h4>
					</div>
					<div class="modal-body">

						<form class="form-horizontal" >
							<div class="box-body">

								<div class="form-group">
									<label class="col-sm-2 control-label">账号</label>

									<div class="col-sm-10">
										<span class="label label-danger">${sysUser.cUsrName}</span>
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-2 control-label">原密码</label>

									<div class="col-sm-10">
										<input type="password" class="form-control" name="password" placeholder="原密码" id="password">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">新密码</label>

									<div class="col-sm-10">
										<input type="password" class="form-control" name="newPassword" placeholder="新密码" id="newPassword">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">确认密码</label>

									<div class="col-sm-10">
										<input type="password" class="form-control" name="confirmPassword" placeholder="确认密码" id="confirmPassword">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary btn-flat" id="btnPassword">修改</button>
							</div>
						</form>

					</div>

				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>

		<!-- REQUIRED JS SCRIPTS -->

		<!-- jQuery 3 -->
		<script type="text/javascript" src="<%=basePath%>static/libs/jquery.min.js?ver=<%=version%>"></script>
		<!-- Bootstrap 3.3.7 -->
		<script type="text/javascript" src="<%=basePath%>static/libs/bootstrap.min.js?ver=<%=version%>"></script>
		<!-- AdminLTE App -->
		<script type="text/javascript" src="<%=basePath%>static/libs/adminlte.min.js?ver=<%=version%>"></script>
		
		
		<!-- jquery-slimscroll -->
		<script type="text/javascript" src="<%=basePath%>static/plugins/jquery-slimscroll/jquery.slimscroll.min.js?ver=<%=version%>"></script>
		
		
		<!-- layer -->
		<script type="text/javascript" src="<%=basePath%>static/plugins/layer/layer.js?ver=<%=version%>"></script>
		<script type="text/javascript" src="<%=basePath%>static/plugins/layer/layerTool.js?ver=<%=version%>"></script>
		
		<!-- Owner -->
		<script type="text/javascript" src="<%=basePath%>static/libs/md5/md5a.js?ver=<%=version%>"></script>
		<script type="text/javascript" src="<%=basePath%>static/libs/aes/aes.js?ver=<%=version%>"></script>
		<script type="text/javascript" src="<%=basePath%>static/js/sys/index.js?ver=<%=version%>"></script>

		<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->

	</body>

</html>