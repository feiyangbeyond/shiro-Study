<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<c:forEach items="${menuList}" var="menuItem">
		<c:if test="${menuItem.menuType == 0}">
			<li class="treeview active">
				<a href="javascript:;">
					<c:if test="${ not empty menuItem.menuIcon}">
						<i class="${menuItem.menuIcon}"></i>
					</c:if>
					<c:if test="${empty menuItem.menuIcon}">
						<i class="fa fa-circle-o"></i>
					</c:if>
					<span>${menuItem.menuName}</span>
					<span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i> </span>
				</a>
				<ul class="treeview-menu">
					<c:set var="menuList" value="${menuItem.subList}" scope="request" />
					<c:import url="menutree.jsp"/>
				</ul>
			</li>
		</c:if>
		<c:if test="${menuItem.menuType == 1}">
			<li>
				<a href="${menuItem.menuUrl}" target="contentFrame">
					<c:if test="${not empty menuItem.menuIcon}">
						<i class="${menuItem.menuIcon}"></i>
					</c:if>
					<c:if test="${empty menuItem.menuIcon}">
						<i class="fa fa-circle-o"></i>
					</c:if>
					<span>${menuItem.menuName}</span>
				</a>
			</li>
		</c:if>
</c:forEach>