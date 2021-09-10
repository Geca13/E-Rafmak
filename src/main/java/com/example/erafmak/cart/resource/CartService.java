package com.example.erafmak.cart.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.erafmak.cart.repository.CartItemRepository;
import com.example.erafmak.cart.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartItemRepository ciRepository;
	
	
	

}
