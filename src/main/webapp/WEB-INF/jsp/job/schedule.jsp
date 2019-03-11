
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
<title>定时任务</title>

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

	<div id="schedule-list">
		<div class="grid-btn row">
			<div class="form-group col-sm-3">
				<div class="input-group">
					<input type="text" class="form-control" id="searchName" placeholder="bean名称">
						<span class="input-group-btn">
						<button class="btn btn-default form-control" type="button" id="getSchedule">查询</button>
					</span>
				</div>
			</div>
			<div class="form-group col-sm-9">
				<shiro:hasPermission name="sys:schedule:save">
					<a class="btn btn-primary btn-flat" id="addSchedule"><i
						class="fa fa-plus"></i>&nbsp;新增</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:schedule:update">
					<a class="btn btn-primary btn-flat" id="updateSchedule"> <i
						class="fa fa-pencil-square-o"></i>&nbsp;修改
					</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:schedule:delete">
					<a class="btn btn-primary btn-flat" id="deleteSchedule"><i
						class="fa fa-trash-o"></i>&nbsp;删除</a>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="sys:schedule:pause">
					<a class="btn btn-primary btn-flat" id="pauseSchedule"><i
						class="fa fa-pause"></i>&nbsp;暂停</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:schedule:resume">
					<a class="btn btn-primary btn-flat" id="resumeSchedule"> <i
						class="fa fa-play"></i>&nbsp;恢复
					</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:schedule:run">
					<a class="btn btn-primary btn-flat" id="runSchedule"><i
						class="fa fa-arrow-circle-right"></i>&nbsp;立即执行</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:schedule:log">
					<a class="btn btn-warning btn-flat pull-right" id="logSchedule">日志列表</a>
				</shiro:hasPermission>
			</div>
		</div>
		<table id="jqGrid"></table>
		<div id="jqGridPager"></div>
	</div>


	<div class="panel panel-default  div-display-none" id="schedule-information">
		<div class="panel-heading" id="schedule-title">任务</div>
		<div class="panel-body">
			<form class="form-horizontal" action="" method="post">
				<div class="form-group">
					<label class="col-sm-2 control-label">bean名称</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" placeholder="Spring bean 名称,对应的注解名称" id="beanName">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">方法名称</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" placeholder="方法名称" id="methodName">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">参数</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" placeholder="参数" id="params">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">cron表达式</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" placeholder="例(整点执行)：0 ? * * * ? *" id="cronExpression">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" placeholder="备注" id="remark">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"></label>
					<div class="col-sm-4">
						<input type="button" class="btn btn-primary btn-flat" value="确定" id="saveOrUpdate" /> 
						<input type="button" class="btn btn-warning btn-flat pull-right" value="返回" id="returnList" />
					</div>
				</div>
			</form>
		</div>

	</div>



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

	<!-- Owner  -->
	<script src="static/js/common.js"></script>
	<script src="static/js/job/schedule.js"></script>
</body>
</html>