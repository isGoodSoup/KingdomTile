package com.kingdomtile.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.kingdomtile.entity.Player;
import com.kingdomtile.main.Panel;

public class Key extends SuperObject {
	private Panel panel;
	
	public Key(Panel panel) {
		this.panel = panel;
		setName("Key");
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/objects/key.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

	@Override
	public void toggle() { return; }

	@Override
	public void onPickup(Player player, int index) {
		 player.setKeyCounter(player.getKeyCounter() + 1);
		 panel.playFX(0);
		 player.nullifyObject(index);
	}
}
