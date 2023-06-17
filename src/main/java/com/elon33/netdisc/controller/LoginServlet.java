package com.elon33.netdisc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.mapred.JobConf;

import com.elon33.netdisc.model.*;

/**
 * Servlet implementation class LoginServlet
 * 处理登录的Servlet
 */
public class LoginServlet extends HttpServlet {

    /**
     * 处理GET请求
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * 处理POST请求
     *
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserBeanCl ubc = new UserBeanCl();
        if (ubc.checkUser(username, password)) {
            //用户合法，跳转到界面
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            String path = HdfsDAO.getHdfs() + "/" + username;
            session.setAttribute("currentPath", path);
            JobConf conf = HdfsDAO.config();
            HdfsDAO hdfs = new HdfsDAO(conf);
            String folder = "/" + username;
            FileStatus[] list = hdfs.ls(folder);
            request.setAttribute("list", list);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            //用户不合法，调回登录界面，并提示错误信息
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
