package dev.codenmore.tilegame.entities.creatures;

import java.awt.Graphics;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.gfx.Assets;

public class Player extends Creatures
{
	private Game game;
	public Player(Game game,float x, float y) 
	{
		super(x, y);
		this.game = game;
	}

	@Override
	public void update() 
	{
		if(game.getKeyManager().up)
			y -= 3;
		if(game.getKeyManager().down)
			y += 3;
		if(game.getKeyManager().left)
			x -= 3;
		if(game.getKeyManager().right)
			x += 3;
	}

	@Override
	public void draw(Graphics g) 
	{
		g.drawImage(Assets.player, (int)x, (int)y, null);
	}
	
}
