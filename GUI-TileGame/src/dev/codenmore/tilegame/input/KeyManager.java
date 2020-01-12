package dev.codenmore.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener //which key pressed 
{
	private boolean[] keys;
	public boolean up,down,left,right;
	public KeyManager()
	{
		keys = new boolean[256]; //every key has an KeyCode in range 256 we can understand which key pressed
	}
	
	public void update()
	{
		up = keys[KeyEvent.VK_W]; //W is out up key
		down = keys[KeyEvent.VK_S]; 
		left = keys[KeyEvent.VK_A]; 
		right = keys[KeyEvent.VK_D];
	}
	
	@Override
	public void keyTyped(KeyEvent e) 
	{

	}

	@Override
	public void keyPressed(KeyEvent e) 
	{	
		keys[e.getKeyCode()] = true;
		System.out.println("Pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{		
		keys[e.getKeyCode()] = false;

	}
	
}
