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
import com.example.erafmak.coatsAndPrimers.entity.Thinner;
import com.example.erafmak.coatsAndPrimers.repository.ThinnerRepository;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class ThinnerService {
	
	@Autowired
	ThinnerRepository thinnerRepository;
	
	@Autowired
	ManufacturerService manService;
	
	    public Thinner newThinner(Thinner thinner, MultipartFile multiPartFile) throws IOException {
			
	    	uploadThinnerImage(thinner, multiPartFile);
	        thinner.setIsAvailable(true);
			return thinnerRepository.save(thinner);
			
		}

		private void uploadThinnerImage(Thinner thinner, MultipartFile multiPartFile) throws IOException {
			String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
			
			Path currentPath = Paths.get(".");
			Path absolutePath = currentPath.toAbsolutePath();
			
			thinner.setImageUrl("/img/thinners/" + fileName);
			
			String uploadDir = absolutePath + "/src/main/resources/static/img/thinners/";
			Path uploadPath = Paths.get(uploadDir);
			
	        try (InputStream inputStream = multiPartFile.getInputStream()) {
				
				
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
				
			} catch (IOException e) {
				throw new IOException("Something went wrong during image upload");
			}
		}
		
		public Thinner findThinnerById(Long id) {
			return thinnerRepository.findById(id).get();
		}
		
		public void deleteThinner(Long id) {
			Thinner thinner = thinnerRepository.findById(id).get();
			deleteImage( thinner);
			thinner.setManufacturer(null);
			thinnerRepository.delete(thinner);
		}
		
		private void deleteImage(Thinner thinner) {
			String storedImage = thinner.getImageUrl().substring(thinner.getImageUrl().lastIndexOf("/"));
			Path currentPath = Paths.get(".");
			Path absolutePath = currentPath.toAbsolutePath();
			
			String uploadDir = absolutePath + "/src/main/resources/static/img/thinners/";
			
	            File file = new File(uploadDir + storedImage);
	            if(file.exists()) {
	            	file.delete();
	            }    
		}
		
		public List<Thinner> thinners() {
			return thinnerRepository.findAll();
		}

		public Thinner updatePrice(Long id, Double price) {
			Thinner thinner = findThinnerById(id);
			thinner.setPrice(price);
			return thinnerRepository.save(thinner);
			
		}

		public Thinner updateThinnerName(Long id, String name) {
			Thinner thinner = findThinnerById(id);
			thinner.setName(name);
			return thinnerRepository.save(thinner);
			
		}
		
		public Thinner updateThinnerDescription(Long id, String description) {
			Thinner thinner = findThinnerById(id);
			thinner.setDescription(description);
			return thinnerRepository.save(thinner);
			
		}
	    
		public Thinner updateManufacturer(Long id, String manufacturer) {
			Thinner thinner = findThinnerById(id);
			thinner.setManufacturer(manService.findByName(manufacturer));
			return thinnerRepository.save(thinner);
			
		}

		public Thinner updateThinnerAvailability(Long id) {
			Thinner thinner = findThinnerById(id);
			thinner.setIsAvailable(!thinner.getIsAvailable());
			return thinnerRepository.save(thinner);
			
		}
		
		public Thinner updateThinnerImage(Long id, MultipartFile multiPartFile) throws IOException {
			
			Thinner thinner = findThinnerById(id);
			deleteImage(thinner);
			
			try {
				
				uploadThinnerImage(thinner, multiPartFile);
			} catch (IOException e) {
				throw new IOException("Something went wrong during image upload, please try again");
			}
			return thinnerRepository.save(thinner);
			
		}
}
