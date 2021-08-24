package com.example.erafmak.sprayGuns.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.erafmak.manufacturers.ManufacturerService;
import com.example.erafmak.sprayGuns.entity.Extras;
import com.example.erafmak.sprayGuns.repository.ExtrasRepository;

@Service
public class ExtrasService {
	
	@Autowired
	ExtrasRepository extrasRepository;
	
	@Autowired
	ManufacturerService manService;
	
    public Extras newExtras(Extras extras) {
		
		return extrasRepository.save(extras);
		
	}
	
	public Extras findExtrasById(Long id) {
		return extrasRepository.findById(id).get();
	}
	
	public void deleteExtras(Long id) {
		Extras extras = extrasRepository.findById(id).get();
		extras.setManufacturer(null);
		extrasRepository.delete(extras);
	}
	
	public List<Extras> extrass() {
		return extrasRepository.findAll();
	}

	public Extras updatePrice(Long id, Double price) {
		Extras extras = findExtrasById(id);
		extras.setPrice(price);
		return extrasRepository.save(extras);
		
	}

	public Extras updateExtrasName(Long id, String name) {
		Extras extras = findExtrasById(id);
		extras.setName(name);
		return extrasRepository.save(extras);
		
	}
	
	public Extras updateExtrasDescription(Long id, String description) {
		Extras extras = findExtrasById(id);
		extras.setDescription(description);
		return extrasRepository.save(extras);
		
	}
    
	public Extras updateManufacturer(Long id, String manufacturer) {
		Extras extras = findExtrasById(id);
		extras.setManufacturer(manService.findByName(manufacturer));
		return extrasRepository.save(extras);
		
	}

	public Extras updateExtrasQuantity(Long id, Integer quantity) {
		Extras extras = findExtrasById(id);
		extras.setQty(extras.getQty() + quantity);
		return extrasRepository.save(extras);
		
	}

}
