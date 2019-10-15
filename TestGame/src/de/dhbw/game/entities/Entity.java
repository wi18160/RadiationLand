package de.dhbw.game.entities;

import de.dhbw.game.gfx.Screen;
import de.dhbw.game.level.Level;

//Blaupause für unterklassen, also alle weiteren entities
//STRUKTUR: ENTITIES -> MOB -> (PLAYER, DOG, NPC)

public abstract class Entity {
	
	//------------------POSITION UND LEVEL-------------------------------
	public int x;
	public int y; 
	protected Level level;

	public Entity(Level level) {
		init(level);
	}

	public final void init(Level level) {
		this.level = level;
	}

	public abstract void tick();

	public abstract void render(Screen screen);
}