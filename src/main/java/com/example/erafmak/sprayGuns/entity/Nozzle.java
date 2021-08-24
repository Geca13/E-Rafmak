package com.example.erafmak.sprayGuns.entity;


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
public class Nozzle {
	
	@Id
	private Long id;
	
	private String description;
	
	@Enumerated
	private NozzleSize nozzleSize;
	
	private Double price;
	
	private Integer qty;
	
	private String imageUrl;
	
	@ManyToOne
	private Manufacturer manufacturer;

}
