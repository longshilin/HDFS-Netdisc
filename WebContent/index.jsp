<%@ include file="head.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.elon33.netdisc.model.HdfsDAO" %>
<%@ page import="org.apache.hadoop.fs.FileStatus" %>
<%
    String username = (String) session.getAttribute("username");
    String path = (String) session.getAttribute("currentPath"); // path=hdfs://192.168.1.101:9000/elon/集合常考点.png
    String path1 = path.substring(path.indexOf(username) - 1); //  /elon...
    //System.out.println(path.substring(path.lastIndexOf("/")+1));
%>
<body style="text-align: center; margin-bottom: 100px;">
<div class="navbar">
    <div class="navbar-inner">
        <a href="IndexPageServlet" class="brand" href="#" style="margin-left: 100px;">HDFS网盘</a>
        <ul class="nav">
            <li><a href="IndexPageServlet">首页</a></li>
            <%if (!path.substring(path.lastIndexOf("/") + 1).equals(username)) { %>
            <li><a href="PrevdirServlet">上一级目录</a></li>
            <%} %>
            <li><a href="LogoutServlet">退出</a></li>

        </ul>
    </div>
</div>

<div
        style="margin: 0px auto; text-align: left; width: 1200px; height: 40px;">
    <div style="float: left;">
        <form class="form-inline" method="POST" enctype="MULTIPART/FORM-DATA"
              action="UploadServlet">
            <div style="line-height: 50px; float: left;">
                <input type="submit" name="submit" id="btn" value="上传文件" onsubmit="return false"/>
            </div>
            &nbsp;
            <div style="line-height: 50px; float: left;">
                <input type="file" id="file1" name="file1" size="30"/>
            </div>
        </form>
    </div>
    <div style="float: right;">
        <form class="form-inline" method="POST" action="MkdirServlet">
            <div style="line-height: -200px; float: right;">
                当前路径：<%=path1%>/
                <input type="text" name="dir" size="30"/> <input type="hidden"
                                                                 name="path" value=<%=path1%>> <input type="submit"
                                                                                                      name="submit"
                                                                                                      value="新建文件夹"/>
            </div>
        </form>
    </div>
</div>


<div style="margin: 0px auto; width: 1200px; height:auto; min-height:440px; background: #fff; ">
    <table class="table table-hover"
           style="width: 1000px; margin-left: 100px;">
        <tr style="border-bottom: 2px solid #ddd">
            <td>文件名</td>
            <td style="width: 100px">类型</td>
            <td style="width: 100px;">大小(KB)</td>
            <td style="width: 100px;">操作</td>
            <td style="width: 100px;">操作</td>
        </tr>
        <%
            FileStatus[] list = (FileStatus[]) request.getAttribute("list");
            if (list != null)
                for (int i = 0; i < list.length; i++) {
        %>
        <tr style="border-bottom: 1px solid #eee">
            <%
                if (list[i].isDir()) {
                    out.print("<td> <a href=\"DocumentServlet?filePath=" + list[i].getPath() + "\">"
                            + list[i].getPath().getName() + "</a></td>");
                } else {
                    out.print("<td>" + list[i].getPath().getName() + "</td>");
                }
            %>
            <td><%=(list[i].isDir() ? "目录" : "文件")%>
            </td>
            <td><%=list[i].isDir() ? "-" : list[i].getLen() / 1024%>
            </td>
            <td><a
                    href="DeleteFileServlet?filePath=<%=java.net.URLEncoder.encode(list[i].getPath().toString(), "GB2312")%>"><img
                    width="20" height="20" style="padding-top: 3px;" src="assets/ico/delete.png"></a></td>
            <td><a
                    href="DownloadServlet?filePath=<%=java.net.URLEncoder.encode(list[i].getPath().toString(), "GB2312")%>"><img
                    width="20" height="20" src="assets/ico/download.png"></a></td>

        </tr>

        <%
                }
				/* if (list.length > 0) {
					String path_temp = list[0].toString();
					//session.setAttribute("currentPath", path_temp.substring(path_temp.indexOf("hdfs"), path_temp.lastIndexOf("/")));
				} */
        %>
    </table>

</div>

</div>
<script type="text/javascript">
    document.getElementById("btn").onclick = function () {
        if (document.getElementById("file1").value == "") {
            alert("请选择上传文件");
            document.getElementById("btn").disabled = true;
        }
        document.getElementById("file1").onclick = function () {
            document.getElementById("btn").disabled = false;
        }
    }
</script>
</body>
