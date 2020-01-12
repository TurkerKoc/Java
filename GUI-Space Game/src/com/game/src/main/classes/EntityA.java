package com.game.src.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;
//entities which can not collide each other
public interface EntityA //bullet, player
{
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds(); //getting bounds for every entity for collision
	
	public double getX();
	public double getY();
}
