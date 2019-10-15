package de.dhbw.game.entities.item;

import de.dhbw.game.Game;
import de.dhbw.game.entities.Item;
import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Screen;
import de.dhbw.game.level.Level;

public class EP extends Item {

	public EP(Level level, String name, int x, int y) {
		super(level, name, x, y);
	}

	private int colour = Colours.get(-1, 055, 305, 005);
	private int abstand = 10; // Abstand zu Player in pixeln
	public boolean aufgenommen = false;

	public void tick() {// ------------------60 MAL PRO SEKUNDE-------------------------------

		if (aufgenommen == true) {
			this.colour = Colours.get(-1, -1, -1, -1);
		}

		if (Game.playerY + abstand > this.y && Game.playerY - abstand < this.y && Game.playerX + abstand > this.x
				&& Game.playerX - abstand < this.x && aufgenommen == false) { //WENN PLAYER NAH GENUG BEI PIZZA IST, 
			//DANN WIRD SIE GEGESSEN UND ENERGIE AUF 100 GESETZT

			System.out.println("+ 1");
			aufgenommen = true;

		} else {

		}

	}

	public void render(Screen screen) {
		int xTile = 2;
		int yTile = 3;	
		int tile = xTile + yTile * 32;
		screen.render(x-4, y, tile, colour, 0, 1);
		

	}

	public boolean hasCollided(int xa, int ya) { // HITBOX
		return false;
	}
}