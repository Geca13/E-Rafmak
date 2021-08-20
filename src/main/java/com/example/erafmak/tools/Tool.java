package com.example.erafmak.tools;

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
public class Tool {
	
	@Id
	private Long id;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	private Integer qty;
	
	@Enumerated
	private Power power;
	
	@ManyToOne
	private Manufacturer manufacturer;
	
	private String imageUrl;

}
