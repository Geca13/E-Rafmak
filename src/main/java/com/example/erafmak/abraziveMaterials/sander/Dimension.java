package com.example.erafmak.abraziveMaterials.sander;

public enum Dimension {

	D125("125 mm"),
	D150("150 mm"),
	D70X198("70mm x 198 mm"),
	D70X420("70mm x 420 mm"),
	DA4("A4"),
	DA8("A8"),
	D10X115("10M x 115mm"),
	D70X400("70mm x 400mm"),
	D50X115("50M x 115mm"),
	D115X125("115mm x 125mm"),
	D77("77mm"),
	D115X230("115mm x 230mm"),
	D70X125("70mm x 125mm");
	
	
	
	
private final String displayValue;
    
    private Dimension(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
