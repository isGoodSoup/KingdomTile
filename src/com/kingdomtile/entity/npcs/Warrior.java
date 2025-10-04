package com.kingdomtile.entity.npcs;

import java.util.Random;

import javax.imageio.ImageIO;

import com.kingdomtile.entity.Entity;
import com.kingdomtile.main.Panel;

public class Warrior extends Entity {
	private Random random = new Random();
	private Panel panel;
	
	public Warrior(Panel panel) {
		super(panel);
		this.panel = panel;
		this.direction = "down";
		this.speed = 1;
		getSprite();
	}
	
	@Override
	public void getSprite() {
		String defaultPath = "/com/kingdomtile/npc/";
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-down_01.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-down_02.png"));
			idle1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-idle_01.png"));
			idle2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-idle_02.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-up_01.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-up_02.png"));
			idle3 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-up_idle_01.png"));
			idle4 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-up_idle_02.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-left_01.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-left_02.png"));
			idle5 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-left_idle_01.png"));
			idle6 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-left_idle_02.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-right_01.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-right_02.png"));
			idle7 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-right_idle_01.png"));
			idle8 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "warrior-right_idle_02.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void action() {
	    actionCounter++;
	    if (actionCounter > 120) {
	        int i = random.nextInt(5);
	        switch(i) {
	            case 0: direction = "up"; break;
	            case 1: direction = "down"; break;
	            case 2: direction = "left"; break;
	            case 3: direction = "right"; break;
	            case 4: direction = "idle"; break;
	        }
	        actionCounter = 0;
	    }
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}
}
