package de.dhbw.game.ui;

import de.dhbw.game.entities.mob.Player;
import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Font;
import de.dhbw.game.gfx.Screen;

public class TextBox extends Interface{

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
	private String[] textoption;
	private String response;
	private int timer;
	
	
	
	public TextBox(Screen screen, Player player, int xPos, int yPos, int pixelWidth, int pixelHeight, int TileTopBottomIndex, int TileLeftRightIndex, int TileFillIndex, int Color, boolean fill, String header, String[] textoption) {
		super(screen, player, xPos, yPos, pixelWidth, pixelHeight, TileTopBottomIndex, TileLeftRightIndex, TileFillIndex, Color, fill, header, textoption);
		

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
		this.textoption = textoption;
		this.response = "";
		this.timer = 0;
		
		
	}

	
	public void create() {
		
		super.renderBox();
		Font.render(header, screen, screen.xOffset + xPos + ((pixelWidth * 8)/2) - (header.length()*8/2), screen.yOffset + yPos + 8, Color, 1);
	
	
		
		for(int i=0; i< textoption.length; i++) {
			
			if((selected/10) == i) {
				Font.render(textoption[i], screen, screen.xOffset + xPos + ((pixelWidth * 8)/2) - (textoption[i].length()*8/2), screen.yOffset + yPos + 32 + 8*i, Colours.get(-1, -1, 1, 2), 1);

			}else {
				
				Font.render(textoption[i], screen, screen.xOffset + xPos + ((pixelWidth * 8)/2) - (textoption[i].length()*8/2), screen.yOffset + yPos + 32 + 8*i, Colours.get(-1, -1, 1, 555), 1);

				
			}
			

			
		}
		
		
		
	}
	
	public void checkInput() {
		
		if(input.arrowdown.isPressed() && render) 
		{
			
			
			if((selected/10) == textoption.length) {
				selected = 0;
				
			}	
			
			else {
			
		selected++;
			}
		
		}
		
		
		if(input.arrowup.isPressed() && render == true) {
			
			if(selected < 0) {
				selected = textoption.length * 10;
			}
			else {
			
				System.out.println(selected);
			selected--;
			}
			
			
			
			}
		
		if(input.enter.isPressed() && render == true) {
			
			String response = textoption[selected/10];
			this.clear();
			super.getInterfaceOutput(response);
			
		}
		
		
	}
	
}
