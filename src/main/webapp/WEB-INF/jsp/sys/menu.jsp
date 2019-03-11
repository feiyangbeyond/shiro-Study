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
<title>菜单管理</title>

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
<!-- free-jqGrid -->
<link rel="stylesheet" href="<%=basePath%>static/plugins/free-jqgrid/css/ui.jqgrid.css?ver=<%=version%>">
<link rel="stylesheet" href="<%=basePath%>static/plugins/free-jqgrid/css/ui.jqgrid.bootstrap.css?ver=<%=version%>">
<!-- zTree -->
<link rel="stylesheet" href="<%=basePath%>static/plugins/zTree/css/metroStyle/metroStyle.css?ver=<%=version%>">

<!-- Owner -->
<link rel="stylesheet" href="<%=basePath%>static/css/main.css?ver=<%=version%>">
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

	<div id="menu-list">
		<div class="grid-btn row">
			<div class="form-group col-sm-12 text-right">
				<shiro:hasPermission name="sys:menu:save">
					<a class="btn btn-primary btn-flat" id="saveMenu"><i class="fa fa-plus"></i>&nbsp;新增</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:menu:update">
					<a class="btn btn-primary btn-flat" id="updateMenu"><i class="fa fa-pencil-square-o"></i>&nbsp;修改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:menu:delete">
					<a class="btn btn-primary btn-flat" id="deleteMenu"><i class="fa fa-trash-o"></i>&nbsp;删除</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:menu:list">
					<a class="btn btn-primary btn-flat" id="refreshMenu"><i class="fa fa-refresh"></i>&nbsp;刷新</a>
				</shiro:hasPermission>
			</div>
		</div>
		<table id="jqGrid"></table>
	</div>


	<div class="panel panel-default div-display-none" id="menu-information">
		<div class="panel-heading" id="menu-title">菜单</div>
		<div class="panel-body">
			<form class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">类型</label>
					<div class="col-sm-10">
						<label class="radio-inline">
							<input type="radio" name = "menuType" value = 0 >目录
						</label>
						<label class="radio-inline">
							<input type="radio" name = "menuType" value = 1 >菜单
						</label>
						<label class="radio-inline">
							<input type="radio" name = "menuType" value = 2 >按钮
						</label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">名称</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="menuName" placeholder="菜单或按钮名称" id="menuName" >
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">上级菜单</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="parentName" placeholder="默认为一级菜单,请选择" id="parentName" readonly >
					</div>
				</div>
				<div class="form-group" id="div-menu-url">
					<label class="col-sm-2 control-label">菜单URL</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="url" placeholder="菜单URL" id="menuURL">
					</div>
				</div>
				<div class="form-group"  id="div-menu-perms">
					<label class="col-sm-2 control-label">授权标识</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="perms" placeholder="多个逗号分割：user:list,user:update" id="perms">
					</div>
				</div>
				<div class="form-group"  id="div-menu-order">
					<label class="col-sm-2 control-label">序号</label>
					<div class="col-sm-4">
						<input type="number" class="form-control" value=0 name="orderNum" id="orderNum">
					</div>
				</div>
				<div class="form-group" id="div-menu-icon">
					<label class="col-sm-2 control-label">图标</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="icon" id="icon">
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
	
	<div class="modal fade" tabindex="-1" role="dialog" id="showMenuList">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
					<h4 class="modal-title">选择菜单</h4>
				</div>
				<div class="modal-body">
					<ul id="menuTree" class="ztree"></ul>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary btn-flat" id="selectMenu">确定</button>
				</div>
			</div>
		</div>
	</div>



	<!-- REQUIRED JS SCRIPTS -->

	<!-- jQuery 3 -->
	<script type="text/javascript" src="<%=basePath%>static/libs/jquery.min.js?ver=<%=version%>"></script>
	<!-- Bootstrap 3.3.7 -->
	<script type="text/javascript" src="<%=basePath%>static/libs/bootstrap.min.js?ver=<%=version%>"></script>
	<!-- AdminLTE App -->
	<script type="text/javascript" src="<%=basePath%>static/libs/adminlte.min.js?ver=<%=version%>"></script>

	<!-- free-jqGrid -->
	<script type="text/javascript" src="<%=basePath%>static/plugins/free-jqgrid/js/jquery.jqgrid.src.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/plugins/free-jqgrid/js/grid.locale-cn.js?ver=<%=version%>"></script>

	<!-- zTree -->
	<script type="text/javascript" src="<%=basePath%>static/plugins/zTree/js/jquery.ztree.all.js?ver=<%=version%>"></script>
	
	<!-- Owner  -->
	<script type="text/javascript" src="<%=basePath%>static/js/common.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/sys/menu.js?ver=<%=version%>"></script>
</body>
</html>