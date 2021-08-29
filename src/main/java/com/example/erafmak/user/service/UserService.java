package com.example.erafmak.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.erafmak.user.entity.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	

}
