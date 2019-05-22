<%--
  Created by IntelliJ IDEA.
  User: 君行天下
  Date: 2017/7/31
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${readercard.name}的主页</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <style>
        body{
            background-color: rgb(240,242,245);
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation" style="background-color:#fff">
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand " href="reader_main.html"><p class="text-primary">小熊书屋</p></a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li >
                    <a href="reader_querybook.html" >
                        图书查询
                    </a>
                </li>
                <li class="active">
                    <a href="reader_info.html" >
                        个人信息
                    </a>
                </li>
                <li >
                    <a href="mybuy.html" >
                        我的订单
                    </a>
                </li>
                <li >
                    <a href="reader_repasswd.html" >
                        密码修改
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <c:if test="${readercard.vipState==1}">
                        <a href="reader_info.html"><span class="glyphicon glyphicon-user"></span>&nbsp;${readercard.name}(尊贵会员)，已登录</a>
                    </c:if>
                    <c:if test="${readercard.vipState==0}">
                        <a href="reader_info.html"><span class="glyphicon glyphicon-user"></span>&nbsp;${readercard.name}(普通用户)，已登录</a>
                    </c:if>
                </li>
                <li><a href="login.html"><span class="glyphicon glyphicon-log-in"></span>&nbsp;退出</a></li>
            </ul>
        </div>
    </div>
</nav>
<c:if test="${!empty error1}">
    <div class="alert alert-success alert-dismissable">
        <button type="button" class="close" data-dismiss="alert"
                aria-hidden="true">
            &times;
        </button>
            ${error1}
    </div>
</c:if>
<c:if test="${!empty succ}">
    <div class="alert alert-success alert-dismissable">
        <button type="button" class="close" data-dismiss="alert"
                aria-hidden="true">
            &times;
        </button>
            ${succ}
    </div>
</c:if>
<c:if test="${!empty error2}">
    <div class="alert alert-danger alert-dismissable">
        <button type="button" class="close" data-dismiss="alert"
                aria-hidden="true">
            &times;
        </button>
            ${error2}
    </div>
</c:if>
<div class="col-xs-5 col-md-offset-3">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                我的信息
            </h3>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <tr>
                    <th width="20%">读者证号</th>
                    <td>${readerinfo.readerId}</td>
                </tr>
                <tr>
                    <th>姓名</th>
                    <td>${readerinfo.name}</td>
                </tr>
                <tr>
                    <th>性别</th>
                    <td>${readerinfo.sex}</td>
                </tr>
                <tr>
                    <th>生日</th>
                    <td>${readerinfo.birth}</td>
                </tr>
                <tr>
                    <th>地址</th>
                    <td>${readerinfo.address}</td>
                </tr>
                <tr>
                    <th>电话</th>
                    <td>${readerinfo.telcode}</td>
                </tr>
                <tr>
                    <th>会员信息</th>
                    <td>
                        <c:if test="${readercard.vipState==1}">
                            尊贵会员
                        </c:if>
                        <c:if test="${readercard.vipState==0}">
                            普通用户
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <a class="btn btn-success btn-sm" href="reader_info_edit.html" role="button">修改信息</a>
        <a class="btn btn-success btn-sm" href="get_vip_do.html" role="button">开通会员</a>
    </div>
</div>


</body>
</html>
