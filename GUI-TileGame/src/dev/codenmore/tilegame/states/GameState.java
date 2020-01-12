package dev.codenmore.tilegame.states;

import java.awt.Graphics;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.gfx.Assets;

public class GameState extends State 
{
	private Player player;
	
	public GameState(Game game) 
	{
		super(game);
		player = new Player(game,100, 100);
	}
	@Override
	public void update() 
	{
		player.update();
	}
	@Override
	public void draw(Graphics g) 
	{
		player.draw(g);
	}
}
