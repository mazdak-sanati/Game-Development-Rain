package com.thedude.rain.graphics;

public class Sprite {

	private final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE]; // initiate the pixels
		this.x = x * size; // set the location of sprite in sprite-sheet
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				// this is important: here we are locating the specific
				// sprite in sprite sheet and copying that to our sprite class
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];

			}

		}

	}
}
