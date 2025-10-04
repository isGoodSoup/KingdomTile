package com.kingdomtile.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kingdomtile.main.Input;
import com.kingdomtile.main.Panel;
import com.kingdomtile.objects.SuperObject;

public class Player extends Entity {
	private Panel panel;
	private Input key;
	private BufferedImage image;
	private boolean isSprinting = false;
	private boolean shiftJustPressed = false;
	private final int screenX;
	private final int screenY;
	private int livesCounter = 4;
	private int keyCounter = 0;
	private int coinCounter = 0;
	private boolean isSwordHeld = false;
	private final static Logger log = LoggerFactory.getLogger(Player.class);
	
	public Player(Panel panel, Input key) {
		super(panel);
		this.panel = panel;
		this.key = key;
		screenX = panel.getScreenHeight()/2 - (panel.getTileSize()/2);
		screenY = panel.getScreenWidth()/2 - (panel.getTileSize()/2);
		solidRectangle = new Rectangle(8, 16, 32, 32);
		solidRectangleX = solidRectangle.x;
		solidRectangleY = solidRectangle.y;
		defaultValues();
		getPlayerImage();
		log.debug("Player initiated");
	}
	
	public void spawnpoint() {
		worldX = panel.getTileSize() * 23;
		worldY = panel.getTileSize() * 21;
	}
	
	public void defaultValues() {
		spawnpoint();
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		String defaultPath = "/com/kingdomtile/player/";
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-down_01.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-down_02.png"));
			idle1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-idle_01.png"));
			idle2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-idle_02.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-up_01.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-up_02.png"));
			idle3 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-up_idle_01.png"));
			idle4 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-up_idle_02.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-left_01.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-left_02.png"));
			idle5 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-left_idle_01.png"));
			idle6 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-left_idle_02.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-right_01.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-right_02.png"));
			idle7 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-right_idle_01.png"));
			idle8 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "mage-right_idle_02.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void getSprite() {}
	
	@Override
	public void action() {
		String newDirection = null;

	    if(key.isUpPressed()) newDirection = "up";
	    else if(key.isDownPressed()) newDirection = "down";

	    if(key.isLeftPressed()) newDirection = "left";
	    else if(key.isRightPressed()) newDirection = "right";

	    if(newDirection != null) {
	        direction = newDirection;
	        lastDirection = newDirection;
	    } else {
	        direction = "idle";
	    }
	}
	
	@Override
	public void update() {
		action();
		updateSprint();
		isCollisionOn = false;
		panel.getCollision().checkTile(this);
		
		int objectIndex = panel.getCollision().checkObject(this, true);
		pickUp(objectIndex);
		
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
	
	public void updateSprint() {
	    if (key.isShiftPressed()) {
	        if (!shiftJustPressed) {
	            isSprinting = !isSprinting;
	            shiftJustPressed = true;
	        }
	    } else {
	        shiftJustPressed = false;
	    }
	    speed = isSprinting ? sprintSpeed : defaultSpeed;
	}
	
	public void draw(Graphics2D g2) {
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
	
	public void pickUp(int i) {
	    if (i == 999) return;
	    SuperObject obj = panel.getObject()[i];
	    if (obj != null) obj.onPickup(this, i);
	}
	
	public void nullifyObject(int i) {
		panel.getObject()[i] = null;
	}

	public BufferedImage getImage() {
		return image;
	}

	public int getScreenX() {
		return screenX;
	}

	public int getScreenY() {
		return screenY;
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

	public boolean isSprinting() {
		return isSprinting;
	}

	public boolean isShiftJustPressed() {
		return shiftJustPressed;
	}

	public int getLivesCounter() {
		return livesCounter;
	}

	public void setLivesCounter(int livesCounter) {
		this.livesCounter = livesCounter;
	}

	public int getKeyCounter() {
		return keyCounter;
	}

	public void setKeyCounter(int keyCounter) {
		this.keyCounter = keyCounter;
	}
	
	public int getCoinCounter() {
		return coinCounter;
	}

	public void setCoinCounter(int coinCounter) {
		this.coinCounter = coinCounter;
	}

	public boolean isSwordHeld() {
		return isSwordHeld;
	}

	public void setSwordHeld(boolean isSwordHeld) {
		this.isSwordHeld = isSwordHeld;
	}
	
	public String getLastDirection() {
	    return lastDirection;
	}
}
