package com.example.erafmak.safety;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
@RequestMapping("/products")
public class SafetyController {
	
	@Autowired
	SafetyService service;
	
	@Autowired
	ManufacturerService manService;
	
	private final String REDIRECT = "redirect:/products/safety/";
	
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
		return REDIRECT + id;
	}
	
	@PostMapping("/updateSafetyName/{id}")
	public String updateSafetyName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateSafetyName(id , name);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateSafetyDescription/{id}")
	public String updateSafetyDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateSafetyDescription(id , description);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateSafetyManufacturer/{id}")
	public String updateSafetyManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id;
	}
	
	@PostMapping("/removeSizeFromSafety/{id}/{sid}")
	public String removeSizeFromSafety(@PathVariable(value = "id")Long id ,@PathVariable(value = "sid")Long sid ) {
		service.removeSizeFromSafety(sid);
		return REDIRECT + id;
	}
	
	@PostMapping("/setAvailabilityPerSize/{id}/{sid}")
	public String updateQuantityToSafety(@PathVariable(value = "id")Long id ,@PathVariable(value = "sid")Long sid ) {
		service.updateSafetyAvailabilityPerSize(sid);
		return REDIRECT + id;
	}
	
	@GetMapping("/addSizesToSafety/{id}")
    public String addSizesToSafetyForm(Model model , @PathVariable("id")Long id) {
		
		model.addAttribute("safety", service.findSafetyById(id));
		model.addAttribute("sizes", service.sizes(id));
		
		return "sizes";
		
	}
	
	@PostMapping("/addSizesToSafety/{id}")
    public String addSizesToSafety(@PathVariable("id")Long id, @RequestParam("allSizes") List<Size> allSizes) {
		service.addSizesToSafety(id, allSizes);
		return REDIRECT + id;
	}
	
}
