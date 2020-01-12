package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader 
{
	//BufferedImage = way to java says its an image 
	public static BufferedImage loadImage(String path)
	{
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));			
		} catch (IOException e) {
			System.out.println("Invalid Path");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
