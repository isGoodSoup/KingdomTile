package com.kingdomtile.main;

import com.kingdomtile.objects.Chest;
import com.kingdomtile.objects.Door;
import com.kingdomtile.objects.Key;
import com.kingdomtile.objects.Shield;
import com.kingdomtile.objects.Sword;

public class AssetManager {
	private Panel panel;
	private int chestX;
	private int chestY;
	
	public AssetManager(Panel panel) {
		super();
		this.panel = panel;
	}

	public void setObject() {
		chestX = 23; chestY = 8;
		panel.getObject()[0] = new Chest(panel);
		panel.getObject()[0].setWorldX(chestX * panel.getTileSize());
		panel.getObject()[0].setWorldY(chestY * panel.getTileSize());
		
		panel.getObject()[1] = new Door(panel, panel.getPlayer());
		panel.getObject()[1].setWorldX(31 * panel.getTileSize());
		panel.getObject()[1].setWorldY(32 * panel.getTileSize());
		
//		panel.getObject()[2] = new Shield(panel);
//		panel.getObject()[2].setWorldX(chestX * panel.getTileSize());
//		panel.getObject()[2].setWorldY(chestY * panel.getTileSize());
		
		panel.getObject()[3] = new Shield(panel);
		panel.getObject()[3].setWorldX(40 * panel.getTileSize());
		panel.getObject()[3].setWorldY(9 * panel.getTileSize());
		
		chestX = 31; chestY = 28;
		panel.getObject()[4] = new Chest(panel);
		panel.getObject()[4].setWorldX(chestX * panel.getTileSize());
		panel.getObject()[4].setWorldY(chestY * panel.getTileSize());
		
		panel.getObject()[5] = new Key(panel);
		panel.getObject()[5].setWorldX(17 * panel.getTileSize());
		panel.getObject()[5].setWorldY(13 * panel.getTileSize());
		
		panel.getObject()[6] = new Sword(panel);
		panel.getObject()[6].setWorldX(15 * panel.getTileSize());
		panel.getObject()[6].setWorldY(27 * panel.getTileSize());
	}
}
