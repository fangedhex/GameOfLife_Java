package com.github.gameoflife;

import org.newdawn.slick.Graphics;

/**
 * Cellule is the base of the game
 * A cellule can be alive or dead
 * @author wamilou
 *
 */
public class Cellule {
	
	public Cellule() {		
	}

	private boolean alive = false;		
	public void awake() {
		alive = true;
	}	
	public void kill() {
		alive = false;
	}	
	public boolean isAlive() {
		return alive;
	}
	
	public void draw(int x, int y, Graphics g) {
		if(isAlive()) {
			g.fillRect(x*MainGame.TAILLE_CELLULE, y*MainGame.TAILLE_CELLULE, 
					MainGame.TAILLE_CELLULE, MainGame.TAILLE_CELLULE);	
		}
	}
	
}
