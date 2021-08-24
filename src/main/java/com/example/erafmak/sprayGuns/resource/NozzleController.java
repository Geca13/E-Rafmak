package com.example.erafmak.sprayGuns.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.erafmak.manufacturers.ManufacturerService;
import com.example.erafmak.sprayGuns.entity.Nozzle;

@Controller
public class NozzleController {
	
	@Autowired
	NozzleService service;
	
	@Autowired
	ManufacturerService manService;
	
	@GetMapping("/newNozzle")
	public String nozzleModel(Model model) {
		model.addAttribute("nozzle", new Nozzle());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addNozzle";
	}
	
	@PostMapping("/newNozzle")
	public String createNozzle(@ModelAttribute(value = "coat")Nozzle nozzle) {
		service.newNozzle(nozzle);
	return "redirect:/";
		
	}
	
	@PostMapping("/deleteNozzle/{id}")
	public String deleteNozzle(@PathVariable(value = "id")Long id) {
		service.deleteNozzle(id);
		return "redirect:/nozzles";
	}
	
	@GetMapping("/nozzles")
	public String getAllNozzle(Model model) {
		model.addAttribute("nozzles", service.nozzles());
		return "nozzles";
	}
	
	@GetMapping("/nozzle/{id}")
	public String getNozzleDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("nozzle", service.findNozzleById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singleNozzle";
	}
	
	@PostMapping("/updateNozzlePrice/{id}")
	public String updateNozzlePrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return "redirect:/nozzle/" + id;
	}
	
	@PostMapping("/updateNozzleName/{id}")
	public String updateNozzleName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateNozzleName(id , name);
		return "redirect:/nozzle/" + id;
	}
	
	@PostMapping("/updateNozzleDescription/{id}")
	public String updateNozzleDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateNozzleDescription(id , description);
		return "redirect:/nozzle/" + id;
	}
	
	@PostMapping("/updateNozzleManufacturer/{id}")
	public String updateNozzleManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return "redirect:/nozzle/" + id;
	}
	
	@PostMapping("/addQuantityToNozzle/{id}")
	public String updateQuantityToNozzle(@PathVariable(value = "id")Long id , @Param(value = "quantity")Integer quantity) {
		service.updateNozzleQuantity(id , quantity);
		return "redirect:/nozzle/" + id;
	}

}
