package turker.ho.main;

import java.awt.Rectangle;

//common things for every game object
public class GameObject
{
	public double x,y;
	public GameObject(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getBounds(int width, int height) //getting bounds for collision
	{
		return new Rectangle((int)x, (int)y, width, height);  //coordinates and size
	}
}
