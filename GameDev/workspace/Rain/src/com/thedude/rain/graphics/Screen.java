package com.thedude.rain.graphics;

import java.util.Random;

public class Screen {
	private int width, height;
	public int[] pixels; // height * width = size of this array
	public static final int TILESIZE = 16; //tile size 32 *32
	public static final int MAPWIDTH = 64; //tile size 32 *32

	public int[] tiles = new int[64 * 64];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; //pass in through the Game class

		for (int i = 0; i < 64 * 64; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() { //clear the screen
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render() {
		for (int y = 0; y < height; y++) { //filling the entire screen through next loop
			
			if (y < 0 || y >= height) break;//this will stop rendering and fix the error for the ArrayOutOfBoundary crash,
			for (int x = 0; x < width; x++) {
			
				if (x < 0 || x >= width) break; //this will stop rendering and fix the error for the ArrayOutOfBoundary crash,
				//int tileIndex = (x / TILESIZE) + (y / TILESIZE) * MAPWIDTH; //find the tile that needs to be rendered at the time
				//ALternative code optimization better fps specially in a for loop
				int tileIndex = (x >> 4) + (y >> 4) * MAPWIDTH;//same as : x/TILESIZE in above just bitwise
				pixels[x + y * width] = tiles[tileIndex]; //color in hex increasing pixels 1 by one and changing their color
			}
		}
	}
}
