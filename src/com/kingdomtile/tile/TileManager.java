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
		tile = new Tile[100];
		mapTile = new int[panel.getMaxWorldCol()][panel.getMaxWorldRow()];
		getTileImage();
		loadMap("/com/kingdomtile/maps/worldmap.txt");
	}
	
	public void getTileImage() {
		setTileImage(0, "grass_01.png", false);
		setTileImage(1, "stone.png", true);
		setTileImage(2, "water_01.png", true);
		setTileImage(3, "dirt.png", false);
		setTileImage(4, "tree.png", true);
		setTileImage(5, "sand.png", false);
		setTileImage(6, "grass_02.png", false);
		setTileImage(7, "wood.png", false);
		setTileImage(8, "water_02.png", true);
		setTileImage(9, "water_03.png", true);
		setTileImage(10, "water_04.png", true);
		setTileImage(11, "water_05.png", true);
		setTileImage(12, "water_06.png", true);
		setTileImage(13, "water_07.png", true);
		setTileImage(14, "water_08.png", true);
		setTileImage(15, "water_09.png", true);
		setTileImage(16, "water_10.png", true);
		setTileImage(17, "water_11.png", true);
		setTileImage(18, "water_12.png", true);
		setTileImage(19, "water_13.png", true);
		setTileImage(20, "water_14.png", true);
		setTileImage(21, "water_15.png", true);
		setTileImage(22, "water_16.png", true);
		setTileImage(23, "water_17.png", true);
		setTileImage(24, "water_18.png", true);
	}
	
	public void setTileImage(int i, String source, boolean isCollision) {
		try {
			tile[i] = new Tile();
			tile[i].image = ImageIO.read(getClass().getResourceAsStream("/com/kingdomtile/tile/" + source));
			if(isCollision) tile[i].collision = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String map) {
	    try {
	        s = getClass().getResourceAsStream(map);
	        if (s == null) {
	            System.err.println("Map file not found: " + map);
	            return;
	        }

	        b = new BufferedReader(new InputStreamReader(s));
	        col = 0;
	        row = 0;

	        while (row < panel.getMaxWorldRow()) {
	            line = b.readLine();
	            if (line == null) {
	                System.err.println("Map file ended early at row " + row);
	                break;
	            }

	            numbers = line.split(" ");
	            col = 0;

	            while (col < panel.getMaxWorldCol()) {
	                if (col < numbers.length) {
	                    try {
	                        num = Integer.parseInt(numbers[col]);
	                        if (num >= tile.length || tile[num] == null) {
	                            System.err.println("Invalid tile index at " + col + "," + row + ": " + num + ". Defaulting to 0.");
	                            num = 0;
	                        }
	                        mapTile[col][row] = num;
	                    } catch (NumberFormatException e) {
	                        System.err.println("Invalid number at " + col + "," + row + ": " + numbers[col] + ". Defaulting to 0.");
	                        mapTile[col][row] = 0;
	                    }
	                } else {
	                    mapTile[col][row] = 0;
	                }
	                col++;
	            }

	            row++;
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
