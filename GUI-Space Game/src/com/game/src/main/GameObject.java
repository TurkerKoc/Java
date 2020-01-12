package com.game.src.main;

import java.awt.Rectangle;

public class GameObject //all objects which has coordinates will extend this class
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
