<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的订单</title>
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
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        图书查询
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="reader_hot_list.html">热销榜</a></li>
                        <li class="divider"></li>
                        <li><a href="reader_querybook.html">搜索书籍</a></li>
                    </ul>
                </li>
                <li>
                    <a href="reader_info.html" >
                        个人信息
                    </a>
                </li>
                <li class="active">
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

<c:if test="${!empty succ}">
    <div class="alert alert-success alert-dismissable">
        <button type="button" class="close" data-dismiss="alert"
                aria-hidden="true">
            &times;
        </button>
            ${succ}
    </div>
</c:if>
<c:if test="${!empty error}">
    <div class="alert alert-danger alert-dismissable">
        <button type="button" class="close" data-dismiss="alert"
                aria-hidden="true">
            &times;
        </button>
            ${error}
    </div>
</c:if>

<div class="panel panel-default" style="width: 90%;margin-left: 5%;margin-top: 5%">
    <div class="panel-heading">
        <h3 class="panel-title">
            我的订单
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <%--<th>流水号</th>--%>
                <th>下单日期</th>
                <%--<th>客户账号</th>--%>
                <th>书号</th>
                <th>书名</th>
                <th>数量</th>
                <th>付款金额</th>
                <th>订单状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="alog">
                <tr>
                    <%--<td><c:out value="${alog.serialNumber}"></c:out></td>--%>
                    <td><c:out value="${alog.date}"></c:out></td>
                    <%--<td><c:out value="${alog.readerId}"></c:out></td>--%>
                    <td><c:out value="${alog.bookId}"></c:out></td>
                    <td><c:out value="${alog.bookName}"></c:out></td>
                    <td><c:out value="${alog.number}"></c:out></td>
                    <td><c:out value="${alog.price}"></c:out></td>
                    <c:if test="${alog.state==0}">
                        <td>待发货</td>
                    </c:if>
                    <c:if test="${alog.state==1}">
                        <td>待接收</td>
                    </c:if>
                    <c:if test="${alog.state==2}">
                        <td>已接收</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
