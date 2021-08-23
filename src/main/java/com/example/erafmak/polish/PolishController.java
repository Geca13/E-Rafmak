package com.example.erafmak.polish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.erafmak.coatsAndPrimers.entity.Coat;
import com.example.erafmak.coatsAndPrimers.resourse.HardenerService;
import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
public class PolishController {
	
	@Autowired
	PolishService service;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	PadsService padsService;
	
	@GetMapping("/newPolish")
	public String polishModel(Model model) {
		model.addAttribute("polish", new Polish());
		model.addAttribute("manufacturers", manService.manufacturers());
		model.addAttribute("pads", padsService.pads());
		return "addPolish";
	}
	
	@PostMapping("/newPolish")
	public String createPolish(@ModelAttribute(value = "polish")Polish polish) {
		service.newPolish(polish);
	return "redirect:/";
		
	}
	
	@PostMapping("/deletePolish/{id}")
	public String deleteCPolish(@PathVariable(value = "id")Long id) {
		service.deletePolish(id);
		return "redirect:/polishes";
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
		return "redirect:/polish/" + id;
	}
	
	@PostMapping("/updatePolishName/{id}")
	public String updatePolishName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updatePolishName(id , name);
		return "redirect:/polish/" + id;
	}
	
	@PostMapping("/updatePolishDescription/{id}")
	public String updatePolishDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updatePolishDescription(id , description);
		return "redirect:/polish/" + id;
	}
	
	@PostMapping("/updatePolishManufacturer/{id}")
	public String updatePolishManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return "redirect:/polish/" + id;
	}
	
	@PostMapping("/addQuantityToPolish/{id}")
	public String updateQuantityToPolish(@PathVariable(value = "id")Long id , @Param(value = "quantity")Integer quantity) {
		service.updatePolishQuantity(id , quantity);
		return "redirect:/polish/" + id;
	}


}
