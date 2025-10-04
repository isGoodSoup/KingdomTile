package com.kingdomtile.entity.npcs;

import javax.imageio.ImageIO;

import com.kingdomtile.entity.NPC;
import com.kingdomtile.main.Panel;

public class OldMan extends NPC {
	private Panel panel;
	
	public OldMan(Panel panel) {
		super();
		this.panel = panel;
		direction = "down";
		speed = 2;
	}
	
	@Override
	public void getSprite() {
		String defaultPath = "/com/kingdomtile/npc/oldie";
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-down_01.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-down_02.png"));
			idle1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-idle_01.png"));
			idle2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-idle_02.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-up_01.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-up_02.png"));
			idle3 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-up_idle_01.png"));
			idle4 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-up_idle_02.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-left_01.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-left_02.png"));
			idle5 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-left_idle_01.png"));
			idle6 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-left_idle_02.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-right_01.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-right_02.png"));
			idle7 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-right_idle_01.png"));
			idle8 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "oldman-right_idle_02.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}
}
