package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;


//controlling bullets
public class Controller 
{
	//created because there are to many bullets and enemies so we don't have handle them one by one
	private LinkedList<EntityA> eA = new LinkedList<EntityA>(); //player bullet
	private LinkedList<EntityB> eB = new LinkedList<EntityB>(); //enemy
	EntityA entA;
	EntityB entB;
	Random r = new Random();
	private Textures tex;
	private Game game;
	public Controller(Textures tex, Game game)
	{
		this.tex = tex;
		this.game = game;
	}
	
	public void addEnemy(int enemyCount) //create enemies -> enemyCount times
	{
		for(int i=0;i<enemyCount;i++)
			addEntity(new Enemy(r.nextInt(640), -10, tex,this,game)); //call addEntity for enemy method overloading
	}
	
	public void tick()
	{
		//A class (player-bullet)
		for (int i = 0; i < eA.size(); i++) 
		{
			entA = eA.get(i);
			entA.tick();
		}
		
		//D class (enemy)
		for (int i = 0; i < eB.size(); i++) 
		{
			entB = eB.get(i);
			entB.tick();
		}
	}
	public void render(Graphics g)
	{
		//A class (player-bullet)
		for (int i = 0; i < eA.size(); i++) 
		{
			entA = eA.get(i);
			entA.render(g);
		}
		//B class (enemy)
		for (int i = 0; i < eB.size(); i++) 
		{
			entB = eB.get(i);
			entB.render(g);
		}
	}
	public void addEntity(EntityA block)
	{
		eA.add(block);
	}
	public void removeEntity(EntityA block)
	{
		eA.remove(block);
	}
	public void addEntity(EntityB block)
	{
		eB.add(block);
	}
	public void removeEntity(EntityB block)
	{
		eB.remove(block);
	}	
	public LinkedList<EntityA> geteA() {
		return eA;
	}
	public LinkedList<EntityB> geteB() {
		return eB;
	}
	/*
	//private LinkedList<Bullet> b = new LinkedList<Bullet>();
	//private LinkedList<Enemy> e = new LinkedList<Enemy>();
	Random r = new Random(); //for enemy random spawn
	Bullet tempBullet;
	Enemy tempEnemy;
	Game game; 
	Textures tex;
	public Controller(Game game, Textures tex)
	{
		this.game = game; 
		this.tex = tex;
		addEnemy(new Enemy(r.nextInt); //random place enemy
	} 
	
	

	public void tick()
	{
		
		for (int i = 0; i < b.size(); i++) 
		{
			tempBullet = b.get(i);
			
			if(tempBullet.getY()<0) //if bullet is out of scope remove it from llist
				removeBullet(tempBullet);
			
			tempBullet.tick();
		}
		for (int i = 0; i < e.size(); i++) 
		{ 
			tempEnemy = e.get(i);
			
			tempEnemy.tick();
		}
		
	}
	public void render(Graphics g)
	{
		for (int i = 0; i < b.size(); i++) 
		{
			tempBullet = b.get(i);
			tempBullet.render(g);
		}
		for (int i = 0; i < e.size(); i++) 
		{
			tempEnemy = e.get(i);
			
			tempEnemy.render(g);
		}
	}
	public void addBullet(Bullet block)
	{
		b.add(block);
	}
	public void removeBullet(Bullet block)
	{
		b.remove(block);
	}
	public void addEnemy(Enemy block)
	{
		e.add(block);
	}
	public void removeEnemy(Enemy block)
	{
		e.remove(block);
	}
	*/
}
