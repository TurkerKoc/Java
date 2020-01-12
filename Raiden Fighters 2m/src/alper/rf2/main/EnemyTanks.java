package alper.rf2.main;

import java.awt.Graphics;

import java.util.Random;
import java.awt.Rectangle;

import alper.rf2.main.interfaces.GameInterfaceB;

public class EnemyTanks extends GameObject implements GameInterfaceB
{
	Random random = new Random();
	private Images images;
	private double speed = 2;
	private Game game;
	private Lists lists;
	boolean collision=false;
	Thread runner;
	
	private boolean laserCollision; // value for collision with player laser and enemy tank
	private boolean missileCollision;// value for collision with player missile and enemy tank
	private static boolean isCollided; // value for any collision
	private static int collisionTimer; // timer  value for animation
	protected static double xPosition = 0 , yPosition = 0; // holds enemy position after deleting enemy for explosion animation
	protected static boolean isCollidedWithPlayer = false;  // Value for collision with enemy and player

	static int count = 0; // used as a timer for create enemy laser
	
	public EnemyTanks(double x, double y,Images images,Lists lists, Game game) 
	{
		super(x,y);
		this.images = images;
		this.lists = lists;
		this.game= game;
	}	
	public void update() // updates enemy tank information 
	{			
		y+=speed; // moves tank to the bottom of the screen
		count++; // increases timer
		
		if (Player.playerX - x > 0) // if enemy position is left of the player
		{	
			if (Player.playerX - x < 120) // if distance with enemy and player lower than 120
			{
				if (x >=110) // if tank is not on the grass
				{
					x--; // move enemy to the left
				}
			}				
		}
		else /// if enemy position is right of the player
		{
			if (x- Player.playerX < 120) // if distance with enemy and player lower than 120
			{
				if (x <=350) // if tank is not on the grass
				{
					x++; // move enemy to the right
				}
			}
		}
		if (count == 100) // count increases 30 for every second. 100 is about 3.3 seconds. Enemy shoots a laser every 3.3 seconds.				
		{
			lists.addObject(new EnemyLaser((int)x,(int)y, images,game));	
			count =0; // for calculate time for another shoot
		}

		if (Collision.isCollided(this, game.objectA) || Collision.isCollided3(this, game.objectA)) // if enemy tank hit by a missile or a laser from player
		{		
			lists.addEnemyTank(1); // add enemy tank because collided enemy jet will be removed from screen
			missileCollision = true;
			laserCollision = true;
			isCollided = true;
			
		}
		if (isCollided)// if collision
		{
			collisionTimer++;// start timer for explosion animation

		}
		if (this.getY() > 695) // if enemy reaches to the end of the screen, remove it and add one more enemy to the top of the screen
		{
			lists.removeObject(this); // remove enemy tank
			lists.addEnemyTank(1); // add enemy tank to the top of the screen
		}
	}
	public void draw(Graphics g)
	{
		// my animation array size 14. there is at most index of 130. Checking value for greater than 130 is avoiding from arrayIndexOutOfBoundsException
		if(collisionTimer >=130)
		{
			isCollided = false; // setting false for next animation
			collisionTimer = 0; // setting 0 for next animation
		}
		if (isCollided) // if any collision occurs, animate animation array. 
		{
			//collisionTimer/10 for reach every index of explosionArray.
			g.drawImage(images.explosionArray[collisionTimer/10], (int)xPosition-50,(int)yPosition-20, null);
		}
		
		if (missileCollision || laserCollision) // if enemy tank hit by a player laser or player missile
		{
			isCollided =true; 
			Player.score+=20;// increase player score by 20
			lists.removeObject(this); // enemy tank
			xPosition = this.getX(); // assign x position value of collided enemy tank to static value of xPosition
			yPosition = this.getY(); // assign x position value of collided enemy tank to static value of yPosition
		}			
		else // if not collided, draw enemy tank to the frame with new values/
		{
			g.drawImage(images.enemyTankImage, (int)x,(int)y, null);
		}
	}
	
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 40,70); // enemy tank bounds (x,y,width,height)
	}
	public double getX() 
	{
		return x;
	}
	public double setX(double x)
	{
		return this.x = x;
	}
	public double getY() 
	{
		return y;
	}
	public double setY(double y) 
	{
		return this.y = y;
	}
	
	

}
