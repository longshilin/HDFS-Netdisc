package com.elon33.netdisc.model;

/**
 * 用户Bean对象模型
 * 
 * @author elon@elon33.com
 *
 */
public class UserBean {
	String id; // 用户id
	String username; // 用户名
	String email; // 用户email
	String password; // 用户密码

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}

}
