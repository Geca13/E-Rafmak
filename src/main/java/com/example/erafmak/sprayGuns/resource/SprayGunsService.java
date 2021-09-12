package com.example.erafmak.sprayGuns.resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.erafmak.manufacturers.ManufacturerService;
import com.example.erafmak.sprayGuns.entity.SprayGun;
import com.example.erafmak.sprayGuns.repository.SprayGunRepository;

@Service
public class SprayGunsService {
	
	@Autowired
	SprayGunRepository sprayGunRepository;
	
	@Autowired
	ManufacturerService manService;
	
    public SprayGun newSprayGun(SprayGun gun, MultipartFile multiPartFile) throws IOException {
    	
String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		gun.setImageUrl("/img/guns/" + fileName);
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/guns/";
		Path uploadPath = Paths.get(uploadDir);
		
        try (InputStream inputStream = multiPartFile.getInputStream()) {
			
			
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload");
		}
		
		
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
	
	public SprayGun updateSprayGunDescription(Long id, String description) {
		SprayGun gun = findSprayGunById(id);
		gun.setName(description);
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
