package com.example.erafmak.coatsAndPrimers.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.erafmak.coatsAndPrimers.entity.Coat;
import com.example.erafmak.coatsAndPrimers.repository.CoatRepository;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class CoatService {
	
	@Autowired
	CoatRepository coatRepository;
	
	@Autowired
	ManufacturerService manService;
	
    public Coat newCoat(Coat coat) {
		
		return coatRepository.save(coat);
		
	}
	
	public Coat findCoatById(Long id) {
		return coatRepository.findById(id).get();
	}
	
	public void deleteCoat(Long id) {
		Coat coat = coatRepository.findById(id).get();
		coat.setManufacturer(null);
		coat.setHardeners(null);
		coatRepository.delete(coat);
	}
	
	public List<Coat> coats() {
		return coatRepository.findAll();
	}

	public Coat updatePrice(Long id, Double price) {
		Coat coat = findCoatById(id);
		coat.setPrice(price);
		return coatRepository.save(coat);
		
	}

	public Coat updateCoatName(Long id, String name) {
		Coat coat = findCoatById(id);
		coat.setName(name);
		return coatRepository.save(coat);
		
	}
	
	public Coat updateCoatDescription(Long id, String description) {
		Coat coat = findCoatById(id);
		coat.setDescription(description);
		return coatRepository.save(coat);
		
	}
    
	public Coat updateManufacturer(Long id, String manufacturer) {
		Coat coat = findCoatById(id);
		coat.setManufacturer(manService.findByName(manufacturer));
		return coatRepository.save(coat);
		
	}

	public Coat updateCoatQuantity(Long id, Integer quantity) {
		Coat coat = findCoatById(id);
		coat.setQty(coat.getQty() + quantity);
		return coatRepository.save(coat);
		
	}

}
