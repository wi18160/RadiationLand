package de.dhbw.game.entities.environment;
import de.dhbw.game.Game;
import de.dhbw.game.entities.Environment;
import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Font;
import de.dhbw.game.gfx.Screen;
import de.dhbw.game.level.Level;

public class InfoBox extends Environment {

	public InfoBox(Level level, String name, int x, int y, String msg) {
		super(level, name, x, y, y, y, msg);
	}

	private int colour = Colours.get(000, 400, 441, 442);
	private int abstand = 8;
	boolean show;
	

	public void tick() {// ------------------60 MAL PRO SEKUNDE-------------------------------
		
		if (Game.playerY + abstand > this.y & Game.playerY - abstand < this.y & Game.playerX + abstand > this.x & Game.playerX - abstand < this.x){
			
			show = true;
			
			
		}	else {
			show = false;
		}
	}

	public void render(Screen screen) {
		int xTile = 4;
		int yTile = 0;	
		int tile = xTile + yTile * 32;
		screen.render(x, y, tile, colour, 0, 1);
		if (show) Font.render(msg, screen, this.x -32, this.y -32 , Colours.get(000, -1, -1, 555), 1);
		
	}

	public boolean hasCollided(int xa, int ya) { // HITBOX
		return false;
	}
}