package com.skilldistillery.blackjack.app;

import java.util.ArrayList;

import com.skilldistillery.blackjack.common.Card;

public class DisplayHandler {
	
	//note: recommended minimum of 22 for COL_WIDTH, largest is ROUND_OPTIONS
	private final int COL_WIDTH = 22; //width of each player column
	private final int COL_SPACER = 4; //empty space between columns
	private final int LEFT_SPACER = 4; //empty space before first column 
	private final String ROUND_OPTIONS = "1. Hit 2. Stay 3. Exit";
	private int[] inputIndices; //starting index for inputs
	private int numPlayers; //not used?
	
	public void setNumPlayers(int num) {
		inputIndices = new int[numPlayers];
	}
	
	//print player header by sending String[] of names to printRow() for formatting and printing
	public void printPlayerHeader(ArrayList<BlackjackHand> playerList, BlackjackHand currentPlayer, boolean roundOver) {
		int numPlayers = playerList.size();
		String[] playerNames = new String[numPlayers];
		
		//populate playerNames with String
		for (int i = 0; i < numPlayers; i++) {
			String header = "";
			// unique formatting for dealer
			if (i == numPlayers - 1) {
				//if dealer is current player and round is not over
				if ((playerList.get(playerList.size() - 1) == currentPlayer) && (!roundOver) ) {
					header = "-> DEALER <-";
				} else {
					//not current player or round is over
					header = "DEALER";
				}
			} else {
				//if player is current player and round is not over
				if ( (playerList.get(i) == currentPlayer) && (!roundOver) ){
					header = "-> PLAYER " + (i + 1) + " <-";
				} else {
					//not current player or round is over
					header = "PLAYER " + (i + 1);
				}
			}
			playerNames[i] = header;
		}
		
		//send to printRow for printing
		printRow(playerNames);
	}
	
	//print all cards by sending String[][] of card names to printRow() for formatting and printing
	public void printCards(ArrayList<BlackjackHand> playerList, BlackjackHand currentPlayer, boolean roundOver) {
		
		String[][] cards; //[row][player] of width players and of height most cards
		
		int mostCards = 0;
		int numPlayers = playerList.size();
		
		//find who has most cards
		for (BlackjackHand player : playerList) {
			if (player.getNumCards() > mostCards) {
				mostCards = player.getNumCards();
			}
		}
		
		//place all cards into array:String by calling each String()
		cards = new String[mostCards][numPlayers]; 
		for (int r = 0; r < mostCards; r++) {
			for (int p = 0; p < numPlayers; p++) {
				BlackjackHand player = playerList.get(p);
				String currentCard = "";
				//hide first if currentPlayer
				
				//show all cards if (current player and not dealer) OR roundOver
				if ( (currentPlayer == player && !(player == playerList.get(numPlayers - 1))) || roundOver) {
					currentCard = player.printCard("", r);
				} else {
					currentCard = player.printCard("hidefirst", r);
				}
				cards[r][p] = currentCard;
			}
		}
		
		//send each row to printRow
		for (int i = 0; i < mostCards; i++) {
			printRow(cards[i]);
		}
	}
	
	//print statuses by sending String[] status to printRow() for formatting and printing
	public void printPlayerStatus(ArrayList<BlackjackHand> playerList, BlackjackHand currentPlayer, boolean roundOver) {
		
		int numPlayers = playerList.size();
		String[] statusArray = new String[numPlayers];
		
		//for each player
		for (int i = 0; i < numPlayers; i++) {
			BlackjackHand player = playerList.get(i);
			String status = ""; //String for player
			
			//if round is not over and currentPlayer and not dealer
			if (!roundOver && currentPlayer == player && !(currentPlayer == playerList.get(numPlayers - 1))) {
				//show options
				status = ROUND_OPTIONS;
			} 
			
			//show all statuses when round is over, otherwise nothing
			if (roundOver) {
				if (player.isBust()) {
					status = "BUST! ";
				} else if (player.isBlackjack()) {
					status = "BLACKJACK! ";
				}
			}
			
			//insert into Array
			statusArray[i] = status;
		}
		
		printRow(statusArray);
	}
	
	//generic method that will print a formatted row, could also change to return String instead
	//printRow receives each card String and formats for printing to console
	private void printRow(String[] columns) {
		String line = ""; //line that will actually be printed
		int numColumns = columns.length;
		
		//for each column
		for (int i = 0 ; i < numColumns; i++) {
			String card = columns[i]; //card String
			
			//create each column with centered string of card String
			String column = ""; //string for each column after centering
			String leftPadding = "";
			String rightPadding = "";
			
			int leftSpaces = (int) Math.floor( (COL_WIDTH - card.length()) / 2);
			for (int j = 0; j < leftSpaces; j++) {
				leftPadding += " ";
			}
			int rightSpaces = COL_WIDTH - leftSpaces - card.length();
			for (int j = 0; j < rightSpaces; j++) {
				rightPadding += " ";
			}
			
			//create centered column String
			column += leftPadding + card + rightPadding;
			
			//create line
			//add left offset of empty spaces via int LEFT_SPACER on first column only
			if (i == 0) {
				for (int j = 0; j < LEFT_SPACER; j++) {
					line += " ";
				}
			}
			
			// add centered column
			line += column;
			
			//add spacer if not the last column
			if (i < numColumns - 1) {
				for (int j = 0; j < COL_SPACER; j++) {
					line += " ";
				}
			}
		}
		
		//display line
		//could output as formatted String for future implementation
		System.out.println(line);
	}
	
	private int[] getInputIndices(String inputPrompt) {
		return inputIndices;
	}
	

}
