package de.dhbw.game.entities.mob;

import java.util.Random;

import de.dhbw.game.Game;
import de.dhbw.game.entities.Mob;
import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Font;
import de.dhbw.game.gfx.Screen;
import de.dhbw.game.level.Level;

public class Dog extends Mob {

	// Konstruktor: Daten, die �bergeben werden m�ssen, um einen Hund zu erstellen
	public Dog(Level level, String name, int x, int y, int speed, int shirtcolor) {
		super(level, name, x, y, speed, shirtcolor);
	}

	// Eigenschaften
	private int colour = Colours.get(-1, 321, 432, 555); // Farbgebung
	private int scale = 1; // Skalierung
	protected boolean isSwimming = false; // Rendering: Wasser ja/nein
	private int tickCount = 0;
	String aussage = ""; // Aussage
	private int abstand = 32; // Abstand zu Player in pixeln

	public void tick() { // Durchl�uft alle 60 sekunden

		// Relative Position am anfang
		int xa = 0;
		int ya = 0;		

		// Folge Player
		if (Game.playerX != this.x || Game.playerY != this.y) {

			if (Game.playerY + abstand < this.y) {
				ya--;
			}
			if (Game.playerY - abstand > this.y) {
				ya++;
			}
			if (Game.playerX + abstand < this.x) {
				xa--;
			}
			if (Game.playerX - abstand > this.x) {
				xa++;
			}
			if (xa != 0 || ya != 0) { // Bewegung in 2 Richtungen
				move(xa, ya);
				isMoving = true;
			}
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

		Random r = new Random();
		int aussagewechsel = r.nextInt(100);
		if (aussagewechsel > 97) {
			int wechsel = r.nextInt(100);
			if (wechsel < 80)
				aussage = "";
			else
				aussage = "woof";
		}

	}

	// --------------------RENDERING------------------------------
	public void render(Screen screen) { // Anzeige auf dem screen
		int xTile = 0;
		int yTile = 25; // Position auf dem spriteheet
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

		if (isSwimming) { // Anderes Rendering, wenn schwimmen aktiv ist
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
		// Schrift �ber seinem Kopf
		Font.render(aussage, screen, xOffset - ((aussage.length() - 1) / 2 * 8), yOffset - 10,
				Colours.get(-1, -1, -1, 555), 1);

	}

	// --------------------HITBOX------------------------------
	public boolean hasCollided(int xa, int ya) {
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
}