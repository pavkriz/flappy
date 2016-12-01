package cz.uhk.pro2.flappy.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import cz.uhk.pro2.flappy.game.GameBoard;
import cz.uhk.pro2.flappy.game.Tile;
import cz.uhk.pro2.flappy.game.tiles.WallTile;

public class CsvGameBoardLoader implements GameBoardLoader {
	InputStream is;
	
	public CsvGameBoardLoader(InputStream is) {
		this.is = is;
	}
	
	@Override
	public GameBoard loadLevel() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String[] line = br.readLine().split(";");
			int typeCount = Integer.parseInt(line[0]);
			// radky s definicemi typu dlazdic
			Map<String, Tile> tileTypes = new HashMap<>();
			for (int i = 0; i < typeCount; i++) {
				line = br.readLine().split(";");
				String tileType = line[0];
				String clazz = line[1];
				int x = Integer.parseInt(line[2]);
				int y = Integer.parseInt(line[3]);
				int w = Integer.parseInt(line[4]);
				int h = Integer.parseInt(line[5]);
				String url = line[6];
				Tile tile = createTile(clazz, x, y, w, h);
				tileTypes.put(tileType, tile);
			}
			line = br.readLine().split(";");
			int rows = Integer.parseInt(line[0]);
			int columns = Integer.parseInt(line[1]);
			// vytvorime pole dlazdic odpovidajicich rozmeru z CSV
			Tile[][] tiles = new Tile[rows][columns];
			System.out.println(rows + "," + columns);
			for (int i = 0; i < rows; i++) {
				line = br.readLine().split(";");
				for (int j = 0; j < columns; j++) {
					String cell;// = (j<line.length) ? line[j] : "";
					if (j < line.length) {
						// bunka v CSV existuje, ok
						cell = line[j];
					} else {
						// bunka v CSV chybi, povazujeme ji za prazdnou
						cell = "";
					}
					// ziskame odpovidajici typ dlazdice z hashmapy
					tiles[i][j] = tileTypes.get(cell);
				}
			}
			GameBoard gb = new GameBoard(tiles);
			return gb;
		} catch (IOException e) {
			throw new RuntimeException("Chyba pri cteni souboru", e);
		}
	}

	private Tile createTile(String clazz, int x, int y, int w, int h) {
		// TODO Auto-generated method stub
		return null;
	}

}
