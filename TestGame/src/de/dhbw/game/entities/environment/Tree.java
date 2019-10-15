package de.dhbw.game.entities.environment;
import de.dhbw.game.entities.Environment;
import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Screen;
import de.dhbw.game.level.Level;

public class Tree extends Environment {

	public Tree(Level level, String name, int x, int y) {
		super(level, name, x, y, y, y, name);
	}

	private int colour = Colours.get(-1, 232, 321, 000);
	

	public void tick() {// ------------------60 MAL PRO SEKUNDE-------------------------------
		
	}

	public void render(Screen screen) {
		int xTile = 6;
		int yTile = 0;	
		int tile = xTile + yTile * 32;
		screen.render(x-4, y, tile, colour, 0, 3);
		
	}

	public boolean hasCollided(int xa, int ya) { // HITBOX
		return false;
	}
}