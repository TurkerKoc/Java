package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet 
{
	private BufferedImage sheet;
	public SpriteSheet(BufferedImage sheet) 
	{
		this.sheet = sheet;
	}
	public BufferedImage crop(int x, int y, int width, int height) //method to take image from spriteSheet with pos. and with height
	{
		return sheet.getSubimage(x, y, width, height); //subiamge = expected image in spriteSheet
	}
}
