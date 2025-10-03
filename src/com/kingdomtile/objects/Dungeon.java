package com.kingdomtile.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.kingdomtile.entity.Player;
import com.kingdomtile.main.Panel;

public class Dungeon extends SuperObject {
	private Player player;
	private Panel panel;

	public Dungeon(Panel panel, Player player) {
		this.panel = panel;
		this.player = player;
		setName("Dungeon");
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/objects/dungeon.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
	
	public Dungeon(String material) {
		super();
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public void toggle() { return; }

	@Override
	public void onPickup(Player player, int index) { return; }
}
