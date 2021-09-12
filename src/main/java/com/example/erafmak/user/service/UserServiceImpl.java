package com.example.erafmak.user.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.example.erafmak.user.errors.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	
	

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	RoleRepository roleRepository;

	public User registerNewUser(User user) {
		
		emailExists(user);
		
		validatePassword(user);
		
		setPasswordToUser(user);
		
		setRoles(user);
		
		return userRepository.save(user);
	}

	private void setPasswordToUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	}

	private void setRoles(User user) {
		Role role = roleRepository.findByRole(RoleName.ROLE_ADMIN);
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
		User user = userRepository.findByEmail(username);
		  if(user == null) {
		    	throw new UsernameNotFoundException("You are not signUped with that email");
		}
		return new UsersDetails(user);
	}
	
	public User findAuthenticatedUser(@AuthenticationPrincipal UsersDetails user) {
		String userEmail = user.getUsername();
        User authenticated = userRepository.findByEmail(userEmail);
        return authenticated;
	}
	
	
    public void forgotPassword(String token, String email) throws UserNotFoundException {
		
		User user = userRepository.findByEmail(email);
		if(user != null) {
			user.setToken(token);
			userRepository.save(user);
		} else {
			throw new UserNotFoundException("We dont have user with "+ email + " email, in our database ");
		}
	}
    
    
    public User getToken(String token) {
		   
		return userRepository.findByToken(token);
	}
	
	
    public void updatePassword(User user, String newPassword) throws InvalidPasswordException {
		
    	
    	PasswordValidator validator = new PasswordValidator();
    	user.setPassword(passwordEncoder.encode(newPassword));
		if(validator.validate(newPassword)== false) {
	    	throw new InvalidPasswordException("Your chosen password doesnt fit our creteria , it must contain at least 1 number, UpperCase and LowerCase letters and 1 special character");
        }
 	    
		    
		user.setToken(null);
		
		userRepository.save(user);
	}
    
    
      
      
     

}
