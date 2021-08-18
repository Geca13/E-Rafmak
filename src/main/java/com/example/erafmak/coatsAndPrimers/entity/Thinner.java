package com.example.erafmak.coatsAndPrimers.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	private Integer qty;
	
	private Double price;
	
	private String imageUrl;
	
	private Boolean available;

}
