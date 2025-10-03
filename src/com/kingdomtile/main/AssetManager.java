package com.kingdomtile.main;

import com.kingdomtile.objects.Chest;
import com.kingdomtile.objects.Coin;
import com.kingdomtile.objects.Door;
import com.kingdomtile.objects.Key;
import com.kingdomtile.objects.Shield;
import com.kingdomtile.objects.SuperObject;
import com.kingdomtile.objects.Sword;

public class AssetManager {
	private Panel panel;
	
	public AssetManager(Panel panel) {
		super();
		this.panel = panel;
	}
	
	public void setUpObjects() {
        addObject(0, new Chest(panel), 23, 8);
        addObject(1, new Door(panel, panel.getPlayer()), 31, 32);
        addObject(2, new Shield(panel), 40, 13);
        addObject(3, new Chest(panel), 31, 28);
        addObject(4, new Key(panel), 17, 13);
        addObject(5, new Sword(panel), 15, 27);
        addObject(6, new Coin(panel), 27, 10);
        addObject(7, new Coin(panel), 33, 39);
        addObject(8, new Coin(panel), 6, 27);
        addObject(9, new Coin(panel), 31, 24);
    }

	private void addObject(int i, SuperObject obj, int x, int y) {
        obj.setWorldX(x * panel.getTileSize());
        obj.setWorldY(y * panel.getTileSize());
        panel.getObject()[i] = obj;
    }
}
