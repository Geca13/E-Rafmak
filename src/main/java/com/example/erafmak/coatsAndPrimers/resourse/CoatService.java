package com.example.erafmak.coatsAndPrimers.resourse;

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

import com.example.erafmak.coatsAndPrimers.entity.Coat;
import com.example.erafmak.coatsAndPrimers.repository.CoatRepository;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class CoatService {
	
	@Autowired
	CoatRepository coatRepository;
	
	@Autowired
	ManufacturerService manService;
	
    public Coat newCoat(Coat coat, MultipartFile multiPartFile) throws IOException {
    Long id = coats().size() +1l;
    	
		coat.setId(id);
		String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		coat.setImageUrl(fileName);
		Coat saved = coatRepository.save(coat);
		String uploadDir = "./coat-image/"+ saved.getId();
		Path uploadPath = Paths.get(uploadDir);
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try (InputStream inputStream = multiPartFile.getInputStream()) {
			
			
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload");
		}
		
		
		return coatRepository.save(coat);
		
	}
	
	public Coat findCoatById(Long id) {
		return coatRepository.findById(id).get();
	}
	
	public void deleteCoat(Long id) {
		Coat coat = coatRepository.findById(id).get();
		coat.setManufacturer(null);
		coat.setHardeners(null);
		coatRepository.delete(coat);
	}
	
	public List<Coat> coats() {
		return coatRepository.findAll();
	}

	public Coat updatePrice(Long id, Double price) {
		Coat coat = findCoatById(id);
		coat.setPrice(price);
		return coatRepository.save(coat);
		
	}

	public Coat updateCoatName(Long id, String name) {
		Coat coat = findCoatById(id);
		coat.setName(name);
		return coatRepository.save(coat);
		
	}
	
	public Coat updateCoatDescription(Long id, String description) {
		Coat coat = findCoatById(id);
		coat.setDescription(description);
		return coatRepository.save(coat);
		
	}
    
	public Coat updateManufacturer(Long id, String manufacturer) {
		Coat coat = findCoatById(id);
		coat.setManufacturer(manService.findByName(manufacturer));
		return coatRepository.save(coat);
		
	}

	public Coat updateCoatQuantity(Long id, Integer quantity) {
		Coat coat = findCoatById(id);
		coat.setQty(coat.getQty() + quantity);
		return coatRepository.save(coat);
		
	}

}
