package com.skilldistillery.blackjack.app;

public class Settings {

	//GameHandler
	private final int FIRST_PLAYER_INDEX = 0; //0 for Player 1
	private static int displayWidth;
	private static int numPlayers;
	private final int MIN_PLAYERS = 1;
	private final int MAX_PLAYERS = 6;
	
	//InputHandler and DisplayHandler
	//note: recommended minimum of 22 for COL_WIDTH, largest is ROUND_OPTIONS
	private final int COL_WIDTH = 22; //width of each player column
	private final int COL_SPACER = 4; //empty space between columns
	private final int LEFT_SPACER = 4; //empty space before first column 
	private final char LINE_BREAK_CHAR = '-'; //repeated line break character
	private final String CHOOSE_PLAYERS = "Enter number of human players: ";
	private final String ROUND_OPTIONS = "1. Hit 2. Stay 3. Exit";
	private final String CHOICE_PROMPT = "Your choice: ";
	private final String ROUND_OVER_STRING = "ROUND OVER! WINNER"; //leave off : and space for plurality
	private final String ROUND_OVER_OPTIONS = "1. Play again\t2. Main Menu\t3. Exit Game";
	private final String GOODBYE_MESSAGE = "Goodbye - thank you for playing! Exiting game...";
	private final int MIN_CHOICE = 1;
	private final int MAX_CHOICE = 3;
	
	public int getFirstPlayerIndex() {
		return FIRST_PLAYER_INDEX;
	}
	
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
		setDisplayWidth(numPlayers);
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
	private void setDisplayWidth(int numPlayers) {
		displayWidth = LEFT_SPACER + (numPlayers * COL_WIDTH) + ( (numPlayers - 1) * COL_SPACER);
	}
	
	public int getDisplayWidth() {
		return displayWidth;
	}
	
	public int getColWidth() {
		return COL_WIDTH;
	}
	
	public int getColSpacer() {
		return COL_SPACER;
	}
	
	public String getRoundOptions() {
		return ROUND_OPTIONS;
	}
	
	public int getLeftSpacer() {
		return LEFT_SPACER;
	}

	public char getlineBreakChar() {
		return LINE_BREAK_CHAR;
	}
	
	public String getChoosePlayersPrompt() {
		return CHOOSE_PLAYERS;
	}

	public String getChoicePrompt() {
		return CHOICE_PROMPT;
	}

	public String getRoundOverString() {
		return ROUND_OVER_STRING;
	}

	public String getRoundOverOptions() {
		return ROUND_OVER_OPTIONS;
	}
	
	public String getGoodbyeMessage() {
		return GOODBYE_MESSAGE;
	}

	public int getMinChoice() {
		return MIN_CHOICE;
	}

	public int getMaxChoice() {
		return MAX_CHOICE;
	}

	public int getMinPlayers() {
		return MIN_PLAYERS;
	}

	public int getMaxPlayers() {
		return MAX_PLAYERS;
	}
	
}
