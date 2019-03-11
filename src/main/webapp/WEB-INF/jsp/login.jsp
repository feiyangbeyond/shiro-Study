<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>登录界面</title>

<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css?ver=<%=version%>">
<!-- Font Awesome -->
<link rel="stylesheet" href="<%=basePath%>static/css/font-awesome.min.css?ver=<%=version%>">
<link rel="stylesheet" href="<%=basePath%>static/alifonts/iconfont.css?ver=<%=version%>">
<!-- Theme style -->
<link rel="stylesheet" href="<%=basePath%>static/css/AdminLTE.min.css?ver=<%=version%>">
<link rel="stylesheet" href="<%=basePath%>static/css/_all-skins.min.css?ver=<%=version%>">
<!-- iCheck for checkboxes and radio inputs -->
<link rel="stylesheet" href="<%=basePath%>static/plugins/iCheck/all.css?ver=<%=version%>">

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
	if(self!=top){top.location.href=self.location.href;}
</script>  
</head>

<body class="hold-transition login-page">
	<input id="uuidsalt" type="hidden" value="${uuidsalt}">
	<div class="login-box">
		<div class="login-logo">
			<a href="sys/login"><b>!</b>管理系统<b>!</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">登录后台</p>

				<div class="form-group has-feedback">
					<input type="text" class="form-control" placeholder="账号" id="userName"><!-- name="username" -->
					<span class="glyphicon glyphicon-envelope form-control-feedback" ></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="密码"  id="password" >
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label>
								<input type="checkbox" class="minimal" id="rememberMe" > 记住我
							</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button id="loginBtn" class="btn btn-primary btn-block btn-flat">登录</button>
					</div>
					<!-- /.col -->
				</div>
				<!-- 提示语句-->
				<div class="form-group has-error">
					<span id="errMsg" class="help-block"></span>
				</div>
 				
			
			<!--<a href="#">忘记密码</a>&nbsp&nbsp&nbsp-->
			<!--<a href="register.html" class="text-center">注册</a>-->

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery 3 -->
	<script type="text/javascript" src="<%=basePath%>static/libs/jquery.min.js?ver=<%=version%>"></script>
	<!-- Bootstrap 3.3.7 -->
	<script type="text/javascript" src="<%=basePath%>static/libs/bootstrap.min.js?ver=<%=version%>"></script>
	<!-- iCheck -->
	<script type="text/javascript" src="<%=basePath%>static/plugins/iCheck/icheck.min.js?ver=<%=version%>"></script>
	<script>
    //iCheck for checkbox and radio inputs
	    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
	      checkboxClass: 'icheckbox_minimal-blue',
	      radioClass   : 'iradio_minimal-blue'
	    })
	</script>
	<!-- layer -->
	<script type="text/javascript" src="<%=basePath%>static/plugins/layer/layer.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/plugins/layer/layerTool.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/libs/md5/md5a.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/libs/aes/aes.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/libs/aes/pad-zeropadding-min.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/login.js?ver=<%=version%>"></script>
	
</body>
</html>