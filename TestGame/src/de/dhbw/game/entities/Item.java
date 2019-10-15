package de.dhbw.game.entities;


import de.dhbw.game.level.Level;


public abstract class Item extends Entity { //Item, erbt von entity und gibt paar methoden vor, die für alle gelten

    protected String name;
    protected int scale = 1;

    public Item(Level level, String name, int x, int y) { //Konstruktor
        super(level);
        this.name = name;
        this.x = x;
        this.y = y;
        
    }

    public String getName() {
        return name;
    }

    
}