package cz.uhk.pro2.flappy.service;

import cz.uhk.pro2.flappy.game.GameBoard;

/**
 * Spolecne rozhrani pro tridy umoznujici nacitat level.
 * 
 * @author krizpa1
 *
 */
public interface GameBoardLoader {
	/** 
	 * Nacte level (herni plochu).
	 * 
	 * @return
	 */
	GameBoard loadLevel();
}
