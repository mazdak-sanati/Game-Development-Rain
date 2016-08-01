 package com.thedude.rain;

 import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

 public class Game extends Canvas implements Runnable { 	 //our class in sub-class of Canvas meaning its a component
 	private static final long serialVersionUID = 1L; 		 //ITS a convention of Java just do it to remove game warning in the declaration of class
 	
 	//declaring the game frame resolution
 	public static int width = 300;
 	public static int height = width / 16 * 9;
 	public static int scale = 3;
 	
 	private Thread thread;	 								 //declare out thread
 	private JFrame frame;									 //from JFrame library import the frame
 	private boolean running = false; 						 //declare our running for the game loop later used in start and stop method
 	
 	//Class Constructor
 	public Game() {
 		Dimension size = new Dimension ( width * scale, height * scale ); 	// here we use the resolution params by creating Dimension object called size
 		setPreferredSize (size); 							 //Canvas method
 		frame = new JFrame(); 								 //creating the frame
 	}
 	
 	public synchronized void start() { 						 //sync to avoid memory conflict
 		running = true;
 		thread = new Thread(this, "Display"); 				 //this will point at our Game class here
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
 		while (running){
 			update();									 	 //Also called tick(),handle the logic and update the game restrict to 60 times per second (fix it later)
 			render();										 //show us the image (graphic process) unlimited rendering time per second
 			//System.out.println("Running...");
 		}
 	}
 	public void update(){
 		
 	}
 	
 	public void render(){									 //buffer strategy An area in the RAM for temporary storing the pixels in advance
 		BufferStrategy bs = getBufferStrategy();			 //java.awt.image and Canvas auto import Eclipse
 		if (bs == null){ 									 //if bs is not created then create it
 			createBufferStrategy(3);						 //pretty much always at 3 instead of 2, for triple buffer in faster CPUs
 			return;
 		}
 		
 		Graphics g = bs.getDrawGraphics();					 //creating link between graphics and buffer (graphics context) java.awt
 		//***********your graphics goes here*****************
 		//g.setColor(Color.BLACK);							 //Always do this before you fill 
 		g.setColor(new Color(69, 97, 210));				 	 //Alternative way to get all colors (R,G,B)
 		g.fillRect(0, 0, getWidth(), getHeight());			 //using get width and height to make sure we fill the entire window
 		g.dispose();										 //Disposes all the graphics releases the system resources
 		bs.show();											 //buffer swapping 
 	}

 	public static void main(String[] args) {
 		Game game = new Game();
 		game.frame.setResizable(false);						 //we don't want our window to able to resize(graphical error):always the first think to apply to frame
 		game.frame.setTitle("Rain"); 						 //title of our window
 		game.frame.add(game); 								 //adds a component to our frame. fills the window with something. game in this case
 		game.frame.pack(); //sets the size
 		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	 //when you hit the close(X) bottom it will terminate the application process.
 		game.frame.setLocationRelativeTo(null);				 //center the window
 		game.frame.setVisible(true);						 //show our frame. not hidden
 		
 		game.start();
 	}

 }
