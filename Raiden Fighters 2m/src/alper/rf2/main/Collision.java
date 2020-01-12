package alper.rf2.main;

import java.util.LinkedList;

import alper.rf2.main.interfaces.GameInterfaceA;
import alper.rf2.main.interfaces.GameInterfaceB;


/*
 *  GameInterfaceA (Friendly Objects) -> Player, PlayerLaser, PlayerMissile
 * 	GameInterfaceB (Enemy Objects) -> EnemyJets, EnemyTanks, EnemyLaser
 * 
 */

public class Collision 
{
	
	// collision with a laser and enemies
	public static boolean isCollided(GameInterfaceA objectA, LinkedList<GameInterfaceB> objectsB)
	{
		for(int i=0; i<objectsB.size() ; i++)
		{
			if (objectsB.get(i) != null && objectA != null ) 
			{
				if (objectA.getBounds().intersects(objectsB.get(i).getBounds()))
				{
					if (objectsB.get(i) instanceof EnemyJets || objectsB.get(i) instanceof EnemyTanks)
					{
						
							return true;
					}
				}
			}
		}
		return false;
		
	}
	// collision with a missile and enemies
	public static boolean isCollided3(GameInterfaceB objectB, LinkedList<GameInterfaceA> objectsA)
	{
		for(int i=0; i<objectsA.size() ; i++)
		{
			if (objectsA.get(i).getBounds() != null &&
				objectsA.get(i).getBounds1() != null &&
				objectsA.get(i).getBounds2() != null &&
				objectsA.get(i).getBounds3() != null &&
				objectsA.get(i).getBounds4() != null)
			{					
					if (objectB.getBounds().intersects(objectsA.get(i).getBounds()) ||
						objectB.getBounds().intersects(objectsA.get(i).getBounds1()) ||
						objectB.getBounds().intersects(objectsA.get(i).getBounds2()) ||
						objectB.getBounds().intersects(objectsA.get(i).getBounds3()) ||
						objectB.getBounds().intersects(objectsA.get(i).getBounds4()) 
						)
						{
						
							if (objectB.getBounds().intersects(objectsA.get(i).getBounds())) 
							{
								objectsA.get(i).setX(2000);
							}
							if (objectB.getBounds().intersects(objectsA.get(i).getBounds1())) 
							{
								objectsA.get(i).setX(2000);
							}
							if (objectB.getBounds().intersects(objectsA.get(i).getBounds2())) 
							{
								objectsA.get(i).setX(2000);
							}
							if (objectB.getBounds().intersects(objectsA.get(i).getBounds3())) 
							{
								objectsA.get(i).setX(2000);
							}
							if (objectB.getBounds().intersects(objectsA.get(i).getBounds4())) 
							{
								objectsA.get(i).setX(2000);
							}
							
							return true;
							
						}
			}	
		}
		return false;
	}
	// collision with a player and enemy lasers
	public static boolean isCollided2(GameInterfaceA objectA, LinkedList<GameInterfaceB> objectsB)
	{
		for(int i=0; i<objectsB.size() ; i++)
		{
			if (objectsB.get(i) != null) 
			{		
					if (objectA.getBounds().intersects(objectsB.get(i).getBounds()))
					{
						 if (objectsB.get(i) instanceof EnemyJets || objectsB.get(i) instanceof EnemyLaser) 
						 {
							    objectsB.get(i).setY(800);
							    
								return true;

						 }
					}
				
			}
			
		
		}
		return false;
		
	}
	// collision with an enemy and player lasers
	public static boolean isCollided(GameInterfaceB objectB, LinkedList<GameInterfaceA> objectsA)
	{
		for(int i=0; i<objectsA.size() ; i++)
		{
			if (objectB.getBounds().intersects(objectsA.get(i).getBounds()))
			{
				return true;
			}
		}
		return false;	
	}
	

}
