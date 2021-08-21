package com.example.erafmak.manufacturers;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manufacturer {

	@Id
	private Long id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Origin origin;
	
	private String imageUrl;
}
