package com.skilldistillery.blackjack.app;

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
		game.start();
	}

}
