package com.example.erafmak.coatsAndPrimers.entity;

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
public class Hardener {
	
	@Id
	private Long id;
	
	private String hardenerName;
	
	private String hardenerDescription;

	private Double price;
	
	@Enumerated(EnumType.STRING)
	private Weigth weigth;
	
	private Integer qty;
	
	private String imageUrl;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Manufacturer manufacturer;
}
