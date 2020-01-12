package alper.rf2.main;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;
import alper.rf2.main.interfaces.GameInterfaceB;

public class EnemyJets extends GameObject implements GameInterfaceB
{
	Random random = new Random(); 
	private Images images;
	private double speed = random.nextInt(4)+1; // Random number for speed of enemyJets
	private Game game;
	private Lists lists;
	boolean missileCollision=false;   //*	Value changes after
	boolean laserCollision=false;     //* 	missile or laser hits to enemy
	private static int collisionTimer = 0;  // timer value for explosion
	protected static double xPosition = 0 , yPosition = 0; // hold enemy position after deleting enemy for explosion animation
	private static boolean isCollided = false; // changes to true after collision
	protected static boolean isCollidedWithPlayer = false;  // Value for collision with enemy and player
	static int count = 0; // value for enemy laser
	
	public EnemyJets(double x, double y,Images images,Lists lists, Game game) 
	{
		super(x,y);
		this.images = images;
		this.lists = lists;
		this.game= game;
	}
	
	public void update()//update
	{			
		y+=speed; // speed value is created randomly. Y is speed of enemyJets on y axis
		count++; // game runs on 30 fps, every second count increases by 30 because update method called 30 times per second;

	
		if (Player.playerX - x > 0) // if enemy position is left of the player
		{	
			if (Player.playerX - x < 120) // if distance with enemy and player lower than 120
			{
				if (x >=10) // if enemy did not hit the wall
				{
					x--; // move enemy to the left

				}
			}				
		}
		else /// if enemy position is right of the player
		{
			if (x- Player.playerX < 120) // if distance with enemy and player lower than 120
			{
				if (x <=455) // if enemy did not hit the wall
				{
					x++; // move enemy to the right
				}
			}
		}
	
		if (count == 100) // count increases 30 for every second. 100 is about 3.3 seconds. Enemy shoots two lasers every 3.3 seconds.		{					
		{
			lists.addObject(new EnemyLaser((int)x + 20,(int)y, images,game));	
			lists.addObject(new EnemyLaser((int)x + 20,(int)y + 40, images,game));	// +40 difference for shooting second laser behind first laser
			count =0; // for calculate time for another shoot
		}
		
	
		
		if (Collision.isCollided(this, game.objectA) || Collision.isCollided3(this, game.objectA)) // if enemy jet hit by a missile or a laser from player
		{		
			laserCollision = true;
			missileCollision = true;
			lists.addEnemyJet(1); // adds enemy jet because collided enemy jet will be removed from screen
			
		}
		if (laserCollision || missileCollision) // if collided with laser or missile
		{
			isCollided =true;
			xPosition = this.getX(); // assign x position value of collided enemy jet to static value of xPosition
			yPosition = this.getY(); // assign x position value of collided enemy jet to static value of xPosition

		}
		if (isCollided || isCollidedWithPlayer) // if any collision occurs
		{
			collisionTimer++; // start timer for explosion animation

		}
	
		if (this.getY() > 695) // if enemy reaches to the end of the screen, remove it and add one more enemy to the top of the screen
		{
			lists.removeObject(this);// removes enemy
			lists.addEnemyJet(1);// adds enemy
		}	
	}
	
	public void draw(Graphics g)//draw
	{
		// my animation array size 14. there is at most index of 130. Checking value for greater than 130 is avoiding from arrayIndexOutOfBoundsException
		if(collisionTimer >=130)
		{
			isCollided = false;// setting false for next animation
			isCollidedWithPlayer =false; // setting false for next animation
			collisionTimer = 0;// setting 0 for next animation
		}
		if (isCollided || isCollidedWithPlayer) // if any collision occurs, animate animation array. 
		{
			//collisionTimer/10 for reach every index of explosionArray.
			g.drawImage(images.explosionArray[collisionTimer/10], (int)xPosition-50,(int)yPosition-20, null);
		}
		
		if (missileCollision || laserCollision) // if player hits enemyJet
		{
			Player.score+=10; // increase score by 10
			lists.removeObject(this);// remove enemyJet
		}
		else // if there is no collision, draw enemy to the frame with new x and y positions
		{
			g.drawImage(images.enemyJetImage, (int)x,(int)y, null);
		}
	}
	
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 32, 32);// x position, y position, width, height
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
