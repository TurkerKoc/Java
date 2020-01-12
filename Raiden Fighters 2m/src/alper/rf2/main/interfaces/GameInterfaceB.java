package alper.rf2.main.interfaces;

import java.awt.Graphics;

import java.awt.Rectangle;;

public interface GameInterfaceB // interface of enemy jet - enemy tank - enemy laser
{

		public void update();
		public void draw(Graphics g);
		public Rectangle getBounds();

		public double getX();
		public double getY();
		public double setX(double x);
		public double setY(double y);
	}
