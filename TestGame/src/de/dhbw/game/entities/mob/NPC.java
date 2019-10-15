package de.dhbw.game.entities.mob;

import java.util.Random;

import de.dhbw.game.entities.Mob;
import de.dhbw.game.gfx.Colours;
import de.dhbw.game.gfx.Font;
import de.dhbw.game.gfx.Screen;
import de.dhbw.game.level.Level;

public class NPC extends Mob {

	public NPC(Level level, String name, int x, int y, int speed, int shirtcolor) {
		super(level, name, x, y, speed, shirtcolor);
	}

	private int colour = Colours.get(-1, 111, shirtcolor, 543);
	private int scale = 1;
	protected boolean isSwimming = false;
	private int tickCount = 0;
	int richtung = 0;
	// private int abstand = 32; //Abstand zu Player in pixeln
	public boolean laufen = true;
	public String aussage = "";
	public boolean alive = true;
	public int leben = 4;
	Player player;

	public void tick() {// ------------------60 MAL PRO SEKUNDE-------------------------------

		if (alive) {

			Random r = new Random();
			int wechsel = r.nextInt(240);
			// ------------------ MIT 100/240 WAHRSCHEINLICHKEIT ÄNDERT DER ZOMBIE DIE
			// RICHUNG-------------------------------
			if (wechsel > 100) {

				if (wechsel > 235) {
					Random r2 = new Random();
					richtung = r2.nextInt(4);
				}

				int xa = 0;
				int ya = 0;

				// ------------------JA NACH WERT VON RICHTUNG WIRD
				// GELAUFEN-------------------------------
				if (richtung == 0 && laufen == true) {
					ya--;
				}
				if (richtung == 1 && laufen == true) {
					ya++;
				}
				if (richtung == 2 && laufen == true) {
					xa--;
				}
				if (richtung == 3 && laufen == true) {
					xa++;
				}

				if (xa != 0 || ya != 0 && laufen == true) {
					move(xa, ya);
					isMoving = true;

				}
			} else {
				isMoving = false;
			}
			if (level.getTile(this.x >> 3, this.y >> 3).getId() == 3) {
				isSwimming = true;
			}

			if (level.getTile(this.x >> 3, this.y >> 3).getId() == 6) {
				isSwimming = true;
				lavahurt();
			}

			if (isSwimming && level.getTile(this.x >> 3, this.y >> 3).getId() != 3 && isSwimming
					&& level.getTile(this.x >> 3, this.y >> 3).getId() != 6) {
				isSwimming = false;
			}

			if (Player.hitting && Player.hitxmin < this.x + 4 && Player.hitxmax > this.x + 4 && Player.hitymin < this.y
					&& Player.hitymax > this.y) {
				this.hurt();
			} else {

			}

		}

	}

	private void lavahurt() {this.die();}

	private void hurt() {

		if (leben > 0) {
			if (Player.attackdir == 0) {
				this.y = this.y - 10;
			}
			if (Player.attackdir == 1) {
				this.y = this.y + 10;
			}
			if (Player.attackdir == 2) {
				this.x = this.x - 10;
			}
			if (Player.attackdir == 3) {
				this.x = this.x + 10;
			}
			leben--;
		} else {
			this.die();
		}

	}

	private void die() {
		isSwimming = false;
		this.colour = Colours.get(-1, -1, -1, -1);
		alive = false;
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

		Font.render(aussage, screen, xOffset - ((aussage.length() - 1) / 2 * 8), yOffset - 10,
				Colours.get(-1, -1, -1, 555), 1);

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

}