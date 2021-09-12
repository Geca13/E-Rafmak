package com.example.erafmak.polish;

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

@Service
public class PolishService {
	
	@Autowired
	PolishRepository polishRepository;
	
	@Autowired
	ManufacturerService manService;
	
    public Polish newPolish(Polish polish, MultipartFile multiPartFile) throws IOException {
		
String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		polish.setImageUrl("/img/polish/" + fileName);
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/polish/";
		Path uploadPath = Paths.get(uploadDir);
		
        try (InputStream inputStream = multiPartFile.getInputStream()) {
			
			
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload");
		}
		
		return polishRepository.save(polish);
		
	}
	
	public Polish findPolishById(Long id) {
		return polishRepository.findById(id).get();
	}
	
	public void deletePolish(Long id) {
		Polish polish = polishRepository.findById(id).get();
		polish.setManufacturer(null);
		polish.setPads(null);
		polishRepository.delete(polish);
	}
	
	public List<Polish> polishes() {
		return polishRepository.findAll();
	}

	public Polish updatePrice(Long id, Double price) {
		Polish polish = findPolishById(id);
		polish.setPrice(price);
		return polishRepository.save(polish);
		
	}

	public Polish updatePolishName(Long id, String name) {
		Polish polish = findPolishById(id);
		polish.setName(name);
		return polishRepository.save(polish);
		
	}
	
	public Polish updatePolishDescription(Long id, String description) {
		Polish polish = findPolishById(id);
		polish.setDescription(description);
		return polishRepository.save(polish);
		
	}
    
	public Polish updateManufacturer(Long id, String manufacturer) {
		Polish polish = findPolishById(id);
		polish.setManufacturer(manService.findByName(manufacturer));
		return polishRepository.save(polish);
		
	}

	public Polish updatePolishQuantity(Long id, Integer quantity) {
		Polish polish = findPolishById(id);
		polish.setQty(polish.getQty() + quantity);
		return polishRepository.save(polish);
		
	}

}
