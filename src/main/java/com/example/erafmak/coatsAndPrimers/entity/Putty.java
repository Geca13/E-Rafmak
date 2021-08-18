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
public class Putty {
	
	@Id
	private Long id;
	
	private String puttyName;
	
	private String puttyDescription;
	
	private Integer qty;
	
	private Double priceOnPack;
	
	private String imageUrl;
	
	private Boolean available;

}
