package com.skilldistillery.blackjack.app;

import com.skilldistillery.blackjack.common.Deck;

public class BlackjackApp {
	
//	DeckTester game;
	GameOverTesterMultiPlayer game;
	
	{
//		game = new DeckTester();
		game = new GameOverTesterMultiPlayer();
	}
	

	public static void main(String[] args) {
		BlackjackApp app = new BlackjackApp();
		app.run();
	}
	
	private void run() {
		game.setup();
		game.start();
		
	}

}
