package com.skilldistillery.blackjack.common;

public enum Rank {

    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), 
    SEVEN(7), EIGHT(8), NINE(9), TEN(10), 
    JACK(10), QUEEN(10), KING(10), ACE(11);
    
    private int value;

    private Rank(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
    
    @Override
    public String toString() {
//    	String output = this + "";
//    	output = output.substring(0, 1).toUpperCase() + output.substring(1, output.length() - 1).toLowerCase();
//    	return output;
    	return "Rank.toString() not yet implemented";
    }
}