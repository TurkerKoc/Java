import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class PaintPanel extends JPanel 
{
	private int pointCount = 0;
	private Point[] points = new Point[1000];
	public PaintPanel() {
		addMouseMotionListener(
				new MouseMotionAdapter() 
				{
					public void mouseDragged(MouseEvent e) 
					{
						points[pointCount] = e.getPoint();
						pointCount++;
						repaint();
					};
				}
			);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int i = 0; i < pointCount; i++) 
		{
			g.fillOval(points[i].x, points[i].y, 4, 4);
		}
	}
}
