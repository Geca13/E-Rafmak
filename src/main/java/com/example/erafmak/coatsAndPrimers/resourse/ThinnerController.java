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

import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
@RequestMapping("/products")
public class ThinnerController {
	
	@Autowired
	ThinnerService service;
	
	@Autowired
	ManufacturerService manService;
	
	private final String REDIRECT = "redirect:/products/thinner/";
	
	
	@PostMapping("/deleteThinner/{id}")
	public String deleteThinner(@PathVariable(value = "id")Long id) {
		service.deleteThinner(id);
		return "redirect:/thinners";
	}
	
	@GetMapping("/thinners")
	public String getAllThinners(Model model) {
		model.addAttribute("thinners", service.thinners());
		return "thinners";
	}
	
	@GetMapping("/thinner/{id}")
	public String getThinnerDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("thinner", service.findThinnerById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singleThinner";
	}
	
	@PostMapping("/updateThinnerPrice/{id}")
	public String updateThinnerPrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateThinnerName/{id}")
	public String updateThinnerName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateThinnerName(id , name);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateThinnerDescription/{id}")
	public String updateThinnerDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateThinnerDescription(id , description);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateThinnerManufacturer/{id}")
	public String updateThinnerManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id;
	}
	
	@PostMapping("/setAvailabilityToThinner/{id}")
	public String updateAvailabilityToThinner(@PathVariable(value = "id")Long id ) {
		service.updateThinnerAvailability(id);
		return REDIRECT + id;
	}

	@PostMapping("/updateThinnerImage/{id}")
	public String updateImageToThinner(Model model , @PathVariable(value = "id")Long id, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
			service.updateThinnerImage(id , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("thinner", service.findThinnerById(id)) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "singleThinner";
		}
		
		return REDIRECT + id;
	}


}
