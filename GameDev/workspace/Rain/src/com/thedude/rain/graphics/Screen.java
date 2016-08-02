package com.thedude.rain.graphics;

public class Screen {
	private int width, height;
	public int[] pixels; //50400 size
	//by changing the XSPEED and YSPEED you can change the angle of pixel movement
	public static final int XSPEED = 40; //temporary variable, this will define the speed of the pixel moving lower means faster
	public static final int YSPEED = 40; //temporary variable, this will define the speed of the pixel moving lower means faster

	int xtime = 100, ytime = 100, counter = 0; //temporary variable for the display purpose

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; //pass in through the Game class
	}

	public void clear() { //clear the screen
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render() {
		counter++;
		if (counter % XSPEED == 0) xtime--; //this statement will just define the speed of increment for x-axis 
		if (counter % YSPEED == 0) ytime--; //this statement will just define the speed of increment for x-axis

		for (int y = 0; y < height; y++) { //filling the entire screen through next loop
			if (ytime < 0 || ytime >= height) break;
			for (int x = 0; x < width; x++) {
				if (xtime < 0 || xtime >= width) break; //this will stop rendering and fix the error for the ArrayOutOfBoundary crash, 
				pixels[xtime + ytime * width] = 0xff00ff; //color in hex increasing pixels 1 by one and changing their color
			}
		}
	}
}