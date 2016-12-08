package cz.uhk.pro2.flappy.game;

import java.awt.Graphics;

import cz.uhk.pro2.flappy.game.tiles.WallTile;

public class GameBoard implements TickAware {
	Tile[][] tiles;
	int shiftX = 30;
	int viewportWidth = 200; // TODO
	Bird bird;
	
	public GameBoard() {
		tiles = new Tile[20][20]; // TODO
		//tiles[2][1] = new WallTile();
		//tiles[1][1] = new WallTile();
		bird = new Bird(viewportWidth/2, tiles.length*Tile.SIZE/2);
	}
	
	public GameBoard(Tile[][] tiles) {
		this.tiles = tiles;
		bird = new Bird(viewportWidth/2, tiles.length*Tile.SIZE/2);
	}
	
	/**
	 * Kresli cely herni svet (zdi, bonusy, ptaka) na platno g.
	 * @param g
	 */
	public void draw(Graphics g) {
		// spocitame prvni j-index bunky, kteoru ma smysl kreslit
		// (je videt ve viewportu, tj. na obrazovce)
		int minJ = shiftX/Tile.SIZE;
		// +2 protoze celociselne delime jak shiftX tak viewportSize
		// ale chceme "zaokrouhlit" nahoru
		int maxJ = minJ + viewportWidth/Tile.SIZE + 2;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = minJ; j < maxJ; j++) {
				// cheme, aby se svet "tocil" dokola,
				// j2 se pochybuje 0..pocet sloupcu - 1
				int j2 = j % tiles[0].length;
				Tile t = tiles[i][j2];
				if (t != null) { // je na souradnicich i,j dlazdice? 
					int screenX = j*Tile.SIZE - shiftX;
					int screenY = i*Tile.SIZE;				
					t.draw(g, screenX, screenY);
				}
			}
		}
		// ptak
		bird.draw(g);
	}

	@Override
	public void tick(long ticksSinceStart) {
		//s kazdym tickem ve hre posuneme hru o jeden pixel
		//tj. pocet ticku a pixelu posunu se rovnaji
		shiftX = (int)ticksSinceStart;
		
		// dame vedet jeste ptakovi, ze hodiny tickly
		bird.tick(ticksSinceStart);
	}
	
	public int getHeightPix() {
		return tiles.length*Tile.SIZE;
	}
	
	public void kickTheBird() {
		bird.kick();
	}
}
