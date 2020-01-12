package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animations;

public class Enemy extends GameObject implements EntityB
{
	Random r = new Random(); //for enemy random spawn
	private int speed = r.nextInt(3)+1; //giving random speed
		
	private Textures tex; //for enemy image
	
	
	Animations anim; //for fire animations
	
	private Game game;
	private Controller c; //we need to remove enemy so we need controller
	public Enemy(double x, double y, Textures tex,Controller c, Game game) 
	{
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;
		
		anim = new Animations(5,tex.enemy[0],tex.enemy[1],tex.enemy[2]);
		
	}
	
	public void tick()
	{
		y += speed; //giving random speed
		if(getY() >(Game.HEIGHT*Game.SCALE)) //if enemy is out of the bounds then spawn at the beginning again
		{
			speed = r.nextInt(3) + 1; //we can change speed
			setY(-10);
			setX(r.nextInt(Game.WIDTH * Game.SCALE));
		}
		anim.runAnimation();
		
		for (int i = 0; i < game.ea.size(); i++) 
		{
			EntityA tempEnt = game.ea.get(i);
			if(Physics.Collision(this, tempEnt)) //is this enemy collided with any of the entityA
			{
				c.removeEntity(this); //removing killed enemy
				c.removeEntity(tempEnt); //remove Bullet
				game.setEnemyKilled(game.getEnemyKilled()+ 1); //increase enemy killed count for spawning more enemies
			}
		}

		
	}
	public void render(Graphics g)
	{
		g.drawImage(tex.enemy[0], (int)x, (int)y, null);
		anim.drawAnimation(g, x, y, 0);
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public Rectangle getBounds() //getting bounds for collision
	{
		return new Rectangle((int)x, (int)y, 31, 31);  //coordinates and size
	}

}
