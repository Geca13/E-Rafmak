package com.example.erafmak.abraziveMaterials.sander;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@Enumerated(EnumType.STRING)
	private Dimension dimension;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@Enumerated(EnumType.STRING)
	private Granulation granulation;
	
	@Enumerated(EnumType.STRING)
	private Condition condition;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Manufacturer manufacturer;
	
	private String imageUrl;
	
	

}
