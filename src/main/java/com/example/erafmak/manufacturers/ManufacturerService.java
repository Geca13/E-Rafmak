package com.example.erafmak.manufacturers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ManufacturerService {

	@Autowired
	ManufacturerRepository manufacturerRepository;
	
	private static String url = "https://restcountries.eu/rest/v2/all";

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	OriginRepository originRepository;
	
	public List<Origin> getCountriesList(){
		Origin[] countries = restTemplate.getForObject(url, Origin[].class);
		
		return Arrays.asList(countries);
	}
	
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
		
		setOriginToManufacturer(manufacturer);
		
		return manufacturerRepository.save(manufacturer);
	}

	private Origin setOriginToManufacturer(Manufacturer manufacturer) {
		Origin origin = new Origin();
		
		if(originRepository.existsByName(manufacturer.getOrigin().getName())) {
			manufacturer.setOrigin(originRepository.findByName(manufacturer.getOrigin().getName()));
		} else {
			
			origin.setName(manufacturer.getOrigin().getName());
			originRepository.save(origin);
			manufacturer.setOrigin(origin);
		}
		return origin;
	}
	
	
	public List<Origin> origins() {
		return originRepository.findAll();
	}

	public Manufacturer updateManufacturer(Long id, Manufacturer manufacturer) {
		Manufacturer forUpdate = findById(id);
		forUpdate.setEmail(manufacturer.getEmail());
		forUpdate.setName(manufacturer.getName());
		
		if(originRepository.existsByName(manufacturer.getOrigin().getName())) {
			forUpdate.setOrigin(originRepository.findByName(manufacturer.getOrigin().getName()));
		}
		return manufacturerRepository.save(forUpdate);
	}

}

