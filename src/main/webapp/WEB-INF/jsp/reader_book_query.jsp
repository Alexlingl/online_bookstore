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


<div style="padding: 30px 550px 10px">
    <form   method="post" action="reader_querybook_do.html" class="form-inline"  id="searchform">
        <div class="input-group">
            <input type="text" placeholder="输入图书号或图书名" class="form-control" id="search" name="searchWord" class="form-control">
            <span class="input-group-btn">
                            <input type="submit" value="搜索" class="btn btn-default">
            </span>
        </div>
    </form>
    <script>
        function mySubmit(flag){
            return flag;
        }
        $("#searchform").submit(function () {
            var val=$("#search").val();
            if(val==''){
                alert("请输入关键字");
                return mySubmit(false);
            }
        })
    </script>
</div>
<div style="position: relative;top: 10%">
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
</div>
<c:if test="${!empty books}">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                查询结果：
            </h3>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>书名</th>
                    <th>作者</th>
                    <th>译者</th>
                    <th>出版社</th>
                    <th>ISBN</th>
                    <th>价格</th>
                    <th>会员特价</th>
                    <th>状态</th>
                    <th>详情</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td><c:out value="${book.name}"></c:out></td>
                        <td><c:out value="${book.author}"></c:out></td>
                        <td><c:out value="${book.translator}"></c:out></td>
                        <%--<td><c:out value="${book.publishId}"></c:out></td>--%>
                        <td><a href="readerpublishdetail.html?publishId=<c:out value="${book.publishId}"></c:out>&searchWord=<c:out value="${book.name}"></c:out>"><button type="button" class="btn btn-success btn-xs">出版社信息</button></a></td>
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
</c:if>


</body>
</html>
