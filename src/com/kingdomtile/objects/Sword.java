package com.kingdomtile.objects;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.kingdomtile.entity.Player;
import com.kingdomtile.main.Panel;

public class Sword extends SuperObject {
    private Panel panel;
    private boolean isAttacking = false;
    private int attackFrame = 0;
    private final int attackLength = 10;
    private final int thrustDistance = 30;
    private int x, y, size, offsetX, offsetY;

    public Sword(Panel panel) {
        this.panel = panel;
        setName("Sword");

        String defaultPath = "/com/kingdomtile/objects/";
        try {
            sword1 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "sword_01.png"));
            sword2 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "sword_02.png"));
            sword3 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "sword_03.png"));
            sword4 = ImageIO.read(getClass().getResourceAsStream(defaultPath + "sword_04.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }

        image = sword1;
        collision = true;
    }

    public void equip() {
        if(panel.getPlayer().isSwordHeld()) return;
        panel.getPlayer().setSwordHeld(true);
        panel.playFX(0);
    }

    public void attack() {
        if (!isAttacking) {
            isAttacking = true;
            attackFrame = 0;
        }
    }
    
    public void update() {
        if (isAttacking) {
            attackFrame++;
            if (attackFrame >= attackLength) {
                isAttacking = false;
            }
        }
    }
    
    public void draw(Graphics2D g2) {
        Player p = panel.getPlayer();
        x = p.getScreenX();
        y = p.getScreenY();
        offsetX = 20;
        offsetY = 15;
        size = panel.getTileSize();

        int thrustOffset = 0;
        if (isAttacking) {
            int mid = attackLength / 2;
            thrustOffset = (attackFrame <= mid) ?
                    (thrustDistance * attackFrame / mid) :
                    (thrustDistance * (attackLength - attackFrame) / mid);
        }

        switch(p.getLastDirection()) {
            case "up":
                g2.drawImage(sword1, x + offsetX, y - offsetY - thrustOffset, size, size, null);
                break;
            case "down":
                g2.drawImage(sword2, x - offsetX, y + offsetY + 10 + thrustOffset, size, size, null);
                break;
            case "left":
                g2.drawImage(sword3, x - offsetX - thrustOffset, y + offsetY, size, size, null);
                break;
            case "right":
                g2.drawImage(sword4, x + offsetX + thrustOffset, y + offsetY, size, size, null);
                break;
        }
    }

    @Override
    public void toggle() {}

    @Override
    public void onPickup(Player player, int index) {
        equip();
        player.nullifyObject(index);
    }
}
