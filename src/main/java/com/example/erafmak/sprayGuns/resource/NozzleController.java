package com.example.erafmak.sprayGuns.resource;

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
import com.example.erafmak.sprayGuns.entity.NozzleSize;
import com.example.erafmak.sprayGuns.entity.SprayGun;

@Controller
@RequestMapping("/products")
public class NozzleController {
	
	@Autowired
	NozzleService service;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	SprayGunsService sprayGunService;
	
	private final String REDIRECT = "redirect:/products/nozzle/";
	
	
	@GetMapping("/deleteNozzle/{id}")
	public String deleteNozzle(@PathVariable(value = "id")Long id) {
		service.deleteNozzle(id);
		return "redirect:/products/nozzles?delete";
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
		return REDIRECT + id+"?price";
	}
	
	@PostMapping("/updateNozzleSize/{id}")
	public String updateNozzleSize(@PathVariable(value = "id")Long id , @Param(value = "size") NozzleSize size) {
		service.updateSize(id , size);
		return REDIRECT + id+"?size";
	}
	
	@PostMapping("/updateNozzleName/{id}")
	public String updateNozzleName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateNozzleName(id , name);
		return REDIRECT + id+"?name";
	}
	
	@PostMapping("/updateNozzleDescription/{id}")
	public String updateNozzleDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateNozzleDescription(id , description);
		return REDIRECT + id+"?description";
	}
	
	@PostMapping("/updateNozzleManufacturer/{id}")
	public String updateNozzleManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id+"?manufacturer";
	}
	
	@PostMapping("/setAvailabilityToNozzle/{id}")
	public String updateQuantityToNozzle(@PathVariable(value = "id")Long id ) {
		service.updateNozzleAvailability(id);
		return REDIRECT + id+"?available";
	}
	
	@PostMapping("/updateNozzleImage/{id}")
	public String updateImageToNozzle(Model model , @PathVariable(value = "id")Long id, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
			service.updateNozzleImage(id , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("nozzle", service.findNozzleById(id)) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "singleNozzle";
		}
		
		return REDIRECT + id+"?image";
	}
	
	@GetMapping("/connectNozzleToSprayGun/{id}")
    public String connectNozzleToSprayGun(Model model ,@PathVariable ("id") Long id) {
		model.addAttribute("nozzle", service.findNozzleById(id));
		model.addAttribute("sprayGuns", sprayGunService.reducedGuns(id));
		return "sprayGunsList";
	}
	
    @PostMapping("/connectNozzleToSprayGun/{id}")
    public String completeConnectNozzleToSprayGun(@PathVariable("id")Long id, @RequestParam("allSprayGuns")List<SprayGun> allSprayGuns) {
    	service.connectNozzleToSprayGun(id , allSprayGuns);
    	return REDIRECT + id+"?connect";
    }


}
