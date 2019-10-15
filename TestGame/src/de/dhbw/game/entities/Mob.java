package de.dhbw.game.entities;

import de.dhbw.game.level.Level;
import de.dhbw.game.level.tiles.Tile;

public abstract class Mob extends Entity { //standardmob, erbt von entity und gibt paar methoden vor, die für alle gelten

    protected String name;
    protected int speed;
    protected int shirtcolor;
    
    protected int numSteps = 0;
    protected boolean isMoving;
    public int movingDir = 1;
    protected int scale = 1;

    public Mob(Level level, String name, int x, int y, int speed, int shirtcolor) { //Konstruktor
        super(level);
        this.name = name;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.shirtcolor = shirtcolor;
        
    }

    public void move(int xa, int ya) { //stiefeln
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            numSteps--;
            return;
        }
        numSteps++;
      //------------------BEI EINER KOLLISION NICHT LAUFEN-------------------------------
        if (!hasCollided(xa, ya)) {
            if (ya < 0)
                movingDir = 0;
            if (ya > 0)
                movingDir = 1;
            if (xa < 0)
                movingDir = 2;
            if (xa > 0)
                movingDir = 3;
            x += xa * speed;
            y += ya * speed;
        }
    }

    public abstract boolean hasCollided(int xa, int ya);

    protected boolean isSolidTile(int xa, int ya, int x, int y) {
        if (level == null) {
            return false;
        }
        Tile lastTile = level.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile newTile = level.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        if (!lastTile.equals(newTile) && newTile.isSolid()) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public int getNumSteps() {
        return numSteps;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public int getMovingDir() {
        return movingDir;
    }
    
    public int getShirtColor() {
        return shirtcolor;
    }

    public void setNumSteps(int numSteps) {
        this.numSteps = numSteps;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public void setMovingDir(int movingDir) {
        this.movingDir = movingDir;
    }
    public void setShirtColor(int shirtcolor) {
        this.shirtcolor = shirtcolor;
    }

}