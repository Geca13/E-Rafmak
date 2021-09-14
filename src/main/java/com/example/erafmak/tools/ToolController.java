package com.example.erafmak.tools;

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
public class ToolController {
	
	@Autowired
	ToolService service;
	
	@Autowired
	ManufacturerService manService;
	
	private final String REDIRECT = "redirect:/products/tool/";
	
	
	@PostMapping("/deleteTool/{id}")
	public String deleteTool(@PathVariable(value = "id")Long id) {
		service.deleteTool(id);
		return "redirect:/tools";
	}
	
	@GetMapping("/tools")
	public String getAllTools(Model model) {
		model.addAttribute("tools", service.tools());
		return "tools";
	}
	
	@GetMapping("/tool/{id}")
	public String getHToolDetails(Model model , @PathVariable(value = "id")Long id) {
		model.addAttribute("tool", service.findToolById(id)) ;
		model.addAttribute("manufacturers", manService.manufacturers()) ;
		return "singleTool";
	}
	
	@PostMapping("/updateToolPrice/{id}")
	public String updateToolPrice(@PathVariable(value = "id")Long id , @Param(value = "price") Double price) {
		service.updatePrice(id , price);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateToolName/{id}")
	public String updateToolName(@PathVariable(value = "id")Long id , @Param(value = "name") String name) {
		service.updateToolName(id , name);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateToolDescription/{id}")
	public String updateToolDescription(@PathVariable(value = "id")Long id , @Param(value = "description") String description) {
		service.updateToolDescription(id , description);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateToolManufacturer/{id}")
	public String updateToolManufactorer(@PathVariable(value = "id")Long id , @Param(value = "manufacturer")String manufacturer) {
		service.updateManufacturer(id , manufacturer);
		return REDIRECT + id;
	}
	
	@PostMapping("/setAvailabilityToTool/{id}")
	public String updateQuantityToTool(@PathVariable(value = "id")Long id ) {
		service.updateToolAvailability(id);
		return REDIRECT + id;
	}
	
	@PostMapping("/updateToolImage/{id}")
	public String updateImageToTool(Model model , @PathVariable(value = "id")Long id, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
			service.updateToolImage(id , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("tool", service.findToolById(id)) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "singleTool";
		}
		
		return REDIRECT + id;
	}


}
