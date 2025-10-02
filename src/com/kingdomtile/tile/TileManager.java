package com.kingdomtile.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.kingdomtile.main.Panel;

public class TileManager extends Tile {
	private Panel panel;
	private Tile[] tile;
	private int col;
	private int row;
	// World Position
	private int worldCol;
	private int worldRow;
	private int worldX; 
	private int worldY;
	private int screenX;
	private int screenY;
	// Map Settings
	private int mapTile[][];
	private int tileNum;
	private InputStream s;
	private BufferedReader b;
	private String line;
	private String[] numbers;
	private int num;
	
	public TileManager(Panel panel) {
		super();
		this.panel = panel;
		tile = new Tile[20];
		mapTile = new int[panel.getMaxWorldCol()][panel.getMaxWorldRow()];
		getTileImage();
		loadMap("/com/kingdomtile/maps/worldmap.txt");
	}
	
	public void getTileImage() {
		setTileImage(0, "/com/kingdomtile/tile/grass_01.png", false);
		setTileImage(1, "/com/kingdomtile/tile/stone.png", true);
		setTileImage(2, "/com/kingdomtile/tile/water_01.png", true);
		setTileImage(3, "/com/kingdomtile/tile/dirt.png", false);
		setTileImage(4, "/com/kingdomtile/tile/tree.png", true);
		setTileImage(5, "/com/kingdomtile/tile/sand.png", false);
		setTileImage(6, "/com/kingdomtile/tile/grass_02.png", false);
		setTileImage(7, "/com/kingdomtile/tile/wood.png", false);
		setTileImage(8, "/com/kingdomtile/tile/water_02.png", true);
		setTileImage(9, "/com/kingdomtile/tile/water_03.png", true);
		setTileImage(10, "/com/kingdomtile/tile/water_04.png", true);
		setTileImage(11, "/com/kingdomtile/tile/water_05.png", true);
		setTileImage(12, "/com/kingdomtile/tile/water_06.png", true);
		setTileImage(13, "/com/kingdomtile/tile/water_07.png", true);
		setTileImage(14, "/com/kingdomtile/tile/water_08.png", true);
		setTileImage(15, "/com/kingdomtile/tile/water_09.png", true);
		setTileImage(16, "/com/kingdomtile/tile/water_10.png", true);
		setTileImage(17, "/com/kingdomtile/tile/water_11.png", true);
		setTileImage(18, "/com/kingdomtile/tile/water_12.png", true);
	}
	
	public void setTileImage(int i, String source, boolean isCollision) {
		try {
			tile[i] = new Tile();
			tile[i].image = ImageIO.read(getClass().getResourceAsStream(source));
			if(isCollision) tile[i].collision = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String map) {
		try {
			s = getClass().getResourceAsStream(map);
			b = new BufferedReader(new InputStreamReader(s));
			col = 0;
			row = 0;
			
			while(col < panel.getMaxWorldCol() && row < panel.getMaxWorldRow()) {
				line = b.readLine();
				
				while(col < panel.getMaxWorldCol()) {
					numbers = line.split(" ");
					num = Integer.parseInt(numbers[col]);
					mapTile[col][row] = num;
					col++;
				}
				
				if(col == panel.getMaxWorldCol()) {
					col = 0;
					row++;
				}
			}
			b.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g) {
		worldCol = 0;
		worldRow = 0;
		
		while(worldCol < panel.getMaxWorldCol() && worldRow < panel.getMaxWorldRow()) {
			tileNum = mapTile[worldCol][worldRow];
			worldX = worldCol * panel.getTileSize();
			worldY = worldRow * panel.getTileSize();
			screenX = worldX - panel.getPlayer().getWorldX() + panel.getPlayer().getScreenX();
			screenY = worldY - panel.getPlayer().getWorldY() + panel.getPlayer().getScreenY();
			if(worldX + panel.getTileSize() > panel.getTileSize() - panel.getPlayer().getScreenX() && 
			   worldX - panel.getTileSize() < panel.getPlayer().getWorldX() + panel.getPlayer().getScreenX() && 
			   worldY + panel.getTileSize() > panel.getPlayer().getWorldY() - panel.getPlayer().getScreenY() && 
			   worldY - panel.getTileSize() < panel.getPlayer().getWorldY() + panel.getPlayer().getScreenY()) {
				g.drawImage(tile[tileNum].getImage(), screenX, screenY, panel.getTileSize(), panel.getTileSize(), null);
			}
			worldCol++;
			
			if(worldCol == panel.getMaxWorldCol()) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
	
	public Panel getPanel() {
		return panel;
	}

	public void setPanel(Panel panel) {
		this.panel = panel;
	}

	public Tile[] getTile() {
		return tile;
	}

	public int[][] getMapTile() {
		return mapTile;
	}

	public int getTileNum() {
		return tileNum;
	}
}
