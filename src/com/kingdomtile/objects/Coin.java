package com.kingdomtile.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.kingdomtile.main.Panel;

public class Coin extends SuperObject {
	private Panel panel;
	
	public Coin(Panel panel) {
		this.panel = panel;
		setName("Coin");
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/objects/coin.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
	
	@Override
	public void toggle() {
		panel.playFX(2);
		panel.getPlayer().setCoinCounter(panel.getPlayer().getCoinCounter() + 1);
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}
}
