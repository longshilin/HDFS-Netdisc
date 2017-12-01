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
import com.sun.security.ntlm.*;

/**
 * Servlet implementation class DeleteFileServlet
 */
public class DeleteFileServlet extends HttpServlet {
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		HttpSession session = request.getSession(); 
		String username = (String) session.getAttribute("username");
		String filePath = new String(request.getParameter("filePath").getBytes("ISO-8859-1"),"GB2312");
		
 		JobConf conf = HdfsDAO.config();
        HdfsDAO hdfs = new HdfsDAO(conf);
        hdfs.rmr(filePath);
        System.out.println("===="+filePath+"====");
         //FileStatus[] list = hdfs.ls("/user/root/");
        FileStatus[] list = hdfs.ls("/"+username);
        request.setAttribute("list",list);
		request.getRequestDispatcher("index.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
