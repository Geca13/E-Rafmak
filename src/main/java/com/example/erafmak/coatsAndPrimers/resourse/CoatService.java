package com.example.erafmak.coatsAndPrimers.resourse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.erafmak.coatsAndPrimers.entity.Coat;
import com.example.erafmak.coatsAndPrimers.entity.Weigth;
import com.example.erafmak.coatsAndPrimers.repository.CoatRepository;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class CoatService {
	
	@Autowired
	CoatRepository coatRepository;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	HardenerService hardenerService;
	
    public Coat newCoat(Coat coat , MultipartFile multiPartFile) throws IOException {
    	
		uploadImage(coat, multiPartFile);
		coat.setIsAvailable(true);
		return coatRepository.save(coat);
	}

	private void uploadImage(Coat coat, MultipartFile multiPartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		coat.setImageUrl("/img/coats/" + fileName);
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/coats/";
		Path uploadPath = Paths.get(uploadDir);
		
        try (InputStream inputStream = multiPartFile.getInputStream()) {
			
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload, please try again");
		}
	}
	
	public Coat findCoatById(Long id) {
		return coatRepository.findById(id).get();
	}
	
	public void deleteCoat(Long id) {
		
		Coat coat = coatRepository.findById(id).get();
		deleteImage(coat);
		coat.setManufacturer(null);
		coat.setHardeners(null);
		
		coatRepository.delete(coat);
	}

	private void deleteImage(Coat coat) {
		String storedImage = coat.getImageUrl().substring(coat.getImageUrl().lastIndexOf("/"));
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/coats/";
		
            File file = new File(uploadDir + storedImage);
            if(file.exists()) {
            	file.delete();
            }    
	}
	
	public List<Coat> coats() {
		return coatRepository.findAll();
	}
	
	public List<Coat> reducedCoats(Long id) {
		
		List<Coat> reduced = new ArrayList<>();
		List<Coat> coats = coats();
		for (Coat coat : coats) {
			if(!coatRepository.existsByNameAndHardeners_Id(coat.getName(),id)) {
				reduced.add(coat);
			}
		}
		return reduced;
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

	public Coat updateCoatAvailability(Long id) {
	
		Coat coat = findCoatById(id);
		coat.setIsAvailable(!coat.getIsAvailable());
		return coatRepository.save(coat);
	}

	public Coat updateCoatImage(Long id, MultipartFile multiPartFile) throws IOException {
		
		Coat coat = findCoatById(id);
		deleteImage(coat);
		
		try {
			uploadImage(coat, multiPartFile);
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload, please try again");
		}
		return coatRepository.save(coat);
	}

	public void updateCoatWeight(Long id, Weigth weigth) {
	
		Coat coat = findCoatById(id);
		coat.setWeigth(weigth);
		coatRepository.save(coat);
	}

	public void disconectHardenerFromCoat(Long id, Long hid) {
	
		Coat coat = findCoatById(id);
		coat.getHardeners().remove(hardenerService.findHardenerById(hid));
		coatRepository.save(coat);
	}

}
