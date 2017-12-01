 <%@ include file="head.jsp"%>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@page import="org.apache.hadoop.fs.FileStatus"%>    
    
<body style="text-align:center;margin-bottom:100px;">
		 <div class="navbar" >
	     <div class="navbar-inner">
	       <a class="brand" href="#" style="margin-left:200px;">网盘</a>
	       <ul class="nav">
 
	         <li><a href="LogoutServlet">退出</a></li>

	       </ul>
	     </div>
	   </div>
	   
	   <div style="margin:0px auto; text-align:left;width:1200px; height:50px;">
	   <form class="form-inline"  method="POST"  enctype="MULTIPART/FORM-DATA"   action="UploadServlet" >
		   <div style="line-height:50px;float:left;">
		  	  <input type="submit" name="submit" value="上传文件"  />
		   </div>  
		   <div style="line-height:50px;float:left;">
		  	  <input type="file" name="file1" size="30"/>
		   </div>  
	   </form>
 
	   </div>
 
      	<div  style="margin:0px auto; width:1200px;height:500px; background:#fff">
	       
 				 
			    
   
	       
	      	<table class="table table-hover" style="width:1000px;margin-left:100px;">
	      		<tr style=" border-bottom:2px solid #ddd">
	      			<td >文件名</td><td style="width:100px">类型</td><td style="width:100px;">大小</td><td style="width:100px;">操作</td><td style="width:100px;">操作</td>
	      		</tr>
	      		<%
			
			FileStatus[] list = (FileStatus[])request.getAttribute("list");
			if(list != null)
			for (int i=0; i<list.length; i++) {
				%>
					<tr style="border-bottom:1px solid #eee">
					<%
						if(list[i].isDir())
						{
							out.print("<td> <a href=\"DocumentServlet?filePath="+list[i].getPath()+"\">"+list[i].getPath().getName()+"</a></td>");
						}else{
							out.print("<td>"+list[i].getPath().getName()+"</td>");
						}
					%>
 						<td><%= (list[i].isDir()?"目录":"文件") %></td>
						<td><%= list[i].getLen()/1024%></td>
						<td><a href="DeleteFileServlet?filePath=<%=java.net.URLEncoder.encode(list[i].getPath().toString(),"GB2312") %>">╳</a></td>
						<td><a href="DownloadServlet?filePath=<%=java.net.URLEncoder.encode(list[i].getPath().toString(),"GB2312") %>">下载</a></td>
			 		
			 		</tr >
				
			<%
			}
			%>
	      	</table>
 
 	 
      
      	</div>
      	 
</body>
 