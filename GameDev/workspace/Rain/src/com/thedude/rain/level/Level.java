package com.thedude.rain.level;

import com.thedude.rain.graphics.Screen;
import com.thedude.rain.level.tile.Tile;

public class Level { // BASE LEVEL OTHE SUBCLASSES WILL INHERET THIS LEVEL

	protected int width, height;
	protected int[] tiles;

	public Level(int width, int height) { // this constructor will build (generate) level
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}

	public Level(String path) { // this constructor will load level from a path
		loadLevel(path);
	}

	private void loadLevel(String path) {
	}

	protected void generateLevel() {
	}

	public void update() { // updates the level, like AI, entities and so on ...

	}

	private void time() { // Defining the level bases on time : DAY-TIME
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		/*defining the corner pins*/
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4; // left most vertical edge of screen. >> 4 = / 16 for tiles precision and not pixels
		int x1 = (xScroll + screen.width) >> 4;
		int y0 = yScroll >> 4; // top horizontal edge of screen
		int y1 = (yScroll + screen.height) >> 4;
		/*actually rendering here*/
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}

	/*placing the tile*/
	public Tile getTile(int x, int y) { // return a tile
		if (tiles[x + y * width] == 0) return Tile.grass;
		return Tile.voidTile; //else return void tile when out of map don't render 
	}

}