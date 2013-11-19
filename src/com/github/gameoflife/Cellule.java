package com.github.gameoflife;

/**
 * Cellule is the base of the game
 * A cellule can be alive or dead
 * @author wamilou
 *
 */
public class Cellule {

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
	
}
