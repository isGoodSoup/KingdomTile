package com.kingdomtile.main;

import com.kingdomtile.entity.Entity;

public class Collision {
	// Worldspace
	private Panel panel;
	private int entityLeftWorldX;
	private int entityRightWorldX;
	private int entityTopWorldY;
	private int entityBottomWorldY;
	// Col & row
	private int entityLeftCol;
	private int entityRightCol;
	private int entityTopRow;
	private int entityBottomRow;
	private int tileNum1, tileNum2;
	
	public Collision(Panel panel) {
		super();
		this.panel = panel;
	}
	
	public void checkTile(Entity entity) {
		entityLeftWorldX = (int) (entity.getX() + entity.getSolidRectangle().getX());
		entityRightWorldX = (int) (entity.getX() + entity.getSolidRectangle().getX() + entity.getSolidRectangle().getWidth());
		entityTopWorldY = (int) (entity.getY() + entity.getSolidRectangle().getY());
		entityBottomWorldY = (int) (entity.getY() + entity.getSolidRectangle().getY() + entity.getSolidRectangle().getHeight());
		entityLeftCol = entityLeftWorldX / panel.getTileSize();
		entityRightCol = entityRightWorldX / panel.getTileSize();
		entityTopRow = entityTopWorldY / panel.getTileSize();
		entityBottomRow = entityBottomWorldY / panel.getTileSize();
		
		switch(entity.getDirection()) {
			case "up": 
				entityTopRow = (entityTopWorldY - entity.getSpeed()) / panel.getTileSize();
				tileNum1 = panel.getTileM().getMapTile()[entityLeftCol][entityTopRow];
				tileNum2 = panel.getTileM().getMapTile()[entityRightCol][entityTopRow];
				if(panel.getTileM().getTile()[tileNum1].isCollision() 
						|| panel.getTileM().getTile()[tileNum2].isCollision()) {
					entity.setCollisionOn(true);
				}
				break;
			case "down": 
				entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / panel.getTileSize();
				tileNum1 = panel.getTileM().getMapTile()[entityLeftCol][entityBottomRow];
				tileNum2 = panel.getTileM().getMapTile()[entityRightCol][entityBottomRow];
				if(panel.getTileM().getTile()[tileNum1].isCollision() 
						|| panel.getTileM().getTile()[tileNum2].isCollision()) {
					entity.setCollisionOn(true);
				}
				break;
			case "left": 
				entityLeftCol= (entityLeftWorldX - entity.getSpeed()) / panel.getTileSize();
				tileNum1 = panel.getTileM().getMapTile()[entityLeftCol][entityTopRow];
				tileNum2 = panel.getTileM().getMapTile()[entityLeftCol][entityBottomRow];
				if(panel.getTileM().getTile()[tileNum1].isCollision() 
						|| panel.getTileM().getTile()[tileNum2].isCollision()) {
					entity.setCollisionOn(true);
				}
				break;
			case "right": 
				entityRightCol= (entityRightWorldX + entity.getSpeed()) / panel.getTileSize();
				tileNum1 = panel.getTileM().getMapTile()[entityRightCol][entityTopRow];
				tileNum2 = panel.getTileM().getMapTile()[entityRightCol][entityBottomRow];
				if(panel.getTileM().getTile()[tileNum1].isCollision() 
						|| panel.getTileM().getTile()[tileNum2].isCollision()) {
					entity.setCollisionOn(true);
				}
				break;
		}
	}
	
	public int checkObject(Entity entity, boolean isPlayer) {
		int index = 999;
		for (int i = 0; i < panel.getObject().length; i++) {
			if(panel.getObject()[i] != null) {
				entity.getSolidRectangle().x = entity.getX() + entity.getSolidRectangle().x;
				entity.getSolidRectangle().y = entity.getY() + entity.getSolidRectangle().y;
				panel.getObject()[i].getSolidRectangle().x = panel.getObject()[i].getWorldX() + panel.getObject()[i].getSolidRectangle().x;			
				panel.getObject()[i].getSolidRectangle().y = panel.getObject()[i].getWorldY() + panel.getObject()[i].getSolidRectangle().y;
				
				switch(entity.getDirection()) {
				case "up": 
					entity.getSolidRectangle().y -= entity.getSpeed();	
					if(entity.getSolidRectangle().intersects(panel.getObject()[i].getSolidRectangle())) {
						if(panel.getObject()[i].isCollision()) {
							entity.setCollisionOn(true);
						}
						if(isPlayer) {
							index = i;
						}
					}
					break;
				case "down": 
					entity.getSolidRectangle().y += entity.getSpeed();
					if(entity.getSolidRectangle().intersects(panel.getObject()[i].getSolidRectangle())) {
						if(panel.getObject()[i].isCollision()) {
							entity.setCollisionOn(true);
						}
						if(isPlayer) {
							index = i;
						}
					}
					break;
				case "left": 
					entity.getSolidRectangle().x -= entity.getSpeed();
					if(entity.getSolidRectangle().intersects(panel.getObject()[i].getSolidRectangle())) {
						if(panel.getObject()[i].isCollision()) {
							entity.setCollisionOn(true);
						}
						if(isPlayer) {
							index = i;
						}
					}
					break;
				case "right": 
					entity.getSolidRectangle().x += entity.getSpeed();
					if(entity.getSolidRectangle().intersects(panel.getObject()[i].getSolidRectangle())) {
						if(panel.getObject()[i].isCollision()) {
							entity.setCollisionOn(true);
						}
						if(isPlayer) {
							index = i;
						}
					}
					break;
				}
				entity.getSolidRectangle().x = entity.getSolidRectangleX();
				entity.getSolidRectangle().y = entity.getSolidRectangleY();
				panel.getObject()[i].getSolidRectangle().x = panel.getObject()[i].getSolidRectangleX();
				panel.getObject()[i].getSolidRectangle().y = panel.getObject()[i].getSolidRectangleY();
			}
		}
		return index;
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

	public int getEntityLeftWorldX() {
		return entityLeftWorldX;
	}

	public int getEntityRightWorldX() {
		return entityRightWorldX;
	}

	public int getEntityTopWorldY() {
		return entityTopWorldY;
	}

	public int getEntityBottomWorldY() {
		return entityBottomWorldY;
	}

	public int getEntityLeftCol() {
		return entityLeftCol;
	}

	public int getEntityRightCol() {
		return entityRightCol;
	}

	public int getEntityTopRow() {
		return entityTopRow;
	}

	public int getEntityBottomRow() {
		return entityBottomRow;
	}

	public int getTileNum1() {
		return tileNum1;
	}

	public int getTileNum2() {
		return tileNum2;
	}
}
