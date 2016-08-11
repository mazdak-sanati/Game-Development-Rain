package com.thedude.rain.entity.mob;

import com.thedude.rain.entity.Entity;
import com.thedude.rain.graphics.Sprite;

public abstract class Mob extends Entity { // mob is a group of entities and basically everything that moves

	protected Sprite sprite;
	protected int dir = 0; // 0 north, 1 east, 2 south, 3 west
	protected boolean moving = false; // moving animations

	public void move(int xa, int ya) {
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		
		//x,y = -1,0 (do nothing),1 (add move right for x or top for y)
		if (!collision()){
			x += xa; //basic moving
			y += ya;
		}
	}

	public void update() {

	}

	private boolean collision() {
		return false;
	}
	
	public void render() {
		
	}

}
