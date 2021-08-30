package com.example.erafmak.user.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.erafmak.user.entity.Role;
import com.example.erafmak.user.entity.RoleName;
import com.example.erafmak.user.entity.RoleRepository;
import com.example.erafmak.user.entity.User;
import com.example.erafmak.user.entity.UserRepository;
import com.example.erafmak.user.errors.EmailAllreadyExistExceptionMessage;
import com.example.erafmak.user.errors.InvalidPasswordException;

@Service
public class UserService implements UserDetailsService {
	
	

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	RoleRepository roleRepository;

	public User registerNewUser(User user) {
		
		emailExists(user);
		
		validatePassword(user);
		
		passwordEncoder.encode(user.getPassword());
		
		setRoles(user);
		
		return userRepository.save(user);
	}

	private void setRoles(User user) {
		Role role = roleRepository.findByRole(RoleName.ROLE_USER);
		user.setRoles(Collections.singleton(role));
	}

	private void validatePassword(User user) {
		PasswordValidator validator = new PasswordValidator();
		if(validator.validate(user.getPassword())== false) {
	    	throw new InvalidPasswordException("Your chosen password doesnt fit our creteria , it must contain at least 1 number, UpperCase and LowerCase letters and 1 special character");
        }
	}

	private void emailExists(User user) {
		User oldUser = userRepository.findByEmail(user.getEmail());
		if(oldUser != null) {
			throw new EmailAllreadyExistExceptionMessage("We allready have user registered with that email , try the forget password option");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
