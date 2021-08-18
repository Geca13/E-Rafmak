package com.example.erafmak.coatsAndPrimers.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Primer {
	
	@Id
	private Long id;
	
	private String primerName;
	
	private String primerDescription;
	
	private Integer qty;
	
	private Double price;
	
	private String imageUrl;
	
	private Boolean available;
	
	@ManyToMany
	private List<Hardener> hardeners = new ArrayList<>();

}
