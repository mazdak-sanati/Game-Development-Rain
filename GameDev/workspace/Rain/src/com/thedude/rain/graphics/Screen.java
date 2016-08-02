package com.thedude.rain.graphics;

import java.util.Random;

public class Screen {
	private int width, height;
	public int[] pixels; // height * width = size of this array
	public static final int TILESIZE = 16; //tile size 32 *32
	public static final int MAP_SIZE = 64; //tile size 32 *32
	public static final int MAP_SIZE_MASK = MAP_SIZE - 1; //tile size 32 *32

	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; //pass in through the Game class

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() { //clear the screen
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int xOffset, int yOffset) { //offsets are temp now
		for (int y = 0; y < height; y++) { //filling the entire screen through next loop
			int yy = y + yOffset; //we dont want to change the for loop
			//if (yy < 0 || yy >= height) break;//this will stop rendering and fix the error for the ArrayOutOfBoundary crash,
			for (int x = 0; x < width; x++) {
				int xx = x + xOffset ;
				//if (xx < 0 || xx >= width) break; //this will stop rendering and fix the error for the ArrayOutOfBoundary crash
				//ALternative code optimization better fps specially in a for loop
				int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + (((yy >> 4) & MAP_SIZE_MASK) * MAP_SIZE);// &: bitwise AND, same as: if  x/16 > 64 then return back to zero 
				pixels[x + y * width] = tiles[tileIndex]; //color in hex increasing pixels 1 by one and changing their color
			}
		}
	}
}
