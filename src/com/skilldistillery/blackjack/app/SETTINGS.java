package com.skilldistillery.blackjack.app;

public class SETTINGS {

	
	//note: recommended minimum of 22 for COL_WIDTH, largest is ROUND_OPTIONS
	public static final int COL_WIDTH = 22; //width of each player column
	public static final int COL_SPACER = 4; //empty space between columns
	public static final int LEFT_SPACER = 4; //empty space before first column 
	public static final String ROUND_OPTIONS = "1. Hit 2. Stay 3. Exit";
	public static final String CHOICE_PROMPT = "Your choice: ";
	//TODO : integrate these here instead of DisplayHandler;
	
	public static final int MIN_CHOICE = 1;
	public static final int MAX_CHOICE = 3;
	
}
