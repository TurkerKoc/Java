package com.game.src.main;

import java.util.LinkedList;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Physics //handle whether we have collisions or not 
{ 
	//is entityA collided with any of the entityB 
	public static boolean Collision(EntityA entA, EntityB entB)
	{
		if(entA.getBounds().intersects(entB.getBounds()))
		{				
			return true;
		}
		return false;
	}
	
	//is entityB collided with any of the entity
	public static boolean Collision(EntityB entB, EntityA entA)
	{
		
		if(entB.getBounds().intersects(entA.getBounds()))
		{				
			return true;
		}
		
		return false;
	}
}
