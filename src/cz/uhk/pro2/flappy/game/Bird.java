package cz.uhk.pro2.flappy.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;

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
	
	Image image; // obrazek ptaka
	
	
	public Bird(int initialX, int initialY, Image image) {
		this.viewportX = initialX;
		this.viewportY = initialY;
		this.image = image;
	}
	
	public void kick() {
		velocityY = koefUp;
		ticksToFall = ticksFlyingUp;
	}
	
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		//g.fillOval(viewportX-Tile.SIZE/2, (int)viewportY-Tile.SIZE/2, Tile.SIZE, Tile.SIZE);
		g.drawImage(image, viewportX-Tile.SIZE/2, (int)viewportY-Tile.SIZE/2, null);
		
		// debug, souradnice ptaka
		g.setColor(Color.BLACK);
		g.drawString(viewportX+", "+viewportY, viewportX, (int)viewportY);
	}
	
	public boolean collidesWithRectangle(int x, int y, int w, int h) {
		// vytvorime kruznici reprezentujici obrys ptaka
		Ellipse2D.Float birdsBoundary = new Ellipse2D.Float(
				viewportX-Tile.SIZE/2, 
				(int)viewportY-Tile.SIZE/2, 
				Tile.SIZE, 
				Tile.SIZE);
		// overime, zda kruznice ma neprazdny prunik s ctvercem zadanym x,y,w,h
		return birdsBoundary.intersects(x, y, w, h);
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
