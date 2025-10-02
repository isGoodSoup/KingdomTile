package com.kingdomtile.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.kingdomtile.main.Panel;

public class Sword extends SuperObject {
	private Panel panel;
	
	public Sword(Panel panel) {
		this.panel = panel;
		setName("Sword");
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/objects/sword.png"));
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
}
