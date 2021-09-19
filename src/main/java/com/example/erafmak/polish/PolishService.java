package com.example.erafmak.polish;

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
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class PolishService {
	
	@Autowired
	PolishRepository polishRepository;
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	PadsService padsService;
	
    public Polish newPolish(Polish polish, MultipartFile multiPartFile) throws IOException {
		Long id = polishes().size() + 1L;
		polish.setId(id);
        uploadPolishImage(polish, multiPartFile);
		polish.setIsAvailable(true);
		
		return polishRepository.save(polish);
		
	}

	private void uploadPolishImage(Polish polish, MultipartFile multiPartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		polish.setImageUrl("/img/polish/" + fileName);
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/polish/";
		Path uploadPath = Paths.get(uploadDir);
		
        try (InputStream inputStream = multiPartFile.getInputStream()) {
			
			
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload");
		}
	}
	
	public Polish findPolishById(Long id) {
		return polishRepository.findById(id).get();
	}
	
	public void deletePolish(Long id) {
		Polish polish = polishRepository.findById(id).get();
		deleteImage(polish);
		polish.setManufacturer(null);
		polish.setPads(null);
		polishRepository.delete(polish);
	}
	
	private void deleteImage(Polish polish) {
		String storedImage = polish.getImageUrl().substring(polish.getImageUrl().lastIndexOf("/"));
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/coats/";
		
            File file = new File(uploadDir + storedImage);
            if(file.exists()) {
            	file.delete();
            }    
	}
	
	public List<Polish> polishes() {
		return polishRepository.findAll();
	}

	public Polish updatePrice(Long id, Double price) {
		Polish polish = findPolishById(id);
		polish.setPrice(price);
		return polishRepository.save(polish);
		
	}

	public Polish updatePolishName(Long id, String name) {
		Polish polish = findPolishById(id);
		polish.setName(name);
		return polishRepository.save(polish);
		
	}
	
	public Polish updatePolishDescription(Long id, String description) {
		Polish polish = findPolishById(id);
		polish.setDescription(description);
		return polishRepository.save(polish);
		
	}
    
	public Polish updateManufacturer(Long id, String manufacturer) {
		Polish polish = findPolishById(id);
		polish.setManufacturer(manService.findByName(manufacturer));
		return polishRepository.save(polish);
		
	}

	public Polish updatePolishAvailability(Long id) {
		Polish polish = findPolishById(id);
		polish.setIsAvailable(!polish.getIsAvailable());
		return polishRepository.save(polish);
		
	}
	
    public Polish updatePolishImage(Long id, MultipartFile multiPartFile) throws IOException {
		
    	Polish polish = findPolishById(id);
		deleteImage(polish);
		
		try {
			
			uploadPolishImage(polish, multiPartFile);
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload, please try again");
		}
		return polishRepository.save(polish);
		
	}

	public void disconectPadsFromPolish(Long id, Long pid) {
		
		Polish polish = findPolishById(id);
		polish.getPads().remove(padsService.findPadsById(pid));
		polishRepository.save(polish);
		
	}

	public List<Polish> reducedPolishes(Long id) {
	
		List<Polish> reduced = new ArrayList<>();
		List<Polish> polishes = polishes();
		for (Polish polish : polishes) {
			if(!polishRepository.existsByNameAndPads_Id(polish.getName(),id)) {
				reduced.add(polish);
			}
		}
		return reduced;
	}

}
