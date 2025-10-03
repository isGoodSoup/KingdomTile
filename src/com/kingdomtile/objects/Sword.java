package com.kingdomtile.objects;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.kingdomtile.entity.Player;
import com.kingdomtile.main.Panel;

public class Sword extends SuperObject {
	private Panel panel;
	
	public Sword(Panel panel) {
		this.panel = panel;
		setName("Sword");
		
		try {
			sword1 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/objects/sword_01.png"));
			sword2 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/objects/sword_02.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		image = sword1;
		collision = true;
	}
	
	public void equip() {
		if(panel.getPlayer().isSwordHeld()) return;
		panel.getPlayer().setSwordHeld(true);
		panel.playFX(0);
	}
	
	public void draw(Graphics2D g2) {
		int x = panel.getPlayer().getScreenX();
		int y = panel.getPlayer().getScreenY();
		int size = panel.getTileSize();
		int angle = 15;
		int offsetX = 40;
		int offsetY = 5;
		
		switch(panel.getPlayer().getLastDirection()) {
			case "up":
				image = sword1;
				g2.drawImage(image, x + size/2, y, size, size, null);
				break;
				
			case "down":
				image = sword2;
				g2.drawImage(image, x - size/2, y, size, size, null);
				break;
				
			case "left": 
				image = sword2;
				g2.rotate(Math.toRadians(-angle), x + size/2, y + size/2); 
				g2.drawImage(image, x - offsetX, y + offsetY - 10, size, size, null); 
				g2.rotate(Math.toRadians(angle), x + size/2, y + size/2);
				break;
				
			case "right":
				image = sword1;
				g2.rotate(Math.toRadians(angle), x + size/2, y + size/2); 
				g2.drawImage(image, x + offsetX, y + offsetY, size, size, null); 
				g2.rotate(Math.toRadians(-angle), x + size/2, y + size/2);
				break;
		}
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
		equip();
        player.nullifyObject(index);
	}
}