package com.example.erafmak.sprayGuns.resource;

import java.io.File;
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
		
    	
        uploadExtrasImage(extras, multiPartFile);
        extras.setIsAvailable(true);
		
		return extrasRepository.save(extras);
		
	}

	private void uploadExtrasImage(Extras extras, MultipartFile multiPartFile) throws IOException {
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
	}
	
	public Extras findExtrasById(Long id) {
		return extrasRepository.findById(id).get();
	}
	
	public void deleteExtras(Long id) {
		Extras extras = extrasRepository.findById(id).get();
		deleteImage(extras);
		extras.setManufacturer(null);
		extrasRepository.delete(extras);
	}
	
	private void deleteImage(Extras extras) {
		String storedImage = extras.getImageUrl().substring(extras.getImageUrl().lastIndexOf("/"));
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/extras/";
		
            File file = new File(uploadDir + storedImage);
            if(file.exists()) {
            	file.delete();
            }    
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

	public Extras updateExtrasAvailibility(Long id) {
		Extras extras = findExtrasById(id);
		extras.setIsAvailable(!extras.getIsAvailable());
		return extrasRepository.save(extras);
		
	}

    public Extras updateExtrasImage(Long id, MultipartFile multiPartFile) throws IOException {
		
    	Extras extras = findExtrasById(id);
		deleteImage(extras);
		
		try {
			
			uploadExtrasImage(extras, multiPartFile);
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload, please try again");
		}
		return extrasRepository.save(extras);
		
	}
}
