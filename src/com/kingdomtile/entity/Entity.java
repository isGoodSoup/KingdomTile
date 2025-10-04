package com.kingdomtile.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kingdomtile.interfaces.NPC;
import com.kingdomtile.main.Panel;

public abstract class Entity implements NPC {
	protected Panel panel;
	protected int worldX, worldY;
	protected int screenX, screenY;
	protected int defaultSpeed = 4;
	protected int speed = defaultSpeed;
	protected int sprintSpeed = speed * 2;
	
	protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	protected BufferedImage idle1, idle2, idle3, idle4, idle5, idle6, idle7, idle8;
	protected BufferedImage image;
	
	protected String direction;
	protected String lastDirection = "down";
	protected int spriteCounter = 0;
	protected int spriteNum = 1;
	protected Rectangle solidRectangle = new Rectangle(0, 0, 48, 48);
	protected int solidRectangleX, solidRectangleY;
	protected boolean isCollisionOn = false;
	protected int actionCounter = 0;
	protected final static Logger log = LoggerFactory.getLogger(Entity.class);
	
	public Entity(Panel panel) {
		this.panel = panel;
	}
	
	public void action() {}
	
	public void update() {
		action();
		
		isCollisionOn = false;
		panel.getCollision().checkTile(this);
		
		if(!isCollisionOn) {
			switch(direction) {
			case "up": 
				worldY -= speed;
				lastDirection = "up";
				break;
				
			case "down": 
				worldY += speed; 
				lastDirection = "down";
				break;
				
			case "left": 
				worldX -= speed; 
				lastDirection = "left";
				break;
				
			case "right": 
				worldX += speed; 
				lastDirection = "right";
				break;
			}
		}
		
		spriteCounter++;
		if (spriteCounter > 12) {
		    spriteNum++;
		    if (spriteNum > 2) {
		        spriteNum = 1;
		    }
		    spriteCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
		screenX = worldX - panel.getPlayer().getWorldX() + panel.getPlayer().getScreenX();
		screenY = worldY - panel.getPlayer().getWorldY() + panel.getPlayer().getScreenY();
		
		if(worldX + panel.getTileSize() > panel.getTileSize() - panel.getPlayer().getScreenX() && 
		   worldX - panel.getTileSize() < panel.getPlayer().getWorldX() + panel.getPlayer().getScreenX() && 
		   worldY + panel.getTileSize() > panel.getPlayer().getWorldY() - panel.getPlayer().getScreenY() && 
		   worldY - panel.getTileSize() < panel.getPlayer().getWorldY() + panel.getPlayer().getScreenY()) {
			
			image = null;
			switch(direction) {
				case "idle": 
					switch(lastDirection) {
			            case "up":
			                image = (spriteNum == 1) ? idle3 : idle4;
			                break;
			                
			            case "down":
			                image = (spriteNum == 1) ? idle1 : idle2;
			                break;
			                
			            case "left":
			                image = (spriteNum == 1) ? idle5 : idle6;
			                break;
			                
			            case "right":
			                image = (spriteNum == 1) ? idle7 : idle8;
			                break;
					}
					break;
				case "up":
					if(spriteNum == 1) image = up1;
					if(spriteNum == 2) image = up2;
					break;
					
				case "down":
					if(spriteNum == 1) image = down1;
					if(spriteNum == 2) image = down2;
					break;
					
				case "left":
					if(spriteNum == 1) image = left1;
					if(spriteNum == 2) image = left2;
					break;
					
				case "right":
					if(spriteNum == 1) image = right1;
					if(spriteNum == 2) image = right2;
					break;
			}
			g2.drawImage(image, screenX, screenY, panel.getTileSize(), panel.getTileSize(), null);
		}
	}
	
	public int getX() {
		return worldX;
	}

	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}

	public int getY() {
		return worldY;
	}

	public void setWorldY(int worldY) {
		this.worldY = worldY;
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
