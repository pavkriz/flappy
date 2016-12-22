package cz.uhk.pro2.flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappy.game.Tile;

public class BonusTile extends AbstractTile {
	
	Tile emptyTile;
	boolean active = true;

	public BonusTile(Image image, Tile emptyTile) {
		super(image);
		this.emptyTile = emptyTile;
	}
	
	/**
	 * Kopirovaci (klonovaci) konstruktor
	 * @param original
	 */
	public BonusTile(BonusTile original) {
		super(original.image);
		emptyTile = original.emptyTile;
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		if (active) {
			super.draw(g, x, y);
		} else {
			emptyTile.draw(g, x, y);
		}
	}

	public void setActive(boolean active) {
		this.active = active;
		
	}
}
