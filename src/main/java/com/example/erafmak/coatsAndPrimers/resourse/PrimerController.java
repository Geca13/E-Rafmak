package com.example.erafmak.coatsAndPrimers.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.erafmak.coatsAndPrimers.entity.Primer;
import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
public class PrimerController {
	
	@Autowired
	PrimerService service;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	HardenerService harService;
	
	@GetMapping("/newPrimer")
	public String primerModel(Model model) {
		model.addAttribute("primer", new Primer());
		model.addAttribute("manufacturers", manService.manufacturers());
		model.addAttribute("hardeners", harService.hardeners());
		return "addPrimer";
	}
	
	@PostMapping("/newPrimer")
	public String createPrimer(@ModelAttribute(value = "coat")Primer primer) {
		service.newPrimer(primer);
	return "redirect:/";
		
	}
	
	@PostMapping("/deletePrimer/{id}")
	public String deletePrimer(@PathVariable(value = "id")Long id) {
		service.deletePrimer(id);
		return "redirect:/primers";
	}
	
	@GetMapping("/primers")
	public String getAllPrimers(Model model) {
		model.addAttribute("primers", service.primers());
		return "primers";
	}
	
	@GetMapping("/primer/{id}")
	public String getPrimerDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("primer", service.findPrimerById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singlePrimer";
	}
	
	@PostMapping("/updatePrimerPrice/{id}")
	public String updatePrimerPrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return "redirect:/primer/" + id;
	}
	
	@PostMapping("/updatePrimerName/{id}")
	public String updatePrimerName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updatePrimerName(id , name);
		return "redirect:/primer/" + id;
	}
	
	@PostMapping("/updatePrimerDescription/{id}")
	public String updatePrimerDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updatePrimerDescription(id , description);
		return "redirect:/primer/" + id;
	}
	
	@PostMapping("/updatePrimerManufacturer/{id}")
	public String updatePrimerManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return "redirect:/primer/" + id;
	}
	
	@PostMapping("/addQuantityToPrimer/{id}")
	public String updateQuantityToPrimer(@PathVariable(value = "id")Long id , @Param(value = "quantity")Integer quantity) {
		service.updatePrimerQuantity(id , quantity);
		return "redirect:/primer/" + id;
	}

}
