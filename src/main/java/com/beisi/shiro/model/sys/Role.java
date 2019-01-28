package com.beisi.shiro.model.sys;

import java.util.List;

public class Role {
	private Integer id;
	private String name;
	private String code;
	private List<Resource> resources;

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", code=" + code + ", resources=" + resources + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// 让判断两个role对象是不是相等的规则，我们要重写，id值一样，我们就让两个role对象是相等的
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Role))
			return false;
		Role other = (Role) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
