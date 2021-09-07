package com.example.erafmak.safety;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
@RequestMapping("/products")
public class SafetyController {
	
	@Autowired
	SafetyService service;
	
	@Autowired
	ManufacturerService manService;
	
	@GetMapping("/newSafety")
	public String primerModel(Model model) {
		model.addAttribute("safety", new Safety());
		model.addAttribute("manufacturers", manService.manufacturers());
		model.addAttribute("sizes", service.sizes());
		return "addSafety";
	}
	
	@PostMapping("/newSafety")
	public String createPrimer(@ModelAttribute(value = "safety")Safety safety, @Param(value = "sizes")List<Size>sizes) {
		service.newSafety(safety , sizes);
	return "redirect:/safety/" + safety.getId();
		
	}
	
	@PostMapping("/deleteSafety/{id}")
	public String deleteSafety(@PathVariable(value = "id")Long id) {
		service.deleteSafety(id);
		return "redirect:/safeties";
	}
	
	@GetMapping("/safeties")
	public String getAllSafety(Model model) {
		model.addAttribute("safeties", service.safeties());
		return "safeties";
	}
	
	@GetMapping("/safety/{id}")
	public String getSafetyDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("safety", service.findSafetyById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singleSafety";
	}
	
	@PostMapping("/updateSafetyPrice/{id}")
	public String updateSafetyPrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return "redirect:/safety/" + id;
	}
	
	@PostMapping("/updateSafetyName/{id}")
	public String updateSafetyName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateSafetyName(id , name);
		return "redirect:/safety/" + id;
	}
	
	@PostMapping("/updateSafetyDescription/{id}")
	public String updateSafetyDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateSafetyDescription(id , description);
		return "redirect:/safety/" + id;
	}
	
	@PostMapping("/updateSafetyManufacturer/{id}")
	public String updateSafetyManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return "redirect:/safety/" + id;
	}
	
	@PostMapping("/addQuantityToSafetyPerSize/{id}/{sid}")
	public String updateQuantityToSafety(@PathVariable(value = "id")Long id ,@PathVariable(value = "sid")Long sid , @Param(value = "quantity")Integer quantity) {
		service.updateSafetyQuantityPerSize(id , sid , quantity);
		return "redirect:/safety/" + id;
	}


}
