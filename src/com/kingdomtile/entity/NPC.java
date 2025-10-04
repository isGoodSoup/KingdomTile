package com.kingdomtile.entity;

import java.awt.image.BufferedImage;

public abstract class NPC extends Entity {
	protected String name;
	protected int age;
	protected int gender;
	protected BufferedImage image;
	protected String role;
	protected String disposition;
	
	public NPC() {}
	
	public NPC(String name, int age, int gender, BufferedImage image, String role, String disposition) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.image = image;
		this.role = role;
		this.disposition = disposition;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getGender() {
		return gender;
	}
	
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getDisposition() {
		return disposition;
	}
	
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	
	public abstract void getSprite();
}
