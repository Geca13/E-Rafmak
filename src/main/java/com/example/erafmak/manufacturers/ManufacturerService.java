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
		
		manufacturer.setId(manufacturers().size() + 1L);
		setOriginToManufacturer(manufacturer);
		
		return manufacturerRepository.save(manufacturer);
	}

	private void setOriginToManufacturer(Manufacturer manufacturer) {
		if(originRepository.existsByName(manufacturer.getOrigin().getName())) {
			manufacturer.setOrigin(originRepository.findByName(manufacturer.getOrigin().getName()));
		} else {
			Origin origin = new Origin(origins().size()+1L, manufacturer.getOrigin().getName());
			originRepository.save(origin);
			manufacturer.setOrigin(origin);
		}
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
