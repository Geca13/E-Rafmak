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
public class CoatController {
	
	@Autowired
	CoatService service;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	HardenerService harService;
	
	private final String REDIRECT = "redirect:/products/coat/";
	

	@GetMapping("/deleteCoat/{id}")
	public String deleteCoat(Model model , @PathVariable(value = "id")Long id) {
		
			service.deleteCoat(id);
	
		return "redirect:/products/coats?delete";
	}
	
	@GetMapping("/coats")
	public String getAllCoats(Model model) {
		model.addAttribute("coats", service.coats());
		return "coats";
	}
	
	@GetMapping("/coat/{id}")
	public String getCoatDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("coat", service.findCoatById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singleCoat";
	}
	
	@PostMapping("/updateCoatPrice/{id}")
	public String updateCoatPrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return REDIRECT + id+"?price";
	}
	
	@PostMapping("/updateCoatName/{id}")
	public String updateCoatName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateCoatName(id , name);
		return REDIRECT + id+"?name";
	}
	
	@PostMapping("/updateCoatDescription/{id}")
	public String updateCoatDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateCoatDescription(id , description);
		return REDIRECT + id+"?description";
	}
	
	@PostMapping("/updateCoatWeight/{id}")
	public String updateCoatWeight(@PathVariable("id")Long id, @Param(value = "weigth")Weigth weigth) {
		service.updateCoatWeight(id, weigth);
		return REDIRECT + id+"?weight";
	}
	
	@PostMapping("/updateCoatManufacturer/{id}")
	public String updateCoatManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id+"?manufacturer";
	}
	
	
	@PostMapping("/setAvailabilityToCoat/{id}")
	public String updateAvailabilityToCoat(@PathVariable(value = "id")Long id ) {
		service.updateCoatAvailability(id);
		return REDIRECT + id+"?available";
	}
	
	@PostMapping("/updateCoatImage/{id}")
	public String updateImageToCoat(Model model , @PathVariable(value = "id")Long id, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
			service.updateCoatImage(id , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("coat", service.findCoatById(id)) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "singleCoat";
		}
		return REDIRECT + id+"?image";
	}
	
	@PostMapping("/disconectHarterFromCoat/{id}/{hid}")
	public String disconnectHardener(@PathVariable("id")Long id,@PathVariable ("hid") Long hid) {
		service.disconectHardenerFromCoat(id, hid);
		return REDIRECT + id+"?disconnect";
	}

}
