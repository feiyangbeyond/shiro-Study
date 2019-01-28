package com.beisi.shiro.model.sys;

import java.util.Date;
import java.util.List;


public class User { // User的名称必须和数据表里的表名称t_user
	private String id;// 出了名字对应以为，坑：int，id，id将获取不到值,整数类型不能用int，要用Integer
	private String username;
	private String password;
	private String email;
	private String phone;
	private Integer enable;// 0未激活，1激活
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date addDate;// 创建日期
	private Date upDate;
	private List<Role> roles; // 角色信息
	
	
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getEnable() {
		return enable;
	}
	public void setEnable(Integer enable) {
		this.enable = enable;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Date getUpDate() {
		return upDate;
	}
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", phone="
				+ phone + ", enable=" + enable + ", addDate=" + addDate + ", upDate=" + upDate + ", roles=" + roles
				+ "]";
	}
}
