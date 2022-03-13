package com.skilldistillery.blackjack.app;

import com.skilldistillery.blackjack.common.Deck;
import java.util.Scanner;
import java.io.*;

public class GameOverTesterSinglePlayer {
	
	private Deck deck;
	private BlackjackHand player;
	private Scanner kb;
	
	{ 
		deck = new Deck();
		player = new BlackjackHand();
		kb = new Scanner(System.in);
	}
	
	public void setup() {}
	
	public void start() {
		
		doRound();
		
	}
	
	private void doRound() {
		
		//initial card
		dealCard();
		printStatus();
		
		while (!player.roundOver()) {
			roundOption();
			printStatus();
		}
		
	}
	
	private void dealCard() {
		player.addCard(deck.dealCard());
	}
	
	private void printStatus() {
		System.out.println(player.getStringCardsWithValueSingleLine());
		System.out.println("Total: " + player.getHandValue());
	}
	
	private void roundOption() {
		System.out.print("1. Hit\t2. Stay\tYour Choice: ");
		int choice = kb.nextInt();
		
		switch (choice) {
		case 1:
			dealCard();
			break;
		case 2:
			player.setPass(true);
			break;
		default:
			System.out.println("INVALID CHOICE");
				
		}
	}

}
