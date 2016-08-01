 package com.thedude.rain;

 import java.awt.Canvas;
 import java.awt.Dimension;

 import javax.swing.JFrame;

 public class Game extends Canvas implements Runnable { //our class in sub-class of Canvas meaning its a component
 	private static final long serialVersionUID = 1L; //ITS a convention of Java just do it to remove game warning in the declaration of class
 	
 	//declaring the game frame resolution
 	public static int width = 300;
 	public static int height = width / 16 * 9;
 	public static int scale = 3;
 	
 	private Thread thread; //declare out thread
 	private JFrame frame;	//from JFrame library import the frame
 	private boolean running = false; //declare our running for the game loop later used in start and stop method
 	
 	//Class Constructor
 	public Game() {
 		Dimension size = new Dimension ( width * scale, height * scale ); // here we use the resolution params by creating Dimension object called size
 		setPreferredSize (size); //Canvas method
 		frame = new JFrame(); // creating the frame
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
 		while (running){
 				System.out.println("Running...");
 		}
 	}


 	public static void main(String[] args) {
 		Game game = new Game();
 		game.frame.setResizable(false); //we don't want our window to able to resize(graphical error):always the first think to apply to frame
 		game.frame.setTitle("Rain"); //title of our window
 		game.frame.add(game); //adds a component to our frame. fills the window with something. game in this case
 		game.frame.pack(); //sets the size
 		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when you hit the close(X) bottom it will terminate the application process.
 		game.frame.setLocationRelativeTo(null);//center the window
 		game.frame.setVisible(true);//show our frame. not hidden
 		
 		game.start();
 	}

 }
