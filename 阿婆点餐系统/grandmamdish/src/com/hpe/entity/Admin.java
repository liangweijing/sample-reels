package com.hpe.entity;

public class Admin {
	private String aid;
	private String adminName;
	private String adminpwd;
	private String authority;
	
	public Admin() {
		
	}
	public Admin(String id,String name,String p,String au) {
		aid=id;
		adminName=name;
		adminpwd=p;
		authority=au;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPwd() {
		return adminpwd;
	}

	public void setPwd(String pwd) {
		this.adminpwd = pwd;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
