package de.dhbw.game.ui;

import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Font;
import de.dhbw.game.gfx.Screen;

public class MainMenu {

	private static Screen screen;

	public MainMenu(Screen screen) {
		
		this.screen = screen;
		
	}
	
	public void render() {
		
	Font.render("Test", screen, 20, 5, Colours.get(000, -1, -1, 555), 1);	
	screen.render(screen.xOffset + 90, screen.yOffset+24, 3+6*32, Colours.get(-1, 8, 2, 255), 0, 1);

		
	}
	
	
	
}
