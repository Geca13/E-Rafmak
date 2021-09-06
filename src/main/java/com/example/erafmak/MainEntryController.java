package com.example.erafmak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.erafmak.abraziveMaterials.helpers.HelperService;
import com.example.erafmak.abraziveMaterials.sander.SanderService;
import com.example.erafmak.coatsAndPrimers.resourse.CoatService;
import com.example.erafmak.coatsAndPrimers.resourse.HardenerService;
import com.example.erafmak.coatsAndPrimers.resourse.PrimerService;
import com.example.erafmak.coatsAndPrimers.resourse.PuttyService;
import com.example.erafmak.coatsAndPrimers.resourse.ThinnerService;
import com.example.erafmak.polish.PadsService;
import com.example.erafmak.polish.PolishService;
import com.example.erafmak.safety.SafetyService;
import com.example.erafmak.sprayGuns.resource.ExtrasService;
import com.example.erafmak.sprayGuns.resource.NozzleService;
import com.example.erafmak.sprayGuns.resource.SprayGunsService;
import com.example.erafmak.tools.ToolService;
import com.example.erafmak.user.entity.User;
import com.example.erafmak.user.entity.UserRepository;
import com.example.erafmak.user.service.UsersDetails;

@Controller
public class MainEntryController {
	
	@Autowired
	CoatService coats;
	
	@Autowired
	ThinnerService thinners;
	
	@Autowired
	PrimerService primers;
	
	@Autowired
	HardenerService hardeners;
	
	@Autowired
	PuttyService putties;;
	
	@Autowired
	HelperService helpers;
	
	@Autowired
	SanderService sanders;
	
	@Autowired
	PadsService pads;
	
	@Autowired
	PolishService polishes;
	
	@Autowired
	SafetyService safeties;
	
	@Autowired
	ExtrasService extras;
	
	@Autowired
	NozzleService nozzles;
	
	@Autowired
	SprayGunsService guns;
	
	@Autowired
	ToolService tools;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/")
	public String getIndexPage(Model model,@AuthenticationPrincipal UsersDetails user) {
		
		model.addAttribute("coats", coats.coats());
		model.addAttribute("thinners", thinners.thinners());
		model.addAttribute("primers", primers.primers());
		model.addAttribute("hardeners", hardeners.hardeners());
		model.addAttribute("putties", putties.putties());
		
		model.addAttribute("sanders", sanders.sanders());
		model.addAttribute("discs", sanders.discs());
		model.addAttribute("wets", sanders.wets());
		model.addAttribute("softs", sanders.softs());
		model.addAttribute("blocks", sanders.blocks());
		model.addAttribute("rolls", sanders.rolls());
		
		model.addAttribute("pads", pads.pads());
		model.addAttribute("polishes", polishes.polishes());
		
		
		model.addAttribute("safeties", safeties.safeties());
		model.addAttribute("extrass", extras.extrass());
		
		model.addAttribute("helpers", helpers.helpers());
		model.addAttribute("nozzles", nozzles.nozzles());
		model.addAttribute("guns", guns.guns());
		model.addAttribute("tools", tools.tools());
		String userEmail = user.getUsername();
        User user1 = userRepository.findByEmail(userEmail);
           model.addAttribute("user", user1);
		
		return "index";
	}
	
	@GetMapping("/login")
	public String getloginPage(Model model) {
		
		model.addAttribute("coats", coats.coats());
		model.addAttribute("thinners", thinners.thinners());
		model.addAttribute("primers", primers.primers());
		model.addAttribute("hardeners", hardeners.hardeners());
		model.addAttribute("putties", putties.putties());
		
		model.addAttribute("sanders", sanders.sanders());
		model.addAttribute("discs", sanders.discs());
		model.addAttribute("wets", sanders.wets());
		model.addAttribute("softs", sanders.softs());
		model.addAttribute("blocks", sanders.blocks());
		model.addAttribute("rolls", sanders.rolls());
		
		model.addAttribute("pads", pads.pads());
		model.addAttribute("polishes", polishes.polishes());
		
		
		model.addAttribute("safeties", safeties.safeties());
		model.addAttribute("extrass", extras.extrass());
		
		model.addAttribute("helpers", helpers.helpers());
		model.addAttribute("nozzles", nozzles.nozzles());
		model.addAttribute("guns", guns.guns());
		model.addAttribute("tools", tools.tools());
		
		
		return "login";
	}
	
	@GetMapping("/logoutSuccess")
	public String logout() {
		return "login";
	}
	
	@GetMapping("/admin")
	public String administration(Model model) {
		
		return "administration";
	}
}
