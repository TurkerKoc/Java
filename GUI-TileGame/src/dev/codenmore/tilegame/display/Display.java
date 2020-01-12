package dev.codenmore.tilegame.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display 
{
	private JFrame frame;
	private Canvas canvas; //allow us to draw our images in it our game will be in this
	private String title;
	private int width, height;
	
	public Display(String title, int width, int height) 
	{
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	private void createDisplay()
	{
		frame = new JFrame(title);
		frame.setSize(width,height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if you won't write this after close clicked your game will contiune execution in backround
		frame.setResizable(false); //user cannot drag and resize window
		frame.setLocationRelativeTo(null); //window will appear in the center of the screen
		frame.setVisible(true); //in default Jframe is not visible 
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width, height)); //size will never change
		canvas.setFocusable(false); //do this to use keyListener on screen 
		
		
		frame.add(canvas);
		frame.pack(); //will resize our window a little bit to see full of canvas
	}
	public Canvas getCanvas() {
		return canvas;
	}
	public JFrame getFrame() {
		return frame;
	}
}
