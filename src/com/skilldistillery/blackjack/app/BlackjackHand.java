package com.skilldistillery.blackjack.app;

import java.util.ArrayList;

import com.skilldistillery.blackjack.common.Card;

public class BlackjackHand extends Hand {
	
	@Override
	public int getHandValue() {
		int totalValue = 0;
		for (Card card : cards) {
			totalValue += card.getValue();
		}
		return totalValue;
	}
	
	private int getHandValueWithoutAces() {
		int totalValue = 0;
		for (Card card : cards) {
			if (card.getValue() != 11) {
				totalValue += card.getValue();
			}
		}
		return totalValue;
	}
	
	public boolean isBlackjack() {
		int total = getHandValue();
		int numAces = getNumAces();
		boolean blackjack = false;
		if (numAces == 0) {
			if (total == 21) {
				blackjack = true;
			}
		} else {
			ArrayList<Integer> possibleTotals = getAcePossibleTotals();
			
			boolean somethingBlackjack = false;
			for (int num : possibleTotals) {
				if (num == 21) {
					somethingBlackjack = true;
				}
			}
			
			blackjack = somethingBlackjack;
		}
		
		return blackjack;
	}
	
	public boolean isBust() {
		int numAces = getNumAces();
		boolean bust = false;
		
		//various options for aces
		int withoutAces = getHandValueWithoutAces();
		ArrayList<Integer> possibleTotals = getAcePossibleTotals();
			
		
		if (numAces == 0) {
			//bust if over 21
			if (withoutAces > 21) {
				bust = true;
			}
		} else {
			//see if any possibilities are <= 21
			boolean somethingNoBust = false;
			for (int num : possibleTotals) {
				if (num <= 21) {
					somethingNoBust = true;
				}
			}
			
			bust = !somethingNoBust;
		}
		
		return bust;
	}
	
	//Max of 5 aces supported
	//could redo with formula for any possibilities
	private ArrayList<Integer> getAcePossibleTotals() {
		int numAces = getNumAces();
		int withoutAces = getHandValueWithoutAces();
		ArrayList<Integer> possibleTotals = new ArrayList<>();
		
		if (numAces > 0) {
			if (numAces == 1) {
				possibleTotals.add(withoutAces + 1);
				possibleTotals.add(withoutAces + 9);
			} else if (numAces == 2) {
				possibleTotals.add(withoutAces + 2);
				possibleTotals.add(withoutAces + 10);
				possibleTotals.add(withoutAces + 18);
			} else if (numAces == 3) {
				possibleTotals.add(withoutAces + 3);
				possibleTotals.add(withoutAces + 11);
				possibleTotals.add(withoutAces + 19);
				possibleTotals.add(withoutAces + 27);
			} else if (numAces == 4) {
				possibleTotals.add(withoutAces + 4);
				possibleTotals.add(withoutAces + 12);
				possibleTotals.add(withoutAces + 20);
				possibleTotals.add(withoutAces + 28);
				possibleTotals.add(withoutAces + 36);
			} else {
				possibleTotals.add(withoutAces + 5);
				possibleTotals.add(withoutAces + 13);
				possibleTotals.add(withoutAces + 21);
				possibleTotals.add(withoutAces + 29);
				possibleTotals.add(withoutAces + 37);
				possibleTotals.add(withoutAces + 45);
			}
		}
		
		return possibleTotals;
	}
	
	public boolean roundOver() {
		if (isBlackjack() || isBust() || isPass()) {
			return true;
		} else {
			return false;
		}
	}
	
	private int getNumAces() {
		int num = 0;
		for (Card card : cards) {
			if (card.getValue() == 11) {
				num++;
			}
		}
		return num;
	}
	
}
