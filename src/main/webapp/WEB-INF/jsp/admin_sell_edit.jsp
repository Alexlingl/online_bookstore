<%--
  Created by IntelliJ IDEA.
  User: 君行天下
  Date: 2017/7/31
  Time: 8:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑 ${publish.publishName}》 的信息</title>
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
<nav  style="position:fixed;z-index: 999;width: 100%;background-color: #fff" class="navbar navbar-default" role="navigation" >
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand" href="admin_main.html">小熊书屋</a>
        </div>
        <div class="collapse navbar-collapse" >
            <ul class="nav navbar-nav navbar-left">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        图书管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="allbooks.html">全部图书</a></li>
                        <li class="divider"></li>
                        <li><a href="book_add.html">增加图书</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        读者管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="allreaders.html">全部读者</a></li>
                        <li class="divider"></li>
                        <li><a href="reader_add.html">增加读者</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        订单管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/selllist.html">全部订单</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        出版社管理
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="adminallpublish.html">全部出版社</a></li>
                        <li class="divider"></li>
                        <li><a href="publish_add.html">增加出版社</a></li>
                    </ul>
                </li>
                <li >
                    <a href="admin_repasswd.html" >
                        密码修改
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;${admin.adminId}，已登录</a></li>
                <li><a href="logout.html"><span class="glyphicon glyphicon-log-in"></span>&nbsp;退出</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="col-xs-6 col-md-offset-3" style="position: relative;top: 10%">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">编辑${sell.serialNumber}号订单的信息</h3>
        </div>
        <div class="panel-body">
            <form action="sell_edit_do.html?serialNumber=${sell.serialNumber}" method="post" id="selledit" >
                <div class="input-group">
                    <span class="input-group-addon">订单日期</span>
                    <input type="text" readonly="readonly" class="form-control" name="date" id="date" value="${sell.date}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">客户账号</span>
                    <input type="text" readonly="readonly" class="form-control" name="readerId" id="readerId"  value="${sell.readerId}" onkeyup="this.value=this.value.replace(/[^\d]/g,'') ">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">书籍编号</span>
                    <input type="text" class="form-control" name="bookId" id="bookId"  value="${sell.bookId}" onkeyup="this.value=this.value.replace(/[^\d]/g,'') ">
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">数量</span>
                    <input type="text" class="form-control" name="number" id="number"  value="${sell.number}" onkeyup="this.value=this.value.replace(/[^\d]/g,'') ">
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">订单状态(0表示待发货,1表示待接收,2表示已接收)</span>
                    <input type="text" class="form-control" name="state" id="state"  value="${sell.state}" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " maxlength="1">
                </div>
                <a class="btn btn-success btn-sm" href="/selllist.html" role="button">返回</a>
                <input type="submit" value="确定" class="btn btn-success btn-sm" class="text-left">
                <script>
                    function mySubmit(flag){
                        return flag;
                    }
                    $("#readeredit").submit(function () {
                        if($("#date").val()==''||$("#readerId").val()==''||$("#number").val()==''||$("#bookId").val()==''||$("#state").val()==''){
                            alert("请填入完整的订单信息！");
                            return mySubmit(false);
                        }
                    })
                </script>
            </form>
        </div>
    </div>

</div>

</body>
</html>
