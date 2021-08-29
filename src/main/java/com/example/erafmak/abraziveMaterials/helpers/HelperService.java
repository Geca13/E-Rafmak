package com.example.erafmak.abraziveMaterials.helpers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.erafmak.manufacturers.Manufacturer;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class HelperService {
	
	@Autowired
	HelperRepository helperRepository;
	
	@Autowired
	ManufacturerService manService;

	public Helper newHelper(Helper helper) {
		
		return helperRepository.save(helper);
		
	}
	
	public Helper findHelperById(Long id) {
		return helperRepository.findById(id).get();
	}
	
	public void deleteHelper(Long id) {
		Helper helper = helperRepository.findById(id).get();
		helper.setManufacturer(null);
		helperRepository.delete(helper);
	}
	
	public List<Helper> helpers() {
		return helperRepository.findAll();
	}

	public Helper updatePrice(Long id, Double price) {
		Helper helper = findHelperById(id);
		helper.setPrice(price);
		return helperRepository.save(helper);
		
	}

	public Helper updateDescription(Long id, String description) {
		Helper helper = findHelperById(id);
		helper.setDescription(description);
		return helperRepository.save(helper);
		
	}
    
	public Helper updateManufacturer(Long id, String manufacturer) {
		Helper helper = findHelperById(id);
		helper.setManufacturer(manService.findByName(manufacturer));
		return helperRepository.save(helper);
		
	}

	public Helper updateHelperQuantity(Long id, Integer quantity) {
		Helper helper = findHelperById(id);
		helper.setQty(helper.getQty() + quantity);
		return helperRepository.save(helper);
		
	}

	public Helper updateName(Long id, String name) {
		Helper helper = findHelperById(id);
		helper.setName(name);
		return helperRepository.save(helper);
		
	}
	

}
