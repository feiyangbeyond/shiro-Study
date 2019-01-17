package com.beisi.shiro.model;

public class Permission {
	private Integer id;
	private String pname;
	private String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", pname=" + pname + ", url=" + url + "]";
	}

}
