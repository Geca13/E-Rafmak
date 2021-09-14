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
public class HardenerController {
	
	@Autowired
	HardenerService service;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	CoatService coatService;
	
	@Autowired
	PrimerService primerService;
	
	private final String REDIRECT = "redirect:/products/hardener/";
	
	
	@PostMapping("/deleteHardener/{id}")
	public String deleteHardener(@PathVariable(value = "id")Long id) {
		service.deleteHardener(id);
		return "redirect:/hardeners";
	}
	
	@GetMapping("/hardeners")
	public String getAllHardeners(Model model) {
		model.addAttribute("hardeners", service.hardeners());
		return "hardeners";
	}
	
	@GetMapping("/hardener/{id}")
	public String getHardenerDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("hardener", service.findHardenerById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singleHardener";
	}
	
	@PostMapping("/updateHardenerPrice/{id}")
	public String updateHardenerPrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateHardenerName/{id}")
	public String updateHardenerName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateHardenerName(id , name);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateHardenerDescription/{id}")
	public String updateHardenerDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateHardenerDescription(id , description);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateHardenerManufacturer/{id}")
	public String updateHardenerManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id;
	}
	
	@PostMapping("/addQuantityToHardener/{id}")
	public String updateQuantityToHardener(@PathVariable(value = "id")Long id , @Param(value = "quantity")Integer quantity) {
		service.updateHardenerQuantity(id , quantity);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateHardenerImage/{id}")
	public String updateImageToHardener(Model model , @PathVariable(value = "id")Long id, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
			service.updateHardenerImage(id , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("hardener", service.findHardenerById(id)) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "singleHardener";
		}
		
		return REDIRECT + id;
	}

	


}
