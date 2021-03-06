package com.skilldistillery.blackjack.app;

import java.util.ArrayList;
import java.util.Scanner;


public class InputHandler {
	
	private Settings settings;
	private Scanner input;
	private int[] inputIndices;
	
	{
		settings = new Settings();
		input = new Scanner(System.in);
	}
	
	public void setInputIndices(int[] inputIndices) {
		this.inputIndices = inputIndices;
	}
	
	public int promptPlayers() {
		String line = "";
		int choice;
		line += settings.getChoosePlayersPrompt();
		choice = promptSingleDigInt(line, settings.getMinPlayers(), settings.getMaxPlayers());
		
		return choice;
	}
	
	public int promptRoundChoice(ArrayList<BlackjackHand> playerList, BlackjackHand currentPlayer) {
		String line = "";
		int choice;
		int numPlayers = playerList.size();
		int currentIndex = playerList.indexOf(currentPlayer);
		
		//insert empty spaces to start at point from inputIndices
		int numSpaces = inputIndices[currentIndex];
		for (int i = 0; i < numSpaces; i++) {
			line += " ";
		}
		
		line += settings.getChoicePrompt();
		choice = promptSingleDigInt(line, settings.getMinChoice(), settings.getMaxChoice());
		
		return choice;
	}
	
	public int promptRoundOverChoice(String leftSpacer) {
		String line = "";
		int choice;
		line += leftSpacer;
		line += settings.getChoicePrompt();
		
		choice = promptSingleDigInt(line, settings.getMinChoice(), settings.getMaxChoice());
		
		return choice;
	}
	
	private int promptSingleDigInt(String prompt, int minChoice, int maxChoice) {
		int output = 0;
		boolean keepTrying = true;
		String choice = "";
		
		while (keepTrying) {
			System.out.print(prompt);
			choice = input.next();
			
			//conditions that must be true for valid input
			if (choice.length() == 1) {
				for (int i = minChoice; i <= maxChoice; i++) {
					if (choice.equals(i + "")) {
						output = Integer.parseInt(choice);
						keepTrying = false;
						break;
					}
				}
			}
		}
		
		return output;
	}
	
	public void closeScanner() {
		input.close();
	}

}
