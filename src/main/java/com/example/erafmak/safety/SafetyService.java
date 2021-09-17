package com.example.erafmak.safety;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	
	    public Safety newSafety(Safety safety,  MultipartFile multiPartFile) throws IOException {
	    	
	    	String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
			
			uploadSafetyImage(safety, multiPartFile, fileName);
			safety.setId(safetyRepository.count()+1L);
			safety.setIsAvailable(true);
			safetyRepository.save(safety);
			
			return safetyRepository.save(safety);
		}



		private void uploadSafetyImage(Safety safety, MultipartFile multiPartFile, String fileName) throws IOException {
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
		
		public List<Size> sizes (Long id){
			List<Size> allSizes = new ArrayList<>(Arrays.asList(Size.values()));
			List<Size> sizes = new ArrayList<>();
			for (Size size : allSizes) {
					if(!safetyRepository.existsByIdAndSizeQty_Size(id, size)) {
						sizes.add(size);
					}
				}
				return sizes;
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

		public SizeQuantity updateSafetyAvailabilityPerSize(Long sid) {
			SizeQuantity available = sqRepository.findById(sid).get();
			available.setIsAvailable(!available.getIsAvailable());
			return sqRepository.save(available);
			
		}

		public void addSizesToSafety(Long id, List<Size> allSizes) {
			Safety safety = findSafetyById(id);
			
			for (Size size : allSizes) {
				SizeQuantity sizeQty = new SizeQuantity();
				sizeQty.setId(sqRepository.count()+1L);
				sizeQty.setIsAvailable(true);
				sizeQty.setSize(size);
				sqRepository.save(sizeQty);
				safety.getSizeQty().add(sizeQty);
				safetyRepository.save(safety);
			}
			
		}

		public void removeSizeFromSafety(Long sid) {
			Safety safety = safetyRepository.findBySizeQty_Id(sid);
			safety.getSizeQty().remove(sqRepository.findById(sid).get());
			safetyRepository.save(safety);
			
			sqRepository.delete(sqRepository.findById(sid).get());
			
		}
		
		
		

}
