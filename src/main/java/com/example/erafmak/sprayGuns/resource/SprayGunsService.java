package com.example.erafmak.sprayGuns.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.erafmak.manufacturers.ManufacturerService;
import com.example.erafmak.sprayGuns.entity.SprayGun;
import com.example.erafmak.sprayGuns.repository.SprayGunRepository;

@Service
public class SprayGunsService {
	
	@Autowired
	SprayGunRepository sprayGunRepository;
	
	@Autowired
	ManufacturerService manService;
	
    public SprayGun newSprayGun(SprayGun gun) {
		
		return sprayGunRepository.save(gun);
		
	}
	
	public SprayGun findSprayGunById(Long id) {
		return sprayGunRepository.findById(id).get();
	}
	
	public void deleteSprayGun(Long id) {
		SprayGun gun = sprayGunRepository.findById(id).get();
		gun.setManufacturer(null);
		gun.setNozzles(null);
		sprayGunRepository.delete(gun);
	}
	
	public List<SprayGun> guns() {
		return sprayGunRepository.findAll();
	}

	public SprayGun updatePrice(Long id, Double price) {
		SprayGun gun = findSprayGunById(id);
		gun.setPrice(price);
		return sprayGunRepository.save(gun);
		
	}

	public SprayGun updateSprayGunName(Long id, String name) {
		SprayGun gun = findSprayGunById(id);
		gun.setName(name);
		return sprayGunRepository.save(gun);
		
	}
	
	public SprayGun updateManufacturer(Long id, String manufacturer) {
		SprayGun gun = findSprayGunById(id);
		gun.setManufacturer(manService.findByName(manufacturer));
		return sprayGunRepository.save(gun);
		
	}

	public SprayGun updateSprayGunQuantity(Long id, Integer quantity) {
		SprayGun gun = findSprayGunById(id);
		gun.setQty(gun.getQty() + quantity);
		return sprayGunRepository.save(gun);
		
	}
	

}
