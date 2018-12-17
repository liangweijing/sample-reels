package com.hpe.entity;

public class Notice {
	private int id;
	private String title;
	private String content;
	private String times;
	
	
	
	public Notice() {
		super();
	}
	public Notice(int id, String title, String content, String times) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.times = times;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	
	
}
