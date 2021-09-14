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
import com.example.erafmak.sprayGuns.entity.Nozzle;
import com.example.erafmak.sprayGuns.repository.NozzleRepository;

@Service
public class NozzleService {
	
	@Autowired
	NozzleRepository nozzleRepository;
	
	@Autowired
	ManufacturerService manService;
	
	public Nozzle newNozzle(Nozzle nozzle, MultipartFile multiPartFile) throws IOException {
		
		
       uploadNozzleImage(nozzle, multiPartFile);
		
		return nozzleRepository.save(nozzle);
		
	}

	private void uploadNozzleImage(Nozzle nozzle, MultipartFile multiPartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
			
			Path currentPath = Paths.get(".");
			Path absolutePath = currentPath.toAbsolutePath();
			
			nozzle.setImageUrl("/img/nozzles/" + fileName);
			
			String uploadDir = absolutePath + "/src/main/resources/static/img/nozzles/";
			Path uploadPath = Paths.get(uploadDir);
			
		    try (InputStream inputStream = multiPartFile.getInputStream()) {
				
				
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
				
			} catch (IOException e) {
				throw new IOException("Something went wrong during image upload");
			}
	}
	
	public Nozzle findNozzleById(Long id) {
		return nozzleRepository.findById(id).get();
	}
	
	public void deleteNozzle(Long id) {
		Nozzle nozzle = nozzleRepository.findById(id).get();
		deleteImage(nozzle);
		nozzle.setManufacturer(null);
		nozzleRepository.delete(nozzle);
	}
	
	private void deleteImage(Nozzle nozzle) {
		String storedImage = nozzle.getImageUrl().substring(nozzle.getImageUrl().lastIndexOf("/"));
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/nozzles/";
		
            File file = new File(uploadDir + storedImage);
            if(file.exists()) {
            	file.delete();
            }    
	}
	
	public List<Nozzle> nozzles() {
		return nozzleRepository.findAll();
	}

	public Nozzle updatePrice(Long id, Double price) {
		Nozzle nozzle = findNozzleById(id);
		nozzle.setPrice(price);
		return nozzleRepository.save(nozzle);
		
	}

	public Nozzle updateNozzleName(Long id, String name) {
		Nozzle nozzle = findNozzleById(id);
		nozzle.setDescription(name);
		return nozzleRepository.save(nozzle);
		
	}
	
	public Nozzle updateNozzleDescription(Long id, String description) {
		Nozzle nozzle = findNozzleById(id);
		nozzle.setDescription(description);
		return nozzleRepository.save(nozzle);
		
	}
    
	public Nozzle updateManufacturer(Long id, String manufacturer) {
		Nozzle nozzle = findNozzleById(id);
		nozzle.setManufacturer(manService.findByName(manufacturer));
		return nozzleRepository.save(nozzle);
		
	}

	public Nozzle updateNozzleQuantity(Long id, Integer quantity) {
		Nozzle nozzle = findNozzleById(id);
		nozzle.setQty(nozzle.getQty() + quantity);
		return nozzleRepository.save(nozzle);
		
	}

    public Nozzle updateNozzleImage(Long id, MultipartFile multiPartFile) throws IOException {
		
    	Nozzle nozzle = findNozzleById(id);
		deleteImage(nozzle);
		
		try {
			
			uploadNozzleImage(nozzle, multiPartFile);
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload, please try again");
		}
		return nozzleRepository.save(nozzle);
		
	}
}
