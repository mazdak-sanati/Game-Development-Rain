package com.thedude.rain.entity;

import java.util.Random;

import com.thedude.rain.graphics.Screen;
import com.thedude.rain.level.Level;

public abstract class Entity {// abstract creates the template, entity is everything that is not a tile(part of the
								// map), it can be time, player, creatures, ...

	public int x, y; // its redundant if entity doesn't have a sprite
	private boolean removed = false; // if its removed from the level
	protected Level level;
	protected final Random random = new Random();

	public void update() { // linked to game.java update()
	}

	public void render(Screen screen) { // entities can move so we don't need constant render position here like xScroll
										// and yScroll
	}

	public void remove() {
		// Remove from Level whenever need
		removed = true;
	}

	public boolean isRemoved() { // check if its removed or not
		return removed;
	}
}
