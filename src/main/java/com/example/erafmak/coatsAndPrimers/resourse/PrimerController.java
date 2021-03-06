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
public class PrimerController {
	
	@Autowired
	PrimerService service;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	HardenerService harService;
	
	private final String REDIRECT = "redirect:/products/primer/";
	
	
	@GetMapping("/deletePrimer/{id}")
	public String deletePrimer(@PathVariable(value = "id")Long id) {
		service.deletePrimer(id);
		return "redirect:/products/primers?delete";
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
		return REDIRECT + id+"?price";
	}
	
	@PostMapping("/updatePrimerName/{id}")
	public String updatePrimerName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updatePrimerName(id , name);
		return REDIRECT + id+"?name";
	}
	
	@PostMapping("/updatePrimerWeight/{id}")
	public String updatePrimerWeight(@PathVariable("id")Long id, @Param(value = "weigth")Weigth weigth) {
		service.updatePrimerWeight(id, weigth);
		return REDIRECT + id+"?weight";
	}
	
	@PostMapping("/updatePrimerDescription/{id}")
	public String updatePrimerDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updatePrimerDescription(id , description);
		return REDIRECT + id+"?description";
	}
	
	@PostMapping("/updatePrimerManufacturer/{id}")
	public String updatePrimerManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id+"?manufacturer";
	}
	
	@PostMapping("/setAvailabilityToPrimer/{id}")
	public String updateAvailabilityToPrimer(@PathVariable(value = "id")Long id) {
		service.updatePrimerAvailability(id);
		return REDIRECT + id+"?available";
	}
	
	@PostMapping("/updatePrimerImage/{id}")
	public String updateImageToCoat(Model model , @PathVariable(value = "id")Long id, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
			service.updatePrimerImage(id , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("primer", service.findPrimerById(id)) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "singlePrimer";
		}
		return REDIRECT + id+"?image";
	}

	@PostMapping("/disconectHarterFromPrimer/{id}/{hid}")
	public String disconnectHardenerFromPrimer(@PathVariable("id")Long id,@PathVariable ("hid") Long hid) {
		service.disconectHardenerFromPrimer(id, hid);
		return REDIRECT + id+"?disconnect";
	}

}
