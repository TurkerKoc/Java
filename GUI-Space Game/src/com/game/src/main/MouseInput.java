package com.game.src.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener
{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		//getting touched coordinates
		int mx = e.getX();
		int my = e.getY();
		/*
		 * public Rectangle playButton = new Rectangle(Game.WIDTH/2 + 120, 150, 100, 50);
			public Rectangle helpButton = new Rectangle(Game.WIDTH/2 + 120, 250, 100, 50);
			public Rectangle quitButton = new Rectangle(Game.WIDTH/2 + 120, 350, 100, 50);
		 */
		
		//play Button
		if(mx >= Game.WIDTH / 2 + 120 && mx<= Game.WIDTH / 2 + 220)
		{
			if(my >= 150 && my <= 200)
			{
				//Pressed play button
				Game.state = Game.STATE.GAME;
			}
		}
		
		//quit Button
		if(mx >= Game.WIDTH / 2 + 120 && mx<= Game.WIDTH / 2 + 220)
		{
			if(my >= 350 && my <= 400)
			{
				//Pressed quit button
				System.exit(1);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
