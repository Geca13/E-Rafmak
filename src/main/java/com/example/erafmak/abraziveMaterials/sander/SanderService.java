package com.example.erafmak.abraziveMaterials.sander;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class SanderService {
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	SanderRepository sanderRepository;
	
	@Autowired
	GranulationQtyRepository gkRepository;
	
	public Sander newSander(Sander sander) {
		
		return sanderRepository.save(sander);
		
	}
	
	public Sander findSanderById(Long id) {
		return sanderRepository.findById(id).get();
	}
	
	public void deleteSander(Long id) {
		Sander sander = sanderRepository.findById(id).get();
		sander.setManufacturer(null);
		sanderRepository.delete(sander);
	}
	
	public List<Sander> sanders() {
		return sanderRepository.findAll();
	}

	public GranulationQty updatePrice(Long gid, Double price) {
		GranulationQty sander = gkRepository.findById(gid).get();
		sander.setPrice(price);
		return gkRepository.save(sander);
		
	}

	public Sander updateSanderName(Long id, String name) {
		Sander sander = findSanderById(id);
		sander.setName(name);
		return sanderRepository.save(sander);
		
	}
    
	public Sander updateManufacturer(Long id, String manufacturer) {
		Sander sander = findSanderById(id);
		sander.setManufacturer(manService.findByName(manufacturer));
		return sanderRepository.save(sander);
		
	}

	public GranulationQty updateSanderQuantity(Long gid, Integer quantity) {
		GranulationQty qty = gkRepository.findById(gid).get();
		qty.setQty(qty.getQty() + quantity);
		return gkRepository.save(qty);
		
	}

	public List<Sander> discs() {
		List<Sander> discs = sanderRepository.findByNameContaining("Disk");
		return discs;
	}

	public List<Sander> rolls() {
		List<Sander> rolls = sanderRepository.findByNameContaining("Roll");
		return rolls;
	}

	public List<Sander> blocks() {
		List<Sander> blocks = sanderRepository.findByNameContaining("Block");
		return blocks;
	}

	public List<Sander> softs() {
		List<Sander> softs = sanderRepository.findByNameContaining("Soft");
		return softs;
	}

	public List<Sander> wets() {
		List<Sander> wets = sanderRepository.findByNameContaining("WPF");
		return wets;
	}

}
