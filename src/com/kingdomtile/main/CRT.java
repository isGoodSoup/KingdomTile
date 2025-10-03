package com.kingdomtile.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

public class CRT {
	private int width, height;
	private BufferedImage buffer;
	
	public CRT(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public Graphics2D getBufferedGraphics() {
		return buffer.createGraphics();
	}
	
	public void render(Graphics2D g, int x, int y) {
	    g.drawImage(buffer, x, y, null);

	    g.setColor(new Color(0, 0, 0, 60));
	    for (int i = 0; i < buffer.getHeight(); i += 2) {
	        g.drawLine(x, y + i, x + buffer.getWidth(), y + i);
	    }

	    RadialGradientPaint vignette = new RadialGradientPaint(
	        new Point(x + buffer.getWidth() / 2, y + buffer.getHeight() / 2),
	        Math.max(buffer.getWidth(), buffer.getHeight()) / 2f,
	        new float[]{0f, 1f},
	        new Color[]{new Color(0, 0, 0, 0), new Color(0, 0, 0, 100)}
	    );
	    g.setPaint(vignette);
	    g.fillRect(x, y, buffer.getWidth(), buffer.getHeight());

	    Composite old = g.getComposite();
	    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
	    g.drawImage(buffer, x + 1, y, null);
	    g.drawImage(buffer, x, y + 1, null);
	    g.setComposite(old);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
