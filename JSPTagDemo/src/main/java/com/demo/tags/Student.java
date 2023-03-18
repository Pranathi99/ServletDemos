package com.demo.tags;

public class Student {
private String fname,lname;
private boolean above90Percent;

public Student(String fname, String lname, boolean above90Percent) {
	super();
	this.fname = fname;
	this.lname = lname;
	this.above90Percent = above90Percent;
}
public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}
public String getLname() {
	return lname;
}
public void setLname(String lname) {
	this.lname = lname;
}
public boolean isAbove90Percent() {
	return above90Percent;
}
public void setAbove90Percent(boolean above90Percent) {
	this.above90Percent = above90Percent;
}
}
