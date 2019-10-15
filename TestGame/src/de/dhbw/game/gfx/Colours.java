package de.dhbw.game.gfx;

public class Colours {

	
	//HOW TO USE COLOURING:
	//SPRITESHEET 1 = black, 2 gray, 3= lightgray, 4 = white
	//if colors not used, colourx = -1;
	//then RGB from 000-555 -> 	ZB 453 = 80% rot, 100% grün, 60% blau
	
    public static int get(int colour1, int colour2, int colour3, int colour4) {
        return (get(colour4) << 24) + (get(colour3) << 16) + (get(colour2) << 8) + get(colour1);
    }

    private static int get(int colour) {
        if (colour < 0) return 255; //wenn eine negative Farbe kommt, dann wird nicht gerendert
        int r = colour / 100 % 10; //
        int g = colour / 10 % 10;
        int b = colour % 10;
        return r * 36 + g * 6 + b;
    }
}
