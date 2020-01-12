package dev.codenmore.tilegame;

import dev.codenmore.tilegame.display.Display;

public class Launcher {
	public static void main(String []args)
	{
		Game game = new Game("First Frame", 400, 400);
		game.start(); //starting the game
	}

}
