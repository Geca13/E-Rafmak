package com.example.erafmak.abraziveMaterials.sander;

public enum Type {
	
	PAPER("Paper"),
	WIRE("Wire"),
	FOAM("Foam"),
	PLASTIC("Plastic");
	
	
    private final String displayValue;
    
    private Type(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }

}
