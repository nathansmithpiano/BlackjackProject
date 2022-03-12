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
    	//format as proper noun
    	String firstLetter = this.name().substring(0,1);
    	String remainder = this.name().substring(1,this.name().length()).toLowerCase();
    	return firstLetter + remainder;
    }
}