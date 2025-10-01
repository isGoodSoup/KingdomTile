package com.kingdomtile.main;

import javax.swing.JFrame;

public class Main {
	private String title = "Kingdom Tile";
	private JFrame frame = new JFrame();
	private Panel panel = new Panel();
	
	public static void main(String[] args) {
		Main start = new Main();
		start.Window();
	}
	
	public void Window() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setTitle(this.title);
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		panel.requestFocusInWindow();
		panel.setGameObjects();
		panel.startGameThread();
	}
}
