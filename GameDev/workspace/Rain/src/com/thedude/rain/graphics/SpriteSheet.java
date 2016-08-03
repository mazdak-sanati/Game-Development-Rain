package com.thedude.rain.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path; // the path to the sprite-sheet
	public final int SIZE;
	public int[] pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256); //its now loaded in the memory
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load(); //sets pixels equal to sprite sheet

	}

	private void load() {
		try { //try and catch added automatically by eclipse
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);//sets the image into array
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
