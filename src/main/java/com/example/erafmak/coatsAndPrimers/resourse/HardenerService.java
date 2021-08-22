package com.example.erafmak.coatsAndPrimers.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.erafmak.coatsAndPrimers.entity.Hardener;
import com.example.erafmak.coatsAndPrimers.repository.HardenerRepository;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class HardenerService {
	
	@Autowired
	HardenerRepository hardenerRepository;
	
	@Autowired
	ManufacturerService manService;
	
    public Hardener newHardener(Hardener hardener) {
		
		return hardenerRepository.save(hardener);
		
	}
	
	public Hardener findHardenerById(Long id) {
		return hardenerRepository.findById(id).get();
	}
	
	public void deleteHardener(Long id) {
		Hardener sander = hardenerRepository.findById(id).get();
		sander.setManufacturer(null);
		hardenerRepository.delete(sander);
	}
	
	public List<Hardener> hardeners() {
		return hardenerRepository.findAll();
	}

	public Hardener updatePrice(Long id, Double price) {
		Hardener hardener = findHardenerById(id);
		hardener.setPrice(price);
		return hardenerRepository.save(hardener);
		
	}

	public Hardener updateHardenerName(Long id, String name) {
		Hardener hardener = findHardenerById(id);
		hardener.setName(name);
		return hardenerRepository.save(hardener);
		
	}
	
	public Hardener updateHardenerDescription(Long id, String description) {
		Hardener hardener = findHardenerById(id);
		hardener.setDescription(description);
		return hardenerRepository.save(hardener);
		
	}
    
	public Hardener updateManufacturer(Long id, String manufacturer) {
		Hardener hardener = findHardenerById(id);
		hardener.setManufacturer(manService.findByName(manufacturer));
		return hardenerRepository.save(hardener);
		
	}

	public Hardener updateHardenerQuantity(Long id, Integer quantity) {
		Hardener hardener = findHardenerById(id);
		hardener.setQty(hardener.getQty() + quantity);
		return hardenerRepository.save(hardener);
		
	}

}
