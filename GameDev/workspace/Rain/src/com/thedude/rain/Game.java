package com.thedude.rain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.thedude.rain.graphics.Screen;
import com.thedude.rain.input.Keyboard;

public class Game extends Canvas implements Runnable { //our class in sub-class of Canvas meaning its a component
	private static final long serialVersionUID = 1L; //It's a convention of Java just do it to remove game warning in the declaration of class

	//declaring the game frame resolution
	public static int width = 300;
	public static int height = 168; //for tutorial purpose
	//public static int height = width / 16 * 9;
	public static int scale = 4;
	public static String title = "Rain";

	private Thread thread; //declare out thread
	private JFrame frame; //from JFrame library import the frame
	private Keyboard key; //import from the packge
	private boolean running = false; //declare our running for the game loop later used in start and stop method

	private Screen screen;
	

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);//An image with an accessible buffer of image data but we can't manipulate the image yet
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); //converting(casting) the image from BufferedImage type to and integer array now we can modify our image

	//Class Constructor
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale); // here we use the resolution parameters by creating Dimension object called size
		setPreferredSize(size); //Canvas method
		
		screen = new Screen(width, height);
		frame = new JFrame(); //creating the frame
		key = new Keyboard(); //creating the keyboard object
		
		addKeyListener(key);
	}

	public synchronized void start() { //sync to avoid memory conflict
		running = true;
		thread = new Thread(this, "Display"); //this will point at our Game class here
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime(); //very precise than currentTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0; //60 is our update rate in second
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus(); //from component class this way we don't have to click on the java window after debug to move around 
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns; //here we define the rate at which our update is 60 times per second
			lastTime = now;
			while (delta >= 1) {
				update(); //Also called tick(),handle the logic and update the game restrict to 60 times per second (fix it later)
				updates++;
				delta--;
			}
			render(); //show us the image (graphic process) unlimited rendering time per second
			//System.out.println("Running...");
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000; //Important we increase the timer which is the same as changing the 1000 in if to 2000 and so on
				//System.out.println(updates + "ups, " + frames + " fps.");
				frame.setTitle(title + " | " + updates + "ups, " + frames + " fps."); //now you can see the fps and timer on the developer game frame
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	int x=0, y=0, zoom = 1; //temp
	public void update() {
		key.update();
		if (key.up) y--;
		if (key.down) y++;
		if (key.right) x++;
		if (key.left) x--;
		if (key.zoom) zoom = 2; else if (!key.zoom) {
			zoom = 1;
		}

	}

	public void render() { //buffer strategy An area in the RAM for temporary storing the pixels in advance
		BufferStrategy bs = getBufferStrategy(); //java.awt.image and Canvas auto import Eclipse
		if (bs == null) { //if bs is not created then create it
			createBufferStrategy(3); //pretty much always at 3 instead of 2, for triple buffer in faster CPUs
			return;
		}
		/*******************Pay attention to the chronological order of the following method calls  befire main***************/
		screen.clear();
		screen.render(x,y,zoom);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics(); //creating link between graphics and buffer (graphics context) java.awt
		//***********your graphics goes here*****************
		//g.setColor(Color.BLACK);							 //Always do this before you fill 
		//g.setColor(new Color(0, 0, 0)); //Alternative way to get all colors (R,G,B)
		//g.fillRect(0, 0, getWidth(), getHeight()); //using get width and height to make sure we fill the entire window
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null); //this way we input our screen on top of frame 
		g.dispose(); //Disposes all the graphics releases the system resources
		bs.show(); //buffer swapping 
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false); //we don't want our window to able to resize(graphical error):always the first think to apply to frame
		game.frame.setTitle(Game.title); //title of our window
		game.frame.add(game); //adds a component to our frame. fills the window with something. game in this case
		game.frame.pack(); //sets the size
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when you hit the close(X) bottom it will terminate the application process.
		game.frame.setLocationRelativeTo(null); //center the window
		game.frame.setVisible(true); //show our frame. not hidden

		game.start();
	}

}
