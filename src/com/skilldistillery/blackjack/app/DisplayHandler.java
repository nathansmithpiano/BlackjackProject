package com.skilldistillery.blackjack.app;

import java.util.ArrayList;

import com.skilldistillery.blackjack.common.Card;

public class DisplayHandler {
	
	Settings settings;
	
	{
		settings = new Settings();
	}
	
	public void printWelcome() {
		printArt();
		System.out.println("------------ OPTIONS ------------");
	}
	
	private void printArt() {
		System.out.println(" _     _            _    _            _    ");
		System.out.println("| |   | |          | |  (_)          | |   ");
		System.out.println("| |__ | | __ _  ___| | ___  __ _  ___| | __");
		System.out.println("| '_ \\| |/ _` |/ __| |/ / |/ _` |/ __| |/ /");
		System.out.println("| |_) | | (_| | (__|   <| | (_| | (__|   < ");
		System.out.println("|_.__/|_|\\__,_|\\___|_|\\_\\ |\\__,_|\\___|_|\\_\\");
		System.out.println("                       _/ |                ");
		System.out.println("                      |__/  art from https://ascii.co.uk");
	}
	
	//print player header by sending String[] of names to printRow() for formatting, print return
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
		
		//send to printRow, print result
		System.out.println(getFormattedRow(playerNames));
	}
	
	//print all cards by sending String[][] of card names to printRow() for formatting, print return
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
		
		//send each row to printRow, print result
		for (int i = 0; i < mostCards; i++) {
			System.out.println(getFormattedRow(cards[i]));
		}
	}
	
	//print statuses by sending String[] status to printRow() for formatting, print return
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
				status = settings.getRoundOptions();
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
		
		//send to printRow, print result
		System.out.println(getFormattedRow(statusArray));
	}
	
	public void printWinners(String winnerString) {
		String line = "";
		line += getLeftSpacer();
		line += settings.getRoundOverString();
		line += winnerString;
		System.out.println(line);
	}
	
	public void printRoundOverOptions() {
		String line = "";
		line += getLeftSpacer();
		line += settings.getRoundOverOptions();
		System.out.println(line);
	}
	
	public void printGoodbye() {
		String line = "";
		line += getLeftSpacer();
		line += settings.getGoodbyeMessage();
		System.out.println(line);
	}
	
	//generic method that will print a formatted row, could also change to return String instead
	//printRow receives each card String and formats for printing to console
	private String getFormattedRow(String[] columns) {
		String line = ""; //line that will actually be printed
		int numColumns = columns.length;
		
		//for each column
		for (int i = 0 ; i < numColumns; i++) {
			String card = columns[i]; //card String
			
			//create each column with centered string of card String
			String column = ""; //string for each column after centering
			String leftPadding = "";
			String rightPadding = "";
			
			int leftSpaces = (int) Math.floor( (settings.getColWidth() - card.length()) / 2);
			for (int j = 0; j < leftSpaces; j++) {
				leftPadding += " ";
			}
			int rightSpaces = settings.getColWidth() - leftSpaces - card.length();
			for (int j = 0; j < rightSpaces; j++) {
				rightPadding += " ";
			}
			
			//create centered column String
			column += leftPadding + card + rightPadding;
			
			//create line
			//add left offset of empty spaces on first column only
			if (i == 0) {
				line += getLeftSpacer();
			}
			
			// add centered column
			line += column;
			
			//add spacer if not the last column
			if (i < numColumns - 1) {
				for (int j = 0; j < settings.getColSpacer(); j++) {
					line += " ";
				}
			}
		}
		
		//output as formatted String for printing or further manipulation
		return line;
	}
	
	public String getLeftSpacer() {
		String output = "";
		
		int leftSpacerCount = settings.getLeftSpacer();
		
		//left offset
		for (int i = 0; i < leftSpacerCount; i++) {
			output += " ";
		}
		
		return output;
	}
	
	public void printLineBreak() {
		String line = "";
		
		//left offset
		line += getLeftSpacer();
		
		//filler for rest of line
		int numFiller = settings.getDisplayWidth();
		for (int i = 0; i < numFiller; i++) {
			line += settings.getlineBreakChar();
		}
		
		System.out.println(line);
	}
	
	public int[] getInputIndices(ArrayList<BlackjackHand> playerList) {
		int numPlayers = playerList.size();
		String choicePrompt = settings.getChoicePrompt();
		String[] promptArray = new String[numPlayers]; //this will be sent to printRow
		int[] inputIndices = new int[numPlayers]; //array of starting indices for future input
		char promptFirstLetter = choicePrompt.charAt(0);
		
		for (int i = 0; i < numPlayers; i++) {
			promptArray[i] = choicePrompt + "X";
		}
		
		String line = getFormattedRow(promptArray);
		int lineLength = line.length();
		int foundCount = 0;
		//parse through line and if matches promptFirstLetter, insert that index to inputIndices
		for (int i = 0 ; i < lineLength; i++) {
			if (line.charAt(i) == promptFirstLetter) {
				inputIndices[foundCount] = i;
				foundCount++;
			}
		}
		
		return inputIndices;
	}
	

}
