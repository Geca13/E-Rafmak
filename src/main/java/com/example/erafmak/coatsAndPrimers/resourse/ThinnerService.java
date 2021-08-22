package com.example.erafmak.coatsAndPrimers.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.erafmak.coatsAndPrimers.entity.Thinner;
import com.example.erafmak.coatsAndPrimers.repository.ThinnerRepository;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class ThinnerService {
	
	@Autowired
	ThinnerRepository thinnerRepository;
	
	@Autowired
	ManufacturerService manService;
	
	    public Thinner newThinner(Thinner thinner) {
			
			return thinnerRepository.save(thinner);
			
		}
		
		public Thinner findThinnerById(Long id) {
			return thinnerRepository.findById(id).get();
		}
		
		public void deleteThinner(Long id) {
			Thinner thinner = thinnerRepository.findById(id).get();
			thinner.setManufacturer(null);
			thinnerRepository.delete(thinner);
		}
		
		public List<Thinner> thinners() {
			return thinnerRepository.findAll();
		}

		public Thinner updatePrice(Long id, Double price) {
			Thinner thinner = findThinnerById(id);
			thinner.setPrice(price);
			return thinnerRepository.save(thinner);
			
		}

		public Thinner updateThinnerName(Long id, String name) {
			Thinner thinner = findThinnerById(id);
			thinner.setName(name);
			return thinnerRepository.save(thinner);
			
		}
		
		public Thinner updateThinnerDescription(Long id, String description) {
			Thinner thinner = findThinnerById(id);
			thinner.setDescription(description);
			return thinnerRepository.save(thinner);
			
		}
	    
		public Thinner updateManufacturer(Long id, String manufacturer) {
			Thinner thinner = findThinnerById(id);
			thinner.setManufacturer(manService.findByName(manufacturer));
			return thinnerRepository.save(thinner);
			
		}

		public Thinner updateThinnerQuantity(Long id, Integer quantity) {
			Thinner thinner = findThinnerById(id);
			thinner.setQty(thinner.getQty() + quantity);
			return thinnerRepository.save(thinner);
			
		}
}
