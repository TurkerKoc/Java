
package alper.rf2.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import alper.rf2.main.interfaces.GameInterfaceA;

public class Missile extends GameObject implements GameInterfaceA
{
private Images images;
BufferedImage image;
private Game game;
public static boolean collided= false;
private int missileWidth = 5, missileHeight = 5  ,x1=0,x2=0,x3=0,x4=0;
private boolean collision = false;
	
	public Missile(double x, double y, Images images, Game game) 
	{	
		super(x, y);
		this.game = game;
		this.images = images;
		image = game.getLaserImage();
		
		x1 = (int) x;
		x2 = (int) x;
		x3 = (int) x;
		x4 = (int) x;
	}

	public void update()
	{
		if (missileHeight <= 80)
		{
			missileWidth += 1;
			missileHeight +=1;
		}
		
		x1 -=2;
		x2 +=2;
		x3 -=4;
		x4 +=4;
		y-=10;
		if (Collision.isCollided(this, game.objectB)) 
		{		
			collision = true;
			this.setX(1000);
		}
	}

	public void draw(Graphics g)
	{
			if (collision)
			{		
				game.score+=10;
				this.setX(1000);
				collision = false;
			}
			g.drawImage(images.missileImage, (int)x,(int)y, missileWidth, missileHeight, null);
			g.drawImage(images.missileImage, (int)x1,(int)y, missileWidth, missileHeight, null);
			g.drawImage(images.missileImage, (int)x2,(int)y, missileWidth, missileHeight, null);
			g.drawImage(images.missileImage, (int)x3,(int)y, missileWidth, missileHeight, null);
			g.drawImage(images.missileImage, (int)x4,(int)y, missileWidth, missileHeight, null);	
	}
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, missileWidth, missileHeight);
	}
	public Rectangle getBounds1() 
	{
		return new Rectangle((int)x1, (int)y, missileWidth, missileHeight);
	}
	public Rectangle getBounds2() 
	{
		return new Rectangle((int)x2, (int)y, missileWidth, missileHeight);
	}
	public Rectangle getBounds3() 
	{
		return new Rectangle((int)x3, (int)y, missileWidth, missileHeight);
	}
	public Rectangle getBounds4() 
	{
		return new Rectangle((int)x4, (int)y, missileWidth, missileHeight);
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
	public int getMissileWidth() {
		return missileWidth;
	}
	public void setMissileWidth(int missileWidth) {
		this.missileWidth = missileWidth;
	}
	public int getMissileHeight() {
		return missileHeight;
	}
	public void setMissileHeight(int missileHeight) {
		this.missileHeight = missileHeight;
	}
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getX2() {
		return x2;
	}
	public void setX2(int x2) {
		this.x2 = x2;
	}
	public int getX3() {
		return x3;
	}
	public void setX3(int x3) {
		this.x3 = x3;
	}
	public int getX4() {
		return x4;
	}
	public void setX4(int x4) {
		this.x4 = x4;
	}	
}
