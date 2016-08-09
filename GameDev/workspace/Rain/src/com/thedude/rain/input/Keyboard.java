package com.thedude.rain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	
	private boolean[] keys = new boolean[120];
	public boolean up, down, left ,right, invert, normal;
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		invert = keys[KeyEvent.VK_I] && keys[KeyEvent.VK_SHIFT];
		normal = keys[KeyEvent.VK_N] && keys[KeyEvent.VK_SHIFT];
		
	/*	for (int i= 0; i < keys.length; i++) {
			if (keys[i]){
			System.out.println("KEYS: " + i);
			}
		}*/
	}


	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}


	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}


	public void keyTyped(KeyEvent e) {
		
	}

}
