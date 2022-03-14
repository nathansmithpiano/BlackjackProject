package com.skilldistillery.blackjack.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.skilldistillery.blackjack.common.Card;
import com.skilldistillery.blackjack.common.Deck;

public class GameHandler {
	
	Settings settings;
	Deck deck;
	private DisplayHandler display;
	private InputHandler input;
	ArrayList<BlackjackHand> playerList;
	
	{
		settings = new Settings();
		deck = new Deck();
		display = new DisplayHandler();
		input = new InputHandler();
		playerList = new ArrayList<>();
	}
	
	
	public void start() {
		mainMenu();
	}
	
	private void mainMenu() {
		display.printWelcome();
		int numPlayers = input.promptPlayers();
		setup(numPlayers);
		playGame();
	}
	
	private void setup(int numPlayers) {
		
		//create players into playerList
		//plus 1 for dealer, dealer is last index
		for (int i = 0; i < numPlayers + 1; i++) {
			playerList.add(new BlackjackHand());
		}
		
		//setup Settings
		settings.setNumPlayers(playerList.size());
		
		//setup inputHandler
		int[] inputIndices = display.getInputIndices(playerList);
		input.setInputIndices(inputIndices);
		
	}
	
	private void playGame() {
		//continue until only 1 player left
		
		BlackjackHand currentPlayer = playerList.get(settings.getFirstPlayerIndex());
		int numPlayers = playerList.size();
		
		boolean userExit = false; //for skipping display
		boolean roundOver = false; //for display
		
		//deal 1 card to each player
		for (BlackjackHand player : playerList) {
			player.addCard(deck.dealCard());
		}
		
		while (!isRoundOver() && !userExit) {
			
			//print for human moves
			if (currentPlayer != playerList.get(numPlayers - 1)) {
				display.printPlayerHeader(playerList, currentPlayer, roundOver);
				display.printCards(playerList, currentPlayer, roundOver); 
				display.printPlayerStatus(playerList, currentPlayer, roundOver);
			}
			
			
			//prompt human players
				//if !currentPlayer.roundOver();
				//change which player is current for display and input
			//if player is still playing
			if (!currentPlayer.roundOver()) {
				//if dealer
				if (currentPlayer == playerList.get(numPlayers - 1)) {
					dealerChoice();
				} else {
					//prompt human players for choice
					int choice = input.promptRoundChoice(playerList, currentPlayer);
					
					switch (choice) {
					case 1:
						//hit
						currentPlayer.addCard(deck.dealCard());
						break;
					case 2:
						//stay
						currentPlayer.setPass(true);
						break;
					case 3:
						//exit
						roundOverReset();
						userExit = true;
						mainMenu();
						break;
					}
				}
			}
			
			
			//line break between human rounds
			if (currentPlayer != playerList.get(numPlayers - 1)) {
				display.printLineBreak();
			}
			
			//move onto next player
			currentPlayer = getNextPlayer(currentPlayer);
		}
		
		//round end
		roundOver = true;
		
		//if user did not exit to main menu (this may be unnecessary)
		if (!userExit) {
			
			//print results
			display.printPlayerHeader(playerList, currentPlayer, roundOver);
			display.printCards(playerList, currentPlayer, roundOver); 
			display.printPlayerStatus(playerList, currentPlayer, roundOver);
			display.printLineBreak();
			display.printWinners(getWinners());
			display.printLineBreak();
			
			roundOverReset();
			String leftSpacer = display.getLeftSpacer();
			display.printRoundOverOptions();
			int choice = input.promptRoundOverChoice(leftSpacer);
			
			switch (choice) {
			case 1:
				//Play again
				System.out.println(); //
				playGame();
				break;
			case 2:
				//Main menu
				mainMenu();
				break;
				
			case 3:
				//Exit game
				exitGame();
			}
			
		}
	}
	
	private String getWinners() {
		int numPlayers = playerList.size();
		String output = "";
		ArrayList<BlackjackHand> winners = new ArrayList<>();
		//winning conditions
		ArrayList<BlackjackHand> nonBust = new ArrayList<>();
		ArrayList<BlackjackHand> twoCardBJ = new ArrayList<>();
		ArrayList<BlackjackHand> multiCardBJ = new ArrayList<>();
		ArrayList<BlackjackHand> highest = new ArrayList<>();
		
		//find highest value of non-bust players
		int highestValue = 0;
		for (BlackjackHand player : playerList) {
			if (player.getHandValue() > highestValue && !player.isBust()) {
				highestValue = player.getHandValue();
			}
		}
		
		//populate lists
		for (int i = 0; i < numPlayers; i++) {
			BlackjackHand player = playerList.get(i);
			
			if (!player.isBust()) {
				nonBust.add(player);
			}
			
			if (player.isBlackjack()) {
				if (player.getNumCards() > 2) {
					multiCardBJ.add(player);
				} else {
					twoCardBJ.add(player);
				}
			}
			
			if (player.getHandValue() == highestValue && !player.isBust()) {
				highest.add(player);
			}
		}
		
		//prioritize
		//only one non-bust
		if (nonBust.size() == 1) {
			winners.addAll(nonBust);
		} else if (twoCardBJ.size() > 0) {
			//someone has two-card blackjack
			winners.addAll(twoCardBJ);
		} else if (multiCardBJ.size() > 0) {
			//someone has multi-card blackjack
			winners.addAll(multiCardBJ);
		} else {
			//highest values
			winners.addAll(highest);
		}
		
		int numWinners = winners.size();
		
		//plurality before adding names
		if (numWinners > 1) {
			output += "S: ";
		} else {
			output += ": ";
		}
		
		//create String
		for (int i = 0; i < numWinners; i++) {
			BlackjackHand winner = winners.get(i);
			//if dealer
			if (winner == playerList.get(numPlayers - 1)) {
				output += "DEALER";
			} else {
				//get index
				for (int j = 0; j < numPlayers; j++) {
					BlackjackHand player = playerList.get(j);
					
					if (player == winner) {
						output += "PLAYER " + (j + 1);
					}
				}
			}
			//comma if more than one and not last
			if ( (numWinners > 1) && (i != numWinners - 1) ){
				output += ", ";
			}
		}
		
		return output;
	}
	
	private BlackjackHand getNextPlayer(BlackjackHand currentPlayer) {
		int numPlayers = playerList.size();
		int currentIndex = getCurrentIndex(currentPlayer);
		
		//if last player (dealer)
		if (currentIndex == numPlayers - 1) {
			return playerList.get(0);
		} else {
			return playerList.get(currentIndex + 1);
		}
		
	}
	
	private int getCurrentIndex(BlackjackHand currentPlayer) {
		int numPlayers = playerList.size();
		int currentIndex = -1;
		for (int i = 0; i < numPlayers; i++) {
			if (playerList.get(i) == currentPlayer) {
				currentIndex = i;
			}
		}
		return currentIndex;
	}
	
	private void dealerChoice() {
		int numPlayers = playerList.size();
		BlackjackHand dealer = playerList.get(numPlayers - 1);
		
		if (dealer.getHandValue() < 17) {
			//hit
			dealer.addCard(deck.dealCard());
		} else {
			//stay
			dealer.setPass(true);
		}
	}
	
	private void roundOverReset() {
		//for all players, including dealer
		for (BlackjackHand player : playerList) {
			//discard all cards
			for (Card card : player.getCardsAsList()) {
				deck.discardCard(card);
			}
			//reset all hands
			player.clear();
		}
	}
	
	private void exitGame() {
		display.printGoodbye();
		input.closeScanner();
		System.exit(1);
	}
	
	private void doRound(int numRounds, ArrayList<BlackjackHand> playerList) {
		//for loop for rounds
		boolean roundOver = false;
		
		//move currentPlayer each round
		int currentPlayerIndex = 0; //start with Player 1
		for (int i = 0; i < numRounds; i++) {
			
			//TESTING: move currentPlayer each round
			if (currentPlayerIndex == playerList.size()) {
				currentPlayerIndex = 0; 
			}
			BlackjackHand currentPlayer = playerList.get(currentPlayerIndex); //choose
			currentPlayerIndex++;
			
			
			System.out.println("\n------ Round " + (i + 1) + ": -----");
			
			//give cards to each player
			for (BlackjackHand player : playerList) {
				player.addCard(deck.dealCard());
			}
			
			//print cards to screen
			display.printPlayerHeader(playerList, currentPlayer, roundOver);
			display.printCards(playerList, currentPlayer, roundOver); 
			display.printPlayerStatus(playerList, currentPlayer, roundOver);
			input.promptRoundChoice(playerList, currentPlayer);
			
		}
		//round over, players discard all cards
		roundOver = true;
		
		//TESTING: show final status
		System.out.println("\n------ ROUND OVER ------");
		display.printPlayerHeader(playerList, null, roundOver); //boolean roundOver
		display.printCards(playerList, null, roundOver); 
		display.printPlayerStatus(playerList, null, roundOver);
	}
	
	private boolean isRoundOver() {
		int roundOverCount = 0;
		int numPlayers = playerList.size();
		
		for (BlackjackHand player : playerList) {
			//count how many players are done
			if (player.roundOver()) {
				roundOverCount++;
			}
		}
		
		//return true if only 1 player left
		if (roundOverCount >= numPlayers - 1) {
			return true;
		} else {
			return false;
		}
	}
	
	private String getRoundOverString() {
		String line = "";
		return line;
	}
	
}
