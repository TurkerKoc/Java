package dev.codenmore.tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.ImageLoader;
import dev.codenmore.tilegame.gfx.SpriteSheet;
import dev.codenmore.tilegame.input.KeyManager;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.MenuState;
import dev.codenmore.tilegame.states.State;
import dev.codenmore.tilegame.input.KeyManager;
//main class of our game
public class Game implements Runnable
{
	private Display display;
	public String title;
	public int width, height;
	
	private Thread thread;
	private boolean running = false;
	
	//buffer is used to prevent flickering(titreme) on your screen
	//buffer is like a invisible screen firstly everything is drawed to buffers the to the screen
	private BufferStrategy bs; //the way of drawings things to the screen
	private Graphics g; //graphic object allow us to draw things to the canvas
	
	//private BufferedImage testImage;
	//private SpriteSheet sheet;
	
	//STATES
	private State gameState;
	private State menuState;
	
	
	//input
	private KeyManager keyManager;
	
	public Game(String title, int width, int height) 
	{
		this.title = title;
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
	}
	
	//initialize all graphics.. 
	public void init()
	{
		display = new Display(title, width, height); //creating our screen
		display.getFrame().addKeyListener(keyManager); //Adding a keyListener to control from keyboard
		//testImage = ImageLoader.loadImage("/textures/sheet.png"); //sprite sheet image 128*128 which contains 32*32 images
		//sheet = new SpriteSheet(testImage);
		Assets.init(); //it will load all assets once
		
		gameState = new GameState(this); //pass instance of the game class
		menuState = new MenuState(this); //passing game object to the states
		State.setState(gameState);
	}
	private void update() //update(tick) everything
	{
		keyManager.update(); //change in keymanager
		
		if(State.getState() != null)
			State.getState().update();
	}
	private void draw() //draw(render) everything to screen
	{
		 bs = display.getCanvas().getBufferStrategy();
		 if (bs == null) 
		 {
			 display.getCanvas().createBufferStrategy(3); //more than 3 buffers are not recommended
			 return;
		 } 
		 g = bs.getDrawGraphics(); //think like this is creating paintbrush for your painting
		//Draw here!
		 
		 g.clearRect(0, 0, width, height); //it will clear all pixels for us
		 //g.drawRect(10, 50, 50, 70); //creates not filled rectangle
		 //g.setColor(Color.red); //every line after this will be red 
		 //g.fillRect(10,50, 50, 70);
		 //g.setColor(Color.green); //now it will be green
 		 //g.fillRect(0, 0, 10, 10);
		 //g.drawImage(testImage, 0,0,null); //drawing image (defined in init) into screen
		 //g.drawImage(sheet.crop(0, 0, 32, 32), 5, 5, null); //displaying first image from spriteSheet image
		 //g.drawImage(sheet.crop(32,0,32,32), 10,10,null); //displaying second image from spriteSheet image
		 //g.drawImage(Assets.grass, x,5,null); // drawing image from Assets(SpriteSheet)
		 if(State.getState() != null)
			 State.getState().draw(g);
		//End Drawing!
		 bs.show();
		 g.dispose(); //graphics object get done properly
	}
	
	@Override
	public void run()  //creating thread will cause to calling run method
	{
		init();
		//Game loop = update all variables, positions, objects etc. then draw everything to the screen and repeat
		
		//below code for making game speed same for everybody
		int fps = 60; //how many times update will be called in a sec
		double timePerTick = 1000000000 / fps; //100000000 = 1sec -> 1sec / fps = update call for 1 sec
		double delta = 0;
		long now;
		long lastTime = System.nanoTime(); //nanoTime() returns computer's current time in nanoSec
		long timer = 0;
		int updates = 0;
		
		while(running)
		{
			now = System.nanoTime(); //now = computer current time in nanoSec
			delta += (now - lastTime) / timePerTick; //delta = how much time we have until we have to call update and draw
			timer += now-lastTime; //time passed since we last called this part
			lastTime = now; //when we are done with this part of the code last = now
			
			if(delta >= 1) //in order to achieve 60 frames per sec we have to call update and draw if delta>=1
			{
				update(); 
				draw();	
				updates++; //we called update one more time
				delta--;
			}
			if(timer >= 1000000000) //if our timer >= 1 sec(in nanoSec)
			{
				System.out.println("Ticks and Frames: " + updates);
				updates = 0;
				timer = 0;
			}
		}
		stop();
	}
	public KeyManager getKeyManager() //return keymaneger object 
	{
		return keyManager;
	}
	public synchronized void start() //synchronized = only one thread at a time can execute it
	{
		if (running) //if it is already running do not initialize thread again 
			return; 
		running = true;
		thread = new Thread(this); //what class do you want to execute = Game Class(this) 
		thread.start(); //will call run method
	} 
	public synchronized void stop()
	{
		if (!running)  // if running false do not stop thread again 
			return; 
		running = false;
		try {
			thread.join(); //Close thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
