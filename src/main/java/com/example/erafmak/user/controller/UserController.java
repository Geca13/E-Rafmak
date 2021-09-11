package com.example.erafmak.user.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.erafmak.user.entity.User;
import com.example.erafmak.user.errors.EmailAllreadyExistExceptionMessage;
import com.example.erafmak.user.errors.InvalidPasswordException;
import com.example.erafmak.user.errors.UserNotFoundException;
import com.example.erafmak.user.service.UserServiceImpl;

import net.bytebuddy.utility.RandomString;

@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl service;
	
	@Autowired
	private JavaMailSender sender;
	
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
	
	
	
	
	@GetMapping ("/forgotPassword")
	public String showForgotPasswordForm(Model model) {
		
		    return "forgottenPassord";
	}
	
	@PostMapping("/forgotPassword")
	public String processRequest(HttpServletRequest request, Model model) {
		
		String email = request.getParameter("email");
		String token = RandomString.make(45);
		
		try {
			service.forgotPassword(token, email);
			
		String resetPasswordLink = getSiteUrl(request) + "/newPassword?token=" + token;
			
			sendEmail(email,resetPasswordLink);
			
			model.addAttribute("emailSentMessage", "Email with reset password link has been sent, please go to your email");
			
		} catch (UserNotFoundException e) {
			model.addAttribute("error", e.getMessage());
		} catch (UnsupportedEncodingException |MessagingException e) {
			model.addAttribute("error", "Something bad happened while sending the email.");
		}
		
		      return "forgottenPassord";
		
	}
	
	private void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		 helper.setFrom("Geca@geca.com", "Java imitacija");
		 helper.setTo(email);
		
		String subject = "Someone triggered reset password for your account... ";
		String content = "Here is the link to reset your password " +
		                 "<a href=\"" + resetPasswordLink +"\"> Change my password</a>" 
		                 +" if it wasnt you, you can ignore this email";
		
		 helper.setSubject(subject);
		 helper.setText(content, true);
		   sender.send(message);
		
	}

	public String getSiteUrl(HttpServletRequest request) {
		String siteUrl = request.getRequestURL().toString();
		     return siteUrl.replace(request.getServletPath(), "");
	}
	
	@GetMapping("/newPassword")
	public String newPasswordForm(@Param(value = "token") String token, Model model) {
		User user = service.getToken(token);
		 if(user == null) {
			model.addAttribute("tokenMessage", "The password was already changed with this token , if it wasn't you please use forgot password option again and cheng ethe password to your email. ");
			 return "newPassword";
		}
		    model.addAttribute("token", token);
		    model.addAttribute("user", user);
		     return "newPassword";
	}
	
	@PostMapping("/newPassword")
	public String setNewPassword(HttpServletRequest request,Model model) {
		
		String token = request.getParameter("token");
		String password = request.getParameter("password");
		User user = service.getToken(token);
        
        try {
		    service.updatePassword(user, password);
		} catch (InvalidPasswordException e) {
			model.addAttribute("error", e.getMessage());
			 return "redirect:/newPassword?token=" + token;
		}
            model.addAttribute("successMessage", "You have succesfully changed your password and you can easily log in now...");
        
	         return "login";
	}

}
