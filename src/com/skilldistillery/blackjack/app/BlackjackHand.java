package com.skilldistillery.blackjack.app;

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
	
	public boolean isBlackjack() {
		return getHandValue() == 21;
	}
	
	public boolean isBust() {
		return getHandValue() > 21;
	}
	
	public boolean roundOver() {
		if (isBlackjack() || isBust() || isPass()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isHard() {
		//true if contains ace
		boolean isHard = false;
		for (Card card : cards) {
			if (card.getValue() == 1 || card.getValue() == 11) {
				isHard = true;
			}
		}
		return isHard;
	}
	
	public boolean isSoft() {
		//opposite of isHard()
		//TODO : this is unnecessary if calling !isHard() later
		return !isHard();
	}

	

}
