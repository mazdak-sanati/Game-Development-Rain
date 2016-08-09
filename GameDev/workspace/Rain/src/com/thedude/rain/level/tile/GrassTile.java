package com.thedude.rain.level.tile;

import com.thedude.rain.graphics.Screen;
import com.thedude.rain.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) { // Constructor
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);// we need tile not pixels thats why we need to do mathematical calculation to
										// change the type << 4 to neglect the effect of offset in Screen class
		
	}

}
