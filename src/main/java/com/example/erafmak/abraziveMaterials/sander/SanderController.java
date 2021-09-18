package com.example.erafmak.abraziveMaterials.sander;



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

import com.example.erafmak.abraziveMaterials.helpers.Helper;
import com.example.erafmak.abraziveMaterials.helpers.HelperService;
import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
@RequestMapping("/products")
public class SanderController {
	
	@Autowired
	SanderService service;
	
	@Autowired
	HelperService helperService;
	
	@Autowired
	ManufacturerService manService;
	
	private final String REDIRECT = "redirect:/products/sander/";
	
	
	@PostMapping("/setAvailabilityToSander/{id}")
	public String setChangeAvailability(@PathVariable(value = "id")Long id) {
		service.changeAvailability(id);
		return REDIRECT + id;
	}
	
	@GetMapping("/deleteSander/{id}")
	public String deleteSander(@PathVariable(value = "id")Long id) {
		service.deleteSander(id);
		return "redirect:/products/sanders";
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
		return REDIRECT + id;
	}
	
	@PostMapping("/updateName/{id}")
	public String updateName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateSanderName(id , name);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateSanderManufacturer/{id}")
	public String updateSanderManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateSanderCondition/{id}")
	public String updateSanderCondition(@PathVariable(value = "id")Long id , @Param(value = "condition")Condition condition) {
		service.updateManufacturerCondition(id , condition);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateSanderDimension/{id}")
	public String updateSanderDimension(@PathVariable(value = "id")Long id , @Param(value = "dimension")Dimension dimension) {
		service.updateManufacturerDimension(id , dimension);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateSanderType/{id}")
	public String updateSanderType(@PathVariable(value = "id")Long id , @Param(value = "type")Type type) {
		service.updateManufacturerType(id , type);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateSanderPieces/{id}")
	public String updateSanderPieces(@PathVariable(value = "id")Long id , @Param(value = "pieces")Integer pieces) {
		service.updateManufacturerPieces(id , pieces);
		return REDIRECT + id;
	}
	
	@PostMapping("/setAvailabilityToSander/{id}/{gid}")
	public String updateAvailabilityPerGranulation(@PathVariable(value = "id")Long id ,@PathVariable(value = "gid")Long gid ) {
		service.updateSanderAvailability(gid);
		return REDIRECT + id;
	}
	
	@GetMapping("/addGranulationToSander/{id}")
    public String addSizesToSafetyForm(Model model , @PathVariable("id")Long id) {
		
		model.addAttribute("sander", service.findSanderById(id));
		model.addAttribute("granulations", service.granulations(id));
		
		return "granulations";
		
	}
	
	@PostMapping("/addGranulationToSander/{id}")
    public String addSizesToSafety(@PathVariable("id")Long id, @RequestParam("allGranulations") List<Granulation> allGranulations) {
		service.addGranulationToSander(id, allGranulations);
		return REDIRECT + id;
	}
	
	@PostMapping("/removeGranulationFromSander/{id}/{gid}")
	public String removeGranulationFromSander(@PathVariable(value = "id")Long id ,@PathVariable(value = "gid")Long gid ) {
		service.removeGranulationFromSander(gid);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateSanderImage/{id}")
	public String updateImageToSander(Model model , @PathVariable(value = "id")Long id, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
			service.updateSanderImage(id , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("coat", service.findSanderById(id)) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "singleHelper";
		}
		return REDIRECT + id;
	}
	
	@GetMapping("/connectSanderToHelper/{id}")
	public String getAllHelpersForm(Model model, @PathVariable("id") Long id) {
		model.addAttribute("sander", service.findSanderById(id));
		model.addAttribute("helpers", helperService.helpers());
		return "helpersList";
	}
	
	@PostMapping("/connectSanderToHelper/{id}")
	public String getAllHelpersForConnection(@PathVariable("id") Long id, @RequestParam("allHelpers") List<Helper> allHelpers) {
		service.connectSanderToHelpers(id, allHelpers);
		return REDIRECT + id;
	}

}
