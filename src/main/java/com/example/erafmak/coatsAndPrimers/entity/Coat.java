package com.example.erafmak.coatsAndPrimers.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coat {
	
	@Id
	private Long id;
	
	private String coatName;
	
	private String coatDescription;
	
	private Integer qty;
	
	private Double price;
	
	private String imageUrl;
	
	private Boolean available;
	
	@OneToMany
	private List<Hardener> hardeners = new ArrayList<>();

}
