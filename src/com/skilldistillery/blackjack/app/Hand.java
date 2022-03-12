package com.skilldistillery.blackjack.app;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.blackjack.common.Card;

public abstract class Hand {
	protected List<Card> cards;

	public abstract int getHandValue();
	
	public Hand() {
		clear();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void clear() {
		cards = new ArrayList<>();
	}
	
	public String showCard(String playerType, int index) {
		if (index == 0 && playerType.equals("dealer")) {
			return "(First card hidden)";
		} else if (cards.size() > index) {
			return cards.get(index).toString();
		} else {
			return "";
		}
	}
	
	public void printAllCards() {
		System.out.println("Total cards: " + cards.size());
		for (Card card : cards) {
			System.out.println(card);
		}
	}


	@Override
	public String toString() {
		return "Hand.toString() not yet implemented";
	}

}
