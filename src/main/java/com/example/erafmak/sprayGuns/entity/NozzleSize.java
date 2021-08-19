package com.example.erafmak.sprayGuns.entity;

public enum NozzleSize {

	PRV("0.8"),
	VTOR("1.0"),
	TRET("1.3"),
	CETVRT("1.4"),
	PETTI("1.5"),
	SESTI("1.6"),
	SEDMA("1.8"),
	OSMA("2.0"),
	DEVETA("2.5");
	
	private String displayValue;
	
	private NozzleSize(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	
	
}
