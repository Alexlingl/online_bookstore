<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>全部图书信息</title>
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

<div class="panel panel-default" style="width: 90%;margin-left: 5%">
    <div class="panel-heading" style="background-color: #fff">
        <h3 class="panel-title">
            热销榜
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>排名</th>
                <th>书名</th>
                <th>作者</th>
                <th>译者</th>
                <th>出版社信息</th>
                <th>ISBN</th>
                <th>价格</th>
                <th>会员特价</th>
                <th>库存数量</th>
                <th>详情</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book" varStatus="idxBooks">
                <tr>
                    <td><c:out value="${idxBooks.count}"></c:out></td>
                    <td><c:out value="${book.name}"></c:out></td>
                    <td><c:out value="${book.author}"></c:out></td>
                    <td><c:out value="${book.translator}"></c:out></td>
                    <td><a href="readerpublishdetail.html?publishId=<c:out value="${book.publishId}"></c:out>"><button type="button" class="btn btn-success btn-xs">出版社信息</button></a></td>
                    <td><c:out value="${book.isbn}"></c:out></td>
                    <td><c:out value="${book.price}"></c:out></td>
                    <td><c:out value="${book.vipPrice}"></c:out></td>
                    <c:if test="${book.state>0}">
                        <td>库存有货</td>
                    </c:if>
                    <c:if test="${book.state==0}">
                        <td>已售完</td>
                    </c:if>
                    <td><a href="readerbookdetail.html?bookId=<c:out value="${book.bookId}"></c:out>"><button type="button" class="btn btn-success btn-xs">详情</button></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
