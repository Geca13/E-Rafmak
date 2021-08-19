package com.example.erafmak.abraziveMaterials.helpers;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.erafmak.manufacturers.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Helper {
	
	@Id
	private Long id;
	
	private String description;
	
	private Double price;
	
	@ManyToOne
	private Manufacturer manufacturer; 
	
	private String imageUrl;

}
