package com.example.erafmak.safety;

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
public class SafetyService {
	
	@Autowired
	SafetyRepository safetyRepository;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	SizeQuantityRepository sqRepository;
	
	
	    public Safety newSafety(Safety safety, List<Size> sizes, MultipartFile multiPartFile) throws IOException {
	    	
	    	String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
			
			Path currentPath = Paths.get(".");
			Path absolutePath = currentPath.toAbsolutePath();
			
			safety.setImageUrl("/img/safeties/" + fileName);
			
			String uploadDir = absolutePath + "/src/main/resources/static/img/safeties/";
			Path uploadPath = Paths.get(uploadDir);
			
	        try (InputStream inputStream = multiPartFile.getInputStream()) {
				
				
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
				
			} catch (IOException e) {
				throw new IOException("Something went wrong during image upload");
			}
			
			safetyRepository.save(safety);
			
			for (Size size : sizes) {
	    		SizeQuantity quantity = new SizeQuantity();
	    		quantity.setSize(size);
	    		sqRepository.save(quantity);
	    		safety.getSizeQty().add(quantity);			}
			
			return safetyRepository.save(safety);
		}
	    
	    
		
		public Safety findSafetyById(Long id) {
			return safetyRepository.findById(id).get();
		}
		
		public void deleteSafety(Long id) {
			Safety safety = safetyRepository.findById(id).get();
			safety.setManufacturer(null);
			safetyRepository.delete(safety);
		}
		
		public List<Safety> safeties() {
			return safetyRepository.findAll();
		}
		
		public List<SizeQuantity> sizes(){
			return sqRepository.findAll();
		}

		public Safety updatePrice(Long id, Double price) {
			Safety safety = findSafetyById(id);
			safety.setPrice(price);
			return safetyRepository.save(safety);
			
		}

		public Safety updateSafetyName(Long id, String name) {
			Safety safety = findSafetyById(id);
			safety.setName(name);
			return safetyRepository.save(safety);
			
		}
		
		public Safety updateSafetyDescription(Long id, String description) {
			Safety safety = findSafetyById(id);
			safety.setDescription(description);
			return safetyRepository.save(safety);
			
		}
	    
		public Safety updateManufacturer(Long id, String manufacturer) {
			Safety safety = findSafetyById(id);
			safety.setManufacturer(manService.findByName(manufacturer));
			return safetyRepository.save(safety);
			
		}

		public SizeQuantity updateSafetyQuantityPerSize(Long id, Long sid, Integer quantity) {
			SizeQuantity qty = sqRepository.findById(sid).get();
			qty.setQty(qty.getQty() + quantity);
			return sqRepository.save(qty);
			
		}

}
