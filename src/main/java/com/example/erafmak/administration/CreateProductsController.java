package com.example.erafmak.administration;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import com.example.erafmak.abraziveMaterials.sander.GranulationQty;
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
import com.example.erafmak.safety.Size;
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
		return "addHelper";
	}
	
	@PostMapping("/newHelper")
	public String createHelper(@ModelAttribute(value = "helper")Helper helper , @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		helperService.newHelper(helper, multiPartFile);
	return REDIRECT+ "helper/" + helper.getId();
	}
	
	@GetMapping("/newSander")
	public String sanderModel(Model model) {
		model.addAttribute("sander", new Sander());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addSander";
	}
	
	@PostMapping("/newSander")
	public String createSander(@ModelAttribute(value = "sander")Sander sander, @RequestParam("fileImage") MultipartFile multiPartFile, @RequestParam("list") List<GranulationQty> list) throws IOException {
		sanderService.newSander(sander,multiPartFile, list);
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
	public String createCoat(@ModelAttribute(value = "coat")Coat coat , @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		coatService.newCoat(coat,multiPartFile);
	return REDIRECT + "coat/" + coat.getId();
	}
	
	@GetMapping("/newHardener")
	public String hardenerModel(Model model) {
		model.addAttribute("hardener", new Hardener());
		model.addAttribute("manufacturers", manService.manufacturers());
		
		return "addHardener";
	}
	
	@PostMapping("/newHardener")
	public String createHardener(@ModelAttribute(value = "hardener")Hardener hardener, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		harService.newHardener(hardener, multiPartFile);
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
	public String createPrimer(@ModelAttribute(value = "primer")Primer primer, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException{
		primerService.newPrimer(primer, multiPartFile);
	return REDIRECT + "primer/" + primer.getId();
	}
	
	@GetMapping("/newPutty")
	public String puttyModel(Model model) {
		model.addAttribute("putty", new Thinner());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addPutty";
	}
	
	@PostMapping("/newPutty")
	public String createPutty(@ModelAttribute(value = "putty")Putty putty , @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException{
		puttyService.newPutty(putty , multiPartFile);
	return REDIRECT + "putty/" + putty.getId();
	}
	
	@GetMapping("/newThinner")
	public String thinnerModel(Model model) {
		model.addAttribute("thinner", new Thinner());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addThinner";
	}
	
	@PostMapping("/newThinner")
	public String createThinner(@ModelAttribute(value = "thinner")Thinner thinner, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException{
		thinnerService.newThinner(thinner , multiPartFile);
	return REDIRECT + "thinner/" + thinner.getId();
	}
	
	@GetMapping("/newPads")
	public String padModel(Model model) {
		model.addAttribute("pads", new Pads());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addPads";
	}
	
	@PostMapping("/newPads")
	public String createPads(@ModelAttribute(value = "pads")Pads pads, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		padsService.newPads(pads , multiPartFile);
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
	public String createPolish(@ModelAttribute(value = "polish")Polish polish, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException{
		polishService.newPolish(polish , multiPartFile);
	return REDIRECT + "polish/" + polish.getId();
	}
	
	@GetMapping("/newSafety")
	public String safetyModel(Model model) {
		model.addAttribute("safety", new Safety());
		model.addAttribute("manufacturers", manService.manufacturers());
		model.addAttribute("sizes", safetyService.sizes());
		return "addSafety";
	}
	
	@PostMapping("/newSafety")
	public String createPrimer(@ModelAttribute(value = "safety")Safety safety, @Param(value = "sizes")List<Size>sizes, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		safetyService.newSafety(safety , sizes , multiPartFile);
	return REDIRECT + "safety/" + safety.getId();
	}
	
	@GetMapping("/newExtras")
	public String extrasModel(Model model) {
		model.addAttribute("extras", new Extras());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addExtrass";
	}
	
	@PostMapping("/newExtras")
	public String createExtras(@ModelAttribute(value = "extras")Extras extras, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		extrasService.newExtras(extras , multiPartFile);
	return REDIRECT + "extras/" + extras.getId();
	}
	
	@GetMapping("/newNozzle")
	public String nozzleModel(Model model) {
		model.addAttribute("nozzle", new Nozzle());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addNozzle";
	}
	
	@PostMapping("/newNozzle")
	public String createNozzle(@ModelAttribute(value = "nozzle")Nozzle nozzle, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		nozzleService.newNozzle(nozzle , multiPartFile);
	return REDIRECT + nozzle.getId();
	}
	
	@GetMapping("/newSprayGun")
	public String sprayGunModel(Model model) {
		model.addAttribute("sprayGun", new SprayGun());
		model.addAttribute("manufacturers", manService.manufacturers());
		model.addAttribute("nozzles", nozzleService.nozzles());
		return "addSprayGun";
	}
	
	@PostMapping("/newSprayGun")
	public String createSprayGun(@ModelAttribute(value = "gun")SprayGun gun, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		gunService.newSprayGun(gun , multiPartFile);
	return REDIRECT + "sprayGun/" + gun.getId();
	}
	
	@GetMapping("/newTool")
	public String toolModel(Model model) {
		model.addAttribute("tool", new Tool());
		model.addAttribute("manufacturers", manService.manufacturers());
		return "addTool";
	}
	
	@PostMapping("/newTool")
	public String createTool(@ModelAttribute(value = "tool")Tool tool, @RequestParam("fileImage") MultipartFile multiPartFile) throws IOException {
		toolService.newTool(tool, multiPartFile);
	return REDIRECT + "tool/" + tool.getId();
	}
	
}
