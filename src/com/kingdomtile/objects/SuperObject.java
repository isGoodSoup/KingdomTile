package com.kingdomtile.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.kingdomtile.main.Panel;

public class SuperObject {
	protected String name;
	protected boolean collision = false;
	protected BufferedImage image;
	protected BufferedImage chest1, chest2, door1, door2;
	protected BufferedImage sword1, sword2;
	
	protected int worldX, worldY;
	protected int screenX, screenY;
	protected Rectangle solidRectangle = new Rectangle(0, 0, 48, 48);
	protected int solidRectangleX = 0;
	protected int solidRectangleY = 0;
	
	public SuperObject() {}
	
	public SuperObject(String name, boolean collision, 
			BufferedImage image, int worldX, int worldY, 
			int screenX, int screenY) {
		super();
		this.name = name;
		this.collision = collision;
		this.image = image;
		this.worldX = worldX;
		this.worldY = worldY;
		this.screenX = screenX;
		this.screenY = screenY;
	}

	public void draw(Graphics2D g2, Panel panel) {
		screenX = worldX - panel.getPlayer().getWorldX() + panel.getPlayer().getScreenX();
		screenY = worldY - panel.getPlayer().getWorldY() + panel.getPlayer().getScreenY();
		
		if(worldX + panel.getTileSize() > panel.getTileSize() - panel.getPlayer().getScreenX() && 
		   worldX - panel.getTileSize() < panel.getPlayer().getWorldX() + panel.getPlayer().getScreenX() && 
		   worldY + panel.getTileSize() > panel.getPlayer().getWorldY() - panel.getPlayer().getScreenY() && 
		   worldY - panel.getTileSize() < panel.getPlayer().getWorldY() + panel.getPlayer().getScreenY()) {
			g2.drawImage(image, screenX, screenY, panel.getTileSize(), panel.getTileSize(), null);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getWorldX() {
		return worldX;
	}

	public void setWorldX(int worldX) {
		this.worldX = worldX;
	}

	public int getWorldY() {
		return worldY;
	}

	public void setWorldY(int worldY) {
		this.worldY = worldY;
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
}
