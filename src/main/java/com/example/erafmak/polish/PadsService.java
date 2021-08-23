package com.example.erafmak.polish;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class PadsService {
	
	@Autowired
	PadsRepository padsRepository;
	
	@Autowired
	ManufacturerService manService;
	
	    public Pads newPads(Pads pads) {
			
			return padsRepository.save(pads);
			
		}
		
		public Pads findPadsById(Long id) {
			return padsRepository.findById(id).get();
		}
		
		public void deletePads(Long id) {
			Pads pads = padsRepository.findById(id).get();
			pads.setManufacturer(null);
			padsRepository.delete(pads);
		}
		
		public List<Pads> pads() {
			return padsRepository.findAll();
		}

		public Pads updatePrice(Long id, Double price) {
			Pads pads = findPadsById(id);
			pads.setPrice(price);
			return padsRepository.save(pads);
			
		}

		public Pads updatePadsName(Long id, String name) {
			Pads pads = findPadsById(id);
			pads.setName(name);
			return padsRepository.save(pads);
			
		}
		
		public Pads updatePadsDescription(Long id, String description) {
			Pads pads = findPadsById(id);
			pads.setDescription(description);
			return padsRepository.save(pads);
			
		}
	    
		public Pads updateManufacturer(Long id, String manufacturer) {
			Pads pads = findPadsById(id);
			pads.setManufacturer(manService.findByName(manufacturer));
			return padsRepository.save(pads);
			
		}

		public Pads updatePadsQuantity(Long id, Integer quantity) {
			Pads pads = findPadsById(id);
			pads.setQty(pads.getQty() + quantity);
			return padsRepository.save(pads);
			
		}

}
