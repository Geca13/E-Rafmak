package com.example.erafmak.abraziveMaterials.sander;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Sander {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Integer piecesInPack;
	
	@Enumerated(EnumType.STRING)
	private Dimension dimension;
	
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@ManyToMany
	private List<GranulationQty> granulationQty;
	
	@Enumerated(EnumType.STRING)
	private Condition condition;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Manufacturer manufacturer;
	
	private String imageUrl;
	
	

}
