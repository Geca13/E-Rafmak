package com.example.erafmak.abraziveMaterials.sander;

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
public class SanderService {
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	SanderRepository sanderRepository;
	
	@Autowired
	GranulationQtyRepository gkRepository;
	
	public Sander newSander(Sander sander, MultipartFile multiPartFile) throws IOException{
		
        String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		sander.setImageUrl("/img/sanders/" + fileName);
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/sanders/";
		Path uploadPath = Paths.get(uploadDir);
		
        try (InputStream inputStream = multiPartFile.getInputStream()) {
			
			
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload");
		}
		
		return sanderRepository.save(sander);
		
	}
	
	public Sander findSanderById(Long id) {
		return sanderRepository.findById(id).get();
	}
	
	public void deleteSander(Long id) {
		Sander sander = sanderRepository.findById(id).get();
		sander.setManufacturer(null);
		sanderRepository.delete(sander);
	}
	
	public List<Sander> sanders() {
		return sanderRepository.findAll();
	}

	public GranulationQty updatePrice(Long gid, Double price) {
		GranulationQty sander = gkRepository.findById(gid).get();
		sander.setPrice(price);
		return gkRepository.save(sander);
		
	}

	public Sander updateSanderName(Long id, String name) {
		Sander sander = findSanderById(id);
		sander.setName(name);
		return sanderRepository.save(sander);
		
	}
    
	public Sander updateManufacturer(Long id, String manufacturer) {
		Sander sander = findSanderById(id);
		sander.setManufacturer(manService.findByName(manufacturer));
		return sanderRepository.save(sander);
		
	}

	public GranulationQty updateSanderQuantity(Long gid, Integer quantity) {
		GranulationQty qty = gkRepository.findById(gid).get();
		qty.setQty(qty.getQty() + quantity);
		return gkRepository.save(qty);
		
	}

	public List<Sander> discs() {
		List<Sander> discs = sanderRepository.findByNameContaining("Disk");
		return discs;
	}

	public List<Sander> rolls() {
		List<Sander> rolls = sanderRepository.findByNameContaining("Roll");
		return rolls;
	}

	public List<Sander> blocks() {
		List<Sander> blocks = sanderRepository.findByNameContaining("Block");
		return blocks;
	}

	public List<Sander> softs() {
		List<Sander> softs = sanderRepository.findByNameContaining("Soft");
		return softs;
	}

	public List<Sander> wets() {
		List<Sander> wets = sanderRepository.findByNameContaining("WPF");
		return wets;
	}

}
