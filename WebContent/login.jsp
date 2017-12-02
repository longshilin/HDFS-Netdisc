 <%@ include file="head.jsp"%>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<head>
  <title>HDFS网盘系统</title>
</head>  
<body style="text-align:center;margin-bottom:100px;">
	<div class="navbar" >
	   <div class="navbar-inner">
	     <a class="brand" href="#" style="margin-left:200px;">网盘</a>
	  </div>
	</div>
	<div  style="text-align:left;margin:0px auto;margin-top:50px; width:1200px;height:500px;">
		<div style="float:left;width:800px; height:100%;background:#009900"></div>
		<div style="float:left;width:400px; height:100%; background:#00CC33">
			<fieldset>
				<form  action="LoginServlet" method="post" class="form-horizontal" style="margin-top:150px;margin-left:100px;">
				          用户&nbsp;&nbsp;<input type="text" id="inputEmail" name="username" >
				<br><br>
					密码&nbsp;&nbsp;<input type="password" id="inputPassword"  name="password">
				<br><br>
					<button type="submit" class="btn">登陆</button>&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="submit" class="btn">注册</button>
				</form>
			</fieldset>
		</div>
  	</div>
</body>
 