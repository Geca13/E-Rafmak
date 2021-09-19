package com.example.erafmak.abraziveMaterials.helpers;

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

import com.example.erafmak.abraziveMaterials.sander.Dimension;
import com.example.erafmak.manufacturers.ManufacturerService;


@Controller
@RequestMapping("/products")
public class HelperController {
	
	@Autowired
	HelperService service;
	
	@Autowired
	ManufacturerService manService;
	
	private final String REDIRECT = "redirect:/products/helper/";
	
	
	@GetMapping("/deleteHelper/{id}")
	public String deleteHelper(@PathVariable(value = "id")Long id) {
		service.deleteHelper(id);
		return "redirect:/products/helpers?delete";
	}
	
	@GetMapping("/helpers")
	public String getAllHelpers(Model model) {
		model.addAttribute("helpers", service.helpers());
		return "helpers";
	}
	
	@GetMapping("/helper/{id}")
	public String getHelperDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("helper", service.findHelperById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singleHelper";
	}
	
	@PostMapping("/updatePrice/{id}")
	public String updatePrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return REDIRECT + id +"?price";
	}
	
	@PostMapping("/updateHelperDimension/{id}")
	public String updateHelperDimension(@PathVariable("id") Long id, @Param(value="dimension") Dimension dimension) {
		service.updateHelperDimension(id, dimension);
		return REDIRECT + id+"?dimension";
	}
	
	@PostMapping("/updateHelperName/{id}")
	public String updateHelperName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateName(id , name);
		return REDIRECT + id+"?name";
	}
	
	@PostMapping("/updateDescription/{id}")
	public String updateDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateDescription(id , description);
		return REDIRECT + id+"?description";
	}
	
	@PostMapping("/updateManufacturer/{id}")
	public String updateManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id+"?manufacturer";
	}
	
	@PostMapping("/setAvailabilityToHelper/{id}")
	public String updateHelperAvailability(@PathVariable(value = "id")Long id ) {
		service.updateHelperAvailability(id);
		return REDIRECT + id+"?available";
	}
	
	@PostMapping("/updateHelperImage/{id}")
	public String updateImageToHelper(Model model , @PathVariable(value = "id")Long id, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
			service.updateHelperImage(id , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("coat", service.findHelperById(id)) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "singleHelper";
		}
		return REDIRECT + id+"?image";
	}
	
	@PostMapping("/disconectSander/{id}/{sid}")
	public String disconectSanderFromHelperList(@PathVariable("id") Long id, @PathVariable("sid") Long sid ) {
	    	service.disconectSanderFromHelperList(id, sid);
		return REDIRECT + id +"?disconnect";
	}
	
	


}
