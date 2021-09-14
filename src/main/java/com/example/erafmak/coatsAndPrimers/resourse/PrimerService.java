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
import com.example.erafmak.coatsAndPrimers.entity.Primer;
import com.example.erafmak.coatsAndPrimers.repository.PrimerRepository;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class PrimerService {
	
	@Autowired
	PrimerRepository primerRepository;
	
	@Autowired
	ManufacturerService manService;
	
    public Primer newPrimer(Primer primer , MultipartFile multiPartFile) throws IOException {
		
        uploadPrimerImage(primer, multiPartFile);
        
		return primerRepository.save(primer);
		
	}

	private void uploadPrimerImage(Primer primer, MultipartFile multiPartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		primer.setImageUrl("/img/primers/" + fileName);
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/primers/";
		Path uploadPath = Paths.get(uploadDir);
		
        try (InputStream inputStream = multiPartFile.getInputStream()) {
			
			
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload");
		}
	}
	
	public Primer findPrimerById(Long id) {
		return primerRepository.findById(id).get();
	}
	
	public void deletePrimer(Long id) {
		Primer primer = primerRepository.findById(id).get();
		deleteImage(primer);
		primer.setManufacturer(null);
		primer.setHardeners(null);
		primerRepository.delete(primer);
	}
	
	private void deleteImage(Primer primer) {
		String storedImage = primer.getImageUrl().substring(primer.getImageUrl().lastIndexOf("/"));
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/primers/";
		
            File file = new File(uploadDir + storedImage);
            if(file.exists()) {
            	file.delete();
            }    
	}
	
	public List<Primer> primers() {
		return primerRepository.findAll();
	}

	public Primer updatePrice(Long id, Double price) {
		Primer primer = findPrimerById(id);
		primer.setPrice(price);
		return primerRepository.save(primer);
		
	}

	public Primer updatePrimerName(Long id, String name) {
		Primer primer = findPrimerById(id);
		primer.setName(name);
		return primerRepository.save(primer);
		
	}
	
	public Primer updatePrimerDescription(Long id, String description) {
		Primer primer = findPrimerById(id);
		primer.setDescription(description);
		return primerRepository.save(primer);
		
	}
    
	public Primer updateManufacturer(Long id, String manufacturer) {
		Primer primer = findPrimerById(id);
		primer.setManufacturer(manService.findByName(manufacturer));
		return primerRepository.save(primer);
		
	}

	public Primer updatePrimerQuantity(Long id, Integer quantity) {
		Primer primer = findPrimerById(id);
		primer.setQty(primer.getQty() + quantity);
		return primerRepository.save(primer);
		
	}
	
    public Primer updatePrimerImage(Long id, MultipartFile multiPartFile) throws IOException {
		
		Primer primer = findPrimerById(id);
		deleteImage(primer);
		
		try {
			
			uploadPrimerImage(primer, multiPartFile);
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload, please try again");
		}
		return primerRepository.save(primer);
		
	}


}
