package com.skilldistillery.blackjack.app;

import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.blackjack.common.Card;

public abstract class Hand {
	
	protected List<Card> cards;
	private boolean pass;
	private boolean isTurn;

	public abstract int getHandValue();
	public abstract boolean roundOver();
	
	public Hand() {
		clear();
	}

	public void addCard(Card card) {
		cards.add(card);
	}
	
	public List<Card> getCardsAsList() {
		return cards;
	}

	public void clear() {
		cards = new ArrayList<>();
		setPass(false);
	}
	
	//UNUSED
//	public Card getHighestCard() {
//		Card highestCard = cards.get(0);
//		for (Card card : cards) {
//			if (card.getValue() > highestCard.getValue()) {
//				highestCard = card;
//			}
//		}
//		return highestCard;
//	}
	
	public void setPass(boolean pass) {
		this.pass = pass;
	}
	
	public boolean isPass() {
		return pass;
	}
	
	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}
	
	public boolean isTurn() {
		return isTurn;
	}
	
	public String printCard(String hidefirst, int index) {
		if (index == 0 && hidefirst.equals("hidefirst")) {
			return "(First card hidden)";
		} else if (cards.size() > index) {
			return cards.get(index).toString();
		} else {
			//returns empty string if nothing at that index
			return "";
		}
	}
	
	public void printAllCards() {
		for (Card card : cards) {
			System.out.println(card);
		}
	}
	
	public String getStringCardsWithValueSingleLine() {
		String line = "";
		for (Card card : cards) {
			line += card + " (" + card.getValue() + ")\t";
		}
		return line;
	}
	
	public int getNumCards() {
		return cards.size();
	}


	@Override
	public String toString() {
		return "Hand.toString() not yet implemented";
	}

}
