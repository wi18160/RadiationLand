package de.dhbw.game.level.tiles;

public class BasicSolidTile extends BasicTile {

	//------------------NICHTS �NDERN-------------------------------
	
    public BasicSolidTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, x, y, tileColour, levelColour);
        this.solid = true;
    }

}