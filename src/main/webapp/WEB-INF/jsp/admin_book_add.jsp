<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书信息添加</title>
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
            <h3 class="panel-title">添加图书</h3>
        </div>
        <div class="panel-body">
            <form action="book_add_do.html" method="post" id="addbook" accept-charset="UTF-8">
                <div class="input-group">
                    <span  class="input-group-addon">书名</span>
                    <input type="text" class="form-control" name="name" id="name">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">作者（多个人名之间请用“；”分割）</span>
                    <input type="text" class="form-control" name="author" id="author" >
                </div>
                <div class="input-group">
                    <span class="input-group-addon">译者（多个人名之间请用“；”分割）</span>
                    <input type="text" class="form-control" name="translator" id="translator" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">出版社编号</span>
                    <input type="text" class="form-control" name="publishId" id="publishId" onkeyup="this.value=this.value.replace(/[^\d]/g,'') ">
                </div>
                <div class="input-group">
                    <span class="input-group-addon">ISBN</span>
                    <input type="text" class="form-control" name="isbn" id="isbn" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">简介</span>
                    <input type="text" class="form-control" name="introduction" id="introduction" >
                </div>
                <div class="input-group">
                    <span class="input-group-addon">语言</span>
                    <input type="text" class="form-control" name="language" id="language" onkeyup="this.value=this.value.replace(/[^\u4e00-\u9fa5a-zA-Z0-9\w]/g,'')">
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">普通价格</span>
                    <input type="text" class="form-control" name="price"  id="price" >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">VIP特价</span>
                    <input type="text" class="form-control" name="vipPrice"  id="vipPrice" >
                </div>
                <div class="input-group">
                    <span class="input-group-addon">出版日期</span>
                    <span class="input-group-addon">年</span>
                    <input type="text" class="form-control" name="year" id="year" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " maxlength="4">
                    <span class="input-group-addon">月</span>
                    <input type="text" class="form-control" name="month" id="month" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " maxlength="2">
                    <span class="input-group-addon">日</span>
                    <input type="text" class="form-control" name="day" id="day" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " maxlength="2">
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">分类号</span>
                    <input type="text" class="form-control" name="classId" id="classId" >
                </div>
                <div class="input-group">
                    <span class="input-group-addon">书架号</span>
                    <input type="text" class="form-control" name="pressmark" id="pressmark" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " >
                </div>
                <div class="input-group">
                    <span  class="input-group-addon">库存数量</span>
                    <input type="text" class="form-control" name="state"  id="state" onkeyup="this.value=this.value.replace(/[^\d]/g,'') ">
                </div>
                <input type="submit" value="确定" class="btn btn-success btn-sm" class="text-left">
                <script>
                    function mySubmit(flag){
                        return flag;
                    }
                    $("#addbook").submit(function () {
                        if($("#name").val()==''||$("#author").val()==''||$("#publish").val()==''||$("#isbn").val()==''||$("#introduction").val()==''||$("#language").val()==''||$("#price").val()==''||$("#pubdate").val()==''||$("#classId").val()==''||$("#pressmark").val()==''||$("#state").val()==''){
                            alert("请填入完整图书信息！");
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
