package com.example.erafmak.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.erafmak.user.entity.User;
import com.example.erafmak.user.errors.EmailAllreadyExistExceptionMessage;
import com.example.erafmak.user.errors.InvalidPasswordException;
import com.example.erafmak.user.service.UserServiceImpl;

@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl service;
	
	@GetMapping("/register")
	public String registerPage(Model model) {
		
		model.addAttribute("user", new User());
		
		return "registrationForm";
	}
	
	@PostMapping("/register")
	public String completeRegistration(@ModelAttribute(value = "user")User user, Model model) {
		
		try {
			service.registerNewUser(user);
		} catch (InvalidPasswordException | EmailAllreadyExistExceptionMessage e) {
			model.addAttribute("error", e.getMessage());
			return "registrationForm";
		}
		
		return "redirect:/register?success";
	}

}
