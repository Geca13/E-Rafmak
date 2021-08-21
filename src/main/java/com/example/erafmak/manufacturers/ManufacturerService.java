package com.example.erafmak.manufacturers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService {

	@Autowired
	ManufacturerRepository manufacturerRepository;
	
	public List<Manufacturer> manufacturers(){
		return manufacturerRepository.findAll();
	}
	
	public Manufacturer findById(Long id) {
		return manufacturerRepository.findById(id).get();
	}
	
	public Manufacturer findByName(String name) {
		return manufacturerRepository.findByName(name);
	}
}
