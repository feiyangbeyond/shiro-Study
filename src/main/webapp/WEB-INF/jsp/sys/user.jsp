
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
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
<!DOCTYPE html> 
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">


<base href="<%=basePath%>">
<title>管理员列表</title>

<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="<%=basePath%>static/css/bootstrap.min.css?ver=<%=version%>">
<link rel="stylesheet" type="text/css" href="<%=basePath%>static/libs/datetimepicker/css/bootstrap-datetimepicker.css">

<!-- Font Awesome -->
<link rel="stylesheet" href="<%=basePath%>static/css/font-awesome.min.css?ver=<%=version%>">
<link rel="stylesheet" href="<%=basePath%>static/alifonts/iconfont.css?ver=<%=version%>">

<!-- Theme style -->
<link rel="stylesheet" href="<%=basePath%>static/css/AdminLTE.min.css?ver=<%=version%>">
<!-- AdminLTE Skins. Choose a skin from the css/skins
     folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="<%=basePath%>static/css/_all-skins.min.css?ver=<%=version%>">
<!-- free-jqGrid -->
<link rel="stylesheet" href="<%=basePath%>static/plugins/free-jqgrid/css/ui.jqgrid.css?ver=<%=version%>">
<link rel="stylesheet" href="<%=basePath%>static/plugins/free-jqgrid/css/ui.jqgrid.bootstrap.css?ver=<%=version%>">


<!-- Owner -->
<link rel="stylesheet" href="static/css/main.css?ver=<%=version%>">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
	</script>
</head>
<body>

	<div id="user-list">
		<div class="grid-btn row">

			<div class="form-group col-sm-3">
				<div class="input-group">
					<input type="text" class="form-control" id="searchName"
						placeholder="真实姓名"> <span class="input-group-btn">
						<button class="btn btn-default form-control" type="button"
							id="getUser">查询</button>
					</span>
				</div>
			</div>
			<div class="form-group col-sm-9 text-right">
				<shiro:hasPermission name="sys:user:save">
					<a class="btn btn-primary btn-flat" id="saveUser"><i
						class="fa fa-plus"></i>&nbsp;新增</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:user:update">
					<a class="btn btn-primary btn-flat" id="updateUser"> <i
						class="fa fa-pencil-square-o"></i>&nbsp;修改
					</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:user:delete">
					<a class="btn btn-primary btn-flat" id="deleteUser"><i
						class="fa fa-trash-o"></i>&nbsp;删除</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:user:list">
					<a class="btn btn-primary btn-flat" id="refreshUser"><i
						class="fa fa-refresh"></i>&nbsp;刷新</a>
				</shiro:hasPermission>
			</div>
		</div>
		<table id="jqGrid"></table>
		<div id="jqGridPager"></div>
	</div>



	<div class="panel panel-default  div-display-none"
		id="user-information">
		<div class="panel-heading" id="user-title">用户信息</div>
		<div class="panel-body">
			<form class="form-horizontal" >
			
				<div class="form-group">
					<label class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="username"
							placeholder="登录账号" id="userName" >
					</div>
				</div>
				
				<!-- 暂时解决Chrome浏览器表单自动填充问题 -->
				<input type="password" class="div-display-none">
				
				<div class="form-group">
					<label class="col-sm-2 control-label">密码</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="password"
							placeholder="密码" id="password" >
					</div>
				</div>	
				
				<div class="form-group">
					<label class="col-sm-2 control-label">真实姓名</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="userrealname"
							placeholder="真实姓名" id="userrealname" >
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">邮箱</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="email"
							placeholder="邮箱" id="email">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="mobile"
							placeholder="手机号" id="mobile">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">账号有效期</label>
					<div class="col-sm-4">
						<input class="form-control input-sm form_datetime" type="text" 
						name="expirationTime" id="expirationTime" placeholder="账号有效期" /> 
						<!-- <span class="iconfont icon-xueqi form-control-feedback"></span> -->
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">角色</label>
					<div class="col-sm-8" id="roleList"></div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">状态</label>
					<div class="col-sm-10">
						<label class="radio-inline"> <input type="radio"
							name="status" value="0">禁用
						</label> <label class="radio-inline"> <input type="radio"
							name="status" value="1">正常
						</label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-4">
						<input type="button" class="btn btn-primary btn-flat" value="确定"
							id="saveOrUpdate" /> <input type="button"
							class="btn btn-warning btn-flat pull-right" value="返回"
							id="returnList" />
					</div>
				</div>
			</form>
		</div>

	</div>



	<!-- REQUIRED JS SCRIPTS -->

	<!-- jQuery 3 -->
	<script type="text/javascript" src="<%=basePath%>static/libs/jquery.min.js?ver=<%=version%>"></script>
	<!-- Bootstrap 3.3.7 -->
	<script type="text/javascript" src="<%=basePath%>static/libs/bootstrap.min.js?ver=<%=version%>"></script>
	<script src="<%=basePath%>static/libs/datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/libs/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<!-- AdminLTE App -->
	<script type="text/javascript" src="<%=basePath%>static/libs/adminlte.min.js?ver=<%=version%>"></script>

	<!-- Encryption -->
	<script type="text/javascript" src="<%=basePath%>static/libs/md5/md5a.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/libs/aes/aes.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/libs/aes/pad-zeropadding-min.js?ver=<%=version%>"></script>
	
	<!-- free-jqGrid -->
	<script type="text/javascript" src="<%=basePath%>static/plugins/free-jqgrid/js/jquery.jqgrid.src.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/plugins/free-jqgrid/js/grid.locale-cn.js?ver=<%=version%>"></script>


	<!-- layer -->
	<script type="text/javascript" src="<%=basePath%>static/plugins/layer/layer.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/plugins/layer/layerTool.js?ver=<%=version%>"></script>

	<!-- Owner  -->
	<script type="text/javascript" src="<%=basePath%>static/js/common.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/sys/user.js?ver=<%=version%>"></script>
</body>
</html>