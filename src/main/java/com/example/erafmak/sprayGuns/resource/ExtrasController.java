package com.example.erafmak.sprayGuns.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.erafmak.manufacturers.ManufacturerService;
import com.example.erafmak.sprayGuns.entity.Extras;

@Controller
public class ExtrasController {
	
	@Autowired
	ExtrasService service;
	
	@Autowired
	ManufacturerService manService;
	
	
	@GetMapping("/newExtras")
	public String extrasModel(Model model) {
		model.addAttribute("extras", new Extras());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addExtras";
	}
	
	@PostMapping("/newExtras")
	public String createExtras(@ModelAttribute(value = "extras")Extras extras) {
		service.newExtras(extras);
	return "redirect:/";
		
	}
	
	@PostMapping("/deleteExtras/{id}")
	public String deleteExtras(@PathVariable(value = "id")Long id) {
		service.deleteExtras(id);
		return "redirect:/extrass";
	}
	
	@GetMapping("/extrass")
	public String getAllExtras(Model model) {
		model.addAttribute("extrass", service.extrass());
		return "extrass";
	}
	
	@GetMapping("/extras/{id}")
	public String getExtrasDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("extras", service.findExtrasById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singleExtras";
	}
	
	@PostMapping("/updateExtrasPrice/{id}")
	public String updateExtrasPrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return "redirect:/hardener/" + id;
	}
	
	@PostMapping("/updateExtrasName/{id}")
	public String updateExtrasName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateExtrasName(id , name);
		return "redirect:/extras/" + id;
	}
	
	@PostMapping("/updateExtrasDescription/{id}")
	public String updateExtrasDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateExtrasDescription(id , description);
		return "redirect:/extras/" + id;
	}
	
	@PostMapping("/updateExtrasManufacturer/{id}")
	public String updateExtrasManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return "redirect:/Extras/" + id;
	}
	
	@PostMapping("/addQuantityToExtras/{id}")
	public String updateQuantityToExtras(@PathVariable(value = "id")Long id , @Param(value = "quantity")Integer quantity) {
		service.updateExtrasQuantity(id , quantity);
		return "redirect:/extras/" + id;
	}

}
