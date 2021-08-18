package com.example.erafmak.coatsAndPrimers.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	private Integer qty;
	
	private Double price;
	
	private String imageUrl;
	
	private Boolean available;
	
	@ManyToOne
	private Coat coat;

}
