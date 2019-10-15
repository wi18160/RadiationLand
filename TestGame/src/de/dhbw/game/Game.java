package de.dhbw.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import de.dhbw.game.entities.environment.InfoBox;
import de.dhbw.game.entities.environment.Portal;
import de.dhbw.game.entities.environment.Tree;
import de.dhbw.game.entities.item.EP;
import de.dhbw.game.entities.item.Pizza;
import de.dhbw.game.entities.mob.Dog;
import de.dhbw.game.entities.mob.Enemy;
import de.dhbw.game.entities.mob.NPC;
import de.dhbw.game.entities.mob.Player;
import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Screen;
import de.dhbw.game.gfx.SpriteSheet;
import de.dhbw.game.level.Level;
import de.dhbw.game.ui.Interface;
import de.dhbw.game.ui.Inventory;
import de.dhbw.game.ui.PlayerStats;
import de.dhbw.game.ui.TextBox;
import de.dhbw.menu.StartMenu;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	//------------------GRUNDEINSTELLUNGEN-------------------------------
	public static final int WIDTH = 120;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 1;
	public static final String NAME = "Game";
	
	public int mapsize = 256*8;

	//------------------FRAME UND TICK EINSTELLUNGEN-------------------------------
	private JFrame frame;
	public boolean running = false;
	public int tickCount = 0;	

	//------------------BUFFEREDIMAGE-------------------------------
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colours = new int[216]; // Anzahl der Farben 6*6*6  

	
	//------------------OBJEKTE-------------------------------
	private Screen screen;
	public static InputHandler input;
	public Level level;
	public Player player; public static int playerX, playerY;		
	public Inventory inventar;
	public PlayerStats stats;
	public TextBox text;
	public Interface inter;
	
	//Kommentar

	//------------------SPIELEINSTELLUNGEN IM KONSTRUKTOR-------------------------------
	public Game() {
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame(NAME);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	
		
	}
	
	//------------------INITIALISIERT DAS SPIEL, LEVEL USW-------------------------------
	public void init() {
		int index = 0;
		//------------------FARBEINSTELLUNGEN-------------------------------
		for (int r = 0; r<6; r++) { 
			for (int g = 0; g<6; g++) {
				for (int b = 0; b<6; b++) {
					int rr =(r*255/5);
					int gg =(g*255/5);
					int bb =(b*255/5);					
					
					colours[index++] = rr<<16 | gg <<8 | bb;
				}
			}
		}
		
		//------------------OBJEKTE, DIE IM LEVEL SICHTBAR SIND-------------------------------
		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/sprite_sheet.png"));		
		input = new InputHandler(this);		
		level = new Level("/level.png");		
		//player = new Player(level, 20,48, input, 145);
		player = new Player(level, 500,500, input, 145);
		
		for (int i = 0; i < 1000; i++) { //RANDOM NPCS ADDEN
			Random r = new Random();
			int shirt = r.nextInt(555);
			int posx = r.nextInt(mapsize);
			int posy = r.nextInt(mapsize);
			int speed = 1;					
			level.addEntity(new NPC(level, "npc"+i, posx, posy, speed, shirt));			
			
		}
		
		for (int i = 0; i < 50; i++) { //RANDOM ENEMYS ADDEN
			Random r = new Random();
			int posx = r.nextInt(mapsize);
			int posy = r.nextInt(mapsize);
			int speed = 1;			
			level.addEntity(new Enemy(level, "enemy"+i, posx, posy, speed, 000));			
			
		}
		
		
		for (int i = 0; i < 20; i++) { //INFOBOXEN
			Random r = new Random();
			int posx = r.nextInt(mapsize);
			int posy = r.nextInt(mapsize);
			String name = "infobox " + i;
			String msg = name;		
			level.addEntity(new InfoBox(level, name, posx, posy, msg));			
			
		}
		
		level.addEntity(new Portal(level, "portal", 550, 500, 200, 200));
		level.addEntity(new Portal(level, "portal2", 575, 500, 550, 500));
		
		for (int i = 0; i < 100; i++) { //RANDOM BÄUME ADDEN
			Random r = new Random();
			int posx = r.nextInt(mapsize);
			int posy = r.nextInt(mapsize);	
			level.addEntity(new Tree(level, "tree"+i, posx, posy));	
			
		}		
				
		level.addEntity(new Dog(level, "dog", 400,400,1, 0));
		level.addEntity(player);		
		//TUTORIAL
		level.addEntity(new Pizza(level, "pizza_tut", 550, 30));
    	level.addEntity(new EP(level, "ep_tut", 750, 30));
	
    	
    	//GUI-Elemente
    	inventar = new Inventory(screen, player, 20, 100, 15, 15, 0+6*32, 1+6*32, 2+6*32, Colours.get(-1,95,000,000), true, "Inventory");
		stats = new PlayerStats(screen, player, 20, 20, 15, 7, 0+6*32, 1+6*32, 2+6*32, Colours.get(-1,95,000,000), true, "");
    	inter = new Interface(screen,player,200,200,30,30,0+1*32,1+1*32,2+1*32,Colours.get(355, 355, 355, 355), true, "Test");    	Interface.addUi(inventar);
    	Interface.addUi(stats);
    	//Interface.addUi(inter);
    	
    	inventar.setRender(true);
    	stats.setRender(true);
    	inter.setRender(true);
   
    	
	}
	
	//------------------THREADING-------------------------------
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}

	public synchronized void stop() {
		running = false;
	}
	
	//------------------SPIELSTART-------------------------------
	public void run() {
		
		//------------------FPS SETTINGS, DAMIT DAS SPIEL GLEICHMÄßIG LÄUFT-------------------------------
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;

		int frames = 0;
		int ticks = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		init();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (delta >= 1) {
				ticks++;
				//------------------TICK AUFGERUFEN-------------------------------
				tick();
				delta -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				//------------------KONSOLE INFORMATION-------------------------------
				System.out.println("FPS: " + frames + " TICKS/s: " + ticks + " immmunity: " + player.immunity + " PlayerX: " + player.x + " PlayerY: " + player.y + " isIndoor: " + Player.isIndoor + " Energy: " + Player.energy + " Leben: " + Player.leben);		
				playerX = player.x; playerY = player.y;
				
				//------------------FRAMES UND TICKS ZURÜCKGESETZT-------------------------------
				frames = 0;
				ticks = 0;
			}			
			
		}
	}
	
	public void tick() {
		tickCount++;	
		//------------------LEVELTICK-------------------------------
		level.tick(); 
				
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			//------------------TRIPLEBUFFERING-------------------------------
			createBufferStrategy(3);
			return;
		}
		
		//------------------SPIELER BLEIBT MITTIG (screen != level)!!!-------------------------------
		int xOffset = player.x - (screen.width/2);
		int yOffset = player.y - (screen.height/2);

		//------------------TILESRENDERING-------------------------------
		
		level.renderTiles(screen, xOffset, yOffset);
		
//		for (int x = 0; x<level.width; x++) {
//			int colour = Colours.get(-1, -1, -1, 1000);
//			if (x % 10 == 0 && x != 0) {
//				colour = Colours.get(-1, -1, -1, 500);
//			}			
//		}
		
		//------------------ENITITESRENDERING (SPIELER, MOBS...)-------------------------------
		level.renderEntities(screen);
		
		//GUI-Elemente rendern
				inventar.setRender(true);
				Interface.render();

				
				if(screen.xOffset > 0) {
					
					inventar.setX(20);
					stats.setX(20);
				}
				if(screen.xOffset == 0){
					
					inventar.setX(screen.width - 150);
					stats.setX(screen.width - 150);
				}
				
				
				for (int y = 0; y<screen.height; y++) {
					for (int x = 0; x<screen.width; x++) {			
						int colourCode = screen.pixels[x+y *screen.width];
						if (colourCode < 255) pixels[x+y*WIDTH] = colours[colourCode];
					}			
				}
		
		
		for (int y = 0; y<screen.height; y++) {
			for (int x = 0; x<screen.width; x++) {			
				int colourCode = screen.pixels[x+y *screen.width];
				if (colourCode < 255) pixels[x+y*WIDTH] = colours[colourCode];
			}			
		}
		
		//------------------2DGRAPHICS STELLT ALLES DAR-------------------------------
					
		Graphics g = bs.getDrawGraphics();
		g.drawRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
//		g.setColor(Color.DARK_GRAY);
//		g.fillRect(5, (HEIGHT*SCALE)-75, (WIDTH*SCALE), 80);
//		g.setColor(Color.LIGHT_GRAY);
//		g.fillRect(10, (HEIGHT*SCALE)-70, (WIDTH*SCALE-10), 70);
			
		
		g.dispose();		
		bs.show();			
		
		
	}
}