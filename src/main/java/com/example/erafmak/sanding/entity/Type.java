package com.example.erafmak.sanding.entity;

public enum Type {
	
	PAPER("Paper"),
	WIRE("Wire"),
	PLASTIC("Plastic");
	
	
    private final String displayValue;
    
    private Type(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }

}
