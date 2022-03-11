package com.skilldistillery.blackjack.app;

import java.util.List;

import com.skilldistillery.blackjack.common.Card;

public abstract class Hand {
	protected List<Card> cards;

	public Hand() {

	}

	public void addCard(Card card) {

	}

	public void clear() {

	}

	public abstract int getHandValue();

	@Override
	public String toString() {
		return "Hand.toString() not yet implemented";
	}

}
