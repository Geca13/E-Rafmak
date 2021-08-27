package com.example.erafmak.abraziveMaterials.sander;

public enum Granulation {

	P40("40"),
	P60("60"),
	P80("80"),
	P100("100"),
	P120("120"),
	P150("150"),
	P180("180"),
	P220("220"),
	P240("240"),
	P280("280"),
	P320("320"),
	P360("360"),
	P400("400"),
	P500("500"),
	P600("600"),
	P800("800"),
	P1000("1000"),
	P1200("1200"),
	P1500("1500"),
	P2000("2000"),
	P2500("2500"),
	P3000("3000"),
	P4000("4000");
	
	
	
private final String displayValue;
    
    private Granulation(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
	
}
