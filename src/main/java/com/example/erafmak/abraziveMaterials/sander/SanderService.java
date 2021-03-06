package com.example.erafmak.abraziveMaterials.sander;

import java.io.File;
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
import com.example.erafmak.abraziveMaterials.helpers.Helper;
import com.example.erafmak.abraziveMaterials.helpers.HelperRepository;
import com.example.erafmak.manufacturers.ManufacturerService;


@Service
public class SanderService {
	
	@Autowired
	ManufacturerService manService;
	
	@Autowired
	SanderRepository sanderRepository;
	
	@Autowired
	HelperRepository helperRepository;
	
	@Autowired
	GranulationQtyRepository gqRepository;
	
	public Sander newSander(Sander sander, MultipartFile multiPartFile) throws IOException{
		
        uploadImageToSander(sander, multiPartFile);
       return sanderRepository.save(sander);
	}

	private void uploadImageToSander(Sander sander, MultipartFile multiPartFile) throws IOException {
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
	}
	
	public Sander findSanderById(Long id) {
		return sanderRepository.findById(id).get();
	}
	
	public void deleteSander(Long id) {
		Sander sander = sanderRepository.findById(id).get();
		deleteImage(sander);
		List<Helper> helpers = helperRepository.findAllBySanders_Id(id);
		if(!helpers.isEmpty()) {
		for (Helper helper : helpers) {
			helper.getSanders().remove(sander);
			helperRepository.save(helper);
		 }
		}
		sander.setManufacturer(null);
		sanderRepository.delete(sander);
	}
	
	private void deleteImage(Sander sander) {
		String storedImage = sander.getImageUrl().substring(sander.getImageUrl().lastIndexOf("/"));
		Path currentPath = Paths.get(".");
		Path absolutePath = currentPath.toAbsolutePath();
		String uploadDir = absolutePath + "/src/main/resources/static/img/sanders/";
		
            File file = new File(uploadDir + storedImage);
            if(file.exists()) {
            	file.delete();
            }
	}

	public List<Sander> sanders() {
		return sanderRepository.findAll();
	}

	public GranulationQty updatePrice(Long gid, Double price) {
		GranulationQty sander = gqRepository.findById(gid).get();
		sander.setPrice(price);
		return gqRepository.save(sander);
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

	public GranulationQty updateSanderAvailability(Long gid) {
		GranulationQty gran = gqRepository.findById(gid).get();
		gran.setIsAvailable(!gran.getIsAvailable());
		return gqRepository.save(gran);
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
	
	public List<Granulation> granulations (Long id){
		List<Granulation> allGranulations = new ArrayList<>(Arrays.asList(Granulation.values()));
		List<Granulation> granulations = new ArrayList<>();
		for (Granulation granulation : allGranulations) {
				if(!sanderRepository.existsByIdAndGranulationQty_Granulation(id, granulation)) {
					granulations.add(granulation);
				}
			}
			return granulations;
	}

	public void addGranulationToSander(Long id, List<Granulation> allGranulations) {
		Sander sander = findSanderById(id);
		for (Granulation granulation : allGranulations) {
			GranulationQty newGranQty = new GranulationQty();
			newGranQty.setId(gqRepository.count()+1L);
			newGranQty.setIsAvailable(true);
			newGranQty.setPrice(0.00);
			newGranQty.setGranulation(granulation);
			gqRepository.save(newGranQty);
			sander.getGranulationQty().add(newGranQty);
			sanderRepository.save(sander);
		}
	}

	public void removeGranulationFromSander(Long gid) {
		Sander sander = sanderRepository.findByGranulationQty_Id(gid);
		sander.getGranulationQty().remove(gqRepository.findById(gid).get());
		sanderRepository.save(sander);
		gqRepository.delete(gqRepository.findById(gid).get());
	}
	
    public Sander updateSanderImage(Long id, MultipartFile multiPartFile) throws IOException {
		
		Sander sander = findSanderById(id);
		deleteImage(sander);
		
		try {
			uploadImageToSander(sander, multiPartFile);
		} catch (IOException e) {
			throw new IOException("Something went wrong during image upload, please try again");
		}
		return sanderRepository.save(sander);
	}

    public void updateManufacturerCondition(Long id, Condition condition) {
    	Sander sander = findSanderById(id);
    	sander.setCondition(condition);
    	sanderRepository.save(sander);
    }

    public void updateManufacturerDimension(Long id, Dimension dimension) {
    	Sander sander = findSanderById(id);
	    sander.setDimension(dimension);
	    sanderRepository.save(sander);
    }

    public void updateManufacturerType(Long id, Type type) {
    	Sander sander = findSanderById(id);
	    sander.setType(type);
	    sanderRepository.save(sander);
    }

	public void updateManufacturerPieces(Long id, Integer pieces) {
		Sander sander = findSanderById(id);
	    sander.setPiecesInPack(pieces);
	    sanderRepository.save(sander);
	}


	public void connectSanderToHelpers(Long id, List<Helper> allHelpers) {
		Sander sander = findSanderById(id);
	
		for (Helper helper : allHelpers) {
			helper.getSanders().add(sander);
			helperRepository.save(helper);
		}
	}
	
	public void checkIfSanderIsAvailable(Long id) {
		Sander sander = findSanderById(id);
	if (!sanderRepository.existsByIdAndGranulationQty_IsAvailable(id, true)) {
		sander.setIsAvailable(false);
		sanderRepository.save(sander);
	    }
	else {
		sander.setIsAvailable(true);
		sanderRepository.save(sander);
	  }
	}
    
	
}
