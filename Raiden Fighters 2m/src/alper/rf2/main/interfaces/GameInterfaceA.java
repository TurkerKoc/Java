package alper.rf2.main.interfaces;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface GameInterfaceA // Interface of player - laser - missile
{

		public void update();
		public void draw(Graphics g);
		public Rectangle getBounds();
		public Rectangle getBounds1();
		public Rectangle getBounds2();
		public Rectangle getBounds3();
		public Rectangle getBounds4();

		
		public double getX();
		public double getY();
		public double setX(double x);
		public double setY(double y);
}
