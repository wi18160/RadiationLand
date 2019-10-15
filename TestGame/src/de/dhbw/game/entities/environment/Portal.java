package de.dhbw.game.entities.environment;
import de.dhbw.game.Game;
import de.dhbw.game.entities.Environment;
import de.dhbw.game.entities.mob.Player;
import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Screen;
import de.dhbw.game.level.Level;

public class Portal extends Environment {

	public static boolean teleport = false;

	public Portal(Level level, String name, int x, int y, int telx, int tely) {
		super(level, name, x, y, telx, telx, name);
	}

	private int colour = Colours.get(-1, 505, 503, -1);
	private int abstand = 8;
	

	public void tick() {// ------------------60 MAL PRO SEKUNDE-------------------------------
		if (Game.playerY + abstand > this.y & Game.playerY - abstand < this.y & Game.playerX + abstand > this.x & Game.playerX - abstand < this.x){
			Player.teleportToX = tely;
			Player.teleportToY = tely;
			teleport = true; 
			
		}	else {
			
		}
	}

	public void render(Screen screen) {
		int xTile = 0;
		int yTile = 2;	
		int tile = xTile + yTile * 32;
		screen.render(x-4, y, tile, colour, 0, 2);
		
	}

	public boolean hasCollided(int xa, int ya) { // HITBOX
		return false;
	}
}