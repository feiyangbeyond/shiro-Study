<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta charset="utf-8">
<!-- 显示地声明如果用ie浏览器的化，要用最新的版本的视图引擎来渲染页面 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>用户管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lib/datetimepicker/css/bootstrap-datetimepicker.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lib/bootstrapSelect/css/bootstrap-select.min.css">
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/static/lib/html5shiv/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath}/static/lib/respond/respond.min.js"></script>
    <![endif]-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/user.css">
</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="search_title_bar">搜索</div>
		</div>
		<div class="row">
			<form action="${pageContext.request.contextPath}/admin/userSearch.html" id="search_form" class="form-inline" method="post">
				<div class="form-group has-feedback">
					<label>报名时间:</label> <input class="form-control input-sm form_datetime" type="text" name="regCourseStartTime"> <span class="iconfont icon-xueqi form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<label>至</label> <input class="form-control input-sm form_datetime" type="text" name="regCourseEndTime"> <span class="iconfont icon-xueqi form-control-feedback"></span>
				</div>
				&nbsp;
				<div class="form-group">
					<label>报名课程:</label> <input class="form-control input-sm" type="text" name="regCourse" placeholder="课程名称">
				</div>
				&nbsp;
				<div class="form-group">
					<label>学员信息:</label> <input class="form-control input-sm" type="text" name="userInfo" placeholder="用户名/手机号">
				</div>
			</form>
		</div>
		<div id="search_btn" class="row text-right">
			<button id="searchBtn" type="button" class="btn btn-default">查询</button>
			<button type="button" data-toggle="modal" data-target="#addUserModal" class="btn btn-default">添加</button>
			<button id="batchDelUsersBtn" type="button" class="btn btn-default">删除</button>
			<button type="button" class="btn btn-default">导入</button>
			<button type="button" class="btn btn-default">导出</button>
		</div>
		<div id="content_table" class="row">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<tr>
						<td style="width: 30px;"><input type="checkbox" class="chkall" onclick="chkall();"></td>
						<td>用户名</td>
						<td>手机号</td>
						<td>邮箱地址</td>
						<td>角色</td>
						<td>用户状态</td>
						<td style="width: 100px;">操作</td>
					</tr>
					<c:forEach items="${userDatasByPager.list}" var="user">
						<tr>
							<td><input value="${user.id}" type="checkbox" name="chkuser" class="chkone" onclick="chkone();"></td>
							<td>${user.username}</td>
							<td>${user.phone}</td>
							<td>${user.email}</td>
							<td>
							<c:forEach items="${user.roles}" var="role">
								${role.rname}&nbsp;
							</c:forEach>
							</td>
							<td>${user.enable}</td>
							<td><a href="${pageContext.request.contextPath}/admin/updateUser.html?id=${user.id}" data-target="#updateUserModal" data-toggle="modal">编辑 |</a> <a href="${pageContext.request.contextPath}/admin/delUser.html?id=${user.id}" onclick="return delSure()">| 删除</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div id="pager" class="row">
			<p class="pull-left">
				总共有<span> ${userDatasByPager.total} </span>记录，当前页<span> ${userDatasByPager.pageNum} / ${userDatasByPager.pages} </span>页
			</p>
			<div class="btngroup pull-right">
				<a href="${pageContext.request.contextPath}/admin/userManager.html?pageNum=1&pageSize=10" type="button" class="btn btn-default">首页</a>
				<a href="${pageContext.request.contextPath}/admin/userManager.html?pageNum=${userDatasByPager.prePage}&pageSize=10" type="button" class="btn btn-default">上一页</a>
				<a href="${pageContext.request.contextPath}/admin/userManager.html?pageNum=${userDatasByPager.nextPage}&pageSize=10" type="button" class="btn btn-default">下一页</a>
				<a href="${pageContext.request.contextPath}/admin/userManager.html?pageNum=${userDatasByPager.pages}&pageSize=10" type="button" class="btn btn-default">尾页</a>
			</div>
		</div>
	</div>
	<!-- Modal 添加 -->
	<div class="modal fade" data-backdrop="false" id="addUserModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加用户</h4>
				</div>
				<div class="modal-body">
					<form id="addUserFrom" action="${pageContext.request.contextPath}/admin/addUser.html" method="post">
						<div class="form-group">
							<label>用户名：</label> <input type="text" name="username" class="form-control" placeholder="用户名">
						</div>
						<div class="form-group">
							<label>密码：</label> <input type="password" name="password" class="form-control" placeholder="密码">
						</div>
						<div class="form-group">
							<label>关联的角色：</label> 
							<select name="roleIds" class="selectpicker form-control" multiple data-live-search="true">
								<c:forEach items="${allRoles}" var="role">
									<option value="${role.id}">${role.rname}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label>手机号：</label> <input type="text" name="phone" class="form-control" placeholder="手机号码">
						</div>
						<div class="form-group">
							<label>邮箱：</label> <input type="text" name="email" class="form-control" placeholder="电子邮箱">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="addUserBtn" type="button" class="btn btn-primary">添加用户</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal 编辑 -->
	<div class="modal fade" data-backdrop="false" id="updateUserModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<!--这里的内容是动态改变的 -->
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/static/lib/jquery/jquery.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/lib/datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script src="${pageContext.request.contextPath}/static/lib/bootstrapSelect/js/bootstrap-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/lib/bootstrapSelect/js/i18n/defaults-zh_CN.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/user.js"></script>
	<script type="text/javascript">
		var isFirstPage = ${userDatasByPager.isFirstPage};//是否为第一页
		var isLastPage = ${userDatasByPager.isLastPage};//是否为最后一页
		var isLastPage = ${userDatasByPager.isLastPage};//是否有前一页
		var isLastPage = ${userDatasByPager.isLastPage};//是否有后一页
		if(isFirstPage){//为第一页
			$("#pager > div").children('a').eq(1).addClass("hiden");
		}
		if(isLastPage){
			$("#pager > div").children('a').eq(2).addClass("hiden");
		}
	</script>
</body>

</html>