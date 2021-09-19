package com.example.erafmak.sprayGuns.resource;

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
public class SprayGunsController {
	
	@Autowired
	SprayGunsService service;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	NozzleService nozzleService;
	
	private final String REDIRECT = "redirect:/products/sprayGun/";
	
	
	@GetMapping("/deleteSprayGun/{id}")
	public String deleteSprayGun(@PathVariable(value = "id")Long id) {
		service.deleteSprayGun(id);
		return "redirect:/products/guns?delete";
	}
	
	@GetMapping("/guns")
	public String getAllPrimers(Model model) {
		model.addAttribute("guns", service.guns());
		return "guns";
	}
	
	@GetMapping("/sprayGun/{id}")
	public String getSprayGunDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("sprayGun", service.findSprayGunById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singleGun";
	}
	
	@PostMapping("/updateSprayGunPrice/{id}")
	public String updateSprayGunPrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return REDIRECT + id+"?price";
	}
	
	@PostMapping("/updateSprayGunName/{id}")
	public String updateSprayGunName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateSprayGunName(id , name);
		return REDIRECT + id+"?name";
	}
	
	@PostMapping("/updateSprayGunDescription/{id}")
	public String updateSprayGunDescription(@PathVariable(value = "id")Long id , @Param(value = "name") String description) {
		service.updateSprayGunDescription(id , description);
		return REDIRECT + id+"?description";
	}
	
	
	@PostMapping("/updateSprayGunManufacturer/{id}")
	public String updateSprayGunManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id+"?manufacturer";
	}
	
	@PostMapping("/setAvailabilityToSprayGun/{id}")
	public String updateQuantityToSprayGun(@PathVariable(value = "id")Long id ) {
		service.updateSprayGunAvailability(id);
		return REDIRECT + id+"?available";
	}
	
	@PostMapping("/updateSprayGunImage/{id}")
	public String updateImageToSprayGun(Model model , @PathVariable(value = "id")Long id, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
			service.updateSprayGunImage(id , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("gun", service.findSprayGunById(id)) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "singleGun";
		}
		
		return REDIRECT + id+"?image";
	}

	@PostMapping("/disconectNozzleFromSprayGun/{id}/{nid}")
	public String disconnectNozzle(@PathVariable("id")Long id,@PathVariable ("nid") Long nid) {
		service.disconectNozzleFromGun(id, nid);
		return REDIRECT + id+"?disconnect";
	}

}
