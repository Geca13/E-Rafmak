package com.example.erafmak.administration;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.erafmak.abraziveMaterials.helpers.Helper;
import com.example.erafmak.abraziveMaterials.helpers.HelperService;
import com.example.erafmak.abraziveMaterials.sander.Sander;
import com.example.erafmak.abraziveMaterials.sander.SanderService;
import com.example.erafmak.coatsAndPrimers.entity.Coat;
import com.example.erafmak.coatsAndPrimers.entity.Hardener;
import com.example.erafmak.coatsAndPrimers.entity.Primer;
import com.example.erafmak.coatsAndPrimers.entity.Putty;
import com.example.erafmak.coatsAndPrimers.entity.Thinner;
import com.example.erafmak.coatsAndPrimers.resourse.CoatService;
import com.example.erafmak.coatsAndPrimers.resourse.HardenerService;
import com.example.erafmak.coatsAndPrimers.resourse.PrimerService;
import com.example.erafmak.coatsAndPrimers.resourse.PuttyService;
import com.example.erafmak.coatsAndPrimers.resourse.ThinnerService;
import com.example.erafmak.manufacturers.ManufacturerService;
import com.example.erafmak.polish.Pads;
import com.example.erafmak.polish.PadsService;
import com.example.erafmak.polish.Polish;
import com.example.erafmak.polish.PolishService;
import com.example.erafmak.safety.Safety;
import com.example.erafmak.safety.SafetyService;
import com.example.erafmak.sprayGuns.entity.Extras;
import com.example.erafmak.sprayGuns.entity.Nozzle;
import com.example.erafmak.sprayGuns.entity.SprayGun;
import com.example.erafmak.sprayGuns.resource.ExtrasService;
import com.example.erafmak.sprayGuns.resource.NozzleService;
import com.example.erafmak.sprayGuns.resource.SprayGunsService;
import com.example.erafmak.tools.Tool;
import com.example.erafmak.tools.ToolService;

@Controller
@RequestMapping("/create")
public class CreateProductsController {
	
	private final String REDIRECT = "redirect:/products/";
	
	@Autowired
	ManufacturerService manService;

	@Autowired
	HelperService helperService;
	
	@Autowired
	SanderService sanderService;
	
	@Autowired
	CoatService coatService;
	
	@Autowired
	HardenerService harService;
	
	@Autowired
	PrimerService primerService;
	
	@Autowired
	PuttyService puttyService;
	
	@Autowired
	ThinnerService thinnerService;
	
	@Autowired
	PadsService padsService;
	
	@Autowired
	PolishService polishService;
	
	@Autowired
	SafetyService safetyService;
	
	@Autowired
	ExtrasService extrasService;
	
	@Autowired
	NozzleService nozzleService;
	
	@Autowired
	SprayGunsService gunService;
	
	@Autowired
	ToolService toolService;
	
	@GetMapping("/newHelper")
	public String helperModel(Model model) {
		model.addAttribute("helper", new Helper());
		model.addAttribute("manufacturers", manService.manufacturers());
		model.addAttribute("sanders", sanderService.sanders());
		return "addHelper";
	}
	
	@PostMapping("/newHelper")
	public String createHelper(Model model,@ModelAttribute(value = "helper")Helper helper , @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
		helperService.newHelper(helper, multiPartFile);
		} catch (IOException e) {
			model.addAttribute("helper", new Helper());
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "addHelper";
		}
	return REDIRECT+ "helper/" + helper.getId();
	}
	
	@GetMapping("/newSander")
	public String sanderModel(Model model) {
		model.addAttribute("sander", new Sander());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addSander";
	}
	
	@PostMapping("/newSander")
	public String createSander(Model model,@ModelAttribute(value = "sander")Sander sander, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
		sanderService.newSander(sander,multiPartFile);
		} catch (IOException e) {
			model.addAttribute("sander", new Sander());
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "addSander";
		}
	return REDIRECT + "sander/" + sander.getId();
	}
	
	@GetMapping("/newCoat")
	public String coatModel(Model model) {
		model.addAttribute("coat", new Coat());
		model.addAttribute("manufacturers", manService.manufacturers());
		model.addAttribute("hardeners", harService.hardeners());
		return "addCoat";
	}
	
	@PostMapping("/newCoat")
	public String createCoat(Model model ,@ModelAttribute(value = "coat")Coat coat , @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		
try {
	coatService.newCoat(coat,multiPartFile);
		} catch (IOException e) {
			model.addAttribute("coat", new Coat()) ;
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "addCoat";
		}
	return REDIRECT + "coat/" + coat.getId();
	}
	
	@GetMapping("/newHardener")
	public String hardenerModel(Model model) {
		model.addAttribute("hardener", new Hardener());
		model.addAttribute("manufacturers", manService.manufacturers());
		
		return "addHardener";
	}
	
	@PostMapping("/newHardener")
	public String createHardener(Model model,@ModelAttribute(value = "hardener")Hardener hardener, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
		harService.newHardener(hardener, multiPartFile);
		} catch (IOException e) {
			model.addAttribute("hardener", new Hardener());
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "addCoat";
		}
	return REDIRECT + "hardener/" + hardener.getId();
	}
	
	@GetMapping("/newPrimer")
	public String primerModel(Model model) {
		model.addAttribute("primer", new Primer());
		model.addAttribute("manufacturers", manService.manufacturers());
		model.addAttribute("hardeners", harService.hardeners());
		return "addPrimer";
	}
	
	@PostMapping("/newPrimer")
	public String createPrimer(Model model,@ModelAttribute(value = "primer")Primer primer, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException{
		try {
		primerService.newPrimer(primer, multiPartFile);
		} catch (IOException e) {
			model.addAttribute("primer", new Primer());
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "addPrimer";
		}
	return REDIRECT + "primer/" + primer.getId();
	}
	
	@GetMapping("/newPutty")
	public String puttyModel(Model model) {
		model.addAttribute("putty", new Putty());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addPutty";
	}
	
	@PostMapping("/newPutty")
	public String createPutty(Model model,@ModelAttribute(value = "putty")Putty putty , @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException{
		try {
		puttyService.newPutty(putty , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("putty", new Putty());
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "addPutty";
		}
	return REDIRECT + "putty/" + putty.getId();
	}
	
	@GetMapping("/newThinner")
	public String thinnerModel(Model model) {
		model.addAttribute("thinner", new Thinner());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addThinner";
	}
	
	@PostMapping("/newThinner")
	public String createThinner(Model model,@ModelAttribute(value = "thinner")Thinner thinner, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException{
		try {
		thinnerService.newThinner(thinner , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("thinner", new Thinner());
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "addThinner";
		}
	return REDIRECT + "thinner/" + thinner.getId();
	}
	
	@GetMapping("/newPads")
	public String padModel(Model model) {
		model.addAttribute("pads", new Pads());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addPads";
	}
	
	@PostMapping("/newPads")
	public String createPads(Model model,@ModelAttribute(value = "pads")Pads pads, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
		padsService.newPads(pads , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("pads", new Pads());
			model.addAttribute("manufacturers", manService.manufacturers()) ;
			model.addAttribute("error", e.getMessage());
			return "addPads";
		}
	return REDIRECT + "pads/" + pads.getId();
	}
	
	@GetMapping("/newPolish")
	public String polishModel(Model model) {
		model.addAttribute("polish", new Polish());
		model.addAttribute("manufacturers", manService.manufacturers());
		model.addAttribute("pads", padsService.pads());
		return "addPolish";
	}
	
	@PostMapping("/newPolish")
	public String createPolish(Model model,@ModelAttribute(value = "polish")Polish polish, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException{
		try {
		polishService.newPolish(polish , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("polish", new Polish());
			model.addAttribute("manufacturers", manService.manufacturers());
			model.addAttribute("pads", padsService.pads());
			model.addAttribute("error", e.getMessage());
			return "addPolish";
		}
	return REDIRECT + "polish/" + polish.getId();
	}
	
	@GetMapping("/newSafety")
	public String safetyModel(Model model) {
		model.addAttribute("safety", new Safety());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addSafety";
	}
	
	@PostMapping("/newSafety")
	public String createPrimer(Model model,@ModelAttribute(value = "safety")Safety safety,  @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
		safetyService.newSafety(safety , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("safety", new Safety());
			model.addAttribute("manufacturers", manService.manufacturers());
			model.addAttribute("error", e.getMessage());
			return "addSafety";
		}
	return REDIRECT + "safety/" + safety.getId();
	}
	
	@GetMapping("/newExtras")
	public String extrasModel(Model model) {
		model.addAttribute("extras", new Extras());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addExtrass";
	}
	
	@PostMapping("/newExtras")
	public String createExtras(Model model,@ModelAttribute(value = "extras")Extras extras, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
		extrasService.newExtras(extras , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("extras", new Extras());
			model.addAttribute("manufacturers", manService.manufacturers());
			model.addAttribute("error", e.getMessage());
			return "addExtrass";
		}
	return REDIRECT + "extras/" + extras.getId();
	}
	
	@GetMapping("/newNozzle")
	public String nozzleModel(Model model) {
		model.addAttribute("nozzle", new Nozzle());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addNozzle";
	}
	
	@PostMapping("/newNozzle")
	public String createNozzle(Model model,@ModelAttribute(value = "nozzle")Nozzle nozzle, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
		nozzleService.newNozzle(nozzle , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("nozzle", new Nozzle());
			model.addAttribute("manufacturers", manService.manufacturers());
			model.addAttribute("error", e.getMessage());
			return "addNozzle";
		}
	return REDIRECT + "nozzle/" + nozzle.getId();
	}
	
	@GetMapping("/newSprayGun")
	public String sprayGunModel(Model model) {
		model.addAttribute("sprayGun", new SprayGun());
		model.addAttribute("manufacturers", manService.manufacturers());
		model.addAttribute("nozzles", nozzleService.nozzles());
		return "addSprayGun";
	}
	
	@PostMapping("/newSprayGun")
	public String createSprayGun(Model model,@ModelAttribute(value = "gun")SprayGun gun, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
		gunService.newSprayGun(gun , multiPartFile);
		} catch (IOException e) {
			model.addAttribute("sprayGun", new SprayGun());
			model.addAttribute("manufacturers", manService.manufacturers());
			model.addAttribute("nozzles", nozzleService.nozzles());
			model.addAttribute("error", e.getMessage());
			return "addSprayGun";
		}
	return REDIRECT + "sprayGun/" + gun.getId();
	}
	
	@GetMapping("/newTool")
	public String toolModel(Model model) {
		model.addAttribute("tool", new Tool());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addTool";
	}
	
	@PostMapping("/newTool")
	public String createTool(Model model,@ModelAttribute(value = "tool")Tool tool, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		try {
		toolService.newTool(tool, multiPartFile);
		} catch (IOException e) {
			model.addAttribute("tool", new Tool());
			model.addAttribute("manufacturers", manService.manufacturers());
			return "addTool";
		}
	return REDIRECT + "tool/" + tool.getId();
	}
	
}
