package de.dhbw.game.entities.mob;

import de.dhbw.game.InputHandler;
import de.dhbw.game.entities.Mob;
import de.dhbw.game.entities.environment.Portal;
import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Font;
import de.dhbw.game.gfx.Screen;
import de.dhbw.game.level.Level;

public class Player extends Mob {

	private InputHandler input;
	// ------------------EIGENSCHAFTEN-------------------------------
	private int colour = Colours.get(-1, 111, shirtcolor, 543);
	private int scale = 1;
	public int speed = 2;
	protected boolean isSwimming = false;
	private int tickCount = 0;
	public static boolean isIndoor = false;
	public static int energy = 100;
	public static int teleportToX;
	public static int teleportToY;
	public static boolean hitting = false;
	public static int hitxmin;
	public static int hitxmax;
	public static int hitymin;
	public static int hitymax;
	public static int attackdir;
	public static int leben = 10;
	public static boolean immunity;	
	public int health = 100;
	public int maxhealth = 100;
	

	public Player(Level level, int x, int y, InputHandler input, int shirtcolor) {
		super(level, "Player", x, y, 1, shirtcolor);
		this.input = input;

	}

	public void tick() {
		
		int xa = 0;
		int ya = 0;

		if (tickCount % 75 == 0) {
			immunity = false;
		}
		if (immunity) {
			colour = Colours.get(-1, 111, 444, 444);
		} else {
			colour = Colours.get(-1, 111, shirtcolor, 543);
		}

		if (tickCount % 30 == 0 && energy < 100) {
			energy++;
		}
		if (tickCount % 45 == 0 && energy == 100 && leben < 10) {
			leben++;
		}
		
		// ------------------STEUERUNG (SIEHE
		// INPUTHANDLER)-------------------------------
		if (input.attack.isPressed()) {
			if (energy > 0) {
				hitting = true;
				energy--;
			}

		}
		if (input.attack.isPressed() == false) {
			hitting = false;
		}

//			if (input.shift.isPressed()) {
//				speed = 2;
//
//			}
//			if (!input.shift.isPressed()) {
//				speed = 2;
//			}

		if (input.up.isPressed()) {
			for (int i = 1; i <= speed; i++) {
				ya--;
			}
			movingDir = 0;
			hitxmin = x - 4;
			hitxmax = x + 12;
			hitymin = y - 24;
			hitymax = y - 8;
			attackdir = 0;
		}
		if (input.down.isPressed()) {
			for (int i = 1; i <= speed; i++) {
				ya++;
			}
			movingDir = 1;
			hitxmin = x - 4;
			hitxmax = x + 12;
			hitymin = y + 8;
			hitymax = y + 24;
			attackdir = 1;
		}
		if (input.left.isPressed()) {
			for (int i = 1; i <= speed; i++) {
				xa--;
			}
			movingDir = 2;
			hitxmin = x - 20;
			hitxmax = x - 4;
			hitymin = y - 8;
			hitymax = y + 8;
			attackdir = 2;
		}
		if (input.right.isPressed()) {
			for (int i = 1; i <= speed; i++) {
				xa++;
			}
			movingDir = 3;
			hitxmin = x + 12;
			hitxmax = x + 28;
			hitymin = y - 8;
			hitymax = y + 8;
			attackdir = 3;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			isMoving = true;

		} else {
			isMoving = false;
		}
		if (level.getTile(this.x >> 3, this.y >> 3).getId() == 3
				|| level.getTile(this.x >> 3, this.y >> 3).getId() == 6) {// WATER lava
			isSwimming = true;
		}

		if (isSwimming && level.getTile(this.x >> 3, this.y >> 3).getId() != 3 && isSwimming
				&& level.getTile(this.x >> 3, this.y >> 3).getId() != 6) {
			isSwimming = false;
		}

		if (level.getTile(this.x >> 3, this.y >> 3).getId() == 10) {// FLOOR
			isIndoor = true;
		} else {
			isIndoor = false;
		}

		if (Portal.teleport == true) {
			this.x = teleportToX;
			this.y = teleportToY;
			Portal.teleport = false;
		}

		tickCount++;

	}

	public void render(Screen screen) {
		int xTile = 0;
		int yTile = 28;
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;

		if (movingDir == 1) { // wenn er nach unten stiefelt, dann xTile 2 nach rechts, um auf dem spritesheet
								// zu verschieben
			xTile += 2;
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
		}

		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;

		// --------------------------HITTING----------------

		if (hitting) {
			if (movingDir == 0) {
				screen.render(x + 4, y - 16, 128, colour, 0, 1);
				screen.render(x - 4, y - 16, 128, colour, 1, 1);

			}
			if (movingDir == 1) {
				screen.render(x + 4, y + 8, 129, colour, 0, 1);
				screen.render(x - 4, y + 8, 129, colour, 1, 1);

			}
			if (movingDir == 2) {
				screen.render(x - 12, y - 8, 128, colour, 1, 1);
				screen.render(x - 12, y, 129, colour, 1, 1);

			}
			if (movingDir == 3) {
				screen.render(x + 12, y - 8, 128, colour, 0, 1);
				screen.render(x + 12, y, 129, colour, 0, 1);

			}

		}

		// --------------------------SCHWIMMEN----------------

		if (isSwimming) {
			int waterColour = 0;
			yOffset += 4;
			if (tickCount % 60 < 15) {
				waterColour = Colours.get(-1, -1, 225, -1);
			} else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
				yOffset -= 1;
				waterColour = Colours.get(-1, 225, 115, -1);
			} else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
				waterColour = Colours.get(-1, 115, -1, 225);
			} else {
				yOffset -= 1;
				waterColour = Colours.get(-1, 225, 115, -1);
			}
			screen.render(xOffset, yOffset + 3, 0 + 27 * 32, waterColour, 0x00, 1);
			screen.render(xOffset + 8, yOffset + 3, 0 + 27 * 32, waterColour, 0x01, 1);
		}

		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, colour, flipTop, scale);
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, colour, flipTop,
				scale);

		if (!isSwimming) {
			screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, colour,
					flipBottom, scale);
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier,
					(xTile + 1) + (yTile + 1) * 32, colour, flipBottom, scale);
		}

		// ------------------LEBENSANZEIGE-------------------------------
		Font.render("" + energy, screen, xOffset - ((2 - 1) / 2 * 8), yOffset - 10, Colours.get(-1, -1, -1, 555), 1);

		// TUTORIAL
//		Font.render("move: WASD", screen, 20, 18, Colours.get(-1, -1, -1, 555), 1);
//		Font.render("attack: x", screen, 114, 18, Colours.get(-1, -1, -1, 555), 1);
//		Font.render("some places are hidden", screen, 300, 12, Colours.get(-1, -1, -1, 555), 1);
//		Font.render("eat to survive", screen, 500, 12, Colours.get(-1, -1, -1, 555), 1);
//		Font.render("collect ep", screen, 700, 12, Colours.get(-1, -1, -1, 555), 1);
		
		
		

	}

	public boolean hasCollided(int xa, int ya) { // HITBOX
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}
		return false;
	}

	public void teleportTo(int tel_x, int tel_y) { //funktioniert noch nicht wie ich es mir vorstelle
		x = 100;
		y = 100;

	}

	public static void hurt() {

		if (immunity == false) {
			leben = leben-1;
			immunity = true;	
		}
		else return;
				
	}

		

}
