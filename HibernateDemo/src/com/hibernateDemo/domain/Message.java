package com.hibernateDemo.domain;

public class Message {
	public Message() {
		super();
	}
	public Message(String text) {
		super();
		this.text = text;
	}
	private int id;
	private String text;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
