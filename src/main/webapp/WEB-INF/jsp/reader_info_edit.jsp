<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="col-xs-5 col-md-offset-3">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                信息修改
            </h3>
        </div>
        <div class="panel-body">
            <form action="reader_edit_do_r.html" method="post" id="edit" >

                <div class="input-group">
                    <span  class="input-group-addon">读者证号</span>
                    <input type="text" readonly="readonly" class="form-control" name="readerId" id="readerId" value="${readerinfo.readerId}">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">姓名</span>
                    <input type="text" class="form-control" name="name" id="name" value="${readerinfo.name}" >
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
                    <input type="text" class="form-control" name="year" id="year"  value="${readerinfo.year}" >
                    <span class="input-group-addon">月</span>
                    <input type="text" class="form-control" name="month" id="month"  value="${readerinfo.month}" >
                    <span class="input-group-addon">日</span>
                    <input type="text" class="form-control" name="day" id="day"  value="${readerinfo.day}" >
                </div>

                <div class="input-group">
                    <span  class="input-group-addon">地址</span>
                    <input type="text" class="form-control" name="address" id="address"  value="${readerinfo.address}" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">电话</span>
                    <input type="text" class="form-control" name="telcode" id="telcode"  value="${readerinfo.telcode}" >
                </div>
                <br/>
                <a class="btn btn-success btn-sm" href="/reader_info.html" role="button">返回</a>
                <input type="submit" value="确定" class="btn btn-success btn-sm" class="text-left">
                <script>
                    function mySubmit(flag){
                        return flag;
                    }
                    $("#edit").submit(function () {
                        if($("#name").val()==''||$("#sex").val()==''||$("#birth").val()==''||$("#address").val()==''||$("#telcode").val()==''){
                            alert("请填入完整用户信息！");
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
