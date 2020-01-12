package alper.rf2.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;


import java.awt.Rectangle;

import alper.rf2.main.interfaces.GameInterfaceA;

public class Player extends GameObject implements GameInterfaceA
{
	private double velocityX=0 , velocityY=0 , backgroundY=-500;
	public static int timer = 300; // 30 per second
	private int remaniningSeconds=-1;
	public boolean missileAllowed = false;
	private Images images;
	public static double playerX =0;
	
	int remainingLife = 3;
	public static int score = 0;
	String scoreString;
	JLabel life1 = new JLabel();
	JLabel life2 = new JLabel();
	JLabel life3 = new JLabel();
	
	public Player(double x, double y,Images images) 
	{
		super(x, y);
		this.images = images;	
		playerX = this.x;
	}
	public void update()
	{
		
		x+=velocityX;
		y+=velocityY;
		playerX = x;
		backgroundY += 0.9;
		timer--;
		
		if (backgroundY >= 0) 
		{
			backgroundY = -481;
		}
		if(x < 0)
		{
			x=0;
		}
		if(x > 500-34)
		{
			x=500-34;
		}
		if(y < 0)
		{
			y=0;
		}
		if(y > 700-46)
		{
			y=700-46;
		}
	
	}
	
	public void draw(Graphics g)//draw
	{//image
		g.drawImage(images.backgroundImage, 0, (int)backgroundY, null);
		scoreString = Integer.toString(score);
		
		g.setFont(new Font("Stencil", Font.BOLD, 20));
		g.setColor(Color.RED);
		g.drawString("Score " + (scoreString), 230, 20);

		remaniningSeconds = timer/30;

		
		if(remaniningSeconds == 0)
		{
			Game.fireMissile = true;
		}
	
		
		g.setFont(new Font("Stencil", Font.BOLD, 15));
		g.setColor(Color.red);
		if (Game.fireMissile)
		{
			g.drawString("Missile Available", 355, 685);

		}
		else
		{
			g.drawString("Next Missile in " + Integer.toString(remaniningSeconds), 365, 685);

		}
		
		
		g.drawImage(images.playerImage, (int)x,(int)y, null);
		if (remainingLife>=1) 
		{
			
			g.drawImage(images.lifeImage, 5, 5, null);
		if (remainingLife>=2) 
		{
			g.drawImage(images.lifeImage, 35, 5, null);

		}
		if(remainingLife==3) 
		{
			g.drawImage(images.lifeImage, 65, 5, null);
		}
		
	}
		
		

		

	
	}
	public Rectangle getBounds() 
	{
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public double getX() {
		return x;
	}
	public double setX(double x) {
		return this.x = x;
	}
	public double getY() {
		return y;
	}
	public double setY(double y) {
		return this.y = y;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	@Override
	public Rectangle getBounds1() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Rectangle getBounds2() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Rectangle getBounds3() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Rectangle getBounds4() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
