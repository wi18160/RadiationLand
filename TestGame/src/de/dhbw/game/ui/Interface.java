package de.dhbw.game.ui;

import java.util.ArrayList;

import de.dhbw.game.Game;
import de.dhbw.game.InputHandler;
import de.dhbw.game.entities.mob.Player;
import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Font;
import de.dhbw.game.gfx.Screen;

 

public class Interface {

	private Screen screen;
	private int xPos;
	private int yPos;
	private int pixelWidth;
	private int pixelHeight;
	private Player player;
	private int TileTopBottomIndex;
	private int TileLeftRightIndex;
	private int TileFillIndex;
	private int Color;
	private boolean fill;
	public int selected;
	private boolean render;
	private int type;
	private String header;
	private String[] textoption;
	private static ArrayList<Interface> uiarr = new ArrayList<Interface>(); // Create an ArrayList object
	private String response;
	private int timer;
	
	
	//Konstruktor für Interface-Box mit oder ohne Überschrift
	public Interface(Screen screen, Player player, int xPos, int yPos, int pixelWidth, int pixelHeight, int TileTopBottomIndex, int TileLeftRightIndex, int TileFillIndex, int Color, boolean fill, String header) {
		
		this.selected = 0;
		this.render = false;
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
	
	//Konstruktor für Interface mit Header und Textauswahl
	public Interface(Screen screen, Player player, int xPos, int yPos, int pixelWidth, int pixelHeight, int TileTopBottomIndex, int TileLeftRightIndex, int TileFillIndex, int Color, boolean fill, String header, String[] textoption) {
		
		
		this.selected = 0;
		this.render = false;
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
		this.textoption = textoption;
		this.type = 0;
		this.response = "";
		
		
	}
	
	
	public void setRender(boolean render) {
		
		this.render = render;
	}
	
	public boolean getRender() {
		
		return this.render;
		
	}
	
	public static InputHandler input = Game.input;

	
public void renderBox() {
		
	
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
//			screen.render(screen.xOffset + xPos2, screen.yOffset + yPos1+8*y, 3+6*32, Color, 0, 1);
//			screen.render(screen.xOffset + xPos1, screen.yOffset + yPos1+8*y, 3+6*32, Color, 1, 1);
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
//		
		
	//Die angegebene X-Koordinate ist die obere linke Koordinate der Box
			int xPos1 = xPos;
			//Obere linke Ecke + Boxbreite mal 8 (weil 8x8 Pixel) minus 8 (da die Gesamtbreite angegeben ist)
			int xPos2 = xPos - 8 + pixelWidth * 8;
			int yPos1 = yPos;
			int yPos2 = yPos - 8 + pixelHeight * 8;
			
		
			
			//Screen 
			// Höhe 29 Pixel
			//Breite 40 Pixel
			
			
			//Ecken rendern
			screen.render(screen.xOffset + xPos1, screen.yOffset + yPos1, TileTopBottomIndex, Color, 0, 1);
			screen.render(screen.xOffset + xPos2, screen.yOffset + yPos1, TileTopBottomIndex, Color, 1, 1);
			screen.render(screen.xOffset + xPos1, screen.yOffset + yPos2, TileTopBottomIndex, Color, 2, 1);
			screen.render(screen.xOffset + xPos2, screen.yOffset + yPos2, TileTopBottomIndex, Color, 3, 1);
			
			//Links/Rechts rendern
			for(int x=1; x < pixelWidth - 1; x++) {
				
				screen.render(screen.xOffset + xPos1 + 8*x, screen.yOffset + yPos1, TileLeftRightIndex, Color, 0, 1);
				screen.render(screen.xOffset + xPos1 + 8*x, screen.yOffset + yPos2, TileLeftRightIndex, Color, 2, 1);
			
				
			}
			
			//Oben/Unten rendern
			for(int y=1; y<pixelHeight - 1; y++) {
				
				screen.render(screen.xOffset + xPos2, screen.yOffset + yPos1+8*y, TileFillIndex, Color, 0, 1);
				screen.render(screen.xOffset + xPos1, screen.yOffset + yPos1+8*y, TileFillIndex, Color, 1, 1);
			}
			
			//Wenn gewünscht, Box befüllen
			if(fill==true) {

			for(int y=1; y < pixelHeight - 1; y++) {
				
				for(int x=1; x < pixelWidth - 1; x++) {
					
					screen.render(screen.xOffset + xPos1+8*x, screen.yOffset+yPos1+8*y, 3+6*32, Color, 0, 1);
					
				}
				
			}
			
			Font.render(header, screen, screen.xOffset + xPos + ((pixelWidth * 8)/2) - (header.length()*8/2), screen.yOffset + yPos + 8, Colours.get(-1, -1, 1, 555), 1);
			
			
			}
		
	}

	public void checkInput() {
		
		
		
			
			
			
		
		
	}
	
	public String getInterfaceOutput(String response) {
		
		System.out.println(response);
		return response;
		
	}

	
	public void clear() {
		
		this.render = false;
		
	}
	
	public void setX(int x) {
		
		this.xPos = x;
		
	}
	
	public int getX() {
		
		return xPos;
	}
	
	public static void addUi(Interface uiarr) {
		
		Interface.uiarr.add(uiarr);
		
	}
	
	public void create() {
		
		renderBox();
	}
	
	public static void render() {
	
		
		
		
		
		for(int i=0; i < uiarr.size(); i++) {
					
					Boolean render = uiarr.get(i).render;
					
					uiarr.get(i).checkInput();
					uiarr.get(0).setRender(true);
					
				
					
					if(render) 
					{
										
						uiarr.get(i).create();
						
						
					}
					
		}						
		
		
	}

}
