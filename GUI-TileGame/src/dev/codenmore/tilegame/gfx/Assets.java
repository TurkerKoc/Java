 package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets 
{
	private static final int width = 32, height = 32; //sizes of one image in spriteSheet
	public static BufferedImage player, dirt, grass, stone, tree;
	
	//this method is used to load everything once into our game
	//by doing in oder init it loads over and over again
	public static void init() 
	{
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/TileGame.png"));
		player = sheet.crop(0, 0, width, height);
		grass = sheet.crop(width, 0, width, height);
		dirt = sheet.crop(2*width, 0, width, height);
		stone = sheet.crop(3*width, 0, width, height);
		tree = sheet.crop(0, height, width, height);
	}
}
