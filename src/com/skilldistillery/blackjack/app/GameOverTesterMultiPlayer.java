package com.skilldistillery.blackjack.app;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.blackjack.common.Card;
import com.skilldistillery.blackjack.common.Deck;

public class GameOverTesterMultiPlayer {
	
	Deck deck;
	private DisplayHandler display;
	ArrayList<BlackjackHand> playerList;
	
	// SETTINGS:
	private int firstPlayer = 0; //human player goes first
	//note: dealer is at last index
	
	{
		deck = new Deck();
		display = new DisplayHandler();
		playerList = new ArrayList<>();
	}
	
	public void setup() {
		
		//do via input later
		int numPlayers = 2;
		
		//create players into playerList
		//plus 1 for dealer, dealer is last index
		for (int i = 0; i < numPlayers + 1; i++) {
			playerList.add(new BlackjackHand());
		}
		
	}
	
	public void start() {
		
		//give initial cards to players
		int numCards = 3;
		for (int i = 0; i < numCards; i++) {
			//for each player
			for (BlackjackHand player : playerList) {
				player.addCard(deck.dealCard());
			}
		}
		
		//temporary, do test number of rounds
		int testRounds = 5;
		doRound(testRounds, playerList);
//		printPlayers();
	}
	
	private void doRound(int numRounds, ArrayList<BlackjackHand> playerList) {
		//for loop for rounds
		//TODO: make this continuous until roundOver
		for (int i = 0; i < numRounds; i++) {
			BlackjackHand currentPlayer = playerList.get(0); //Player 1 by default
			
			System.out.println("\n------ Round " + (i + 1) + ": -----");
			
			//give cards to each player
			for (BlackjackHand player : playerList) {
				player.addCard(deck.dealCard());
			}
			
			//print cards to screen
			
			//TODO: Add something for when round is over, show all cards
			// see DisplayHandler line 66
			display.printPlayerHeader(playerList, currentPlayer);
			display.printCards(playerList, currentPlayer);
//			printStatusSingleLine(playerList);
			
			//round over, players discard all cards
		}
	}
	
	//print status as columns
	private void printPlayers() {
		// settings
		// TODO: move to future Settings class?
		int colWidth = 18; //longest card toString() "Queen of Diamonds" is length 17
		
		//find who has most cards
		int mostCards = 0;
		for (BlackjackHand player : playerList) {
			if (player.getNumCards() > mostCards) {
				mostCards = player.getNumCards();
			}
		}
		
		//create arrayList of length mostCards + 2 (header and status lines)
		ArrayList<String> toPrint = new ArrayList<>(); //print later
		for (int i = 0; i < mostCards + 2; i++) {
			toPrint.add("");
		}
		
		// TODO : create array[numPlayers][mostCards]
		String[][] printArray = new String[playerList.size()][mostCards];
		
		//for each player
		for (int i = 0; i < playerList.size(); i++) {
			//for each card
			List<Card> cards = playerList.get(i).getCardsAsList();
			for (int j = 0; j < cards.size(); j++) {
				printArray[i][j] = cards.get(j).toString();
			}
		}
		
		//insert into toPrint
		// TODO: formatting
		for (int i = 0; i < mostCards; i++) {
			String line = "";
			for (int j = 0; j < playerList.size(); j++) {
				line += printArray[j][i];
			}
			//format via method call and return using similar logic to below
			//add header and status lines
			//hide first card for all but current player
			toPrint.add(line);
		}
		
		
//		for (int i = 0; i < playerList.size(); i++) {
//			//header with player names
//			String playerLabel = "|| PLAYER " + (i + 1) + " ||"; //length 14
//			//dealer override
//			if (i == playerList.size() - 1) {
//				playerLabel = "||  DEALER" + "  ||"; //length 14
//			}
//			//header left padding
//			int hLeftPadding = (int) Math.floor((colWidth - playerLabel.length()) / 2);
//			for (int j = 0; j < hLeftPadding; j++) {
//				toPrint.set(0, toPrint.get(0) + " ");
//			}
//			//header label
//			toPrint.set(0, toPrint.get(0) + playerLabel);
//			//header right padding
//			int hRightPadding = colWidth - playerLabel.length() - hLeftPadding;
//			for (int j = 0; j < hRightPadding; j++) {
//				toPrint.set(0, toPrint.get(0) + " ");
//			}
//			
//			//cards
//			List<Card> cards = playerList.get(i).getCardsAsList();
//			for (int j = 0; j < cards.size(); j++) {
//				String card = cards.get(j).toString();
//				//card padding, only applies after player 1 (index 0)
//				if (j > 0) {
//					int prevLength = toPrint.get(0).length();
//					int numSpaces = colWidth - prevLength;
//					for (int k = 0; k < numSpaces; k++) {
//						toPrint.set(j + 1, toPrint.get(j + 1) + " ");
//					}
//				}
//				toPrint.set(j + 1, toPrint.get(j + 1) + card);
//			}
//		}
		
		//print out toPrint
		for (String line : toPrint) {
			System.out.println(line);
		}
		
	}

}
