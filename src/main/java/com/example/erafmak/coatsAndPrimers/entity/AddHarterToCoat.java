package com.example.erafmak.coatsAndPrimers.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddHarterToCoat {

	private List<Coat> coats;
	
	private Hardener hardener;
	
	protected void addHardener() {
		for (Coat coat : coats) {
			coat.getHardeners().add(hardener);
		}
	}
}
