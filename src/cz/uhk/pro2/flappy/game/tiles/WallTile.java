package cz.uhk.pro2.flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappy.game.Tile;

public class WallTile implements Tile {
	Image image;
	
//	public WallTile(Image image) {
//		this.image = image;
//	}
	
	@Override
	public void draw(Graphics g, int x, int y) {
		//g.drawRect(x, y, Tile.SIZE, Tile.SIZE);
		g.drawImage(image, x, y, null);
	}

}
