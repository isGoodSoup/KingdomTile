package com.kingdomtile.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Entity {
	protected int worldX, worldY;
	protected int defaultSpeed = 4;
	protected int speed = defaultSpeed;
	protected int sprintSpeed = speed * 2;
	protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	protected BufferedImage idle1, idle2, idle3, idle4, idle5, idle6, idle7, idle8;
	protected String direction;
	protected String lastDirection = "down";
	protected int spriteCounter = 0;
	protected int spriteNum = 1;
	protected Rectangle solidRectangle = new Rectangle(0, 0, 48, 48);
	protected int solidRectangleX, solidRectangleY;
	protected boolean isCollisionOn = false;
	protected final static Logger log = LoggerFactory.getLogger(Entity.class);
	
	public int getX() {
		return worldX;
	}
	
	public int getY() {
		return worldY;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSprintSpeed() {
		return sprintSpeed;
	}

	public void setSprintSpeed(int sprintSpeed) {
		this.sprintSpeed = sprintSpeed;
	}

	public int getSpriteCounter() {
		return spriteCounter;
	}

	public void setSpriteCounter(int spriteCounter) {
		this.spriteCounter = spriteCounter;
	}

	public int getSpriteNum() {
		return spriteNum;
	}

	public void setSpriteNum(int spriteNum) {
		this.spriteNum = spriteNum;
	}

	public Rectangle getSolidRectangle() {
		return solidRectangle;
	}

	public int getSolidRectangleX() {
		return solidRectangleX;
	}

	public int getSolidRectangleY() {
		return solidRectangleY;
	}

	public boolean isCollisionOn() {
		return isCollisionOn;
	}

	public void setCollisionOn(boolean isCollisionOn) {
		this.isCollisionOn = isCollisionOn;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
}
