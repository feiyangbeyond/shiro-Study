package com.beisi.shiro.model.sys;

import java.io.Serializable;
import java.util.Date;

public class Permission implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String pname;
	private String pathUrl;
	private Integer sortNum;// 权限过滤排序
	private Date addDate;
	private Date upDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPathUrl() {
		return pathUrl;
	}

	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
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

	@Override
	public String toString() {
		return "Permission [id=" + id + ", pname=" + pname + ", pathUrl=" + pathUrl + ", sortNum=" + sortNum
				+ ", addDate=" + addDate + ", upDate=" + upDate + "]";
	}

}
