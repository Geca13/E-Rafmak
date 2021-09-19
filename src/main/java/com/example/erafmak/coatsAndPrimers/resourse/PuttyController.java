package com.example.erafmak.coatsAndPrimers.resourse;

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

import com.example.erafmak.coatsAndPrimers.entity.Weigth;
import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
@RequestMapping("/products")
public class PuttyController {
	
	@Autowired
	PuttyService service;
	
	@Autowired
	ManufacturerService manService;
	
	private final String REDIRECT = "redirect:/products/putty/";
	
	
	
	@GetMapping("/deletePutty/{id}")
	public String deletePutty(@PathVariable(value = "id")Long id) {
		service.deletePutty(id);
		return "redirect:/putties?delete";
	}
	
	@GetMapping("/putties")
	public String getAllPutties(Model model) {
		model.addAttribute("putties", service.putties());
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
		return REDIRECT + id+"?price";
	}
	
	@PostMapping("/updatePuttyWeight/{id}")
	public String updatePuttyWeight(@PathVariable("id")Long id, @Param(value = "weigth")Weigth weigth) {
		service.updatePuttyWeight(id, weigth);
		return REDIRECT + id+"?weight";
	}
	
	@PostMapping("/updatePuttyName/{id}")
	public String updatePuttyName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updatePuttyName(id , name);
		return REDIRECT + id+"?name";
	}
	
	@PostMapping("/updatePuttyDescription/{id}")
	public String updatePuttyDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updatePuttyDescription(id , description);
		return REDIRECT + id+"?description";
	}
	
	@PostMapping("/updatePuttyManufacturer/{id}")
	public String updatePuttyManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id+"?manufacturer";
	}
	
	@PostMapping("/setAvailabilityToPutty/{id}")
	public String updateQuantityToPutty(@PathVariable(value = "id")Long id) {
		service.updatePuttyAvailability(id);
		return REDIRECT + id+"?available";
	}

	@PostMapping("/updatePuttyImage/{id}")
	public String updateImageToPutty(Model model , @PathVariable(value = "id")Long id, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
			service.updatePuttyImage(id , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("putty", service.findPuttyById(id)) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "singlePutty";
		}
		
		return REDIRECT + id+"?image";
	}

}
