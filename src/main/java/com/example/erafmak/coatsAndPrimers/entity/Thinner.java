package com.example.erafmak.coatsAndPrimers.entity;

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
public class Thinner {
	
	@Id
	private Long id;
	
	private String thinnerName;
	
	private String thinnerDescription;
	
	private Double price;
	
	@Enumerated
	private Weigth weigth;
	
	private Integer qty;
	
	private String imageUrl;
	
	@ManyToOne
	Manufacturer manufacturer;

}
