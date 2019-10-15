package de.dhbw.menu;

import javax.swing.JFrame;

public class ScreenController {

	
	private static StartMenu menu;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SplashScreen screen = new SplashScreen();
		screen.setVisible(true);
		
		try {
			Thread.sleep(2100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		screen.dispose();
		
		menu = new StartMenu();
		menu.setVisible(true);
		
	}

	public static void removeMenu() {
		// TODO Auto-generated method stub
	
		menu.dispose();
		
	}

}
