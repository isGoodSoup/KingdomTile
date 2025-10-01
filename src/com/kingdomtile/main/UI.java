package com.kingdomtile.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
	private Panel panel;
	private Font font;
	private int playerX;
	private int playerY;
	
	public UI(Panel panel) {
		this.panel = panel;
	}
	
	public void draw(Graphics2D g2) {
		g2.setFont(setFont(new Font("Arial", Font.BOLD, 20)));
		g2.setColor(Color.white);
		playerX = panel.getPlayer().getX() / panel.getTileSize();
		playerY = panel.getPlayer().getY() / panel.getTileSize();
		g2.drawString("x: " + playerX + " y: " + playerY, 20, 30);
	}

	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

	public Font getFont() {
		return font;
	}

	public Font setFont(Font font) {
		this.font = font;
		return font;
	}
}
