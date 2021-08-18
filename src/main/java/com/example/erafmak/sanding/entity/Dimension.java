package com.example.erafmak.sanding.entity;

public enum Dimension {

	PRVA("125 mm"),
	VTORA("150 mm"),
	TRETA("70 x 198 mm"),
	CHETVRTA("70 x 420 mm"),
	PETTA("A4"),
	SESTA("A8"),
	SEDMA("10m * 115mm"),
	OSMA("50M * 115MM");
	
	
	
private final String displayValue;
    
    private Dimension(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
