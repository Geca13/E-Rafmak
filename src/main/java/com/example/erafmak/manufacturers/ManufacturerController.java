package com.example.erafmak.manufacturers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administration")
public class ManufacturerController {
	
	@Autowired
	ManufacturerService service;
	
	
	@GetMapping("/addManufacturer")
	public String createManufacturer(Model model) {
		model.addAttribute("manufacturer", new Manufacturer());
		model.addAttribute("origins", service.getCountriesList());
		return "addManufacturer";
	}
	
	@PostMapping("/addManufacturer")
	public String createNewManufacturer(Manufacturer manufacturer) {
		service.createNewManufacturer(manufacturer);
		return "redirect:/administration/manufacturers";
	}
	
	@GetMapping("/manufacturers")
	public String getAllManufacturers(Model model) {
		model.addAttribute("manufacturers", service.manufacturers());
		return "manufacturers";
	}
	
	@GetMapping("/updateManufacturer/{id}")
	public String getManufacturersDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("manufacturer", service.findById(id)) ;
		model.addAttribute("origins", service.getCountriesList());
		return "updateManufacturer";
	}
	
	
	
	@PostMapping("/updateManufacturer/{id}")
	public String updateCoatName(@PathVariable(value = "id")Long id , Manufacturer manufacturer ) {
		service.updateManufacturer(id, manufacturer );
		return "redirect:/administration/manufacturers";
	}

}
