package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.main.Game.STATE;
import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animations;

public class Player extends GameObject implements EntityA
{	
	private double velX = 0;
	private double velY = 0;
	
	private BufferedImage player;
	
	private Textures tex;
	Animations anim;
	private Game game;
	Controller c;
	public Player(double x, double y, Textures tex, Game game, Controller c) //getting textures from Game class
	{
		super(x, y); //sending game object to coordinates
		this.tex = tex;
		this.game = game;
		this.c = c;
		anim = new Animations(5,tex.player[0],tex.player[1],tex.player[2]);
		//SpriteSheet ss = new SpriteSheet(game.getSpriteSheet()); //getting loaded image from game
		//player = ss.grabImage(1, 1, 31, 31); //grabbing the image from spriteSheet
	}
	
	public void tick()
	{
		x+=velX; //do this for smooth movement (called 60 times in sec)
		y+=velY; //do this for smooth movement (called 60 times in sec)
		
		//limiting player not to go out of screen
		if(x<= 0)
			x=0;
		if(x >= 640 -30)
			x=640-30;
		if(y<= 0)
			y=0;
		if(y >= 480 - 41)
			y=480 - 41;
		
		//if player collide with enemy then -10 health
		for (int i = 0; i < game.eb.size(); i++) 
		{
			EntityB tempEnt = game.eb.get(i);
			if(Physics.Collision(this, tempEnt))
			{
				c.removeEntity(tempEnt);
				game.HEALTH -= 10;
				game.setEnemyKilled(game.getEnemyKilled() + 1);
				if(game.HEALTH<=0)
				{
					game.state = STATE.MENU;
					game.HEALTH=200;
				}
			}
		}
		
		anim.runAnimation();
		
	}
	public void render(Graphics g)
	{
		g.drawImage(tex.player[0], (int)x, (int)y, null);
		anim.drawAnimation(g, x, y, 0);
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
	public Rectangle getBounds() //getting bounds for collision
	{
		return new Rectangle((int)x, (int)y, 31, 31);  //coordinates and size
	}
}
