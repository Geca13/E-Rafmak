package com.example.erafmak.polish;

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
public class PolishController {
	
	@Autowired
	PolishService service;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	PadsService padsService;
	
	private final String REDIRECT = "redirect:/products/polish/";
	
	
	@GetMapping("/deletePolish/{id}")
	public String deleteCPolish(@PathVariable(value = "id")Long id) {
		service.deletePolish(id);
		return "redirect:/products/polishes?delete";
	}
	
	@GetMapping("/polishes")
	public String getAllPolishes(Model model) {
		model.addAttribute("polishes", service.polishes());
		return "polishes";
	}
	
	@GetMapping("/polish/{id}")
	public String getPolishDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("polish", service.findPolishById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singlePolish";
	}
	
	@PostMapping("/updatePolishPrice/{id}")
	public String updatePolishPrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return REDIRECT + id+"?price";
	}
	
	@PostMapping("/updatePolishName/{id}")
	public String updatePolishName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updatePolishName(id , name);
		return REDIRECT + id+"?name";
	}
	
	@PostMapping("/updatePolishDescription/{id}")
	public String updatePolishDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updatePolishDescription(id , description);
		return REDIRECT + id+"?description";
	}
	
	@PostMapping("/updatePolishManufacturer/{id}")
	public String updatePolishManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id+"?manufacturer";
	}
	
	@PostMapping("/setAvailabilityToPolish/{id}")
	public String updateSetAvailabilityToPolish(@PathVariable(value = "id")Long id) {
		service.updatePolishAvailability(id);
		return REDIRECT + id+"?available";
	}
	
	@PostMapping("/updatePolishImage/{id}")
	public String updateImageToPolish(Model model , @PathVariable(value = "id")Long id, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
			service.updatePolishImage(id , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("polish", service.findPolishById(id)) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "singlePolish";
		}
		return REDIRECT + id+"?image";
	}
	
	@PostMapping("/disconectPadsFromPolish/{id}/{pid}")
	public String disconnectPads(@PathVariable("id")Long id,@PathVariable ("pid") Long pid) {
		service.disconectPadsFromPolish(id, pid);
		return REDIRECT + id+"?disconnect";
	}



}
