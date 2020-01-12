package com.game.src.main;

import java.awt.image.BufferedImage;

//created because we do not want to create SpriteSheet ss in all classes
public class Textures 
{
	//public BufferedImage player, missile, enemy;
	public BufferedImage[]player  = new BufferedImage[3];
	public BufferedImage[]missile  = new BufferedImage[3];
	public BufferedImage[]enemy  = new BufferedImage[3];
	private SpriteSheet ss = null;
	public Textures(Game game)
	{
		ss = new SpriteSheet(game.getSpriteSheet());
		getTextures();
	}
	public void getTextures()
	{
		player[0] = ss.grabImage(1, 1, 31, 31);
		player[1] = ss.grabImage(1, 2, 31, 31);
		player[2] = ss.grabImage(1, 3, 30, 30);
		
		missile[0] = ss.grabImage(2, 1, 27, 27);
		missile[1] = ss.grabImage(2, 2, 27, 27);
		missile[2] = ss.grabImage(2, 3, 27, 27);
		
		enemy[0] = ss.grabImage(3, 1, 28,28);
		enemy[1] = ss.grabImage(3, 2, 28,28);
		enemy[2] = ss.grabImage(3, 3, 28,28);
	}
	
}
