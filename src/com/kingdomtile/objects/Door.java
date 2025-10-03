package com.kingdomtile.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.kingdomtile.entity.Player;
import com.kingdomtile.main.Panel;

public class Door extends SuperObject {
	private Player player;
	private Panel panel;
	private String material;

	public Door(Panel panel, Player player) {
		this.panel = panel;
		this.player = player;
		setName("Door");
		
		try {
			door1 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/objects/door_01.png"));
			door2 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/objects/door_02.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		image = door1;
		collision = true;
	}
	
	public Door(String material) {
		super();
		this.material = material;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}
	
	@Override
	public void toggle() {
		if(image == door2) return;
		if(player.getKeyCounter() == 1) {
			panel.playFX(1);
			image = door2;
			collision = false;
			player.setKeyCounter(0);
		} else {
			return;
		}
    }

	@Override
	public void onPickup(Player player, int index) {
		if (panel.getKey().isEJustPressed()) toggle();
	}
}
