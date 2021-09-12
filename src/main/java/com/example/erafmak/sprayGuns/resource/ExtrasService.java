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
import com.example.erafmak.sprayGuns.entity.Extras;
import com.example.erafmak.sprayGuns.repository.ExtrasRepository;

@Service
public class ExtrasService {
	
	@Autowired
	ExtrasRepository extrasRepository;
	
	@Autowired
	ManufacturerService manService;
	
    public Extras newExtras(Extras extras , MultipartFile multiPartFile) throws IOException {
		
        String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		extras.setImageUrl("/img/extras/" + fileName);
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/extras/";
		Path uploadPath = Paths.get(uploadDir);
		
        try (InputStream inputStream = multiPartFile.getInputStream()) {
			
			
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload");
		}
		
		return extrasRepository.save(extras);
		
	}
	
	public Extras findExtrasById(Long id) {
		return extrasRepository.findById(id).get();
	}
	
	public void deleteExtras(Long id) {
		Extras extras = extrasRepository.findById(id).get();
		extras.setManufacturer(null);
		extrasRepository.delete(extras);
	}
	
	public List<Extras> extrass() {
		return extrasRepository.findAll();
	}

	public Extras updatePrice(Long id, Double price) {
		Extras extras = findExtrasById(id);
		extras.setPrice(price);
		return extrasRepository.save(extras);
		
	}

	public Extras updateExtrasName(Long id, String name) {
		Extras extras = findExtrasById(id);
		extras.setName(name);
		return extrasRepository.save(extras);
		
	}
	
	public Extras updateExtrasDescription(Long id, String description) {
		Extras extras = findExtrasById(id);
		extras.setDescription(description);
		return extrasRepository.save(extras);
		
	}
    
	public Extras updateManufacturer(Long id, String manufacturer) {
		Extras extras = findExtrasById(id);
		extras.setManufacturer(manService.findByName(manufacturer));
		return extrasRepository.save(extras);
		
	}

	public Extras updateExtrasQuantity(Long id, Integer quantity) {
		Extras extras = findExtrasById(id);
		extras.setQty(extras.getQty() + quantity);
		return extrasRepository.save(extras);
		
	}

}
