<%@ include file="head.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<body style="text-align:center;margin-bottom:100px;">
<div class="navbar">
    <div class="navbar-inner">
        <a class="brand" href="#" style="margin-left:100px;">HDFS-Netdisc网盘</a>
    </div>
</div>
<div style="text-align:left;margin:0px auto;margin-top:50px; width:1200px;height:450px;">
    <div style="float:left;width:800px; height:100%; background:#00CC02">
        <img width="800px" src="assets/img/hadoop-bg2.jpg">
    </div>
    <div style="float:left;width:400px; height:100%; background:#00CED1; font-family:黑体;">
        <div style="padding-top:80px; margin-left:60px; "><p style="font-size: 4em">用户登陆</p></div>
        <fieldset>
            <form action="LoginServlet" method="post" class="form-horizontal"
                  style="margin-top:80px;margin-left:100px;">
                用户&nbsp;&nbsp;<input type="text" id="inputEmail" name="username">
                <br><br>
                密码&nbsp;&nbsp;<input type="password" id="inputPassword" name="password">
                <br><br>
                <button style="margin-left:30px; " type="submit" class="btn">登陆</button>&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="submit" class="btn">注册</button>
            </form>
        </fieldset>
    </div>
</div>
</body>