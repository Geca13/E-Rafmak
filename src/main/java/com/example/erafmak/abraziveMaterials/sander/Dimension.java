package com.example.erafmak.abraziveMaterials.sander;

public enum Dimension {

	PRVA("125 mm"),
	VTORA("150 mm"),
	TRETA("70mm x 198 mm"),
	CHETVRTA("70mm x 420 mm"),
	PETTA("A4"),
	SESTA("A8"),
	SEDMA("10M * 115mm"),
	OSMA("70mm x 400mm"),
	DEVETA("50M * 115mm"),
	DESETA("115mm * 125mm"),
	EDINAESETA("77mm"),
	DVANAESETA("115mm x 230mm"),
	TRINAESETA("70mm x 125mm");
	
	
	
	
private final String displayValue;
    
    private Dimension(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
