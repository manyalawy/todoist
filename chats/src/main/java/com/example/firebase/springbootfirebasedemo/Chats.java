package com.example.firebase.springbootfirebasedemo;

public class Chats {

	private static  String title;
	private int id;
	private String body;
	public Chats() {}
	public Chats(String title, int id, String body) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.id = id;
	    this.body = body;
	}

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
	public String toString(){
		return "Chat is : "+ this.title + " " + this.id+" "+this.body;
	}
	
//	public static void main(String[] args) {
//		Chats c = new Chats("asda",1,"isb");
//		System.out.println(c);
//	}
	
}
