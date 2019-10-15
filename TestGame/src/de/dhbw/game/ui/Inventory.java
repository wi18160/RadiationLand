package de.dhbw.game.ui;

import de.dhbw.game.Game;
import de.dhbw.game.InputHandler;
import de.dhbw.game.entities.mob.Player;
import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Font;
import de.dhbw.game.gfx.Screen;

public class Inventory extends Interface{

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
	private int timer;
	public static InputHandler input = Game.input;
	private boolean render = true;

	
	public Inventory(Screen screen, Player player, int xPos, int yPos, int pixelWidth, int pixelHeight, int TileTopBottomIndex,
			int TileLeftRightIndex, int TileFillIndex, int Color, boolean fill, String header) {
		super(screen, player, xPos, yPos, pixelWidth, pixelHeight, TileTopBottomIndex, TileLeftRightIndex, TileFillIndex, Color, fill, header);
		
		this.selected = 0;
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
		this.render = true;
		
	}

	
	public void create() {
		

//		//Die angegebene X-Koordinate ist die obere linke Koordinate der Box
//		int xPos1 = xPos;
//		//Obere linke Ecke + Boxbreite mal 8 (weil 8x8 Pixel) minus 8 (da die Gesamtbreite angegeben ist)
//		int xPos2 = xPos - 8 + pixelWidth * 8;
//		int yPos1 = yPos;
//		int yPos2 = yPos - 8 + pixelHeight * 8;
//		
//	
//		
//		//Screen 
//		// Höhe 29 Pixel
//		//Breite 40 Pixel
//		
//		
//		//Ecken rendern
//		screen.render(screen.xOffset + xPos1, screen.yOffset + yPos1, TileTopBottomIndex, Color, 0, 1);
//		screen.render(screen.xOffset + xPos2, screen.yOffset + yPos1, TileTopBottomIndex, Color, 1, 1);
//		screen.render(screen.xOffset + xPos1, screen.yOffset + yPos2, TileTopBottomIndex, Color, 2, 1);
//		screen.render(screen.xOffset + xPos2, screen.yOffset + yPos2, TileTopBottomIndex, Color, 3, 1);
//		
//		//Links/Rechts rendern
//		for(int x=1; x < pixelWidth - 1; x++) {
//			
//			screen.render(screen.xOffset + xPos1 + 8*x, screen.yOffset + yPos1, TileLeftRightIndex, Color, 0, 1);
//			screen.render(screen.xOffset + xPos1 + 8*x, screen.yOffset + yPos2, TileLeftRightIndex, Color, 2, 1);
//		
//			
//		}
//		
//		//Oben/Unten rendern
//		for(int y=1; y<pixelHeight - 1; y++) {
//			
//			screen.render(screen.xOffset + xPos2, screen.yOffset + yPos1+8*y, TileFillIndex, Color, 0, 1);
//			screen.render(screen.xOffset + xPos1, screen.yOffset + yPos1+8*y, TileFillIndex, Color, 1, 1);
//		}
//		
//		//Wenn gewünscht, Box befüllen
//		if(fill==true) {
//
//		for(int y=1; y < pixelHeight - 1; y++) {
//			
//			for(int x=1; x < pixelWidth - 1; x++) {
//				
//				screen.render(screen.xOffset + xPos1+8*x, screen.yOffset+yPos1+8*y, 3+6*32, Color, 0, 1);
//				
//			}
//			
//		}
//		
//		Font.render(header, screen, screen.xOffset + xPos + ((pixelWidth * 8)/2) - (header.length()*8/2), screen.yOffset + yPos + 8, Colours.get(-1, -1, 1, 555), 1);
//		
//		
//		}
		
		renderBox();
		addItem();
		
		
	}
	
	public void checkInput() {
		
		
		super.setRender(true);
		
		if(input.i.isPressed()) {
			
			timer++;
			System.out.println(timer);
			if((timer/10) > 1) {
				
				super.setRender(false);
				timer = 0;
			}
			
		}
		
	}
	
	

	
	public void addItem() {
		
		
		String items[] = new String[11];
		
		items[0] = "Item 0";
		items[1] = "Item 1";
		items[2] = "Item 2";
		items[3] = "Item 3";
		items[4] = "Item 4";
		items[5] = "Item 5";
		items[6] = "Item 6";
		items[7] = "Item 7";
		items[8] = "Item 8";
		items[9] = "Item 9";
		items[10] = "Item 10";
		
		
	
		
		//Anzahl der Items berechnen, die in die Boxhöhe passen 
		int invspace = ((this.pixelHeight * 8) - 32) / 8;
		int itemss = items.length;
		
		

		for(int i = 0; i<itemss/2; i++) {
			
			screen.render(screen.xOffset+this.getX()+8, screen.yOffset+this.getY()+24+i*8, 8+6*32, Colours.get(-1, -1, -1, 255), 1, 1);
			Font.render(items[i], screen, screen.xOffset+this.getX()+16, screen.yOffset+this.getY()+24+i*8, Colours.get(-1, -1, -1, 255), 1);
			Font.render("3", screen, screen.xOffset+this.getX2()-8, screen.yOffset+this.getY()+24+i*8, Colours.get(-1, -1, -1, 255), 1);
		}
		
		
	}
	
	
}
