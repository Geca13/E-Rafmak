package com.example.erafmak.polish;

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

@Service
public class PadsService {
	
	@Autowired
	PadsRepository padsRepository;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	PolishRepository polishRepository;
	
	    public Pads newPads(Pads pads, MultipartFile multiPartFile) throws IOException  {
			
	    	uploadPadsImage(pads, multiPartFile);
	        
			return padsRepository.save(pads);
			
		}

		private void uploadPadsImage(Pads pads, MultipartFile multiPartFile) throws IOException {
			String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
			
			Path currentPath = Paths.get(".");
			Path absolutePath = currentPath.toAbsolutePath();
			
			pads.setImageUrl("/img/pads/" + fileName);
			
			String uploadDir = absolutePath + "/src/main/resources/static/img/pads/";
			Path uploadPath = Paths.get(uploadDir);
			
	        try (InputStream inputStream = multiPartFile.getInputStream()) {
				
				
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
				
			} catch (IOException e) {
				throw new IOException("Something went wrong during image upload");
			}
		}
		
		public Pads findPadsById(Long id) {
			return padsRepository.findById(id).get();
		}
		
		public void deletePads(Long id) {
			Pads pads = padsRepository.findById(id).get();
			deleteImage(pads);
			pads.setManufacturer(null);
			removePads(id, pads);
			padsRepository.delete(pads);
		}
		
		private void deleteImage(Pads pads) {
			String storedImage = pads.getImageUrl().substring(pads.getImageUrl().lastIndexOf("/"));
			Path currentPath = Paths.get(".");
			Path absolutePath = currentPath.toAbsolutePath();
			
			String uploadDir = absolutePath + "/src/main/resources/static/img/pads/";
			
	            File file = new File(uploadDir + storedImage);
	            if(file.exists()) {
	            	file.delete();
	            }    
		}

		private void removePads(Long id, Pads pads) {
			List<Polish> polishes = polishRepository.findAllByPads_Id(id);
			if(!polishes.isEmpty()) {
			for (Polish polish : polishes) {
				polish.pads.remove(pads);
				polishRepository.save(polish);
			}
			}
		}
		
		public List<Pads> pads() {
			return padsRepository.findAll();
		}

		public Pads updatePrice(Long id, Double price) {
			Pads pads = findPadsById(id);
			pads.setPrice(price);
			return padsRepository.save(pads);
			
		}

		public Pads updatePadsName(Long id, String name) {
			Pads pads = findPadsById(id);
			pads.setName(name);
			return padsRepository.save(pads);
			
		}
		
		public Pads updatePadsDescription(Long id, String description) {
			Pads pads = findPadsById(id);
			pads.setDescription(description);
			return padsRepository.save(pads);
			
		}
	    
		public Pads updateManufacturer(Long id, String manufacturer) {
			Pads pads = findPadsById(id);
			pads.setManufacturer(manService.findByName(manufacturer));
			return padsRepository.save(pads);
			
		}

		public Pads updatePadsQuantity(Long id, Integer quantity) {
			Pads pads = findPadsById(id);
			pads.setQty(pads.getQty() + quantity);
			return padsRepository.save(pads);
			
		}
		
		public Pads updatePadsImage(Long id, MultipartFile multiPartFile) throws IOException {
			
			Pads pads = findPadsById(id);
			deleteImage(pads);
			
			try {
				
				uploadPadsImage(pads, multiPartFile);
			} catch (IOException e) {
				throw new IOException("Something went wrong during image upload, please try again");
			}
			return padsRepository.save(pads);
			
		}

}
