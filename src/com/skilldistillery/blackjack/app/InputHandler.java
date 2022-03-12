package com.skilldistillery.blackjack.app;

import java.util.Scanner;

public class InputHandler {
	
	Scanner input;
	
	{
		input = new Scanner(System.in);
	}
	
	private int promptSingleDigInt(String prompt, int firstOption, int lastOption) {
		int output = 0;
		boolean keepTrying = true;
		String choice = "";
		
		while (keepTrying) {
			System.out.print(prompt + " (" + firstOption + "-" + lastOption + "): ");
			choice = input.next();
			
			//conditions that must be true for valid input
			if (choice.length() == 1) {
				for (int i = firstOption; i < lastOption; i++) {
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
