package com.example.erafmak.sprayGuns.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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
	NozzleService nozzleService;
	
	@Autowired
	ManufacturerService manService;
	
    public SprayGun newSprayGun(SprayGun gun, MultipartFile multiPartFile) throws IOException {
    	
        uploadSprayGunImage(gun, multiPartFile);
		gun.setIsAvailable(true);
		
		return sprayGunRepository.save(gun);
	}

	private void uploadSprayGunImage(SprayGun gun, MultipartFile multiPartFile) throws IOException {
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
	}
	
	public SprayGun findSprayGunById(Long id) {
		return sprayGunRepository.findById(id).get();
	}
	
	public void deleteSprayGun(Long id) {
		SprayGun gun = sprayGunRepository.findById(id).get();
		deleteImage(gun);
		gun.setManufacturer(null);
		gun.setNozzles(null);
		sprayGunRepository.delete(gun);
	}
	
	private void deleteImage(SprayGun gun) {
		String storedImage = gun.getImageUrl().substring(gun.getImageUrl().lastIndexOf("/"));
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/guns/";
		
            File file = new File(uploadDir + storedImage);
            if(file.exists()) {
            	file.delete();
            }    
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
		gun.setDescription(description);
		return sprayGunRepository.save(gun);
	}
	
	public SprayGun updateManufacturer(Long id, String manufacturer) {
		
		SprayGun gun = findSprayGunById(id);
		gun.setManufacturer(manService.findByName(manufacturer));
		return sprayGunRepository.save(gun);
	}

	public SprayGun updateSprayGunAvailability(Long id) {
	
		SprayGun gun = findSprayGunById(id);
		gun.setIsAvailable(!gun.getIsAvailable());
		return sprayGunRepository.save(gun);
	}
	
    public SprayGun updateSprayGunImage(Long id, MultipartFile multiPartFile) throws IOException {
		
    	SprayGun gun = findSprayGunById(id);
		deleteImage(gun);
		
		try {
			
			uploadSprayGunImage(gun, multiPartFile);
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload, please try again");
		}
		return sprayGunRepository.save(gun);
	}

	public void disconectNozzleFromGun(Long id, Long nid) {
	
		SprayGun gun = findSprayGunById(id);
		gun.getNozzles().remove(nozzleService.findNozzleById(nid));
		sprayGunRepository.save(gun);
	}

	public List<SprayGun> reducedGuns(Long id) {
	
		List<SprayGun> reduced = new ArrayList<>();
		List<SprayGun> sprayGuns = guns();
		for (SprayGun gun : sprayGuns) {
			if(!sprayGunRepository.existsByNameAndNozzles_Id(gun.getName(),id)) {
				reduced.add(gun);
			}
		}
		return reduced;
	}
}
