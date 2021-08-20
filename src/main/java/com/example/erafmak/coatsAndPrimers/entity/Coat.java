package com.example.erafmak.coatsAndPrimers.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Coat {
	
	@Id
	private Long id;
	
	private String coatName;
	
	private String coatDescription;
	
	private Double price;
	
	@Enumerated
	private Weigth weigth;
	
	private Integer qty;
	
	private String imageUrl;
	
	@ManyToMany
	private List<Hardener> hardeners = new ArrayList<>();
	
	@ManyToOne
	Manufacturer manufacturer;

}
