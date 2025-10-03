package com.kingdomtile.objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.kingdomtile.main.Panel;

public class Chest extends SuperObject {
	private Panel panel;
	private String material;
	
	public Chest(Panel panel) {
		this.panel = panel;
		setName("Chest");
		
		try {
			chest1 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/objects/chest_01.png"));
			chest2 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/objects/chest_02.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		image = chest1;
		collision = true;
	}
	
	@Override
	public void toggle() {
		if(image == chest2) return;
		panel.playFX(0);
		image = chest2;
    }

	public Chest(String material, String name) {
		super();
		this.material = material;
		this.name = name;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
}
