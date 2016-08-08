package com.thedude.rain.graphics;

import java.util.Random;

import com.thedude.rain.level.tile.Tile;

public class Screen {
	public int width, height;
	public int[] pixels; // height * width = size of this array
	public static int TILE_SIZE = 0; // 2POW(TILESIZE) = actual tile size
	public static final int MAP_SIZE = 64; // tile size 32 *32
	public static final int MAP_SIZE_MASK = MAP_SIZE - 1; // tile size 32 *32
	public int xOffset, yOffset;

	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; // pass in through the Game class

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() { // clear the screen
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int xOffset, int yOffset, int zoom) { // offsets are temp now
		TILE_SIZE = zoom;
		for (int y = 0; y < height; y++) { // filling the entire screen through next loop
			int yp = y + yOffset;
			if (yp < 0 || yp >= height) continue;
			for (int x = 0; x < (width); x++) {
				int xp = x + xOffset;
				if (xp < 0 || xp >= width) continue;
				/*with somewhat working zoom - also in the for loop condition change height and width to (height*TILESIZE)*/
				 //pixels[xp + yp * width] = Sprite.grass.pixels [(x >> (TILE_SIZE - 1) & 15) + ( y >> (TILE_SIZE - 1) & 15) * Sprite.grass.SIZE];
				pixels[xp + yp * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];
			}
		}
	}

	public void renderTile(int xp, int yp, Tile tile){//render individual tiles
		xp -= xOffset; //this will fix the inverted move on keyboard
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp ; //ya is absolute position episode 28
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp ; //xa is absolute position episode 28
				if (xa < 0 || xa >= width || ya < 0 || ya >= width) break; //screen restriction arrayOutOfBoundry the game might crash without this line
				pixels[xa + ya * width] = tile.sprite.pixels[x  + y  * tile.sprite.SIZE];//we don't need offset for the right side of the equation since the sprite is absolute and not offset
			}
		}
	}
	public void setOffset(int xoffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}