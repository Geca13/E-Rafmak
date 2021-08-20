package com.example.erafmak.coatsAndPrimers.entity;

public enum Weigth {
	
	ONE("1 lit."),
	TWO("2.5 lit."),
	THREE("3 lit."),
	FOUR("3.5 lit."),
	FIVE("5 lit.");
	
private final String displayValue;
    
    private Weigth(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }

}
