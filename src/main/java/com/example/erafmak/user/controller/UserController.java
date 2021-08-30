package com.example.erafmak.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.erafmak.user.entity.User;
import com.example.erafmak.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		
		model.addAttribute("user", new User());
		
		return "register";
	}
	
	@PostMapping("/register")
	public String completeRegistration(@ModelAttribute(value = "user")User user) {
		service.registerNewUser(user);
		return "";
	}

}
