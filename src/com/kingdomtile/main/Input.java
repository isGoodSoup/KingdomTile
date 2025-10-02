package com.kingdomtile.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
	private boolean isUpPressed, isDownPressed, isLeftPressed, isRightPressed, isSpacePressed;
	private boolean isEPressed, EJustPressed;
	private boolean isShiftPressed, isCtrlPressed;
	private boolean isEscapePressed;
	private int key;
	
	public void update() {
		EJustPressed = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP) {
			isUpPressed = true;
		}
		
		if(key == KeyEvent.VK_DOWN) {
			isDownPressed = true;
		}
		
		if(key == KeyEvent.VK_LEFT) {
			isLeftPressed = true;
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			isRightPressed = true;
		}
		
		if(key == KeyEvent.VK_SPACE) {
			isSpacePressed = true;
		}
		
		if(key == KeyEvent.VK_E) {
			if(!isEPressed) {
				EJustPressed = true;
			}
			isEPressed = true;
		}
		
		if(key == KeyEvent.VK_SHIFT) {
			isShiftPressed = true;
		}
		
		if(key == KeyEvent.VK_CONTROL) {
			isCtrlPressed = true;
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			isEscapePressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP) {
			isUpPressed = false;
		}
		
		if(key == KeyEvent.VK_DOWN) {
			isDownPressed = false;
		}
		
		if(key == KeyEvent.VK_LEFT) {
			isLeftPressed = false;
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			isRightPressed = false;
		}
		
		if(key == KeyEvent.VK_SPACE) {
			isSpacePressed = false;
		}
		
		if(key == KeyEvent.VK_E) {
			isEPressed = false;
		}
		
		if(key == KeyEvent.VK_SHIFT) {
			isShiftPressed = false;
		}
		
		if(key == KeyEvent.VK_CONTROL) {
			isCtrlPressed = false;
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			isEscapePressed = false;
		}
	}

	public boolean isUpPressed() {
		return isUpPressed;
	}

	public boolean isDownPressed() {
		return isDownPressed;
	}

	public boolean isLeftPressed() {
		return isLeftPressed;
	}

	public boolean isRightPressed() {
		return isRightPressed;
	}

	public boolean isSpacePressed() {
		return isSpacePressed;
	}

	public boolean isEPressed() {
		return isEPressed;
	}

	public boolean isEJustPressed() {
		return EJustPressed;
	}

	public boolean isShiftPressed() {
		return isShiftPressed;
	}

	public boolean isCtrlPressed() {
		return isCtrlPressed;
	}

	public boolean isEscapePressed() {
		return isEscapePressed;
	}
}
