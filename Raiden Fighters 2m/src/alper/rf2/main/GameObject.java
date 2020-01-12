package alper.rf2.main;

import java.awt.Rectangle;

public class GameObject // Root of all object in this game
{						// Each object has a x,y value and bounds with rectangle
	protected double x;
	protected double y;
	protected boolean collided = false;
	
	public GameObject(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getBounds(int width, int height) 
	{
		return new Rectangle((int)x, (int)y, width,height);
	}
}
