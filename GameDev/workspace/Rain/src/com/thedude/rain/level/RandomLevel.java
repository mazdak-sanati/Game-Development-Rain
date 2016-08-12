package com.thedude.rain.level;

import java.util.Random;

public class RandomLevel extends Level { // this is sort of a random class template

	private static final Random random = new Random();

	public RandomLevel(int width, int height) { // we used the int,int constructor inheriting from LEvel class, because
												// we want to generate and not import a level
		super(width, height); // whatever the parameter we input here is going back to Level.Level(int,int) and run the
								// constructor
	}

	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x + y * width] = random.nextInt(4); // random from zero to (arg) the random output will represent
															// the type of tile e.g. grass,water,wall,etc
			}
		}

	}
}
