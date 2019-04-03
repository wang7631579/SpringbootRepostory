package com.example.demo.entity;

import java.io.Serializable;

public class Teacher   implements  Serializable{
	private String tname;
	private String tage;

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTage() {
		return tage;
	}

	public void setTage(String tage) {
		this.tage = tage;
	}

}
