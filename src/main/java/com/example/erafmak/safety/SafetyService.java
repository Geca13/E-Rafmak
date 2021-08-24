package com.example.erafmak.safety;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class SafetyService {
	
	@Autowired
	SafetyRepository safetyRepository;
	
	@Autowired
	ManufacturerService manService;
	
	
	    public Safety newSafety(Safety safety) {
			
			return safetyRepository.save(safety);
			
		}
		
		public Safety findSafetyById(Long id) {
			return safetyRepository.findById(id).get();
		}
		
		public void deleteSafety(Long id) {
			Safety safety = safetyRepository.findById(id).get();
			safety.setManufacturer(null);
			safetyRepository.delete(safety);
		}
		
		public List<Safety> safeties() {
			return safetyRepository.findAll();
		}

		public Safety updatePrice(Long id, Double price) {
			Safety safety = findSafetyById(id);
			safety.setPrice(price);
			return safetyRepository.save(safety);
			
		}

		public Safety updateSafetyName(Long id, String name) {
			Safety safety = findSafetyById(id);
			safety.setName(name);
			return safetyRepository.save(safety);
			
		}
		
		public Safety updateSafetyDescription(Long id, String description) {
			Safety safety = findSafetyById(id);
			safety.setDescription(description);
			return safetyRepository.save(safety);
			
		}
	    
		public Safety updateManufacturer(Long id, String manufacturer) {
			Safety safety = findSafetyById(id);
			safety.setManufacturer(manService.findByName(manufacturer));
			return safetyRepository.save(safety);
			
		}

		public Safety updateSafetyQuantity(Long id, Integer quantity) {
			Safety safety = findSafetyById(id);
			safety.setQty(safety.getQty() + quantity);
			return safetyRepository.save(safety);
			
		}

}
