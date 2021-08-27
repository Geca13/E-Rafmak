package com.example.erafmak.safety;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class SizeQuantity {
	
	@Id
	private Long id;
	
	private Integer qty;
	
	@Enumerated(EnumType.STRING)
	private Size size;

}
