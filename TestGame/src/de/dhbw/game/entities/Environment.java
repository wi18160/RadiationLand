package de.dhbw.game.entities;
import de.dhbw.game.level.Level;


public abstract class Environment extends Entity { //Item, erbt von entity und gibt paar methoden vor, die für alle gelten

    protected String name;
    protected String msg;
    protected int scale = 1;
    protected int telx;
    protected int tely;

    public Environment(Level level, String name, int x, int y, int telx, int tely, String msg) { //Konstruktor
        super(level);
        this.name = name;
        this.x = x;
        this.y = y;
        this.telx = telx;
        this.tely = tely;
        this.msg = msg;
        
    }

	public String getName() {
        return name;
    }
	public String getMsg() {
        return msg;
    }

    
}