package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Game extends Canvas implements Runnable 
{
	public static final int WIDTH = 320; //Width of our frame
	public static final int HEIGHT = WIDTH/12 * 9;  //height of our frame
	public static final int SCALE = 2; //scale factor
	public static String TITLE = "2D Space Game"; //Title of the game
	
	private boolean running = false; //game depends on this
	private Thread thread;
	
	//buffered image is loading your image before projecting it
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB); //RGB red green blue
	private BufferedImage spriteSheet = null; //SpriteSheet image will be loaded here
	private BufferedImage backGround = null; //backgorund image
	
	private Player p; //player object
	private Controller c; //to control all bullets and enemies
	private Textures tex; //all textures are grabbed from SpriteSheet in Textures class
	
	private boolean isShooting = false;
	
	private int enemyCount = 5; //telling how many spaceships to spawn depending on enemy you killed  (killed+1)
	private int enemyKilled = 0; 
	
	public LinkedList<EntityA> ea; //(player-missile)
	public LinkedList<EntityB> eb; //(enemy)
	
	public static int HEALTH = 100 * 2;
	
	private Menu menu;
	
	public static enum STATE //enum for seperating game and menu states
	{
		MENU,
		GAME
	};
	
	public static STATE state = STATE.MENU; //current state
		
	
	public static void main(String args[])
	{
		Game game = new Game(); //game object
		
		// setting size of the component
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT*SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT*SCALE));
		
		JFrame frame = new JFrame(game.TITLE); //creating our frame
		frame.add(game);
		frame.pack(); //Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //in the middle of the screen
		frame.setVisible(true);
		
		game.start(); //Starting our game

	}
	
	
	public void init() //initialize
	{
		requestFocus(); //play without a first click on screen
		BufferedImageLoader loader = new BufferedImageLoader();
		spriteSheet = loader.loadImage("/SpriteSheet.png"); //loading image
		backGround = loader.loadImage("/Background.png");
		
		
		tex = new Textures(this); //need to run before player so image will be grabbed from SpriteSheet
		c = new Controller(tex,this); //creating bullet and enemy Controller 
		p = new Player(200, 200,tex,this,c); //returning the game

		menu = new Menu();
		ea = c.geteA(); //getting all a entities in controller
		eb = c.geteB();
		
		this.addKeyListener(new KeyInput(this)); //put this otherwise key inputs will not work
		this.addMouseListener(new MouseInput());
		c.addEnemy(enemyCount);
		
		//SpriteSheet ss = new SpriteSheet(spriteSheet); //Creating to grab images from spriteSheet image
		
		
		//temp -> grabbing loaded image
		//SpriteSheet ss = new SpriteSheet(spriteSheet);
		//player = ss.grabImage(1, 1, 31, 31);
	}
	
	//only one thread at a time can run it
	private synchronized void start() //actually start our thread
	{
		  if(running)
			  return;
		  running = true; //if it is not running run our game loop
		  thread = new Thread(this);
		  thread.start();
	}
	
	private synchronized void stop() //stop game loop
	{
		if(!running)
			return;
		running = false; //stop our game loop
		try {
			thread.join(); //Waits for this thread to die.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
	}
	@Override
	public void run() //when the thread is created it calls run 
	{
		init(); //initializing 
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0; // 60 is frame count per second
		double ns = 1000000000 / amountOfTicks;
		double delta = 0; //Change in time/frame to go lastTime to now
		
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running)
		{
			//game loop works until you exit the game
			long now = System.nanoTime();
			delta += (now - lastTime) / ns; 
			lastTime = now;
			
			if(delta >= 1)
			{
				tick();
				updates++;				
				delta--; //reset the change
			}
			try
			{
				render();
				frames++;
			} catch (Exception e){}
			
			if(System.currentTimeMillis() - timer >1000)
			{
				timer += 1000;
				//System.out.println(updates + "Ticks , Fps " + frames); // 60 ticks and 8million fps
				updates = 0;
				frames = 0;
			}
		}
		stop(); //after the game we will stop thread
		
	}
	
	public void tick() //=update() -> everything that game updates
	{
		//if our state is GAME then run tick methods
		if(state == STATE.GAME)
		{
			p.tick(); //calling players updates
			c.tick(); //bullets
		}
		if(enemyKilled >= enemyCount) //if you kill enemies create more enemies
		{
			enemyCount += 2;
			enemyKilled = 0;
			c.addEnemy(enemyCount);
		}
	}
	public void render() //draw things to the screen
	{
		BufferStrategy bs = this.getBufferStrategy(); //return used buffer strategy
		if(bs == null) // previous buffer disposed, returns null
		{
			// creates 3 frames before drawing the frame
			createBufferStrategy(3); //method from canvas loading 3 images if it has time just before showing 
			
			return;
		}
		Graphics g = bs.getDrawGraphics(); //graphics contents for drawing buffers
		//DRAWIN AREA
		/////////////////////////////////// 
		g.drawImage(image,0,0,getWidth(),getHeight(), this); //black background on screen
		//g.setColor(Color.red);
		//g.fillRect(0, 0, 800, 800);
		
		g.drawImage(backGround,0,0,null);

		if(state == STATE.GAME) //if state is game then run it
		{
			p.render(g); //player image
			c.render(g); //bullets bullets	
			
			//health image
			g.setColor(Color.white);
			g.fillRect(5, 5, 200, 50);
			
			g.setColor(Color.gray);
			g.fillRect(5, 5, 200, 50);
			
			g.setColor(Color.green);
			g.fillRect(5, 5, HEALTH, 50);
		}
		else if(state == STATE.MENU)
		{
			menu.render(g); //render menu state
		}
		//temp
		//g.drawImage(player, 200, 200, this);

		///////////////////////////////////		
		g.dispose(); // releases resources
		bs.show(); // shows the frame
	}
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		if(state== STATE.GAME) //if game state 
		{
			if(key == KeyEvent.VK_RIGHT)
			{
				p.setVelX(5);
			}
			else if(key == KeyEvent.VK_LEFT)
			{
				p.setVelX(-5);
				
			}
			else if(key == KeyEvent.VK_DOWN)
			{
				p.setVelY(5);
			}
			else if(key == KeyEvent.VK_UP)
			{
				p.setVelY(-5);
			}
			else if(key == KeyEvent.VK_SPACE && !isShooting) //bullet with space
			{
				isShooting = true;
				c.addEntity(new Bullet(p.getX(), p.getY()-5, tex, this));
			}		
		}
	}
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT)
		{
			p.setVelX(0);
		}
		else if(key == KeyEvent.VK_LEFT)
		{
			p.setVelX(0);

		}
		else if(key == KeyEvent.VK_DOWN)
		{
			p.setVelY(0);
		}
		else if(key == KeyEvent.VK_UP)
		{
			p.setVelY(0);
		}
		else if(key == KeyEvent.VK_SPACE) //bullet with space
		{
			isShooting = false;
		}

	}
	

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	public int getEnemyCount() {
		return enemyCount;
	}
	public int getEnemyKilled() {
		return enemyKilled;
	}
	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}
	public void setEnemyKilled(int enemyKilled) {
		this.enemyKilled = enemyKilled;
	}
	public boolean getRunning()
	{
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
}
