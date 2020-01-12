package alper.rf2.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter // Key adapter is used for only writing needed methods.
{
	Game game;
	
	public Input(Game game)
	{
		this.game = game;
	}  	
	public void keyPressed(KeyEvent e) 
	{	
		game.keyPressed(e);
	}
	public void keyReleased(KeyEvent e) 
	{
		game.keyReleased(e);	
	}

}
