package com.kingdomtile.main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	private Clip clip;
	private URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/com/kingdomtile/fx/new-item.wav");
		soundURL[1] = getClass().getResource("/com/kingdomtile/fx/door-open.wav");
		soundURL[2] = getClass().getResource("/com/kingdomtile/fx/coin-pickup.wav"); // by chieuk
		soundURL[3] = getClass().getResource("/com/kingdomtile/fx/main-theme.wav"); // by nickpanek
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		if (clip == null) return;
	    clip.stop();
	    clip.setFramePosition(0); // rewind
	    clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}
