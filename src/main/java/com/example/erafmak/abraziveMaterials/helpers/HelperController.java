package com.example.erafmak.abraziveMaterials.helpers;

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
public class HelperController {
	
	@Autowired
	HelperService service;
	
	@Autowired
	ManufacturerService manService;
	
	private final String REDIRECT = "redirect:/products/helper/";
	
	
	@GetMapping("/newHelper")
	public String helperModel(Model model) {
		model.addAttribute("helper", new Helper());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addHelper";
	}
	
	@PostMapping("/newHelper")
	public String createHelper(@ModelAttribute(value = "helper")Helper helper) {
		service.newHelper(helper);
	return REDIRECT+ helper.getId();
		
	}
	
	@PostMapping("/deleteHelper/{id}")
	public String deleteHelper(@PathVariable(value = "id")Long id) {
		service.deleteHelper(id);
		return "redirect:/products/helpers";
	}
	
	@GetMapping("/helpers")
	public String getAllHelpers(Model model) {
		model.addAttribute("helpers", service.helpers());
		return "helpers";
	}
	
	@GetMapping("/helper/{id}")
	public String getHelperDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("helper", service.findHelperById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singleHelper";
	}
	
	@PostMapping("/updatePrice/{id}")
	public String updatePrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateHelperName/{id}")
	public String updateHelperName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateName(id , name);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateDescription/{id}")
	public String updateDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateDescription(id , description);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateManufacturer/{id}")
	public String updateManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id;
	}
	
	@PostMapping("/addQuantity/{id}")
	public String updateHelperQuantity(@PathVariable(value = "id")Long id , @Param(value = "quantity")Integer quantity) {
		service.updateHelperQuantity(id , quantity);
		return REDIRECT + id;
	}
	
	

}
