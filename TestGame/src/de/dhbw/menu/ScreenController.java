package de.dhbw.menu;

public class ScreenController {

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
		
		StartMenu menu = new StartMenu();
		menu.setVisible(true);
		
	}

}
