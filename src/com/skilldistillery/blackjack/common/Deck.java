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
	}
	
	// TODO: Should this be empty?
	public Deck() {
		shuffle(cards);
	}
	
	// TODO: Will this be necessary?
	public int cardsLeftInDeck() {
		return size();
	}
	
	public Card dealCard() {
		//if card remains in deck
		if (size() == 0) {
			// shuffle discard, set as deck, clear discard
			System.out.println("Deck is empty");
			shuffle(discard);
			cards = discard;
			discard.clear();
		}
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
	
	// TODO: Will this be necessary?
	// If we are just passing size() to cardsLeftInDeck(), why public?
	public int size() {
		return cards.size();
	}
	
	public void printDeck() {
		System.out.println("Deck has " + size() + " cards:");
		for (Card card : cards) {
			System.out.println(card);
		}
	}

}
