package com.example.erafmak.polish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.erafmak.coatsAndPrimers.entity.Coat;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class PolishService {
	
	@Autowired
	PolishRepository polishRepository;
	
	@Autowired
	ManufacturerService manService;
	
    public Polish newPolish(Polish polish) {
		
		return polishRepository.save(polish);
		
	}
	
	public Polish findPolishById(Long id) {
		return polishRepository.findById(id).get();
	}
	
	public void deletePolish(Long id) {
		Polish polish = polishRepository.findById(id).get();
		polish.setManufacturer(null);
		polish.setPads(null);
		polishRepository.delete(polish);
	}
	
	public List<Polish> polishes() {
		return polishRepository.findAll();
	}

	public Polish updatePrice(Long id, Double price) {
		Polish polish = findPolishById(id);
		polish.setPrice(price);
		return polishRepository.save(polish);
		
	}

	public Polish updatePolishName(Long id, String name) {
		Polish polish = findPolishById(id);
		polish.setName(name);
		return polishRepository.save(polish);
		
	}
	
	public Polish updatePolishDescription(Long id, String description) {
		Polish polish = findPolishById(id);
		polish.setDescription(description);
		return polishRepository.save(polish);
		
	}
    
	public Polish updateManufacturer(Long id, String manufacturer) {
		Polish polish = findPolishById(id);
		polish.setManufacturer(manService.findByName(manufacturer));
		return polishRepository.save(polish);
		
	}

	public Polish updatePolishQuantity(Long id, Integer quantity) {
		Polish polish = findPolishById(id);
		polish.setQty(polish.getQty() + quantity);
		return polishRepository.save(polish);
		
	}

}
