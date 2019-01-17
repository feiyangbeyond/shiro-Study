package com.beisi.shiro.model;

public class UserRole {
	private Integer id;
	private Integer rid;
	private Integer uid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", rid=" + rid + ", uid=" + uid + "]";
	}
	
}
