package com.example.erafmak.coatsAndPrimers.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.erafmak.coatsAndPrimers.entity.Putty;
import com.example.erafmak.coatsAndPrimers.entity.Thinner;
import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
public class PuttyController {
	
	@Autowired
	PuttyService service;
	
	@Autowired
	ManufacturerService manService;
	
	
	@GetMapping("/newPutty")
	public String puttyModel(Model model) {
		model.addAttribute("putty", new Thinner());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addPutty";
	}
	
	@PostMapping("/newPutty")
	public String createPutty(@ModelAttribute(value = "putty")Putty putty) {
		service.newPutty(putty);
	return "redirect:/";
		
	}
	
	@PostMapping("/deletePutty/{id}")
	public String deletePutty(@PathVariable(value = "id")Long id) {
		service.deletePutty(id);
		return "redirect:/putties";
	}
	
	@GetMapping("/putties")
	public String getAllPutties(Model model) {
		model.addAttribute("putties", service.thinners());
		return "putties";
	}
	
	@GetMapping("/putty/{id}")
	public String getPuttyDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("putty", service.findPuttyById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singlePutty";
	}
	
	@PostMapping("/updatePuttyPrice/{id}")
	public String updatePuttyPrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return "redirect:/putty/" + id;
	}
	
	@PostMapping("/updatePuttyName/{id}")
	public String updatePuttyName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updatePuttyName(id , name);
		return "redirect:/putty/" + id;
	}
	
	@PostMapping("/updatePuttyDescription/{id}")
	public String updatePuttyDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updatePuttyDescription(id , description);
		return "redirect:/putty/" + id;
	}
	
	@PostMapping("/updatePuttyManufacturer/{id}")
	public String updatePuttyManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return "redirect:/putty/" + id;
	}
	
	@PostMapping("/addQuantityToPutty/{id}")
	public String updateQuantityToPutty(@PathVariable(value = "id")Long id , @Param(value = "quantity")Integer quantity) {
		service.updatePuttyQuantity(id , quantity);
		return "redirect:/putty/" + id;
	}


}
