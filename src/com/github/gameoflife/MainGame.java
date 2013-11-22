package com.github.gameoflife;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MainGame extends BasicGame {

	private boolean PLAYING = false;

	public static final int TAILLE_CELLULE = 10;
	public static final int GRILLE_X = 64;
	public static final int GRILLE_Y = 48;

	private Cellule[][] cellules;

	public MainGame() {
		super("Game of Life");
	}

	/**
	 * Initialisation of the game
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		cellules = new Cellule[GRILLE_X][GRILLE_Y];

		for (int x = 0; x < GRILLE_X; x++) {
			for (int y = 0; y < GRILLE_Y; y++) {
				cellules[x][y] = new Cellule();
			}
		}
	}

	private int neightborCount(int x, int y) {
		int acc = 0;

		for (int x2 = -1; x2 <= 1; x2++) {
			for (int y2 = -1; y2 <= 1; y2++) {

				if (x + x2 < 0 || x + x2 >= GRILLE_X || y + y2 < 0
						|| y + y2 >= GRILLE_Y)
					continue;
				if (x2 == 0 && y2 == 0)
					continue;

				if (cellules[x + x2][y + y2] != null) {
					if (cellules[x + x2][y + y2].isAlive()) {
						acc++;
					}
				}
			}
		}

		return acc;
	}

	/**
	 * Input reading
	 */
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		Input input = gc.getInput();

		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			int mouseX = gc.getInput().getMouseX();
			int mouseY = gc.getInput().getMouseY();
			int gridX = (int) (Math.floor(mouseX / TAILLE_CELLULE));
			int gridY = (int) (Math.floor(mouseY / TAILLE_CELLULE));

			cellules[gridX][gridY].awake();
		}

		if (input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
			int mouseX = gc.getInput().getMouseX();
			int mouseY = gc.getInput().getMouseY();
			int gridX = (int) (Math.floor(mouseX / TAILLE_CELLULE));
			int gridY = (int) (Math.floor(mouseY / TAILLE_CELLULE));

			cellules[gridX][gridY].kill();
		}

		if (input.isKeyPressed(Input.KEY_SPACE)) {
			PLAYING = !PLAYING;
		}

		/*
		 * Algorithm of the game
		 */
		if (PLAYING) {
			Cellule[][] copie = new Cellule[GRILLE_X][GRILLE_Y];
			
			for (int x = 0; x < GRILLE_X; x++) {
				for (int y = 0; y < GRILLE_Y; y++) {
					copie[x][y] = new Cellule();
					if(cellules[x][y].isAlive()) {							
						copie[x][y].awake();
					}
				}
			}
			
			for (int x = 0; x < GRILLE_X; x++) {
				for (int y = 0; y < GRILLE_Y; y++) {
					if (cellules[x][y] != null) {
						int nbc = neightborCount(x, y);

						if (!cellules[x][y].isAlive() && nbc == 3) {
							copie[x][y].awake();
						} else if (cellules[x][y].isAlive()
								&& (nbc > 3 || nbc < 2)) {
							copie[x][y].kill();
						}
					}
				}
			}
			
			cellules = copie;
		}
	}

	/**
	 * Drawing function
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (int x = 0; x < GRILLE_X; x++) {
			for (int y = 0; y < GRILLE_Y; y++) {
				if (cellules[x][y] != null) {
					cellules[x][y].draw(x, y, g);
				}
			}
		}

		/** Drawing a cursor under the mouse */
		int mouseX = gc.getInput().getMouseX();
		int mouseY = gc.getInput().getMouseY();
		int gridX = (int) (Math.floor(mouseX / TAILLE_CELLULE) * TAILLE_CELLULE);
		int gridY = (int) (Math.floor(mouseY / TAILLE_CELLULE) * TAILLE_CELLULE);
		g.setColor(Color.orange);
		g.drawRect(gridX, gridY, TAILLE_CELLULE, TAILLE_CELLULE);

		g.drawString(String.format("FPS : %d", gc.getFPS()), 20, 15);
		String status = "PAUSE";
		if (PLAYING) {
			status = "PLAYING";
		}
		g.drawString(status, 20, 30);

		g.setColor(Color.white);
	}

	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new MainGame());
			appgc.setDisplayMode(GRILLE_X * TAILLE_CELLULE, GRILLE_Y
					* TAILLE_CELLULE, false);
			appgc.setVSync(true);
			appgc.setShowFPS(false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

}
