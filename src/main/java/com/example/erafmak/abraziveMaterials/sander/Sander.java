package com.example.erafmak.abraziveMaterials.sander;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
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
public class Sander {
	
	@Id
	private Long id;
	
	private String name;
	
	private Integer piecesInPack;
	
	private Integer qty;
	
	private Double price;
	
	@Enumerated
	private Dimension dimension;
	
	@Enumerated
	private Type type;
	
	@Enumerated
	private Granulation granulation;
	
	@Enumerated
	private Condition condition;
	
	@ManyToOne
	private Manufacturer manufacturer;
	
	private String imageUrl;
	
	

}
