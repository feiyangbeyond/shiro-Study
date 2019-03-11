
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
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">


<base href="<%=basePath%>">
<title>定时任务日志</title>

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

	<div id="schedule-list">
		<div class="grid-btn row">
			<div class="form-group col-sm-3">
				<div class="input-group">
					<input type="text" class="form-control" id="searchName" placeholder="用户名、用户操作">
						<span class="input-group-btn">
						<button class="btn btn-default form-control" type="button" id="getLog">查询</button>
					</span>
				</div>
			</div>
			<div class="form-group col-sm-9 text-right">
				<a class="btn btn-primary btn-flat" id="exportFile">导出 Excel</a>
			</div>
		</div>
		<table id="jqGrid"></table>
		<div id="jqGridPager"></div>
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

	<!-- tableExport -->
	<script type="text/javascript" src="<%=basePath%>static/plugins/tableExport/libs/FileSaver/FileSaver.min.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/plugins/tableExport/libs/js-xlsx/xlsx.core.min.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/plugins/tableExport/tableExport.js?ver=<%=version%>"></script>


	<!-- Owner  -->
	<script type="text/javascript" src="<%=basePath%>static/js/common.js?ver=<%=version%>"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/sys/log.js?ver=<%=version%>"></script>
</body>
</html>