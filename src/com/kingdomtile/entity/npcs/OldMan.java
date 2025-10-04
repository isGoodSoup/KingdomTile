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
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-down_01.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-down_02.png"));
			idle1 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-idle_01.png"));
			idle2 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-idle_02.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-up_01.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-up_02.png"));
			idle3 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-up_idle_01.png"));
			idle4 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-up_idle_02.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-left_01.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-left_02.png"));
			idle5 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-left_idle_01.png"));
			idle6 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-left_idle_02.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-right_01.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-right_02.png"));
			idle7 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-right_idle_01.png"));
			idle8 = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/player/mage-right_idle_02.png"));
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
