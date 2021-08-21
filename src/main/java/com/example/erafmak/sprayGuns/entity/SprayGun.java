package com.example.erafmak.sprayGuns.entity;

import java.util.List;

import javax.persistence.Entity;
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
public class SprayGun {
	
	@Id
	private Long id;
	
	private String name;
	
	private Double price;
	
	private Integer qty;
	
	@ManyToMany
	private List<Nozzle> nozzles;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Manufacturer manufacturer;
	
	private String imageUrl;

}
