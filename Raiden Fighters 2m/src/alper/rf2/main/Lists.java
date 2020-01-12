package alper.rf2.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import alper.rf2.main.interfaces.GameInterfaceA;
import alper.rf2.main.interfaces.GameInterfaceB;

public class Lists // Linked lists of all objects
{
	
	private LinkedList <GameInterfaceA> objectsA = new LinkedList<GameInterfaceA>();// laser,missile
	private LinkedList <GameInterfaceB> objectsB = new LinkedList<GameInterfaceB>();// enemyJets, enemy tanks,enemylaser

	GameInterfaceA objectA;
	GameInterfaceB objectB;

	Random random = new Random();
	private Images images;
	private Game game;
	
	public Lists(Images textures, Game game) 
	{
		this.images = textures;
		this.game = game;
		
	}
	public void update()
	{
		// A object friendly // updates all A objects
		for(int i = 0; i<objectsA.size() ; i++)
		{
			objectA = objectsA.get(i);
			objectA.update();
		}
		// B object enemy // updates all B objects
		for(int i = 0; i<objectsB.size() ; i++)
		{
			objectB = objectsB.get(i);
			objectB.update();
		}
		
	}
	public void draw(Graphics g)
	{
		// A Object friendly // updates all A objects
		for(int i = 0; i<objectsA.size() ; i++)
		{
			objectA = objectsA.get(i);
			objectA.draw(g);
		}
		
		// B Object enemy // updates all B objects
		for(int i = 0; i<objectsB.size() ; i++)
		{
			objectB = objectsB.get(i);
			objectB.draw(g);
		}
	}
	public void addEnemyJet(int enemyCount)
	{
		for(int i=0 ; i< enemyCount; i++)
		{
			addObject(new EnemyJets(random.nextInt(470), -1, images,this, game));
		}
	}
	public void addEnemyTank(int enemyCount)
	{
		for(int i=0 ; i< enemyCount; i++)
		{
			addObject(new EnemyTanks(random.nextInt(240) + 110, -50, images,this, game));
		}
	}
	public void addEnemy()
	{
			addObject(new EnemyJets(random.nextInt(500), -1, images,this, game));	
	}
	public void addObject(GameInterfaceA object)
	{
		objectsA.add(object);
		
	}
	public void removeObject(GameInterfaceA object)
	{
		objectsA.remove(object);
		
	}
	public void addObject(GameInterfaceB object)
	{
		objectsB.add(object);
		
	}
	public void removeObject(GameInterfaceB object)
	{	
		objectsB.remove(object);	
	}
	public LinkedList<GameInterfaceA> getObjectA()
	{
		return objectsA;
	}
	public LinkedList<GameInterfaceB> getObjectB()
	{
		return objectsB;
	}
}