package com.kingdomtile.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {
	private BufferedImage image;
	private Panel panel;
	private Font font;
	private int playerX;
	private int playerY;
	private int iconX, iconY;
	private int textX, textY;
	private int iconWidth = 48;
	private int iconHeight = iconWidth;
	
	public UI(Panel panel) {
		this.panel = panel;
		getIcon();
	}
	
	public void getIcon() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/ui/icon.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		iconX = 20; iconY = 15;
		textX = iconX + image.getWidth() + 40;
		textY = iconY + image.getHeight() / 2 + 20;
		
		g2.setFont(setFont(new Font("Arial", Font.BOLD, 25)));
		g2.setColor(Color.black);
		playerX = panel.getPlayer().getX() / panel.getTileSize();
		playerY = panel.getPlayer().getY() / panel.getTileSize() + 1;
		g2.drawString("x: " + playerX + " y: " + playerY, textX, textY);
		g2.drawImage(image, iconX, iconY, iconWidth, iconHeight, panel);
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

	public BufferedImage getImage() {
		return image;
	}

	public int getIconWidth() {
		return iconWidth;
	}

	public int getIconHeight() {
		return iconHeight;
	}
}
