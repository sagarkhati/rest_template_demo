package com.ssk.rest_template_demo.models;

public class Posts {

	private int id;
	private String title;
	private String body;
	private int userId;

	public Posts() {
		super();
	}

	public Posts(int id, String title, String body, int userId) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.userId = userId;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
