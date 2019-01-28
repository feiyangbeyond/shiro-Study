package com.beisi.shiro.model.sys;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Role {
	private String id;
	private String rname;
	private String rcode;
	private Date addDate;
	private Date upDate;
	private Set<Permission> permission;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
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

	public Set<Permission> getPermission() {
		return permission;
	}

	public void setPermission(Set<Permission> permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", rname=" + rname + ", rcode=" + rcode + ", addDate=" + addDate + ", upDate="
				+ upDate + ", permission=" + permission + "]";
	}

//	@Override
//	public boolean equals(Object obj) {
//		// 让判断两个role对象是不是相等的规则，我们要重写，id值一样，我们就让两个role对象是相等的
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (!(obj instanceof Role))
//			return false;
//		Role other = (Role) obj;
//		if (id != other.id)
//			return false;
//		return true;
//	}
}
