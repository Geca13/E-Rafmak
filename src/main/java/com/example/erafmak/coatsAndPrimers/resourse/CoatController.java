package com.example.erafmak.coatsAndPrimers.resourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.erafmak.coatsAndPrimers.entity.Coat;
import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
public class CoatController {
	
	@Autowired
	CoatService service;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	HardenerService harService;
	
	@GetMapping("/newCoat")
	public String coatModel(Model model) {
		model.addAttribute("coat", new Coat());
		model.addAttribute("manufacturers", manService.manufacturers());
		model.addAttribute("hardeners", harService.hardeners());
		return "addCoat";
	}
	
	@PostMapping("/newCoat")
	public String createCoat(@ModelAttribute(value = "coat")Coat coat) {
		service.newCoat(coat);
	return "redirect:/";
		
	}
	
	@PostMapping("/deleteCoat/{id}")
	public String deleteCoat(@PathVariable(value = "id")Long id) {
		service.deleteCoat(id);
		return "redirect:/coats";
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
		return "redirect:/coat/" + id;
	}
	
	@PostMapping("/updateCoatName/{id}")
	public String updateCoatName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateCoatName(id , name);
		return "redirect:/coat/" + id;
	}
	
	@PostMapping("/updateCoatDescription/{id}")
	public String updateCoatDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateCoatDescription(id , description);
		return "redirect:/coat/" + id;
	}
	
	@PostMapping("/updateCoatManufacturer/{id}")
	public String updateCoatManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return "redirect:/coat/" + id;
	}
	
	@PostMapping("/addQuantityToCoat/{id}")
	public String updateQuantityToCoat(@PathVariable(value = "id")Long id , @Param(value = "quantity")Integer quantity) {
		service.updateCoatQuantity(id , quantity);
		return "redirect:/coat/" + id;
	}

}
