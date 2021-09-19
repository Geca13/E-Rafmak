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

import com.example.erafmak.coatsAndPrimers.entity.Coat;
import com.example.erafmak.coatsAndPrimers.entity.Hardener;
import com.example.erafmak.coatsAndPrimers.entity.Primer;
import com.example.erafmak.coatsAndPrimers.entity.Weigth;
import com.example.erafmak.coatsAndPrimers.repository.CoatRepository;
import com.example.erafmak.coatsAndPrimers.repository.HardenerRepository;
import com.example.erafmak.coatsAndPrimers.repository.PrimerRepository;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class HardenerService {
	
	@Autowired
	HardenerRepository hardenerRepository;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	PrimerRepository primerRepository;
	
	@Autowired
	CoatRepository coatRepository;
	
    public void newHardener(Hardener hardener ,MultipartFile multiPartFile) throws IOException {
		
        uploadHardenerImage(hardener, multiPartFile);
    	hardener.setIsAvailable(true);
		hardenerRepository.save(hardener);
		
	}

	private void uploadHardenerImage(Hardener hardener, MultipartFile multiPartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		hardener.setImageUrl("/img/hardeners/" + fileName);
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/hardeners/";
		Path uploadPath = Paths.get(uploadDir);
		
        try (InputStream inputStream = multiPartFile.getInputStream()) {
			
			
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload");
		}
	}
	
	public Hardener findHardenerById(Long id) {
		return hardenerRepository.findById(id).get();
	}
	
	public void deleteHardener(Long id) {
		Hardener hardener = hardenerRepository.findById(id).get();
		deleteImage(hardener);
		hardener.setManufacturer(null);
		
		removeFromPrimers(id, hardener);
		removeFromCoats(id, hardener);
		
		hardenerRepository.delete(hardener);
	}
	
	
	private void deleteImage(Hardener hardener) {
		String storedImage = hardener.getImageUrl().substring(hardener.getImageUrl().lastIndexOf("/"));
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/hardeners/";
		
            File file = new File(uploadDir + storedImage);
            if(file.exists()) {
            	file.delete();
            }    
	}

	private void removeFromCoats(Long id, Hardener hardener) {
		List<Coat> coats = coatRepository.findAllByHardeners_Id(id);
		for (Coat coat : coats) {
			if(!coats.isEmpty()) {
				coat.getHardeners().remove(hardener);
			}
			coatRepository.save(coat);
		}
	}

	private void removeFromPrimers(Long id, Hardener hardener) {
		List<Primer> primers = primerRepository.findAllByHardeners_Id(id);
		if(!primers.isEmpty()) {
		for (Primer primer : primers) {
			primer.getHardeners().remove(hardener);
			primerRepository.save(primer);
		  }
		
		}
	}
	
	public List<Hardener> hardeners() {
		return hardenerRepository.findAll();
	}

	public Hardener updatePrice(Long id, Double price) {
		Hardener hardener = findHardenerById(id);
		hardener.setPrice(price);
		return hardenerRepository.save(hardener);
		
	}

	public Hardener updateHardenerName(Long id, String name) {
		Hardener hardener = findHardenerById(id);
		hardener.setName(name);
		return hardenerRepository.save(hardener);
		
	}
	
	public Hardener updateHardenerDescription(Long id, String description) {
		Hardener hardener = findHardenerById(id);
		hardener.setDescription(description);
		return hardenerRepository.save(hardener);
		
	}
    
	public Hardener updateManufacturer(Long id, String manufacturer) {
		Hardener hardener = findHardenerById(id);
		hardener.setManufacturer(manService.findByName(manufacturer));
		return hardenerRepository.save(hardener);
		
	}

	public Hardener updateHardenerAvailability(Long id) {
		Hardener hardener = findHardenerById(id);
		hardener.setIsAvailable(!hardener.getIsAvailable());
		return hardenerRepository.save(hardener);
		
	}
	
    public Hardener updateHardenerImage(Long id, MultipartFile multiPartFile) throws IOException {
		
    	Hardener hardener = findHardenerById(id);
		deleteImage(hardener);
		
		try {
			
			uploadHardenerImage(hardener, multiPartFile);
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload, please try again");
		}
		return hardenerRepository.save(hardener);
		
	}

	public void connectHardenerToCoats(Long id, List<Coat> allCoats) {
		
		Hardener hardener = findHardenerById(id);
		for (Coat coat : allCoats) {
			coat.getHardeners().add(hardener);
			coatRepository.save(coat);
		}
	}

	public void connectHardenerToPrimer(Long id, List<Primer> allPrimers) {
		
		Hardener hardener = findHardenerById(id);
		for (Primer primer : allPrimers) {
			primer.getHardeners().add(hardener);
			primerRepository.save(primer);
		}
	}

	public void updateHardenerWeight(Long id, Weigth weigth) {
		Hardener hardener = findHardenerById(id);
		hardener.setWeigth(weigth);
		hardenerRepository.save(hardener);
	}


}
