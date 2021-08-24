package com.example.erafmak.tools;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class ToolService {
	
	@Autowired
	ToolRepository toolRepository;
	
	@Autowired
	ManufacturerService manService;
	
    public Tool newTool(Tool tool) {
		
		return toolRepository.save(tool);
		
	}
	
	public Tool findToolById(Long id) {
		return toolRepository.findById(id).get();
	}
	
	public void deleteTool(Long id) {
		Tool tool = toolRepository.findById(id).get();
		tool.setManufacturer(null);
		toolRepository.delete(tool);
	}
	
	public List<Tool> tools() {
		return toolRepository.findAll();
	}

	public Tool updatePrice(Long id, Double price) {
		Tool tool = findToolById(id);
		tool.setPrice(price);
		return toolRepository.save(tool);
		
	}

	public Tool updateToolName(Long id, String name) {
		Tool tool = findToolById(id);
		tool.setName(name);
		return toolRepository.save(tool);
		
	}
	
	public Tool updateToolDescription(Long id, String description) {
		Tool tool = findToolById(id);
		tool.setDescription(description);
		return toolRepository.save(tool);
		
	}
    
	public Tool updateManufacturer(Long id, String manufacturer) {
		Tool tool = findToolById(id);
		tool.setManufacturer(manService.findByName(manufacturer));
		return toolRepository.save(tool);
		
	}

	public Tool updateToolQuantity(Long id, Integer quantity) {
		Tool tool = findToolById(id);
		tool.setQty(tool.getQty() + quantity);
		return toolRepository.save(tool);
		
	}

}
