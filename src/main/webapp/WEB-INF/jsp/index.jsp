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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css">
</head>

<body>
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
                    <li><a href="${pageContext.request.contextPath}/login.html"><span style="color:#337ab7; font-weight: bolder;" class="iconfont icon-ren111"></span> 登录</a></li>
                    <li><a href=""><span style="color:#337ab7;"  class="iconfont icon-zhuce2"></span> 注册</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div id="carousel-qhkt" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-qhkt" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-qhkt" data-slide-to="1"></li>
            <li data-target="#carousel-qhkt" data-slide-to="2"></li>
        </ol>
        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active" data-lg-img="${pageContext.request.contextPath}/static/image/slide_01_2000x410.jpg" data-xs-img="${pageContext.request.contextPath}/static/image/slide_01_768x410.jpg">
                <!-- <img src="${pageContext.request.contextPath}/static/image/slide_01_2000x410.jpg" alt="carousel"> -->
            </div>
            <div class="item" data-lg-img="${pageContext.request.contextPath}/static/image/slide_02_2000x410.jpg" data-xs-img="${pageContext.request.contextPath}/static/image/slide_02_768x410.jpg">
                <!-- <img src="${pageContext.request.contextPath}/static/image/slide_02_2000x410.jpg" alt="carousel"> -->
            </div>
            <div class="item" data-lg-img="${pageContext.request.contextPath}/static/image/slide_03_2000x410.jpg" data-xs-img="${pageContext.request.contextPath}/static/image/slide_03_768x410.jpg">
                <!-- <img src="${pageContext.request.contextPath}/static/image/slide_03_2000x410.jpg" alt="carousel"> -->
            </div>
        </div>
        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-qhkt" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-qhkt" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <div id="courseList" class="container">
        <div class="page-header">
            <h3 class="text-center">最新发布课程<a href="#">全部课程<i class="iconfont icon-dayuhao1"></i></a></h3>
        </div>
        <div class="row">
            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <a href="#"> <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course"></a>
                    <div class="caption">
                        <h3><a href="#">Java系列技术之JVM调优</a></h3>
                        <h3 class="course_price">¥50.00 <a class="course_group" href="#">其他课程</a></h3>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <a href="#"> <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course"></a>
                    <div class="caption">
                        <h3><a href="#">Java系列技术之JVM调优</a></h3>
                        <h3 class="course_price">¥50.00 <a class="course_group" href="#">其他课程</a></h3>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <a href="#"> <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course"></a>
                    <div class="caption">
                        <h3><a href="#">Java系列技术之JVM调优</a></h3>
                        <h3 class="course_price">¥50.00 <a class="course_group" href="#">其他课程</a></h3>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <a href="#"> <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course"></a>
                    <div class="caption">
                        <h3><a href="#">Java系列技术之JVM调优</a></h3>
                        <h3 class="course_price">¥50.00 <a class="course_group" href="#">其他课程</a></h3>
                    </div>
                </div>
            </div>
        </div>

        <div class="page-header">
            <h3 class="text-center">JAVASE课程<a href="#">全部课程<i class="iconfont icon-dayuhao1"></i></a></h3>
        </div>
        <div class="row">
            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <a href="#"> <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course"></a>
                    <div class="caption">
                        <h3><a href="#">Java系列技术之JVM调优</a></h3>
                        <h3 class="course_price">¥50.00 <a class="course_group" href="#">其他课程</a></h3>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <a href="#"> <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course"></a>
                    <div class="caption">
                        <h3><a href="#">Java系列技术之JVM调优</a></h3>
                        <h3 class="course_price">¥50.00 <a class="course_group" href="#">其他课程</a></h3>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <a href="#"> <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course"></a>
                    <div class="caption">
                        <h3><a href="#">Java系列技术之JVM调优</a></h3>
                        <h3 class="course_price">¥50.00 <a class="course_group" href="#">其他课程</a></h3>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <a href="#"> <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course"></a>
                    <div class="caption">
                        <h3><a href="#">Java系列技术之JVM调优</a></h3>
                        <h3 class="course_price">¥50.00 <a class="course_group" href="#">其他课程</a></h3>
                    </div>
                </div>
            </div>
        </div>

        <div class="page-header">
            <h3 class="text-center">JAVAEE课程<a href="#">全部课程<i class="iconfont icon-dayuhao1"></i></a></h3>
        </div>
        <div class="row">
            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <a href="#"> <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course"></a>
                    <div class="caption">
                        <h3><a href="#">Java系列技术之JVM调优</a></h3>
                        <h3 class="course_price">¥50.00 <a class="course_group" href="#">其他课程</a></h3>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <a href="#"> <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course"></a>
                    <div class="caption">
                        <h3><a href="#">Java系列技术之JVM调优</a></h3>
                        <h3 class="course_price">¥50.00 <a class="course_group" href="#">其他课程</a></h3>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <a href="#"> <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course"></a>
                    <div class="caption">
                        <h3><a href="#">Java系列技术之JVM调优</a></h3>
                        <h3 class="course_price">¥50.00 <a class="course_group" href="#">其他课程</a></h3>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6">
                <div class="thumbnail">
                    <a href="#"> <img src="${pageContext.request.contextPath}/static/image/jvm.jpg" alt="course"></a>
                    <div class="caption">
                        <h3><a href="#">Java系列技术之JVM调优</a></h3>
                        <h3 class="course_price">¥50.00 <a class="course_group" href="#">其他课程</a></h3>
                    </div>
                </div>
            </div>
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/index.js"></script>
</body>

</html>