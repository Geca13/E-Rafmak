package com.example.erafmak.coatsAndPrimers.resourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.erafmak.coatsAndPrimers.entity.Primer;
import com.example.erafmak.coatsAndPrimers.repository.PrimerRepository;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class PrimerService {
	
	@Autowired
	PrimerRepository primerRepository;
	
	@Autowired
	ManufacturerService manService;
	
    public Primer newPrimer(Primer primer) {
		
		return primerRepository.save(primer);
		
	}
	
	public Primer findPrimerById(Long id) {
		return primerRepository.findById(id).get();
	}
	
	public void deletePrimer(Long id) {
		Primer primer = primerRepository.findById(id).get();
		primer.setManufacturer(null);
		primer.setHardeners(null);
		primerRepository.delete(primer);
	}
	
	public List<Primer> primers() {
		return primerRepository.findAll();
	}

	public Primer updatePrice(Long id, Double price) {
		Primer primer = findPrimerById(id);
		primer.setPrice(price);
		return primerRepository.save(primer);
		
	}

	public Primer updatePrimerName(Long id, String name) {
		Primer primer = findPrimerById(id);
		primer.setName(name);
		return primerRepository.save(primer);
		
	}
	
	public Primer updatePrimerDescription(Long id, String description) {
		Primer primer = findPrimerById(id);
		primer.setDescription(description);
		return primerRepository.save(primer);
		
	}
    
	public Primer updateManufacturer(Long id, String manufacturer) {
		Primer primer = findPrimerById(id);
		primer.setManufacturer(manService.findByName(manufacturer));
		return primerRepository.save(primer);
		
	}

	public Primer updatePrimerQuantity(Long id, Integer quantity) {
		Primer primer = findPrimerById(id);
		primer.setQty(primer.getQty() + quantity);
		return primerRepository.save(primer);
		
	}


}
