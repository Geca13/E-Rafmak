package com.example.erafmak.tools;

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
public class ToolService {
	
	@Autowired
	ToolRepository toolRepository;
	
	@Autowired
	ManufacturerService manService;
	
    public Tool newTool(Tool tool, MultipartFile multiPartFile) throws IOException {
    	
        uploadToolImage(tool, multiPartFile);
		
		
		return toolRepository.save(tool);
		
	}

	private void uploadToolImage(Tool tool, MultipartFile multiPartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multiPartFile.getOriginalFilename());
		
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		tool.setImageUrl("/img/tools/" + fileName);
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/tools/";
		Path uploadPath = Paths.get(uploadDir);
		
        try (InputStream inputStream = multiPartFile.getInputStream()) {
			
			
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
			
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload");
		}
	}
	
	public Tool findToolById(Long id) {
		return toolRepository.findById(id).get();
	}
	
	public void deleteTool(Long id) {
		Tool tool = toolRepository.findById(id).get();
		deleteImage(tool);
		tool.setManufacturer(null);
		toolRepository.delete(tool);
	}
	
	private void deleteImage(Tool tool) {
		String storedImage = tool.getImageUrl().substring(tool.getImageUrl().lastIndexOf("/"));
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		
		String uploadDir = absolutePath + "/src/main/resources/static/img/tools/";
		
            File file = new File(uploadDir + storedImage);
            if(file.exists()) {
            	file.delete();
            }    
	}
	
	public List<Tool> tools() {
		return toolRepository.findAll();
	}

	public Tool updatePrice(Long id, Double price) {
		Tool tool = findToolById(id);
		tool.setPrice(price);
		return toolRepository.save(tool);
		
	}

	public Tool updateToolName(Long id, String name) {
		Tool tool = findToolById(id);
		tool.setName(name);
		return toolRepository.save(tool);
		
	}
	
	public Tool updateToolDescription(Long id, String description) {
		Tool tool = findToolById(id);
		tool.setDescription(description);
		return toolRepository.save(tool);
		
	}
    
	public Tool updateManufacturer(Long id, String manufacturer) {
		Tool tool = findToolById(id);
		tool.setManufacturer(manService.findByName(manufacturer));
		return toolRepository.save(tool);
		
	}

	public Tool updateToolQuantity(Long id, Integer quantity) {
		Tool tool = findToolById(id);
		tool.setQty(tool.getQty() + quantity);
		return toolRepository.save(tool);
		
	}
	
public Tool updateToolImage(Long id, MultipartFile multiPartFile) throws IOException {
		
	Tool tool = findToolById(id);
		deleteImage(tool);
		
		try {
			
			uploadToolImage(tool, multiPartFile);
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload, please try again");
		}
		return toolRepository.save(tool);
		
	}

}
