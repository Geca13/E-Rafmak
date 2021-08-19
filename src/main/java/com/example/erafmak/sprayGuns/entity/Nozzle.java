package com.example.erafmak.sprayGuns.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
	
	@Enumerated
	private NozzleSize nozzleSize;
	
	private Double prize;
	
	private String imageUrl;

}
