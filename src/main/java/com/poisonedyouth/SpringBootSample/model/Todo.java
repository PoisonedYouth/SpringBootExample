package com.poisonedyouth.SpringBootSample.model;

import javax.validation.constraints.Size;
import java.util.Date;

public class Todo {
	private int id;
	private String user;
	@Size(min = 10, message = "Enter at least 10 Characters!")
	private String desc;
	private Date targetDate;
	private boolean isDone;

	public Todo(int id, String user, String desc, Date targetDate, boolean isDone) {
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}

	public Todo() {
		this.id = 0;
		this.user = "";
		this.desc = "";
		this.targetDate = new Date();
		this.isDone = false;
	}

	public int getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public String getDesc() {
		return desc;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public void setDone(boolean done) {
		isDone = done;
	}

	@Override
	public String toString() {
		return "Todo{" + "id=" + id + ", user='" + user + '\'' + ", desc='" + desc + '\'' + ", targetDate=" + targetDate + ", isDone=" + isDone
				+ '}';
	}
}
