package com.skilldistillery.blackjack.app;

import com.skilldistillery.blackjack.common.Card;
import com.skilldistillery.blackjack.common.Deck;

public class GameHandler {
	
	private	InputHandler ih;
	private BlackjackHand player1;
	private BlackjackHand dealer;
	private BlackjackHand[] players;
	private BlackjackHand[] allPlayers; //including dealer
	
	private Deck deck;
	private int numHumanPlayers = 2; //later, dynamic
	
	{
		ih = new InputHandler();
		dealer = new BlackjackHand();
		players = new BlackjackHand[numHumanPlayers]; //later, do dynamically based on input
		allPlayers = new BlackjackHand[numHumanPlayers + 1]; //later, do dynamically based on input
		deck = new Deck();
	}
	
	public void setup() {
		System.out.println("-- Welcome to Blackjack! --");
		
		//add all players (BlackjackHand) to array;
		for (int i = 0; i < numHumanPlayers; i++) {
			players[i] = new BlackjackHand();
			allPlayers[i] = players[i];
		}
		//add dealer to allPlayers
		allPlayers[numHumanPlayers] = dealer;
	}
	
	public void start() {
		
		//1 card to all
		dealCardToAll();
		
		
		boolean continueRound = true;
		
		int roundCount = 1;
		while (continueRound) {
			//print info
			printPlayerHeader();
			printCards(roundCount);
			printPlayerStatus();
			
			//deal 1 card to all players
			// TODO: update this via input later
			dealCard(players); //can be single, multiple, or array of BlackjackHand objects
			dealerAction();
			
			
			continueRound = !checkForWinner();
			
			roundCount++;
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
	
	private void dealerAction() {
		if (dealer.getHandValue() < 17) {
			dealCard(dealer);
		}
	}
	
	private void printCards(int roundCount) {
		for (int i = 0; i < roundCount; i++) {
			String line = "";
			for (BlackjackHand player : players) {
				String cardInfo = player.showCard("human", i);
				line += cardInfo + "\t";
				//tab fix
				if (cardInfo.length() < 16) {
					line += "\t";
				}
			}
			line += dealer.showCard("dealer", i);
			System.out.println(line);
		}
	}
	
	private void printPlayerHeader() {
		String line = "";
		for (int i = 0; i < players.length; i++) { //includes dealer
			//header line
			line += "* PLAYER " + (i+1) + " *\t\t";
		}
		line += "* DEALER *";
		System.out.println(line);
	}
	
	private void printPlayerStatus() {
		String line = "";
		for (BlackjackHand player : allPlayers) {
			if (player.isBust()) {
				line += " - BUST! -  \t\t";
			}
			if (player.isBlackjack()) {
				line += "- BLACKJACK! -\t\t";
			}
		}
		System.out.println(line);
		
	}
	
	private boolean checkForWinner() {
		boolean gameOver = false;
		
		//ends when all but 1 player bust
		int numBust = 0;
		
		for (BlackjackHand player : allPlayers) {
			boolean isBust = player.isBust();
			if (isBust) {
				numBust++;
			}
		}
		
		gameOver = (numBust >= allPlayers.length - 1);
		
		return gameOver;
	}
	
	
	
	

}
