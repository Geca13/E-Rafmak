package com.example.erafmak.cart.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
	
    @Id
    private Long id;
	
	private String name;
	
	private String desc1;
	
	private String desc2;
	
	private Double price;
	
	private Integer qty;
	
	private Double cartItemTotal;
	
	private String image;

}
