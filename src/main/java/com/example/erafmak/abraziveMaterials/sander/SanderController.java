package com.example.erafmak.abraziveMaterials.sander;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
@RequestMapping("/products")
public class SanderController {
	
	@Autowired
	SanderService service;
	
	@Autowired
	ManufacturerService manService;
	
	
	@PostMapping("/deleteSander/{id}")
	public String deleteSander(@PathVariable(value = "id")Long id) {
		service.deleteSander(id);
		return "redirect:/sanders";
	}
	
	@GetMapping("/sanders")
	public String getAllSander(Model model) {
		model.addAttribute("sanders", service.sanders());
		return "sanders";
	}
	
	@GetMapping("/sanders/discs")
	public String getAllDiscs(Model model) {
		model.addAttribute("sanders", service.discs());
		return "sanders";
	}
	
	@GetMapping("/sanders/rolls")
	public String getAllRools(Model model) {
		model.addAttribute("sanders", service.rolls());
		return "sanders";
	}
	
	@GetMapping("/sanders/blocks")
	public String getAllBlocks(Model model) {
		model.addAttribute("sanders", service.blocks());
		return "sanders";
	}
	
	@GetMapping("/sanders/softs")
	public String getAllSofts(Model model) {
		model.addAttribute("sanders", service.softs());
		return "sanders";
	}
	
	@GetMapping("/sanders/wets")
	public String getAllWets(Model model) {
		model.addAttribute("sanders", service.wets());
		return "sanders";
	}
	
	@GetMapping("/sander/{id}")
	public String getSanderDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("sander", service.findSanderById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singleSander";
	}
	
	@PostMapping("/updateSanderPrice/{id}/{gid}")
	public String updatePrice(@PathVariable(value = "id")Long id ,@PathVariable(value = "gid")Long gid , @Param(value = "price") Double price) {
		service.updatePrice(gid , price);
		return "redirect:/sander/" + id;
	}
	
	@PostMapping("/updateName/{id}")
	public String updateName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateSanderName(id , name);
		return "redirect:/sander/" + id;
	}
	
	@PostMapping("/updateSanderManufacturer/{id}")
	public String updateSanderManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return "redirect:/sander/" + id;
	}
	
	@PostMapping("/setAvailabilityToSander/{id}/{gid}")
	public String updateAvailabilityPerGranulation(@PathVariable(value = "id")Long id ,@PathVariable(value = "gid")Long gid ) {
		service.updateSanderAvailability(gid);
		return "redirect:/sander/" + id;
	}

}
