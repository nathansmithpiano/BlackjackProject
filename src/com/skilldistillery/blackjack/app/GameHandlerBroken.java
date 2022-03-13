package com.skilldistillery.blackjack.app;

import com.skilldistillery.blackjack.common.Card;
import com.skilldistillery.blackjack.common.Deck;

public class GameHandlerBroken {
	
	private	InputHandler ih;
	private BlackjackHand player1;
	private BlackjackHand dealer;
	private BlackjackHand[] humanPlayers;
	private BlackjackHand[] allPlayers; //including dealer
	
	private Deck deck;
	private int numHumanPlayers = 2; //later, dynamic
	
	{
		ih = new InputHandler();
		dealer = new BlackjackHand();
		humanPlayers = new BlackjackHand[numHumanPlayers]; //later, do dynamically based on input
		allPlayers = new BlackjackHand[numHumanPlayers + 1]; //later, do dynamically based on input
		deck = new Deck();
	}
	
	public void setup() {
		System.out.println("-- Welcome to Blackjack! --");
		
		//add all players (BlackjackHand) to array;
		for (int i = 0; i < numHumanPlayers; i++) {
			humanPlayers[i] = new BlackjackHand();
			allPlayers[i] = humanPlayers[i];
		}
		//add dealer to allPlayers
		allPlayers[numHumanPlayers] = dealer;
	}
	
	public void start() {
		
		//deal 1 card to all
		dealCardToAll();
		
		
		boolean continueRound = true;
		int roundCount = 1;
		//print info
		printCardsAll(roundCount, false);
		
		while (continueRound) {
			
			// Prompt for choices
			// Human players
			boolean didSomeoneHit = false;
			System.out.println(" - - - OPTIONS:  1. Hit  2. Stay");
			for (int i = 0; i < humanPlayers.length; i++) {
				//do not prompt if bust, blackjack, or pass
				if (!humanPlayers[i].isBust() && !humanPlayers[i].isBlackjack() && !humanPlayers[i].isPass()) {
					int choice = promptNext(i);
					//if hit, draw
					if (choice == 1) {
						dealCard(humanPlayers[i]);
						didSomeoneHit = true;
					} else {
						humanPlayers[i].setPass(true);
					}
				}
			}
			//Dealer
			dealerAction();
			
			roundCount++;
			if (checkForWinner()) {
				//last round
				printCardsAll(roundCount, true);
			} else {
				printCardsAll(roundCount, false);
			}
			continueRound = !checkForWinner();
			
		}
	}
	
	private void dealCardToAll() {
		for (BlackjackHand player : allPlayers) {
			player.addCard(deck.dealCard());
		}
	}
	
	//temporary, delete later
	private void dealCard(BlackjackHand... playerArray) {
		for (BlackjackHand player : playerArray) {
			Card card = deck.dealCard();
			player.addCard(card);
		}
	}
	
	private int promptNext(int playerNum) {
		return ih.promptRound(playerNum);
	}
	
	private void dealerAction() {
		if (dealer.getHandValue() < 17) {
			//hit
			dealCard(dealer);
		}
	}
	
	private void printCardsAll(int roundCount, boolean isLast) {
		printPlayerHeader();
		printCards(roundCount, isLast);
		printPlayerStatus();
	}
	
	private void printCards(int roundCount, boolean isLast) {
//		System.out.println("Round: " + roundCount);
		for (int i = 0; i < roundCount; i++) {
			String line = "";
			for (BlackjackHand player : humanPlayers) {
				String cardInfo = player.printCard("showfirst", i);
				line += cardInfo + "\t";
				//no card, tab fix
				if (cardInfo.length() < 1) {
					line += "\t";
				}
				//short card, tab fix
				if (cardInfo.length() < 16) {
					line += "\t";
				}
			}
			if (isLast) {
				line += dealer.printCard("showfirst", i);
			} else {
				line += dealer.printCard("hidefirst", i);
			}
			System.out.println(line);
		}
	}
	
	private void printPlayerHeader() {
		String line = "\n";
		for (int i = 0; i < humanPlayers.length; i++) { //includes dealer
			//header line
			line += "* PLAYER " + (i+1) + " *\t\t";
		}
		line += "* DEALER *";
		System.out.println(line);
	}
	
	private void printPlayerStatus() {
		String line = "";
		boolean hasStatus = false;
		for (BlackjackHand player : allPlayers) {
			if (player.isBust()) {
				hasStatus = true;
				line += " - BUST! -  \t\t";
			}
			if (player.isBlackjack()) {
				hasStatus = true;
				line += "- BLACKJACK! -\t\t";
			}
		}
		if (hasStatus) {
			System.out.println(line);
		}
		
	}
	
	private boolean checkForWinner() {
		boolean gameOver = false;
		
		//ends when all but 1 player bust
		int numBust = 0;
		//ends when all but 1 player pass
		int numPass = 0;
		
		for (BlackjackHand player : allPlayers) {
			boolean isBust = player.isBust();
			if (isBust) {
				numBust++;
			}
			boolean isPass = player.isPass();
			if (player.isPass()) {
				numPass++;
			}
		}
		
		//TODO: game does not terminate properly
		//end criteria is sloppy, redo
		//patch up tab stuff, plan for average card instead of tabs?
		
		
		gameOver = (numBust >= allPlayers.length - 1)
				|| (numPass >= allPlayers.length - 1);
		
		return gameOver;
	}
	
	
	
	

}
