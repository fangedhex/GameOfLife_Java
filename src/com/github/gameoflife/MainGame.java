package com.github.gameoflife;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class MainGame extends BasicGame{
	
	public static final int TAILLE_CELLULE = 10;
	
	public MainGame() {
		super("Game of Life");
	}

	/**
	 * Initialisation of the game
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {}

	/**
	 * Input reading
	 */
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
	}

	/**
	 * Drawing function
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {	
		/** Drawing a cursor under the mouse */
		int mouseX = gc.getInput().getMouseX();
		int mouseY = gc.getInput().getMouseY();		
		int gridX = (int) (Math.floor( mouseX/TAILLE_CELLULE )*TAILLE_CELLULE);
		int gridY = (int) (Math.floor( mouseY/TAILLE_CELLULE )*TAILLE_CELLULE);
		g.drawRect(gridX, gridY, TAILLE_CELLULE, TAILLE_CELLULE);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new MainGame());
			appgc.setDisplayMode(640, 480, false);
			appgc.setVSync(true);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
