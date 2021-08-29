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
	
	@Autowired
	PolishRepository polishRepository;
	
	    public Pads newPads(Pads pads) {
			
			return padsRepository.save(pads);
			
		}
		
		public Pads findPadsById(Long id) {
			return padsRepository.findById(id).get();
		}
		
		public void deletePads(Long id) {
			Pads pads = padsRepository.findById(id).get();
			pads.setManufacturer(null);
			removePads(id, pads);
			padsRepository.delete(pads);
		}

		private void removePads(Long id, Pads pads) {
			List<Polish> polishes = polishRepository.findAllByPads_Id(id);
			if(!polishes.isEmpty()) {
			for (Polish polish : polishes) {
				polish.pads.remove(pads);
				polishRepository.save(polish);
			}
			}
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
