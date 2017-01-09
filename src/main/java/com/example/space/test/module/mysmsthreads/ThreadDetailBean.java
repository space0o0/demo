package com.example.space.test.module.mysmsthreads;

public class ThreadDetailBean {
	private String phone;
	private String date;
	private String content;
	private int layoutId;

	public ThreadDetailBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ThreadDetailBean(String phone, String date, String content,
			int layoutId) {
		super();
		this.phone = phone;
		this.date = date;
		this.content = content;
		this.layoutId = layoutId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(int layoutId) {
		this.layoutId = layoutId;
	}

}
