package com.example.erafmak.abraziveMaterials.sander;

public enum Granulation {

	PRVA("40"),
	VTORA("60"),
	TRETA("80"),
	CHETVRTA("100"),
	PETTA("120"),
	SHESTA("150"),
	SEDMA("180"),
	OSMA("240"),
	DEVETA("280"),
	DESETA("320"),
	EDINAESETA("360"),
	DVANAESETA("400"),
	TRIENAESETA("500"),
	CHETIRINAESETA("600"),
	PETNAESETA("800"),
	SESTNAESETA("1000"),
	SEDUMNAESETA("1200"),
	OSUMNAESETA("1500"),
	DEVETNAESETA("2000"),
	DVAESETA("2500"),
	DVAESETIPRVA("3000"),
	DVAESETIVTORA("4000");
	
	
private final String displayValue;
    
    private Granulation(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
	
}
