package com.elon33.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.mapred.JobConf;

import com.elon33.model.HdfsDAO;

/**
 * Servlet implementation class MkdirServlet
 * 创建文件夹的控制器
 */
public class MkdirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		String path1 = (String)session.getAttribute("currentPath");
		String path2 = (String)request.getParameter("dir");
		
		//调用hdfs的mkdir方法创建目录
		JobConf conf = HdfsDAO.config();
        HdfsDAO hdfs = new HdfsDAO(conf);
        hdfs.mkdirs(path1+"/"+path2);
        
        FileStatus[] list = hdfs.ls(path1);
		request.setAttribute("list",list);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
