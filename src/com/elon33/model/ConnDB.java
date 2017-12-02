package com.elon33.model;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * 数据库访问模型
 * @author elon@elon33.com
 */
public class ConnDB {
	private Connection con = null;

	/**
	 * 返回数据库访问连接对象
	 * @return DB Connection
	 */
	public Connection getConn() {
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 得到连接
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hadoop?user=root&password=123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// if (con != null)
		// System.out.println("******数据库连接成功！*******");
		return con;
	}
}
