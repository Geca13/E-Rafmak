package com.example.erafmak.coatsAndPrimers.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
public class Coat {
	
	@Id
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	@Enumerated(EnumType.STRING)
	private Weigth weigth;
	
	private Boolean isAvailable;
	
	private String imageUrl;
	
	@ManyToMany
	private List<Hardener> hardeners = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Manufacturer manufacturer;
	
	
}
