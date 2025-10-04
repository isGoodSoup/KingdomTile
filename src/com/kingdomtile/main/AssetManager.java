package com.kingdomtile.main;

import com.kingdomtile.entity.Entity;
import com.kingdomtile.entity.npcs.OldMan;
import com.kingdomtile.entity.npcs.Warrior;
import com.kingdomtile.objects.Chest;
import com.kingdomtile.objects.Coin;
import com.kingdomtile.objects.Door;
import com.kingdomtile.objects.Dungeon;
import com.kingdomtile.objects.Key;
import com.kingdomtile.objects.Shield;
import com.kingdomtile.objects.SuperObject;
import com.kingdomtile.objects.Sword;
import com.kingdomtile.objects.Well;

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
        addObject(5, new Sword(panel), 23, 34);
        addObject(6, new Well(panel), 38, 9);
        addObject(7, new Coin(panel), 27, 10);
        addObject(8, new Coin(panel), 33, 39);
        addObject(9, new Coin(panel), 6, 27);
        addObject(10, new Coin(panel), 31, 24);
        addObject(11, new Dungeon(panel, panel.getPlayer()), 31, 16);
        addObject(12, new Chest(panel), 22, 43);
    }
	
	public void setUpNPC() {
		addNPC(0, new OldMan(panel), 34, 36);
		addNPC(1, new Warrior(panel), 30, 21);
	}

	private void addObject(int i, SuperObject obj, int x, int y) {
        obj.setWorldX(x * panel.getTileSize());
        obj.setWorldY(y * panel.getTileSize());
        panel.getObject()[i] = obj;
    }
	
	private void addNPC(int i, Entity ent, int x, int y) {
		ent.setWorldX(x * panel.getTileSize());
		ent.setWorldY(y * panel.getTileSize());
		panel.getNPC()[i] = ent;
	}
}
