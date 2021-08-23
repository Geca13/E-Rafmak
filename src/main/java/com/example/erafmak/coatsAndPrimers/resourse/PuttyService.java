package com.example.erafmak.coatsAndPrimers.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.erafmak.coatsAndPrimers.entity.Putty;
import com.example.erafmak.coatsAndPrimers.repository.PuttyRepository;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class PuttyService {
	
	@Autowired
	PuttyRepository puttyRepository;
	
	@Autowired
	ManufacturerService manService;
	
	    public  Putty newPutty(Putty putty) {
			
			return puttyRepository.save(putty);
			
		}
		
		public Putty findPuttyById(Long id) {
			return puttyRepository.findById(id).get();
		}
		
		public void deletePutty(Long id) {
			Putty putty = puttyRepository.findById(id).get();
			putty.setManufacturer(null);
			puttyRepository.delete(putty);
		}
		
		public List<Putty> thinners() {
			return puttyRepository.findAll();
		}

		public Putty updatePrice(Long id, Double price) {
			Putty putty = findPuttyById(id);
			putty.setPrice(price);
			return puttyRepository.save(putty);
			
		}

		public Putty updatePuttyName(Long id, String name) {
			Putty putty = findPuttyById(id);
			putty.setName(name);
			return puttyRepository.save(putty);
			
		}
		
		public Putty updatePuttyDescription(Long id, String description) {
			Putty putty = findPuttyById(id);
			putty.setDescription(description);
			return puttyRepository.save(putty);
			
		}
	    
		public Putty updateManufacturer(Long id, String manufacturer) {
			Putty putty = findPuttyById(id);
			putty.setManufacturer(manService.findByName(manufacturer));
			return puttyRepository.save(putty);
			
		}

		public Putty updatePuttyQuantity(Long id, Integer quantity) {
			Putty putty = findPuttyById(id);
			putty.setQty(putty.getQty() + quantity);
			return puttyRepository.save(putty);
			
		}

}
