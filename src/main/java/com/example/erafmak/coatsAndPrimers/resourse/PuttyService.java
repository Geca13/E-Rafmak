package com.example.erafmak.coatsAndPrimers.resourse;

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
import com.example.erafmak.coatsAndPrimers.entity.Putty;
import com.example.erafmak.coatsAndPrimers.repository.PuttyRepository;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class PuttyService {
	
	@Autowired
	PuttyRepository puttyRepository;
	
	@Autowired
	ManufacturerService manService;
	
	    public  Putty newPutty(Putty putty , MultipartFile multiPartFile) throws IOException {
	    	
	    	uploadPuttyImage(putty, multiPartFile);
			
			return puttyRepository.save(putty);
			
		}

		private void uploadPuttyImage(Putty putty, MultipartFile multiPartFile) throws IOException {
			String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
			
			Path currentPath = Paths.get(".");
			Path absolutePath = currentPath.toAbsolutePath();
			
			putty.setImageUrl("/img/putties/" + fileName);
			
			String uploadDir = absolutePath + "/src/main/resources/static/img/putties/";
			Path uploadPath = Paths.get(uploadDir);
			
	        try (InputStream inputStream = multiPartFile.getInputStream()) {
				
				
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
				
			} catch (IOException e) {
				throw new IOException("Something went wrong during image upload");
			}
		}
		
		public Putty findPuttyById(Long id) {
			return puttyRepository.findById(id).get();
		}
		
		public void deletePutty(Long id) {
			Putty putty = puttyRepository.findById(id).get();
			deleteImage(putty);
			putty.setManufacturer(null);
			puttyRepository.delete(putty);
		}
		
		private void deleteImage(Putty putty) {
			String storedImage = putty.getImageUrl().substring(putty.getImageUrl().lastIndexOf("/"));
			Path currentPath = Paths.get(".");
			Path absolutePath = currentPath.toAbsolutePath();
			
			String uploadDir = absolutePath + "/src/main/resources/static/img/putties/";
			
	            File file = new File(uploadDir + storedImage);
	            if(file.exists()) {
	            	file.delete();
	            }    
		}
		
		public List<Putty> putties() {
			return puttyRepository.findAll();
		}

		public Putty updatePrice(Long id, Double price) {
			Putty putty = findPuttyById(id);
			putty.setPrice(price);
			return puttyRepository.save(putty);
			
		}

		public Putty updatePuttyName(Long id, String name) {
			Putty putty = findPuttyById(id);
			putty.setName(name);
			return puttyRepository.save(putty);
			
		}
		
		public Putty updatePuttyDescription(Long id, String description) {
			Putty putty = findPuttyById(id);
			putty.setDescription(description);
			return puttyRepository.save(putty);
			
		}
	    
		public Putty updateManufacturer(Long id, String manufacturer) {
			Putty putty = findPuttyById(id);
			putty.setManufacturer(manService.findByName(manufacturer));
			return puttyRepository.save(putty);
			
		}

		public Putty updatePuttyQuantity(Long id, Integer quantity) {
			Putty putty = findPuttyById(id);
			putty.setQty(putty.getQty() + quantity);
			return puttyRepository.save(putty);
			
		}
		
		public Putty updatePuttyImage(Long id, MultipartFile multiPartFile) throws IOException {
			
			Putty putty = findPuttyById(id);
			deleteImage(putty);
			
			try {
				
				uploadPuttyImage(putty, multiPartFile);
			} catch (IOException e) {
				throw new IOException("Something went wrong during image upload, please try again");
			}
			return puttyRepository.save(putty);
			
		}

}
