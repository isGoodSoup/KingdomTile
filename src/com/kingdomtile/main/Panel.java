package com.kingdomtile.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kingdomtile.entity.Entity;
import com.kingdomtile.entity.Player;
import com.kingdomtile.objects.SuperObject;
import com.kingdomtile.tile.TileManager;

public class Panel extends JPanel implements Runnable {
	// Screen Settings
	private static final long serialVersionUID = 1L;
	private final int tileSizeOriginal = 16;
	private int scale = 4;
	private int tileSize = tileSizeOriginal * scale;
	private int screenRatio = 10;
	private int maxScreenCol = screenRatio;
	private int maxScreenRow = screenRatio;
	private int offsetX;
	private int offsetY;
	
	// World Settings
	private final int worldRatio = 50;
	private final int maxWorldCol = worldRatio;
	private final int maxWorldRow = worldRatio;
	private final int worldWidth = tileSize * maxScreenCol;
	private final int worldHeight = tileSize * maxScreenRow;
	
	// FPS
	private TileManager tileM = new TileManager(this);
	private volatile boolean paused = false;
	private int fps = 60;
	private long currentTime;
	private double drawInterval = 1000000000 / fps;
	private double lastTime;
	private double delta = 0;
	private CRT crt = new CRT(getScreenWidth(), getScreenHeight());
	
	
	// Core
	private Graphics2D crtG;
	private Thread gameThread;
	private Input key = new Input();
	private Collision collision = new Collision(this);
	private AssetManager asset = new AssetManager(this);
	private Sound sound = new Sound();
	private Player player = new Player(this, key);
	private Entity[] NPC = new Entity[10];
	private UI GUI = new UI(this);
	private SuperObject object[] = new SuperObject[20];
	private final static Logger log = LoggerFactory.getLogger(Panel.class);
	
	public Panel() {
		log.info("Session started");
		this.setPreferredSize(new Dimension(getScreenWidth(), getScreenHeight()));
		this.revalidate();
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
	}
	
	@Override
	public void run() {
		lastTime = System.nanoTime();
	    while (gameThread != null) {
	        currentTime = System.nanoTime();
	        delta += (currentTime - lastTime) / drawInterval;
	        lastTime = currentTime;

	        if (key.isEscapeJustPressed()) {
	            paused = !paused;
	        }

	        repaint();

	        if (!paused) {
	            if (delta >= 1) {
	                update();
	                delta--;
	            }
	        }

	        key.update();

	        try {
	            Thread.sleep(16);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }
	}
		
	public void update() {
	    player.update();

	    if(player.isSwordHeld() && key.isSpacePressed()) {
	        player.getSword().attack();
	    }

	    if(player.isSwordHeld() && player.getSword() != null) {
	        player.getSword().update();
	    }

	    for (Entity ent : NPC) {
	        if (ent != null) ent.update();
	    }
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    crtG = crt.getBufferedGraphics();
	    crtG.setColor(Color.BLACK);
	    crtG.fillRect(0, 0, crt.getWidth(), crt.getHeight());

	    crtG.setClip(0, 0, crt.getWidth(), crt.getHeight());

	    tileM.draw(crtG);

	    for (SuperObject obj : object) {
	        if (obj != null) obj.draw(crtG, this);
	    }
	    
	    for (Entity ent : NPC) {
	    	if (ent != null) ent.draw(crtG);
	    }
	    
	    if(player.isSwordHeld() && player.getSword() != null) {
	        String dir = player.getLastDirection();
	        if ("up".equals(dir) || "left".equals(dir)) {
	            player.getSword().draw(crtG);
	            player.draw(crtG);
	        } else {
	            player.draw(crtG);
	            player.getSword().draw(crtG);
	        }
	    } else {
	        player.draw(crtG);
	    }

	    GUI.draw(crtG);

	    if (paused) {
	        crtG.setColor(new Color(0, 0, 0, 150));
	        crtG.fillRect(0, 0, crt.getWidth(), crt.getHeight());
	        GUI.pauseScreen(crtG);
	    }

	    offsetX = ((getWidth() - crt.getWidth()) / 2) & ~1;
	    offsetY = ((getHeight() - crt.getHeight()) / 2) & ~1;

	    Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
	    crt.render(g2, offsetX, offsetY);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.loop();
	}
	
	public void playFX(int i) {
		sound.setFile(i);
		sound.play();
	}
	
	public void setGameStart() {
		asset.setUpObjects();
		asset.setUpNPC();
		playMusic(3);
	}
	
	public AssetManager getAsset() {
		return asset;
	}

	public UI getGUI() {
		return GUI;
	}

	public boolean isPaused() {
		return paused;
	}

	public int getScale() {
		return scale;
	}
	
	public void setScale(int scale) {
		this.scale = scale;
		this.tileSize = tileSizeOriginal * scale;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	public int getMaxScreenCol() {
		return maxScreenCol;
	}
	
	public void setMaxScreenCol(int maxScreenCol) {
		this.maxScreenCol = maxScreenCol;
	}
	
	public int getMaxScreenRow() {
		return maxScreenRow;
	}
	
	public void setMaxScreenRow(int maxScreenRow) {
		this.maxScreenRow = maxScreenRow;
	}
	
	public int getScreenWidth() {
		return tileSize * maxScreenCol;
	}
	
	public int getScreenHeight() {
		return tileSize * maxScreenRow;
	}

	public Sound getSound() {
		return sound;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Collision getCollision() {
		return collision;
	}

	public int getWorldRatio() {
		return worldRatio;
	}

	public int getMaxWorldCol() {
		return maxWorldCol;
	}

	public int getMaxWorldRow() {
		return maxWorldRow;
	}

	public int getWorldWidth() {
		return worldWidth;
	}

	public int getWorldHeight() {
		return worldHeight;
	}

	public TileManager getTileM() {
		return tileM;
	}

	public Input getKey() {
		return key;
	}
	
	public SuperObject[] getObject() {
		return object;
	}

	public void setObject(SuperObject[] object) {
		this.object = object;
	}

	public Entity[] getNPC() {
		return NPC;
	}

	public void setNPC(Entity[] nPC) {
		NPC = nPC;
	}
}
