package de.dhbw.game.level.tiles;


import de.dhbw.game.entities.mob.Player;
import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Screen;
import de.dhbw.game.level.Level;

public class IndoorTile extends Tile {
	
	//------------------NICHTS ÄNDERN-------------------------------

    protected int tileId;
    protected int tileColour;
    
    //konstruktor

    public IndoorTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, false, false, levelColour);
        this.tileId = x + y * 32; //Zählt durch das spritesheet durch
        this.tileColour = tileColour; //farbe wird übergeben
    }
    
    
    //geerbte methoden

    public void tick() {
    	
    	if (Player.isIndoor==false) {
    		this.tileColour = Colours.get(000,000,000,000);
    	}
    	else {this.tileColour = Colours.get(-1, 432, 321, -1);}
    		
    	
    }

    public void render(Screen screen, Level level, int x, int y) { 	   	
    	
    	
        	screen.render(x, y, tileId, tileColour, 0x00, 1);    	
        	
    	
    }

}
