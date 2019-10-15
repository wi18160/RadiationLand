package de.dhbw.game.level.tiles;


import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Screen;
import de.dhbw.game.level.Level;

public abstract class Tile {

	//------------------NEUE TILES HINZUFÜGEN-------------------------------
	
    public static final Tile[] tiles = new Tile[256];
  //------------------ID; POSITION AUF SHEET; FARBEN; FARBE AUF MAP-------------------------------
    public static final Tile VOID = new BasicSolidTile(0, 		0, 0, Colours.get(000, -1, -1, -1), 0xFF000000);
    public static final Tile COBBLE = new BasicSolidTile(1, 	1, 0, Colours.get(-1, 333, 444, -1), 0xFF808080);
    public static final Tile GRASS = new BasicTile(2, 			2, 0, Colours.get(-1, 131, 141, -1), 0xFF00FF00);
    public static final Tile WATER = new AnimatedTile(3, 		new int[][] { { 0, 5 }, { 1, 5 }, { 2, 5 }, { 1, 5 } }, Colours.get(-1, 004, 115, -1), 0xFF0000FF, 1000);
    public static final Tile DESERT = new BasicTile(4, 			3, 0, Colours.get(-1, 440, 111, -1), 0xFFFF0000);
    public static final Tile FLOWER = new BasicTile(5, 			4, 1, Colours.get(131, 400, 050, 004), 0xFFFFFFFF);
    public static final Tile LAVA = new AnimatedTile(6, 		new int[][] { { 0, 5 }, { 1, 5 }, { 2, 5 }, { 1, 5 } }, Colours.get(-1, 500, 430, -1), 0xFFFF00FF, 250);
    public static final Tile CACTUS = new BasicSolidTile(7, 	2, 1, Colours.get(440, 000, 131, 111), 0xFF00FFFF);
    public static final Tile BUSH = new BasicSolidTile(8, 		0, 1, Colours.get(131, 232, 000, 141), 0xFF008000);
    public static final Tile STONE = new BasicSolidTile(9, 		1, 1, Colours.get(000, 131, 222, 333), 0xFFFF8000);
    public static final Tile FLOOR = new IndoorTile(10, 		5, 0, Colours.get(-1, 432, 321, -1), 0xFFFFFF00);
    public static final Tile SEEROSE = new BasicTile(11,		3, 1, Colours.get(004, 000, 141, 131), 0xFF800000);
    
  //------------------NICHTS ÄNDERN-------------------------------
    protected byte id;
    protected boolean solid;
    protected boolean emitter;
    private int levelColour;

  //------------------NICHTS ÄNDERN-------------------------------
    public Tile(int id, boolean isSolid, boolean isEmitter, int levelColour) {
        this.id = (byte) id;
        if (tiles[id] != null) throw new RuntimeException("Duplicate tile id on " + id); // error meldung wenn was am spritesheet verkackt wurde
        this.solid = isSolid;
        this.emitter = isEmitter;
        this.levelColour = levelColour;
        tiles[id] = this;
    }

  //------------------NICHTS ÄNDERN-------------------------------
    //paar getter
    public byte getId() {
        return id;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isEmitter() {
        return emitter;
    }

    public int getLevelColour() {
        return levelColour;
    }  

    // paar methoden für die unterklassen
    
    public abstract void tick();

    public abstract void render(Screen screen, Level level, int x, int y); // alle unterklassen erben diese methode

	
}
