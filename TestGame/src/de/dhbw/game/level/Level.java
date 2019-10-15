package de.dhbw.game.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import de.dhbw.game.entities.Entity;
import de.dhbw.game.entities.item.EP;
import de.dhbw.game.entities.item.Hamburger;
import de.dhbw.game.entities.item.Pizza;
import de.dhbw.game.entities.mob.NPC;
import de.dhbw.game.gfx.Screen;
import de.dhbw.game.level.tiles.Tile;

//------------------!!!!!!!!!!!!!-------------------------------
//------------------NICHTS ÄNDERN-------------------------------
//------------------!!!!!!!!!!!!!-------------------------------

public class Level {

    private byte[] tiles;
    public int width;
    public int height;
    private List<Entity> entities = new ArrayList<Entity>();
    private String imagePath;
    private BufferedImage image;

  //------------------MAP GENERATOR-------------------------------
    
//    public Level (int width, int height) { 
//    	tiles = new byte[width*height];
//    	this.width = width;
//    	this.height = height;
//    	this.generateLevel();
//    	
//    }
    
    public Level(String imagePath) { //WELT AUS BILD
        if (imagePath != null) {
            this.imagePath = imagePath;
            this.loadLevelFromFile();
        } else {
           this.width = 64;
            this.height = 64;
            tiles = new byte[width * height];
            this.generateLevel();
        }
    }

  //------------------DATEI LADEN-------------------------------
    private void loadLevelFromFile() {
        try {
            this.image = ImageIO.read(Level.class.getResource(this.imagePath));
            this.width = this.image.getWidth();
            this.height = this.image.getHeight();
            tiles = new byte[width * height];
            this.loadTiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  //------------------TILES LADEN-------------------------------
    private void loadTiles() {
        int[] tileColours = this.image.getRGB(0, 0, width, height, null, 0, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tileCheck: for (Tile t : Tile.tiles) {
                    if (t != null && t.getLevelColour() == tileColours[x + y * width]) {
                        this.tiles[x + y * width] = t.getId();
                        break tileCheck;
                    }
                }
            }
        }
    }

  //------------------SPIEL SPEICHERN (BIS JETZT NICHT VERWENDET)-------------------------------
    @SuppressWarnings("unused")
    private void saveLevelToFile() {
        try {
            ImageIO.write(image, "png", new File(Level.class.getResource(this.imagePath).getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
  //------------------SPIEL SPEICHERN (BIS JETZT NICHT VERWENDET)-------------------------------
    public void alterTile(int x, int y, Tile newTile) {
        this.tiles[x + y * width] = newTile.getId();
        image.setRGB(x, y, newTile.getLevelColour());
    }
    
  //------------------ZUFALLSLEVEL, WIRD VON PNG ÜBERBLENDET-------------------------------
    public void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x * y % 10 < 9) {
                    tiles[x + y * width] = Tile.GRASS.getId();
                } else tiles[x + y * width] = Tile.STONE.getId();
            }
        }
    }
    
    public void tick() {   	
    	
    	Random r = new Random();
    	int spawn = r.nextInt(1000);
    	int posx = r.nextInt(1008);
		int posy = r.nextInt(920);	    	
    	
    	if (spawn == 50) {		
		this.addEntity(new Pizza(this, "pizzaneu", posx, posy));	
    	}
    	if (spawn == 51) {
    	this.addEntity(new Hamburger(this, "hamburger", posx, posy));	
    	}
    	if (spawn == 52) {
        	this.addEntity(new EP(this, "ep", posx, posy));	
        }
    	
    	for (Entity e : entities) {
            e.tick();
        }

        for (Tile t : Tile.tiles) {
            if (t == null) {
                break;
            }
            t.tick();
        }
        
        
    }

    public void renderTiles(Screen screen, int xOffset, int yOffset) {
        if (xOffset < 0)
            xOffset = 0;
        if (xOffset > ((width << 3) - screen.width))
            xOffset = ((width << 3) - screen.width);
        if (yOffset < 0)
            yOffset = 0;
        if (yOffset > ((height << 3) - screen.height))
            yOffset = ((height << 3) - screen.height);

        screen.setOffset(xOffset, yOffset);

        for (int y = (yOffset >> 3); y < (yOffset + screen.height >> 3) + 1; y++) {
            for (int x = (xOffset >> 3); x < (xOffset + screen.width >> 3) + 1; x++) {
                getTile(x, y).render(screen, this, x << 3, y << 3);
            }
        }
    }

    public void renderEntities(Screen screen) {
        for (Entity e : entities) {
            e.render(screen);
        }
    }

    public Tile getTile(int x, int y) {
        if (0 > x || x >= width || 0 > y || y >= height)
            return Tile.VOID;
        return Tile.tiles[tiles[x + y * width]];
    }

    public synchronized void addEntity(Entity entity) {
        this.entities.add(entity);
    }
    
      

    public synchronized void removeEntity(String name) {
        int index = 0;
        for (Entity e : getEntities()) {
            if (e instanceof NPC && ((NPC) e).getName().equals(name)) {
                break;
            }
            index++;
        }
        this.getEntities().remove(index);
    }
    
    @SuppressWarnings("unused")
	private int getPlayerNPCIndex(String name) {
        int index = 0;
        for (Entity e : getEntities()) {
            if (e instanceof NPC && ((NPC) e).getName().equals(name)) {
                break;
            }
            index++;
        }
        return index;
    }


  public synchronized List<Entity> getEntities() {
      return this.entities;
  }
//
}