package com.skilldistillery.blackjack.app;

import java.util.Scanner;

public class InputHandlerOLD {
	
	Scanner input;
	
	{
		input = new Scanner(System.in);
	}
	
	public int promptRound(int playerNum) {
		
		//tab over for players
		String tabSpacer = "";
		if (playerNum != 0) {
			for (int i = 0; i < playerNum; i++) {
				tabSpacer += "\t\t\t";
			}
		}
		String prompt = tabSpacer + "Player " + (playerNum + 1) + ": ";
		int choice = promptSingleDigInt(prompt, 1, 2);
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

}
