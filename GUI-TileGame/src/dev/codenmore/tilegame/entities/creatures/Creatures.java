package dev.codenmore.tilegame.entities.creatures;

import dev.codenmore.tilegame.entities.Entitiy;

public abstract class Creatures extends Entitiy
{
	protected int health;
	public Creatures(float x, float y)
	{
		super(x, y);
		health = 10;
	}
	
	
}
