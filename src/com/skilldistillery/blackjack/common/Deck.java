package com.skilldistillery.blackjack.common;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	
	private List<Card> cards;
	
	public Deck() {
		cards = new ArrayList<>();
	}
	
	public int cardsLeftInDeck() {
		
		return -1;
	}
	
	public Card dealCard() {
		return cards.get(0);
	}
	
	public void shuffle() {
		
	}
	
	public int size() {
		return -1;
	}

}
