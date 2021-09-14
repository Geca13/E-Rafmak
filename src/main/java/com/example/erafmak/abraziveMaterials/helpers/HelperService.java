package com.example.erafmak.abraziveMaterials.helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.erafmak.coatsAndPrimers.entity.Coat;
import com.example.erafmak.manufacturers.Manufacturer;
import com.example.erafmak.manufacturers.ManufacturerService;

@Service
public class HelperService {
	
	@Autowired
	HelperRepository helperRepository;
	
	@Autowired
	ManufacturerService manService;

	public Helper newHelper(Helper helper, MultipartFile multiPartFile) throws IOException {
		
		
        uploadHelperImage(helper, multiPartFile);
		
		return helperRepository.save(helper);
		
	}

	private void uploadHelperImage(Helper helper, MultipartFile multiPartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		helper.setImageUrl("/img/blocks/" + fileName);
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/blocks/";
		Path uploadPath = Paths.get(uploadDir);
		
        try (InputStream inputStream = multiPartFile.getInputStream()) {
			
			
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload");
		}
	}
	
	public Helper findHelperById(Long id) {
		return helperRepository.findById(id).get();
	}
	
	public void deleteHelper(Long id) {
		Helper helper = helperRepository.findById(id).get();
		deleteImage(helper);
		helper.setManufacturer(null);
		helperRepository.delete(helper);
	}
	
	private void deleteImage(Helper helper) {
		String storedImage = helper.getImageUrl().substring(helper.getImageUrl().lastIndexOf("/"));
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/blocks/";
		
            File file = new File(uploadDir + storedImage);
            if(file.exists()) {
            	file.delete();
            }    
	}
	
	public List<Helper> helpers() {
		return helperRepository.findAll();
	}

	public Helper updatePrice(Long id, Double price) {
		Helper helper = findHelperById(id);
		helper.setPrice(price);
		return helperRepository.save(helper);
		
	}

	public Helper updateDescription(Long id, String description) {
		Helper helper = findHelperById(id);
		helper.setDescription(description);
		return helperRepository.save(helper);
		
	}
    
	public Helper updateManufacturer(Long id, String manufacturer) {
		Helper helper = findHelperById(id);
		helper.setManufacturer(manService.findByName(manufacturer));
		return helperRepository.save(helper);
		
	}

	public Helper updateHelperQuantity(Long id, Integer quantity) {
		Helper helper = findHelperById(id);
		helper.setQty(helper.getQty() + quantity);
		return helperRepository.save(helper);
		
	}

	public Helper updateName(Long id, String name) {
		Helper helper = findHelperById(id);
		helper.setName(name);
		return helperRepository.save(helper);
		
	}
	
    public Helper updateHelperImage(Long id, MultipartFile multiPartFile) throws IOException {
		
		Helper helper = findHelperById(id);
		deleteImage(helper);
		
		try {
			uploadHelperImage(helper, multiPartFile);
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload, please try again");
		}
		return helperRepository.save(helper);
		
	}
	

}
