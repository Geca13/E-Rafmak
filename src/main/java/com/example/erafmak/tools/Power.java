package com.example.erafmak.tools;

public enum Power {
	
	ELECTRIC("Electric"),
	PNEUMATIC("Pneumatic");
	
	private String displayValue;
	
	private Power(String displayValue) {
		 this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
        return displayValue;
    }
	

}
