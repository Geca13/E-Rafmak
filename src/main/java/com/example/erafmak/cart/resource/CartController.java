package com.example.erafmak.cart.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.erafmak.cart.entity.CartItem;
import com.example.erafmak.user.entity.User;
import com.example.erafmak.user.service.UserServiceImpl;
import com.example.erafmak.user.service.UsersDetails;

@Controller
public class CartController {
	
	@Autowired
	CartService service;
	
	@Autowired
	UserServiceImpl userService;
	
	
	@GetMapping("/cart/{id}")
	public String getCart(Model model,@PathVariable("id") Long id, @AuthenticationPrincipal UsersDetails user) {
		
		Map<User, List<CartItem>> cart = new HashMap<>();
		User authenticated = userService.findAuthenticatedUser(user);
		cart.put(authenticated, null);
		model.addAttribute("cart", cart);
		model.addAttribute("user", authenticated.getId());
		if(cart.values().isEmpty()) {
			return "cart?emptyCart";
		}
		return "cart";

	}
	
	@GetMapping("/addToCart/{id}")
	public String AddItemToCart() {
		return "";
	}
	
	

}
