package com.example.erafmak.sanding.entity;

public enum Condition {

	DRY("Dry"),
	WET("Wet");
	
	
    private final String displayValue;
    
    private Condition(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }

}
