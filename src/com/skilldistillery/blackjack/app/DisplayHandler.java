package com.skilldistillery.blackjack.app;

import java.util.ArrayList;

import com.skilldistillery.blackjack.common.Card;

public class DisplayHandler {
	
	//note: recommended minimum of 20 for COL_WIDTH
	private final int COL_WIDTH = 20; //width of each player column
	private final int COL_SPACER = 4; //empty space between columns
	private final int LEFT_SPACER = 4; //empty space before first column 
	
	public void printCards(ArrayList<BlackjackHand> playerList, BlackjackHand currentPlayer) {
		
		
		//each Hand has showCard(boolean hideFirst, int index) which will return String of the card info
		//showCard can choose to hide or show
		
		String[][] cards; //[row][player] of width players and of height most cards
		
		int mostCards = 0;
		int numPlayers = playerList.size();
		
		//find who has most cards
		for (BlackjackHand player : playerList) {
			if (player.getNumCards() > mostCards) {
				mostCards = player.getNumCards();
			}
		}
		
		//place all cards into array:String by calling each String()
		cards = new String[mostCards][numPlayers]; 
		for (int r = 0; r < mostCards; r++) {
			for (int p = 0; p < numPlayers; p++) {
				BlackjackHand player = playerList.get(p);
				//hide first if currentPlayer or dealer
				if (currentPlayer == player || currentPlayer == playerList.get(numPlayers - 1)) {
					cards[r][p] = player.printCard("hidefirst", r);
				} else {
					cards[r][p] = player.printCard("", r);
				}
			}
		}
		
		//send each row to printRow
		for (int i = 0; i < mostCards; i++) {
			printRow(cards[i]);
		}
		
		
	}
	
	//printRow receives each card String and formats for printing to console
	private void printRow(String[] cards) {
		String line = ""; //line that will actually be printed
		int numColumns = cards.length;
		
		//for each column
		for (int i = 0 ; i < numColumns; i++) {
			String card = cards[i]; //card String
			
			//create each column with centered string of card String
			String column = ""; //string for each column after centering
			String leftPadding = "";
			String rightPadding = "";
			
			int leftSpaces = (int) Math.floor( (COL_WIDTH - card.length()) / 2);
			for (int j = 0; j < leftSpaces; j++) {
				leftPadding += " ";
			}
			int rightSpaces = COL_WIDTH - leftSpaces - card.length();
			for (int j = 0; j < rightSpaces; j++) {
				rightPadding += " ";
			}
			
			column += leftPadding + card + rightPadding;
			
			//left offset of empty spaces via int LEFT_SPACER on first column only
			if (i == 0) {
				for (int j = 0; j < LEFT_SPACER; j++) {
					line += " ";
				}
			}
			
			// add centered column to line
			line += column;
			
			//add spacer if not the last column
			if (i < numColumns - 1) {
				for (int j = 0; j < COL_SPACER; j++) {
					line += " ";
				}
			}
		}
		
		System.out.println(line);
	}

}
