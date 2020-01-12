package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.main.classes.EntityA;
import com.game.src.main.libs.Animations;

//single bullet properties
public class Bullet extends GameObject implements EntityA
{		
	private Textures tex;
	private Game game;
	
	Animations anim;
	
	public Bullet(double x, double y, Textures tex, Game game) //getting textures from Game class
	{
		super(x,y);
		this.tex = tex;
		this.game = game;
		
		anim = new Animations(5,tex.missile[0],tex.missile[1],tex.missile[2]);
	}
	public void tick()
	{
		y-=10;
		//if(Physics.Collision(this,game.eb))
		//{
		//	System.out.println("Collision detected");
		//}
		anim.runAnimation();
		
	}
	public void render(Graphics g)
	{
		g.drawImage(tex.missile[0], (int)x, (int)y, null);
		anim.drawAnimation(g, x, y, 0);
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public Rectangle getBounds() //getting bounds for collision
	{
		return new Rectangle((int)x, (int)y, 31, 31);  //coordinates and size
	}

}
