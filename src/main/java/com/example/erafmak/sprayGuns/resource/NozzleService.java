package com.example.erafmak.sprayGuns.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.erafmak.manufacturers.ManufacturerService;
import com.example.erafmak.sprayGuns.entity.Nozzle;
import com.example.erafmak.sprayGuns.repository.NozzleRepository;

@Service
public class NozzleService {
	
	@Autowired
	NozzleRepository nozzleRepository;
	
	@Autowired
	ManufacturerService manService;
	
	public Nozzle newNozzle(Nozzle nozzle) {
		
		return nozzleRepository.save(nozzle);
		
	}
	
	public Nozzle findNozzleById(Long id) {
		return nozzleRepository.findById(id).get();
	}
	
	public void deleteNozzle(Long id) {
		Nozzle nozzle = nozzleRepository.findById(id).get();
		nozzle.setManufacturer(null);
		nozzleRepository.delete(nozzle);
	}
	
	public List<Nozzle> nozzles() {
		return nozzleRepository.findAll();
	}

	public Nozzle updatePrice(Long id, Double price) {
		Nozzle nozzle = findNozzleById(id);
		nozzle.setPrice(price);
		return nozzleRepository.save(nozzle);
		
	}

	public Nozzle updateNozzleName(Long id, String name) {
		Nozzle nozzle = findNozzleById(id);
		nozzle.setDescription(name);
		return nozzleRepository.save(nozzle);
		
	}
	
	public Nozzle updateNozzleDescription(Long id, String description) {
		Nozzle nozzle = findNozzleById(id);
		nozzle.setDescription(description);
		return nozzleRepository.save(nozzle);
		
	}
    
	public Nozzle updateManufacturer(Long id, String manufacturer) {
		Nozzle nozzle = findNozzleById(id);
		nozzle.setManufacturer(manService.findByName(manufacturer));
		return nozzleRepository.save(nozzle);
		
	}

	public Nozzle updateNozzleQuantity(Long id, Integer quantity) {
		Nozzle nozzle = findNozzleById(id);
		nozzle.setQty(nozzle.getQty() + quantity);
		return nozzleRepository.save(nozzle);
		
	}

}
