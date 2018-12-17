package com.hpe.entity;

public class MenusOrder {
	private int id;
	private int menuid;
	private int userid;
	private String menunum;
	private String times;
	private String delivery;
	private String menuname;
	private String realname;
	private String phone;
	private String address;
	private String price;
	
	
	
	
	public MenusOrder() {
		super();
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public MenusOrder(int id, int menuid, int userid, String menunum, String times, String delivery, String menuname,
			String realname, String phone, String address, String price) {
		super();
		this.id = id;
		this.menuid = menuid;
		this.userid = userid;
		this.menunum = menunum;
		this.times = times;
		this.delivery = delivery;
		this.menuname = menuname;
		this.realname = realname;
		this.phone = phone;
		this.address = address;
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMenuid() {
		return menuid;
	}
	public void setMenuid(int menuid) {
		this.menuid = menuid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getMenunum() {
		return menunum;
	}
	public void setMenunum(String menunum) {
		this.menunum = menunum;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	
	
	
}
