<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑读者信息《 ${readerInfo.readerId}》</title>
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
            <h3 class="panel-title">编辑读者信息《 ${readerInfo.readerId}》</h3>
        </div>
        <div class="panel-body">
            <form action="reader_edit_do.html?id=${readerInfo.readerId}" method="post" id="readeredit" >

                <div class="input-group">
                    <span  class="input-group-addon">读者证号</span>
                    <input readonly="readonly" type="text" class="form-control" name="readerId" id="readerId" value="${readerInfo.readerId}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">姓名</span>
                    <input type="text" class="form-control" name="name" id="name" value="${readerInfo.name}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">性别</span>
                    <select type="text" class="form-control" name="sex" id="sex">
                        <c:if test="${readerInfo.sex=='男'}">
                            <option value="男" selected="selected">男</option>
                            <option value="女">女</option>
                        </c:if>
                        <c:if test="${readerInfo.sex=='女'}">
                            <option value="男">男</option>
                            <option value="女" selected="selected">女</option>
                        </c:if>
                    </select>
                </div>
                <div class="input-group">
                    <span class="input-group-addon">生日</span>
                    <span class="input-group-addon">年</span>
                    <input type="text" class="form-control" name="year" id="year"  value="${readerInfo.year}" >
                    <span class="input-group-addon">月</span>
                    <input type="text" class="form-control" name="month" id="month"  value="${readerInfo.month}" >
                    <span class="input-group-addon">日</span>
                    <input type="text" class="form-control" name="day" id="day"  value="${readerInfo.day}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">地址</span>
                    <input type="text" class="form-control" name="address" id="address"  value="${readerInfo.address}" >
                </div>
                <div class="input-group">
                    <span class="input-group-addon">电话</span>
                    <input type="text" class="form-control" name="telcode" id="telcode" value="${readerInfo.telcode}" >
                </div>
                <div class="input-group">
                    <span class="input-group-addon">会员信息(0表示会员，1表示普通用户)</span>
                    <input type="text" class="form-control" name="vipState" id="vipState" value="${vipState}" >
                </div>
                <a class="btn btn-success btn-sm" href="/allreaders.html" role="button">返回</a>
                <input type="submit" value="确定" class="btn btn-success btn-sm" class="text-left">
                <script>
                    function mySubmit(flag){
                        return flag;
                    }
                    $("#readeredit").submit(function () {
                        if($("#name").val()==''||$("#author").val()==''||$("#publish").val()==''||$("#isbn").val()==''||$("#introduction").val()==''||$("#language").val()==''||$("#price").val()==''||$("#pubdate").val()==''||$("#classId").val()==''||$("#pressmark").val()==''||$("#state").val()==''){
                            alert("请填入完整读者信息！");
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
