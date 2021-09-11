package com.example.erafmak.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.erafmak.manufacturers.ManufacturerService;

@Controller
@RequestMapping("/products")
public class ToolController {
	
	@Autowired
	ToolService service;
	
	@Autowired
	ManufacturerService manService;
	
	private final String REDIRECT = "redirect:/products/tool/";
	
	@GetMapping("/newTool")
	public String toolModel(Model model) {
		model.addAttribute("tool", new Tool());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addTool";
	}
	
	@PostMapping("/newTool")
	public String createTool(@ModelAttribute(value = "hardener")Tool tool) {
		service.newTool(tool);
	return REDIRECT + tool.getId();
		
	}
	
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
	
	@PostMapping("/addQuantityToTool/{id}")
	public String updateQuantityToTool(@PathVariable(value = "id")Long id , @Param(value = "quantity")Integer quantity) {
		service.updateToolQuantity(id , quantity);
		return REDIRECT + id;
	}

}
