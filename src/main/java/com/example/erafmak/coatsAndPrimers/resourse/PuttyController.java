package com.example.erafmak.coatsAndPrimers.resourse;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.erafmak.coatsAndPrimers.entity.Putty;
import com.example.erafmak.coatsAndPrimers.entity.Thinner;
import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
@RequestMapping("/products")
public class PuttyController {
	
	@Autowired
	PuttyService service;
	
	@Autowired
	ManufacturerService manService;
	
	private final String REDIRECT = "redirect:/products/putty/";
	
	
	@GetMapping("/newPutty")
	public String puttyModel(Model model) {
		model.addAttribute("putty", new Thinner());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addPutty";
	}
	
	@PostMapping("/newPutty")
	public String createPutty(@ModelAttribute(value = "putty")Putty putty , @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException{
		service.newPutty(putty , multiPartFile);
	return REDIRECT + putty.getId();
		
	}
	
	@PostMapping("/deletePutty/{id}")
	public String deletePutty(@PathVariable(value = "id")Long id) {
		service.deletePutty(id);
		return "redirect:/putties";
	}
	
	@GetMapping("/putties")
	public String getAllPutties(Model model) {
		model.addAttribute("putties", service.putties());
		return "putties";
	}
	
	@GetMapping("/putty/{id}")
	public String getPuttyDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("putty", service.findPuttyById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singlePutty";
	}
	
	@PostMapping("/updatePuttyPrice/{id}")
	public String updatePuttyPrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return REDIRECT + id;
	}
	
	@PostMapping("/updatePuttyName/{id}")
	public String updatePuttyName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updatePuttyName(id , name);
		return REDIRECT + id;
	}
	
	@PostMapping("/updatePuttyDescription/{id}")
	public String updatePuttyDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updatePuttyDescription(id , description);
		return REDIRECT + id;
	}
	
	@PostMapping("/updatePuttyManufacturer/{id}")
	public String updatePuttyManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id;
	}
	
	@PostMapping("/addQuantityToPutty/{id}")
	public String updateQuantityToPutty(@PathVariable(value = "id")Long id , @Param(value = "quantity")Integer quantity) {
		service.updatePuttyQuantity(id , quantity);
		return REDIRECT + id;
	}


}
