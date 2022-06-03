package com.example.firebase.springbootfirebasedemo;


public class Notifications {
	public Notifications() {}
	public Notifications(String title, int id, String body) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.id = id;
		this.body = body;
	}
	private static  String title;
	private int id;
	private String body;
	public static  String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}
