package com.skilldistillery.blackjack.app;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.blackjack.common.Card;
import com.skilldistillery.blackjack.common.Deck;

public class DeckTester {
	
	private Deck deck;
	private DisplayHandler display;
	private ArrayList<BlackjackHand> humanPlayers;
	private ArrayList<BlackjackHand> allPlayers;
	
//	SETTINGS:
	private int firstPlayer = 0; //which player goes first
	
	public DeckTester() {
		deck = new Deck();
		display = new DisplayHandler();
		humanPlayers = new ArrayList<>();
		allPlayers = new ArrayList<>();
		
		//create players in ArrayList humanPlayers
		// TODO: do this via user prompt later
		int numHumanPlayers = 4;
		for (int i = 0; i < numHumanPlayers; i++) {
			humanPlayers.add(new BlackjackHand());
		}
		
		//copy to allPlayers and add dealer
		allPlayers.add(new BlackjackHand()); //dealer is at index 0
		allPlayers.addAll(humanPlayers);
		
		
	}
	
	public void setup() {}
	
	public void start() {
		
		doRound(10, humanPlayers, 3); //TODO: change this later
		
		
	}
	
	private void testDeal(BlackjackHand player, int numCards) {
		for (int i = 0; i < numCards; i++) {
			player.addCard(deck.dealCard());
		}
	}
	
	private void doRound(int numRounds, ArrayList<BlackjackHand> playerList, int numCards) {
		//for each round
		BlackjackHand currentPlayer = playerList.get(0); //Player 1 by default
		
		for (int i = 0; i < numRounds; i++) {
			System.out.println("\nRound " + (i + 1) + ":");
			//give cards to each player
			for (BlackjackHand player : playerList) {
				testDeal(player, numCards);
				display.printCards(playerList, currentPlayer, false);
			}
//			printStatusSingleLine(playerList);
			discardAll(playerList);
		}
	}
	
	private void discardAll(ArrayList<BlackjackHand> playerList) {
		//for each player
		for (BlackjackHand player : playerList) {
			//for each card in hand
			List<Card> cardList = player.getCardsAsList();
			for (Card card : cardList) {
				//put in discard
				deck.discardCard(card);
			}
			
			//clear hand
			player.clear();
		}
	}
	
	//show status on single line for each player
	private void printStatusSingleLine(ArrayList<BlackjackHand> playerList) {
		int numPlayers = playerList.size();
		for (int i = 0; i < numPlayers; i++) {
			String line = "Player " + (i + 1);
			line += "\tCards: " + playerList.get(i).getNumCards();
			line += "\tTotal: " + playerList.get(i).getHandValue();
			line += "\tDeck: " + deck.cardsInDeck();
			//tab fix
			if (deck.cardsInDeck() < 10) {
				line += "\t";
			}
			line += "\tDiscard: " + deck.cardsInDiscard();
			line += "\t" + playerList.get(i).getStringCardsWithValueSingleLine();
			System.out.println(line);
		}
	}

}
