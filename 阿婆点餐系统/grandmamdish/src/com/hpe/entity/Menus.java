package com.hpe.entity;

public class Menus {
	public int id;
	private String name;
	private int typeid;
	private String burden;
	private String brief;
	private String price;
	private String sums;
	private String pricel;
	private String sumsl;
	private String imgPath;
	private String typeName;
	//加一个属性typename,方法一：两个数据表连接查询
	//方法二：二次查询
	
	
	public Menus(int id, String name, int typeid, String burden, String brief, String price, String sums, String pricel,
			String sumsl, String imgPath, String typeName) {
		super();
		this.id = id;
		this.name = name;
		this.typeid = typeid;
		this.burden = burden;
		this.brief = brief;
		this.price = price;
		this.sums = sums;
		this.pricel = pricel;
		this.sumsl = sumsl;
		this.imgPath = imgPath;
		this.typeName = typeName;
	}
	public Menus() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getBurden() {
		return burden;
	}
	public void setBurden(String burden) {
		this.burden = burden;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSums() {
		return sums;
	}
	public void setSums(String sums) {
		this.sums = sums;
	}
	public String getPricel() {
		return pricel;
	}
	public void setPricel(String pricel) {
		this.pricel = pricel;
	}
	public String getSumsl() {
		return sumsl;
	}
	public void setSumsl(String sumsl) {
		this.sumsl = sumsl;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	
	
	
}
