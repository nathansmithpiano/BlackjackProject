package com.skilldistillery.blackjack.common;

public class Card {
	
	private Suit suit;
	private Rank rank;
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public int getValue() {
		return -1;
	}
	
	@Override
	public String toString() {
		return "Card.toString() not yet implemented";
	}

}
