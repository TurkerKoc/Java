package dev.codenmore.tilegame.entities;

import java.awt.Graphics;

//everything except tiles are entity (creatures->players)
public abstract class Entitiy 
{
	protected float x,y; //to achieve smooth movement on screen its float
	public Entitiy(float x, float y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public abstract void update();
	public abstract void draw(Graphics g);
}
