package de.dhbw.game.ui;

import de.dhbw.game.entities.mob.Player;
import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Screen;

public class PlayerStats extends Interface{

	private Screen screen;
	private Player player;
	private int xPos;
	private int yPos;
	private int pixelWidth;
	private int pixelHeight;
	private int TileTopBottomIndex;
	private int TileLeftRightIndex;
	private int TileFillIndex;
	private int Color;
	private boolean fill;
	public int selected;
	private String header;
	private boolean render;
	
	
	
	public PlayerStats(Screen screen, Player player, int xPos, int yPos, int pixelWidth, int pixelHeight, int TileTopBottomIndex, int TileLeftRightIndex, int TileFillIndex, int Color, boolean fill, String header) {
super(screen, player, xPos, yPos, pixelWidth, pixelHeight, TileTopBottomIndex, TileLeftRightIndex, TileFillIndex, Color, fill, header);
		
		this.selected = 0;
		this.render = true;
		this.xPos = xPos;
		this.yPos = yPos;
		this.pixelHeight = pixelHeight;
		this.pixelWidth = pixelWidth;
		this.TileTopBottomIndex = TileTopBottomIndex;
		this.TileLeftRightIndex = TileLeftRightIndex;
		this.TileFillIndex = TileFillIndex;
		this.header = header;
		this.Color = Color;
		this.fill = fill;
		this.screen = screen;
		this.player = player;
		
	}

	
	public void create() {
		

		super.renderBox();		
		
		
		int xPos = super.getX();
		
		for(int life = 0; life < player.maxhealth; life = life + 10) {
		
		
		if(life < player.health) {
			
			screen.render(screen.xOffset+(xPos+8)+life, screen.yOffset+yPos+8, 4+6*32, Colours.get(-1, 300 , 310, -1), 0, 1);
			screen.render(screen.xOffset+(xPos+8)+life, screen.yOffset+yPos+24, 5+6*32, Colours.get(-1, 170 , 100, 1), 0, 1);
			screen.render(screen.xOffset+(xPos+8)+life, screen.yOffset+yPos+40, 6+6*32, Colours.get(-1, 155 , 455, -1), 0, 1);


		}
		
		else {
			
			screen.render(screen.xOffset+(xPos+8)+life, screen.yOffset+yPos+8, 4+6*32, Colours.get(-1, 300 , 200, -1), 0, 1);
			screen.render(screen.xOffset+(xPos+8)+life, screen.yOffset+yPos+24, 5+6*32, Colours.get(-1, 300 , 200, -1), 0, 1);
			screen.render(screen.xOffset+(xPos+8)+life, screen.yOffset+yPos+40, 6+6*32, Colours.get(-1, 300 , 200, -1), 0, 1);
		}
		
		
		}
		
		
		
	}
	
}
