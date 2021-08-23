package com.example.erafmak.polish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.erafmak.coatsAndPrimers.entity.Putty;
import com.example.erafmak.coatsAndPrimers.entity.Thinner;
import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
public class PadsController {
	
	@Autowired
	PadsService service;
	
	@Autowired
	ManufacturerService manService;
	
	
	@GetMapping("/newPads")
	public String padModel(Model model) {
		model.addAttribute("pads", new Pads());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addPads";
	}
	
	@PostMapping("/newPads")
	public String createPads(@ModelAttribute(value = "pads")Pads pads) {
		service.newPads(pads);
	return "redirect:/";
		
	}
	
	@PostMapping("/deletePads/{id}")
	public String deletePads(@PathVariable(value = "id")Long id) {
		service.deletePads(id);
		return "redirect:/pads";
	}
	
	@GetMapping("/pads")
	public String getAllPutties(Model model) {
		model.addAttribute("pads", service.pads());
		return "pads";
	}
	
	@GetMapping("/pads/{id}")
	public String getPadsDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("pads", service.findPadsById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singlePads";
	}
	
	@PostMapping("/updatePadsPrice/{id}")
	public String updatePadsPrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return "redirect:/pads/" + id;
	}
	
	@PostMapping("/updatePadsName/{id}")
	public String updatePadsName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updatePadsName(id , name);
		return "redirect:/pads/" + id;
	}
	
	@PostMapping("/updatePadsDescription/{id}")
	public String updatePadsDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updatePadsDescription(id , description);
		return "redirect:/pads/" + id;
	}
	
	@PostMapping("/updatePadsManufacturer/{id}")
	public String updatePadsManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return "redirect:/pads/" + id;
	}
	
	@PostMapping("/addQuantityToPads/{id}")
	public String updateQuantityToPads(@PathVariable(value = "id")Long id , @Param(value = "quantity")Integer quantity) {
		service.updatePadsQuantity(id , quantity);
		return "redirect:/pads/" + id;
	}

}
