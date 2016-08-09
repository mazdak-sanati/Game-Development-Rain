package com.thedude.rain.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles); // Creates our
																// sprite within
																// the sprite
																// e.g
																// Sprite.grass.pixels
																// refer to the
																// green pixels
																// on top-left
																// of out
																// sprite-sheet
																// 0,0 cause its
																// the first
																// tile in
																// sprite-sheet
	public static Sprite voidSprite = new Sprite(16, 0x000000);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE]; // initiate the pixels
		this.x = x * size; // set the location of sprite in sprite-sheet
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}
	private void setColor(int color) {
		for (int i = 0; i < SIZE*SIZE; i++) {
			pixels[i] = color;
		}
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
