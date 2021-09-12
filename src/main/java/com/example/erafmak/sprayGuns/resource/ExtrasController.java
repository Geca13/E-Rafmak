package com.example.erafmak.sprayGuns.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.erafmak.manufacturers.ManufacturerService;
import com.example.erafmak.sprayGuns.entity.Extras;

@Controller
@RequestMapping("/products")
public class ExtrasController {
	
	@Autowired
	ExtrasService service;
	
	@Autowired
	ManufacturerService manService;
	
	private final String REDIRECT = "redirect:/products/extras/";
	
	
	@GetMapping("/newExtras")
	public String extrasModel(Model model) {
		model.addAttribute("extras", new Extras());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addExtrass";
	}
	
	@PostMapping("/newExtras")
	public String createExtras(@ModelAttribute(value = "extras")Extras extras, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		service.newExtras(extras , multiPartFile);
	return REDIRECT + extras.getId();
		
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
		return REDIRECT + id;
	}
	
	@PostMapping("/updateExtrasName/{id}")
	public String updateExtrasName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateExtrasName(id , name);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateExtrasDescription/{id}")
	public String updateExtrasDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateExtrasDescription(id , description);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateExtrasManufacturer/{id}")
	public String updateExtrasManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id;
	}
	
	@PostMapping("/addQuantityToExtras/{id}")
	public String updateQuantityToExtras(@PathVariable(value = "id")Long id , @Param(value = "quantity")Integer quantity) {
		service.updateExtrasQuantity(id , quantity);
		return REDIRECT + id;
	}

}
