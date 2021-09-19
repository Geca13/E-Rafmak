package com.example.erafmak.polish;

import java.io.IOException;
import java.util.List;
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
public class PadsController {
	
	@Autowired
	PadsService service;
	
	@Autowired
	PolishService polishService;
	
	@Autowired
	ManufacturerService manService;
	
	private final String REDIRECT = "redirect:/products/pads/";
	
	
	@GetMapping("/deletePads/{id}")
	public String deletePads(@PathVariable(value = "id")Long id) {
		service.deletePads(id);
		return REDIRECT+"?delete";
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
		return REDIRECT + id+"?price";
	}
	
	@PostMapping("/updatePadsName/{id}")
	public String updatePadsName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updatePadsName(id , name);
		return REDIRECT + id+"?name";
	}
	
	@PostMapping("/updatePadsDescription/{id}")
	public String updatePadsDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updatePadsDescription(id , description);
		return REDIRECT + id+"?description";
	}
	
	@PostMapping("/updatePadsManufacturer/{id}")
	public String updatePadsManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id+"?manufacturer";
	}
	
	@PostMapping("/setAvailabilityToPads/{id}")
	public String updateAvailabilityToPads(@PathVariable(value = "id")Long id ) {
		service.updatePadsAvailability(id);
		return REDIRECT + id+"?available";
	}
	
	@PostMapping("/updatePadsImage/{id}")
	public String updateImageToPads(Model model , @PathVariable(value = "id")Long id, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
			service.updatePadsImage(id , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("pads", service.findPadsById(id)) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "singlePads";
		}
		
		return REDIRECT + id+"?image";
	}

	@GetMapping("/connectPadsToPolish/{id}")
    public String connectPadsToPolish(Model model ,@PathVariable ("id") Long id) {
		model.addAttribute("pads", service.findPadsById(id));
		model.addAttribute("polishes", polishService.reducedPolishes(id));
		return "polishList";
	}
	
    @PostMapping("/connectPadsToPolish/{id}")
    public String completeConnectPadsToPolish(@PathVariable("id")Long id, @RequestParam("allPolishes")List<Polish> allPolishes) {
    	service.connectPadsToPolish(id , allPolishes);
    	return REDIRECT + id+"?connect";
    }

}
