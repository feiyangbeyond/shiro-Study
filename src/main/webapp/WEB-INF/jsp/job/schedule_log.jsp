
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">


<base href="<%=basePath%>">
<title>定时任务日志</title>

<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="static/css/bootstrap.min.css">

<!-- Font Awesome -->
<link rel="stylesheet" href="static/css/font-awesome.min.css">

<!-- Theme style -->
<link rel="stylesheet" href="static/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
     folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="static/css/_all-skins.min.css">
<!-- free-jqGrid -->
<link rel="stylesheet" href="static/plugins/free-jqgrid/css/ui.jqgrid.css">
<link rel="stylesheet" href="static/plugins/free-jqgrid/css/ui.jqgrid.bootstrap.css">

<!-- Owner -->
<link rel="stylesheet" href="static/css/main.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->


</head>
<body>

	<div class="grid-btn row">
		<div class="form-group col-sm-3">
			<div class="input-group">
				<input type="text" class="form-control" id="searchName" placeholder="任务ID">
					<span class="input-group-btn">
					<button class="btn btn-default form-control" type="button" id="getScheduleLog">查询</button>
				</span>
			</div>
		</div>
		<div class="form-group col-sm-9 text-right">
			<a class="btn btn-primary btn-flat" id="exportFile">导出 Excel</a>
			<a class="btn btn-warning btn-flat" id="back">返回</a>
		</div>
	</div>
	<table id="jqGrid"></table>
	<div id="jqGridPager"></div>


	<!-- REQUIRED JS SCRIPTS -->

	<!-- jQuery 3 -->
	<script src="static/libs/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="static/libs/bootstrap.min.js"></script>
	<!-- AdminLTE App -->
	<script src="static/libs/adminlte.min.js"></script>

	<!-- free-jqGrid -->
	<script src="static/plugins/free-jqgrid/js/jquery.jqgrid.src.js"></script>
	<script src="static/plugins/free-jqgrid/js/grid.locale-cn.js"></script>
	
	<!-- tableExport -->
	<script src="static/plugins/tableExport/libs/FileSaver/FileSaver.min.js"></script>
	<script src="static/plugins/tableExport/libs/js-xlsx/xlsx.core.min.js"></script>
	<script src="static/plugins/tableExport/tableExport.js"></script>

	<!-- Owner  -->
	<script src="static/js/common.js"></script>
	<script src="static/js/job/schedule_log.js"></script>
</body>
</html>