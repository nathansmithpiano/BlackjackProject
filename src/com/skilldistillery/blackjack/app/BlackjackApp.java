package com.skilldistillery.blackjack.app;

import com.skilldistillery.blackjack.common.Deck;

public class BlackjackApp {
	
	GameHandler game;
	
	{
		game = new GameHandler();
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
