package com.example.erafmak.sanding.entity;

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
public class Sander {
	
	@Id
	private Long id;
	
	private String name;
	
	private String piecesInPack;
	
	private Integer qty;
	
	private Dimension dimension;
	
	private Type type;
	
	private Granulation granulation;
	
	private Condition condition;
	
	

}
