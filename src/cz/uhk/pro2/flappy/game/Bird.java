package cz.uhk.pro2.flappy.game;

import java.awt.Color;
import java.awt.Graphics;

public class Bird implements TickAware {
	//fyzika
	static final double koefUp = -5.0;
	static final double koefDown = 2;
	static final int ticksFlyingUp = 4;
	
	//souradnice stredu ptaka
	int viewportX;
	double viewportY;
	//rychlost padani (pozitivni) nebo vzletu (negativni)
	double velocityY = koefDown;
	//kolik ticku jeste zbyva, nez ptak po nakopnuti zacne padat
	int ticksToFall = 0;
	
	
	public Bird(int initialX, int initialY) {
		this.viewportX = initialX;
		this.viewportY = initialY;
	}
	
	public void kick() {
		velocityY = koefUp;
		ticksToFall = ticksFlyingUp;
	}
	
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(viewportX-Tile.SIZE/2, (int)viewportY-Tile.SIZE/2, Tile.SIZE, Tile.SIZE);
		
		// debug, souradnice ptaka
		g.setColor(Color.BLACK);
		g.drawString(viewportX+", "+viewportY, viewportX, (int)viewportY);
	}
	
	
	@Override
	public void tick(long ticksSinceStart) {
		if (ticksToFall > 0) {
			// ptak jeste leti nahoru, "cekame"
			ticksToFall--;
		} else {
			// ptak pada nebo ma zacit padat
			velocityY = koefDown;
		}
		viewportY = viewportY + velocityY;
	}

}
