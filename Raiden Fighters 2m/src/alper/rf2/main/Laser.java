package alper.rf2.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import alper.rf2.main.interfaces.GameInterfaceA;

public class Laser extends GameObject implements GameInterfaceA // Player Laser
{
private Images images;
BufferedImage image;
private Game game;
private boolean collision = false;
public static boolean collided= false;

public Laser(double x, double y, Images images, Game game)// Player Laser
{	
		super(x, y);
		this.game = game;
		this.images = images;
		image = game.getLaserImage();
}
	public void update()
	{
		y-=10;// moves up 10 everytime when this method called
	
		if (Collision.isCollided(this, game.objectB)) // collision with laser and enemies
		{						
			collision = true;
		}
	}
	
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 5, 20);
	}
	public void draw(Graphics g)
	{
		
		
		if (collision )
		{
			
			game.score+=10;
			this.setX(this.getX() + 700);


		}
		g.drawImage(images.laserImage, (int)x,(int)y, null);
	}
	@Override
	public double getY() 
	{
		return y;
		
	}
	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}
	@Override
	public double setX(double x) {
		
		return this.x = x;
	}
	@Override
	public double setY(double y)
	{
		return this.y = y;

	}
	@Override
	public Rectangle getBounds1() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	@Override
	public Rectangle getBounds2() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	@Override
	public Rectangle getBounds3() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	@Override
	public Rectangle getBounds4() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	

}
