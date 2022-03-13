package com.skilldistillery.blackjack.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	private List<Card> cards;
	private List<Card> discard;
	
	//initialize, populate, and shuffle deck
	{
		cards = new ArrayList<>(56);
		discard = new ArrayList<>(54);
		
		for (Rank rank : Rank.values()) {
			for (Suit suit : Suit.values()) {
				cards.add(new Card(suit, rank));
			}
		}
		
		shuffle(cards);
	}
	
	public int cardsInDeck() {
		return cards.size();
	}
	
	public int cardsInDiscard() {
		return discard.size();
	}
	
	public Card dealCard() {
		//if deck is out of cards
		if (cardsInDeck() == 0) {
			// put discard into deck and shuffle
//			// TODO : Ask Rob!
//			cards = discard; // this does not work as intended
			cards.clear();
			cards.addAll(discard); //but this does
			discard.clear();
			shuffle(cards);
		}
		//deal card and remove from deck
		Card card = cards.get(0);
		cards.remove(card);
		return card;
	}
	
	public void discardCard(Card card) {
		discard.add(card);
	}
	
	public void shuffle(List<Card> deck) {
		Collections.shuffle(deck);
	}
	
	public void printDeck() {
		System.out.println("Deck has " + cardsInDeck() + " cards:");
		for (Card card : cards) {
			System.out.println(card);
		}
	}

}
