package de.dhbw.game.level.tiles;


import de.dhbw.game.gfx.Screen;
import de.dhbw.game.level.Level;

public class BasicTile extends Tile {
	
	//------------------NICHTS ÄNDERN-------------------------------
    protected int tileId;
    protected int tileColour;
    
    
    //konstruktor
    public BasicTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, false, false, levelColour);
        this.tileId = x + y * 32; //Zählt durch das spritesheet durch
        this.tileColour = tileColour; //farbe wird übergeben
    }
    
    
    //geerbte methoden

    public void tick() { 	    	
    }

    public void render(Screen screen, Level level, int x, int y) {    	
        	screen.render(x, y, tileId, tileColour, 0x00, 1);        	    	
    }

}
