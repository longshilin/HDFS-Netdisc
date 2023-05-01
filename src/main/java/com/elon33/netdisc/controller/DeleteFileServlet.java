package com.elon33.netdisc.controller;

import java.io.IOException;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.mapred.JobConf;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elon33.netdisc.model.HdfsDAO;

/**
 * Servlet implementation class DeleteFileServlet
 * 删除文件的Servlet
 */
public class DeleteFileServlet extends HttpServlet {

    /**
     * 处理GET请求
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        // 获取当前用户的用户名
        String username = (String) session.getAttribute("username");
        // 获取要删除的文件路径，并将其从ISO-8859-1编码转换为UTF-8编码
        String filePath = new String(request.getParameter("filePath").getBytes("iso8859-1"), "utf-8");

        // 配置Hadoop JobConf
        JobConf conf = HdfsDAO.config();
        // 创建HdfsDAO实例
        HdfsDAO hdfs = new HdfsDAO(conf);
        // 删除指定的文件或目录
        hdfs.rmr(filePath);
        // System.out.println("===="+filePath+"====");
        // FileStatus[] list = hdfs.ls("/user/root/");
        String currentPath = (String) session.getAttribute("currentPath");
        // 获取当前路径下的文件列表
        FileStatus[] list = hdfs.ls(currentPath);
        // 将文件列表设置为请求属性，用于在JSP页面中显示
        request.setAttribute("list", list);
        // 转发到index.jsp页面进行显示
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * 处理POST请求，直接调用doGet方法进行处理
     *
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

}
