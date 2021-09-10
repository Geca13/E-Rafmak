package com.example.erafmak.cart.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	@Id
	private Long id;
	
	@ManyToMany
	private List<CartItem> cartItems;
	
	private Double cartItemTotal;
	
	
}
