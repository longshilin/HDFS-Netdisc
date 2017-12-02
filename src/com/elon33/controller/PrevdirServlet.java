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
 * Servlet implementation class PrevdirServlet
 * 上一级目录跳转处理
 */
public class PrevdirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		// session有效性检测
		if (session.getAttribute("username") == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		String PrevDirPath = "";
		String currentPath = (String) session.getAttribute("currentPath");
		System.out.println(currentPath);
		PrevDirPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
		System.out.println("%%%%%%%%%%%%" + PrevDirPath + "%%%%%%%%%%%%");
		JobConf conf = HdfsDAO.config();
		HdfsDAO hdfs = new HdfsDAO(conf);
		FileStatus[] list = hdfs.ls(PrevDirPath);
		request.setAttribute("list", list);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
