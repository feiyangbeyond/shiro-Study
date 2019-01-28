<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <!-- 显示地声明如果用ie浏览器的化，要用最新的版本的视图引擎来渲染页面 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>启航课堂首页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css">
    <!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/static/lib/html5shiv/html5shiv.min.js"></script>
      <script src="${pageContext.request.contextPath}/static/lib/respond/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/font/iconfont.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css">
</head>

<body>
	<input id="uuidsalt" type="hidden" value="${uuidsalt}">
    <nav id="main_nav" class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#qh_navbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand"><i class="iconfont icon-Windsurfing"></i>启航课堂</a>
            </div>
            <div id="qh_navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="">首页</a></li>
                    <li><a href="">全部课程</a></li>
                    <li><a href="">问题讨论</a></li>
                    <li><a href="">学习路线</a></li>
                    <li><a href="">资料下载</a></li>
                </ul>
                <div class="navbar-form navbar-left hidden-sm">
                    <form accept="#" method="get">
                        <div class="input-group">
                            <input type="text" name="search_str" class="form-control" placeholder="课程名称..." />
                            <span class="input-group-btn">
                                <button type="button" class="btn btn-primary"><span class="iconfont icon-fangdajing1"></span></button>
                            </span>
                        </div>
                    </form>
                </div>
                <ul id="loginreg" class="nav navbar-nav navbar-right">
                    <li><a href=""><span style="color:#337ab7; font-weight: bolder;" class="iconfont icon-ren111"></span> 登录</a></li>
                    <li><a href=""><span style="color:#337ab7;"  class="iconfont icon-zhuce2"></span> 注册</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div id="loginhtml" class="container">
        <div class="row">
            <div class="col-md-7 col-sm-6 col-xs-5"></div>
            <div class="col-md-10  col-sm-12 col-xs-14 login-col">
                <ul class="nav nav-tabs">
                    <li class="col-xs-12 active"><a href="#loginform" data-toggle="tab">登录账号</a></li>
                    <li class="col-xs-12"><a data-toggle="tab" href="#regform">注册账号</a></li>
                </ul>
                <div class="tab-content">
                    <div id="loginform" class="tab-pane fade in active">
                        <form action="${pageContext.request.contextPath}/login.html" method="post" onsubmit="return checkForm()">
                            <div class="form-group">
                                <label>账号:</label>
                                <input type="text" name="userInfo" class="form-control" placeholder="邮箱/手机/用户名">
                            </div>
                            <div class="form-group">
                                <label>密码:</label>
                                <input type="password" id="pwd" name="password" class="form-control" placeholder="密码">
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox" name="remeberme">10天内自动登录</label>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="form-control btn btn-primary" value="登录">
                            </div>
                            <div class="form-group">
                                <p><a href="#">找回密码</a> | 还没有账号? <a href="#">注册账号</a></p>
                                <p style="text-align: right;">使用第三账号登录:
                                </p>
                                <p style="text-align: right;">
                                    <a href="#" style="text-decoration: none;"><span style="font-size: 30px;" class="iconfont icon-weixin"></span></a>
                                    <a href="#" style="text-decoration: none;"><span style="font-size: 30px;" class="iconfont icon-QQ"></span></a>
                                </p>
                            </div>
                        </form>
                    </div>
                    <div id="regform" class="tab-pane fade">
                        <form action="#" method="post">
                            <div class="form-group">
                                <label>手机:</label>
                                <input type="text" name="username" class="form-control" placeholder="常用的手机号码">
                            </div>
                            <div class="form-group">
                                <label>用户名:</label>
                                <input type="text" name="username" class="form-control" placeholder="用户名">
                            </div>
                            <div class="form-group">
                                <label>密码:</label>
                                <input type="password" name="password" class="form-control" placeholder="密码">
                            </div>
                            <div class="form-group">
                                <label>手机验证码:</label>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <input type="text" name="phoneCode" class="form-control" placeholder="验证码">
                                    </div>
                                    <div class="col-xs-12">
                                        <input value="点击获取验证码" type="button" name="phoneCode" class="form-control btn btn-default">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group" style="margin-top:30px; margin-bottom: 20px;">
                                <input type="submit" class="form-control btn btn-primary" value="登录">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-7  col-sm-6 col-xs-5"></div>
        </div>
    </div>
    <div class="footer hidden-xs">
        <div class="footericon"><span class="iconfont icon-Windsurfing"></span></div>
        <p class="cr">Copyright © 2018 qihangzaixian. All Rights Reserved.</p>
        <p class="cr">启航在线课程 版权所有 | <a href="#">工具下载</a> | <a href="#">资料下载</a> | <a href="#">视频下载</a> | <a href="#">问题反馈</a> | <a href="#">帮助</a></p>
    </div>
    <script src="${pageContext.request.contextPath}/static/lib/jquery/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.min.js"></script>    
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/md5/md5a.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/aes/aes.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/lib/aes/pad-zeropadding-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/login/login.js"></script>
</body>

</html>