package alper.rf2.main;

import java.awt.image.BufferedImage;

public class Images // All images stored in the images class
					// After initializing game class, all images load.
{
	public BufferedImage playerImage , enemyJetImage,enemyTankImage , missileImage , laserImage, backgroundImage, lifeImage;
	public BufferedImage[] explosionArray = new BufferedImage[14];
	
	public Images(Game game)
	{	
		playerImage = game.getPlayerImage();
		laserImage = game.getLaserImage();
		enemyJetImage = game.getEnemyImage();
		enemyTankImage = game.getEnemyTankImage();
		backgroundImage = game.getBackgroundImage();
		lifeImage = game.getLifeImage();
		missileImage = game.getMissileImage();
		
		for(int i =0; i<14; i++)
		{
			explosionArray[i] = Game.getExplosionImageList().get(i);

		}
	
	}
}
