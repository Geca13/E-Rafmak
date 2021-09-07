package com.example.erafmak.manufacturers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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
		return "redirect:/";
	}
	
	@PostMapping("/deleteManufacturer/{id}")
	public String deleteManufacturer(@PathVariable(value = "id")Long id) {
		service.deleteManufacturer(id);
		return "redirect:/manufacturers";
	}
	
	@GetMapping("/manufacturers")
	public String getAllManufacturers(Model model) {
		model.addAttribute("manufacturers", service.manufacturers());
		return "manufacturers";
	}
	
	@GetMapping("/manufacturer/{id}")
	public String getManufacturersDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("manufacturer", service.findById(id)) ;
		return "singleManufacturer";
	}
	
	
	
	@PostMapping("/updateManufacturerName/{id}")
	public String updateCoatName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateManufacturerName(id , name);
		return "redirect:/manufacturer/" + id;
	}

}
