package com.example.erafmak.safety;

public enum Size {

	S("S"),
	M("M"),
	L("L"),
	XL("XL"),
	XXL("XXL"),
	XXXL("XXXL"),
	STRECHABLE("STRECHABLE");
	
	private String displayValue;
	
	private Size(String displayValue) {
		 this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
        return displayValue;
    }
}
