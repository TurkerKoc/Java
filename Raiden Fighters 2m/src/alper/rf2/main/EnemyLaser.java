package alper.rf2.main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import alper.rf2.main.interfaces.GameInterfaceB;

public class EnemyLaser extends GameObject implements GameInterfaceB
{
	private Images images;
	protected BufferedImage image;
	private Game game;

	public EnemyLaser(double x, double y, Images images, Game game)
	{	
		super(x, y);
		this.setGame(game);
		this.images = images;
		image = game.getLaserImage();
	}
	
	public void update()
	{
		y+=5; // moves enemy laser 5 pixel below to the screen every frame
	}
	public void draw(Graphics g)
	{
		g.drawImage(images.laserImage, (int)x,(int)y, null);// draw laser to frame
	}
	
	@Override
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 32, 32); // enemy laser rectangle size (x,y,width,height)
	}
	public double getY() 
	{
		return y;	
	}
	@Override
	public double getX() 
	{
		return x;
	}
	@Override
	public double setX(double x) {
		
		return this.x = x;
	}
	@Override
	public double setY(double y)
	{
		return this.y = y;

	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	
	

}
