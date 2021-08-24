package com.example.erafmak.manufacturers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService {

	@Autowired
	ManufacturerRepository manufacturerRepository;
	
	@Autowired
	OriginRepository originRepository;
	
	public List<Manufacturer> manufacturers(){
		return manufacturerRepository.findAll();
	}
	
	public Manufacturer findById(Long id) {
		return manufacturerRepository.findById(id).get();
	}
	
	public Manufacturer findByName(String name) {
		return manufacturerRepository.findByName(name);
	}
	
	public Manufacturer createNewManufacturer(Manufacturer manufacturer) {
		return manufacturerRepository.save(manufacturer);
	}
	
	public Manufacturer updateManufacturerName(Long id, String name) {
		Manufacturer manufacturer = findById(id);
		manufacturer.setName(name);
		return manufacturerRepository.save(manufacturer);
	}
	
	public Manufacturer updateManufacturerOrigin(Long id, String origin) {
		Origin findOrigin = originRepository.findByName(origin);
		Manufacturer manufacturer = findById(id);
		manufacturer.setOrigin(findOrigin);
		return manufacturerRepository.save(manufacturer);
	}
	
	public void deleteManufacturer(Long id) {
		Manufacturer manufacturer = findById(id);
		manufacturer.setOrigin(null);
		manufacturerRepository.delete(manufacturer);
	}
	
	public List<Origin> origins() {
		return originRepository.findAll();
	}
}
