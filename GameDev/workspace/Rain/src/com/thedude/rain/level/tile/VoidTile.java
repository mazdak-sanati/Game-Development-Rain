package com.thedude.rain.level.tile;

import com.thedude.rain.graphics.Screen;
import com.thedude.rain.graphics.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile( x << 4, y << 4, this); // << 4 to neglect the effect of offset in Screen class
	}

}
