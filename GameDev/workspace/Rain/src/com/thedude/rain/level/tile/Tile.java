package com.thedude.rain.level.tile;

import com.thedude.rain.graphics.Screen;
import com.thedude.rain.graphics.Sprite;

public class Tile { //each tile should have position, a sprite, rendered, solid or not (collide or NOT)
	
	public int x, y; //for the position of the tile
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
	}
	
	public boolean solid() {
		return false;
	}
	
}
