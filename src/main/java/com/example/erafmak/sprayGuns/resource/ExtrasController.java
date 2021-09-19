package com.example.erafmak.sprayGuns.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
@RequestMapping("/products")
public class ExtrasController {
	
	@Autowired
	ExtrasService service;
	
	@Autowired
	ManufacturerService manService;
	
	private final String REDIRECT = "redirect:/products/extras/";
	
	
	@GetMapping("/deleteExtras/{id}")
	public String deleteExtras(@PathVariable(value = "id")Long id) {
		service.deleteExtras(id);
		return "redirect:/products/extrass?delete";
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
		return REDIRECT + id+"?price";
	}
	
	@PostMapping("/updateExtrasName/{id}")
	public String updateExtrasName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateExtrasName(id , name);
		return REDIRECT + id+"?name";
	}
	
	@PostMapping("/updateExtrasDescription/{id}")
	public String updateExtrasDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateExtrasDescription(id , description);
		return REDIRECT + id+"?description";
	}
	
	@PostMapping("/updateExtrasManufacturer/{id}")
	public String updateExtrasManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id+"?manufacturer";
	}
	
	@PostMapping("/setAvailabilityToExtras/{id}")
	public String updateQuantityToExtras(@PathVariable(value = "id")Long id ) {
		service.updateExtrasAvailibility(id);
		return REDIRECT + id+"?available";
	}
	
	@PostMapping("/updateExtrasImage/{id}")
	public String updateImageToExtras(Model model , @PathVariable(value = "id")Long id, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
			service.updateExtrasImage(id , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("extras", service.findExtrasById(id)) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "singleExtras";
		}
		
		return REDIRECT + id+"?image";
	}


}
