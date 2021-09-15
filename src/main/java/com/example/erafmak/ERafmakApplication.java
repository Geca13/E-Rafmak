package com.example.erafmak;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.erafmak.abraziveMaterials.helpers.Helper;
import com.example.erafmak.abraziveMaterials.helpers.HelperRepository;
import com.example.erafmak.abraziveMaterials.sander.Condition;
import com.example.erafmak.abraziveMaterials.sander.Dimension;
import com.example.erafmak.abraziveMaterials.sander.Granulation;
import com.example.erafmak.abraziveMaterials.sander.GranulationQty;
import com.example.erafmak.abraziveMaterials.sander.GranulationQtyRepository;
import com.example.erafmak.abraziveMaterials.sander.Sander;
import com.example.erafmak.abraziveMaterials.sander.SanderRepository;
import com.example.erafmak.abraziveMaterials.sander.Type;
import com.example.erafmak.coatsAndPrimers.entity.Coat;
import com.example.erafmak.coatsAndPrimers.entity.Hardener;
import com.example.erafmak.coatsAndPrimers.entity.Primer;
import com.example.erafmak.coatsAndPrimers.entity.Putty;
import com.example.erafmak.coatsAndPrimers.entity.Thinner;
import com.example.erafmak.coatsAndPrimers.entity.Weigth;
import com.example.erafmak.coatsAndPrimers.repository.CoatRepository;
import com.example.erafmak.coatsAndPrimers.repository.HardenerRepository;
import com.example.erafmak.coatsAndPrimers.repository.PrimerRepository;
import com.example.erafmak.coatsAndPrimers.repository.PuttyRepository;
import com.example.erafmak.coatsAndPrimers.repository.ThinnerRepository;
import com.example.erafmak.manufacturers.Manufacturer;
import com.example.erafmak.manufacturers.ManufacturerRepository;
import com.example.erafmak.manufacturers.Origin;
import com.example.erafmak.manufacturers.OriginRepository;
import com.example.erafmak.polish.Pads;
import com.example.erafmak.polish.PadsRepository;
import com.example.erafmak.polish.Polish;
import com.example.erafmak.polish.PolishRepository;
import com.example.erafmak.safety.Safety;
import com.example.erafmak.safety.SafetyRepository;
import com.example.erafmak.safety.Size;
import com.example.erafmak.safety.SizeQuantity;
import com.example.erafmak.safety.SizeQuantityRepository;
import com.example.erafmak.sprayGuns.entity.Extras;
import com.example.erafmak.sprayGuns.entity.Nozzle;
import com.example.erafmak.sprayGuns.entity.NozzleSize;
import com.example.erafmak.sprayGuns.entity.SprayGun;
import com.example.erafmak.sprayGuns.repository.ExtrasRepository;
import com.example.erafmak.sprayGuns.repository.NozzleRepository;
import com.example.erafmak.sprayGuns.repository.SprayGunRepository;
import com.example.erafmak.tools.Power;
import com.example.erafmak.tools.Tool;
import com.example.erafmak.tools.ToolRepository;
import com.example.erafmak.user.entity.Role;
import com.example.erafmak.user.entity.RoleName;
import com.example.erafmak.user.entity.RoleRepository;

@SpringBootApplication
public class ERafmakApplication {
	
	
	@Autowired
	SanderRepository sanderRepository;
	
	@Autowired
	HelperRepository helperRepository;
	
	@Autowired
	CoatRepository coatRepository;
	
	@Autowired
	HardenerRepository hardenerRepository;
	
	@Autowired
	PrimerRepository primerRepository;
	
	@Autowired
	PuttyRepository puttyRepository;
	
	@Autowired
	ThinnerRepository thinnerRepository;
	
	@Autowired
	ManufacturerRepository manufacturerRepository;
	
	@Autowired
	OriginRepository originRepository;
	
	@Autowired
	PolishRepository polishRepository;
	
	@Autowired
	SprayGunRepository gunRepository;
	
	@Autowired
	NozzleRepository nozzleRepository;
	
	@Autowired
	ExtrasRepository extrasRepository;
	
	@Autowired
	ToolRepository toolRepository;
	
	@Autowired
	PadsRepository padsRepository;
	
	@Autowired
	SafetyRepository safetyRepository;
	
	@Autowired
	SizeQuantityRepository sizeQtyRepository;
	
	@Autowired
	GranulationQtyRepository granulationQtyRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	private final String ABRAZIVE = "/img/abrazive/";
	
	private final String COATS = "/img/coats/";
	
	private final String HARDENERS = "/img/hardeners/";
	
	private final String PUTTIES = "/img/putties/";
	
	private final String PRIMERS = "/img/primers/";
	
	private final String THINNERS = "/img/thinners/";
	
	private final String PADS = "/img/pads/";
	
	private final String POLISH = "/img/polish/";
	
	private final String GUNS = "/img/guns/";
	
	private final String NOZZLES = "/img/nozzles/";
	
	private final String TOOLS = "/img/tools/";
	
	private final String SAFETIES = "/img/safeties/";
	
	private final String EXTRAS = "/img/extras/";
	
	private final String BLOCKS = "/img/blocks/";
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ERafmakApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		
		try {
			
			roleRepository.save(new Role(1L, RoleName.ROLE_ADMIN));
			roleRepository.save(new Role(2L, RoleName.ROLE_USER));
			
			originRepository.save(new Origin(1L, "Finland"));
			originRepository.save(new Origin(2L, "Nederlands"));
			originRepository.save(new Origin(3L, "Italy"));
			originRepository.save(new Origin(4L, "Belgium"));
			originRepository.save(new Origin(5L, "Germany"));
			
			manufacturerRepository.save(new Manufacturer(1L, "MIRKA",originRepository.findById(1L).get() ,"mirka@mirka.com"));
			manufacturerRepository.save(new Manufacturer(2L, "DeBeer",originRepository.findById(2L).get() ,"deBeer@deBeer.com"));
			manufacturerRepository.save(new Manufacturer(3L, "Spralac",originRepository.findById(2L).get() ,"spralac@spralac.com"));
			manufacturerRepository.save(new Manufacturer(4L, "Finixa",originRepository.findById(4L).get() ,"finixa@finixa.com"));
			manufacturerRepository.save(new Manufacturer(5L, "Sata",originRepository.findById(5L).get() ,"sata@sata.com"));
			manufacturerRepository.save(new Manufacturer(6L, "Spiralflex",originRepository.findById(3L).get() ,"spiralflex@spiralflex.com"));
			
			granulationQtyRepository.save(new GranulationQty(1L, true , 2300.00, Granulation.P80));
			granulationQtyRepository.save(new GranulationQty(2L, true , 2000.00, Granulation.P100));
			granulationQtyRepository.save(new GranulationQty(3L, true , 2000.00, Granulation.P120));
			granulationQtyRepository.save(new GranulationQty(4L, true , 2000.00 ,Granulation.P150));
			granulationQtyRepository.save(new GranulationQty(5L, true , 2000.00 ,Granulation.P180));
			granulationQtyRepository.save(new GranulationQty(6L, true , 2000.00 ,Granulation.P240));
			granulationQtyRepository.save(new GranulationQty(7L, true , 2000.00 ,Granulation.P320));
			granulationQtyRepository.save(new GranulationQty(8L, true , 2000.00 ,Granulation.P400));
			
			granulationQtyRepository.save(new GranulationQty(9L, true , 900.00, Granulation.P40));
			granulationQtyRepository.save(new GranulationQty(10L, true , 850.00, Granulation.P60));
			granulationQtyRepository.save(new GranulationQty(11L, true , 800.00, Granulation.P80));
			granulationQtyRepository.save(new GranulationQty(12L, true , 750.00, Granulation.P100));
			granulationQtyRepository.save(new GranulationQty(13L, true , 750.00, Granulation.P120));
			granulationQtyRepository.save(new GranulationQty(14L, true , 750.00 ,Granulation.P150));
			granulationQtyRepository.save(new GranulationQty(15L, true , 750.00 ,Granulation.P180));
			granulationQtyRepository.save(new GranulationQty(16L, true , 750.00 ,Granulation.P240));
			granulationQtyRepository.save(new GranulationQty(17L, true , 750.00 ,Granulation.P320));
			
			granulationQtyRepository.save(new GranulationQty(18L, true , 1200.00, Granulation.P1200));
			granulationQtyRepository.save(new GranulationQty(19L, true , 1200.00, Granulation.P1500));
			granulationQtyRepository.save(new GranulationQty(20L, true , 1200.00, Granulation.P2000));
			granulationQtyRepository.save(new GranulationQty(21L, true , 1200.00 ,Granulation.P2500));
			
			granulationQtyRepository.save(new GranulationQty(22L, true , 1200.00, Granulation.P1200));
			granulationQtyRepository.save(new GranulationQty(23L, true , 1200.00, Granulation.P1500));
			granulationQtyRepository.save(new GranulationQty(24L, true , 1200.00, Granulation.P2000));
			
			granulationQtyRepository.save(new GranulationQty(25L, true , 1300.00, Granulation.P80));
			granulationQtyRepository.save(new GranulationQty(26L, true , 1200.00, Granulation.P120));
			granulationQtyRepository.save(new GranulationQty(27L, true , 1200.00 ,Granulation.P150));
			granulationQtyRepository.save(new GranulationQty(28L, true , 1200.00 ,Granulation.P180));
			granulationQtyRepository.save(new GranulationQty(29L, true , 1200.00 ,Granulation.P240));
			granulationQtyRepository.save(new GranulationQty(30L, true , 1200.00 ,Granulation.P320));
			granulationQtyRepository.save(new GranulationQty(31L, true , 1200.00 ,Granulation.P400));
			
			granulationQtyRepository.save(new GranulationQty(32L, true , 1300.00, Granulation.P80));
			granulationQtyRepository.save(new GranulationQty(33L, true , 1200.00, Granulation.P120));
			granulationQtyRepository.save(new GranulationQty(34L, true , 1200.00 ,Granulation.P150));
			granulationQtyRepository.save(new GranulationQty(35L, true , 1200.00 ,Granulation.P180));
			granulationQtyRepository.save(new GranulationQty(36L, true , 1200.00 ,Granulation.P240));
			granulationQtyRepository.save(new GranulationQty(37L, true , 1200.00 ,Granulation.P320));
			granulationQtyRepository.save(new GranulationQty(38L, true , 1200.00 ,Granulation.P400));
			
			granulationQtyRepository.save(new GranulationQty(39L, true , 2200.00, Granulation.P80));
			granulationQtyRepository.save(new GranulationQty(40L, true , 1750.00, Granulation.P100));
			granulationQtyRepository.save(new GranulationQty(41L, true , 1750.00, Granulation.P120));
			granulationQtyRepository.save(new GranulationQty(42L, true , 1750.00 ,Granulation.P150));
			granulationQtyRepository.save(new GranulationQty(43L, true , 1750.00 ,Granulation.P180));
			granulationQtyRepository.save(new GranulationQty(44L, true , 1750.00 ,Granulation.P240));
			granulationQtyRepository.save(new GranulationQty(45L, true , 1750.00 ,Granulation.P320));
			granulationQtyRepository.save(new GranulationQty(46L, true , 1750.00 ,Granulation.P400));
			granulationQtyRepository.save(new GranulationQty(47L, true , 1750.00 ,Granulation.P500));
			granulationQtyRepository.save(new GranulationQty(48L, true , 1750.00 ,Granulation.P600));
			granulationQtyRepository.save(new GranulationQty(49L, true , 2200.00 ,Granulation.P800));
			granulationQtyRepository.save(new GranulationQty(50L, true , 2200.00 ,Granulation.P1000));
			
			granulationQtyRepository.save(new GranulationQty(51L, true , 1750.00 ,Granulation.P320));
			granulationQtyRepository.save(new GranulationQty(52L, true , 1750.00 ,Granulation.P400));
			granulationQtyRepository.save(new GranulationQty(53L, true , 1750.00 ,Granulation.P600));
			granulationQtyRepository.save(new GranulationQty(54L, true , 2200.00 ,Granulation.P800));
			
			granulationQtyRepository.save(new GranulationQty(55L, true , 2400.00 ,Granulation.P180));
			granulationQtyRepository.save(new GranulationQty(56L, true , 2400.00 ,Granulation.P360));
			granulationQtyRepository.save(new GranulationQty(57L, true , 2400.00 ,Granulation.P500));
			granulationQtyRepository.save(new GranulationQty(58L, true , 2400.00 ,Granulation.P1000));
			granulationQtyRepository.save(new GranulationQty(59L, true , 2400.00 ,Granulation.P2000));
			granulationQtyRepository.save(new GranulationQty(60L, true , 2400.00 ,Granulation.P3000));
			granulationQtyRepository.save(new GranulationQty(61L, true , 2400.00 ,Granulation.P4000));
			
			granulationQtyRepository.save(new GranulationQty(62L, true , 1100.00 ,Granulation.P2000));
			
			granulationQtyRepository.save(new GranulationQty(63L, true , 950.00 ,Granulation.P220));
			granulationQtyRepository.save(new GranulationQty(64L, true , 950.00 ,Granulation.P240));
			granulationQtyRepository.save(new GranulationQty(65L, true , 950.00 ,Granulation.P280));
			granulationQtyRepository.save(new GranulationQty(66L, true , 950.00 ,Granulation.P320));
			granulationQtyRepository.save(new GranulationQty(67L, true , 950.00 ,Granulation.P360));
			granulationQtyRepository.save(new GranulationQty(68L, true , 950.00 ,Granulation.P400));
			granulationQtyRepository.save(new GranulationQty(69L, true , 950.00 ,Granulation.P500));
			granulationQtyRepository.save(new GranulationQty(70L, true , 950.00 ,Granulation.P600));
			granulationQtyRepository.save(new GranulationQty(71L, true , 950.00 ,Granulation.P800));
			granulationQtyRepository.save(new GranulationQty(72L, true , 950.00 ,Granulation.P1000));
			granulationQtyRepository.save(new GranulationQty(73L, true , 950.00 ,Granulation.P1200));
			
			granulationQtyRepository.save(new GranulationQty(74L, true , 1350.00 ,Granulation.P1500));
			granulationQtyRepository.save(new GranulationQty(75L, true , 1350.00 ,Granulation.P2000));
			
			granulationQtyRepository.save(new GranulationQty(76L, true , 3300.00, Granulation.P80));
			granulationQtyRepository.save(new GranulationQty(77L, true , 3300.00, Granulation.P120));
			granulationQtyRepository.save(new GranulationQty(78L, true , 3300.00 ,Granulation.P150));
			granulationQtyRepository.save(new GranulationQty(79L, true , 3300.00 ,Granulation.P180));
			granulationQtyRepository.save(new GranulationQty(80L, true , 3300.00 ,Granulation.P240));
			granulationQtyRepository.save(new GranulationQty(81L, true , 3300.00 ,Granulation.P320));
			granulationQtyRepository.save(new GranulationQty(82L, true , 3300.00 ,Granulation.P400));
			granulationQtyRepository.save(new GranulationQty(83L, true , 3300.00 ,Granulation.P500));
			
			granulationQtyRepository.save(new GranulationQty(84L, true , 2300.00, Granulation.P80));
			granulationQtyRepository.save(new GranulationQty(85L, true , 2200.00, Granulation.P120));
			granulationQtyRepository.save(new GranulationQty(86L, true , 2200.00 ,Granulation.P150));
			granulationQtyRepository.save(new GranulationQty(87L, true , 2200.00 ,Granulation.P180));
			granulationQtyRepository.save(new GranulationQty(88L, true , 2200.00 ,Granulation.P240));
			granulationQtyRepository.save(new GranulationQty(89L, true , 2200.00 ,Granulation.P320));
			
			granulationQtyRepository.save(new GranulationQty(90L, true , 2300.00, Granulation.P80));
			granulationQtyRepository.save(new GranulationQty(91L, true , 2200.00, Granulation.P120));
			granulationQtyRepository.save(new GranulationQty(92L, true , 2200.00 ,Granulation.P150));
			granulationQtyRepository.save(new GranulationQty(93L, true , 2200.00 ,Granulation.P180));
			granulationQtyRepository.save(new GranulationQty(94L, true , 2200.00 ,Granulation.P240));
			granulationQtyRepository.save(new GranulationQty(95L, true , 2200.00 ,Granulation.P320));
			
			granulationQtyRepository.save(new GranulationQty(96L, true , 2200.00, Granulation.P240));
			granulationQtyRepository.save(new GranulationQty(97L, true , 2200.00 ,Granulation.P320));
			granulationQtyRepository.save(new GranulationQty(98L, true , 2200.00 ,Granulation.P400));
			granulationQtyRepository.save(new GranulationQty(99L, true , 2200.00 ,Granulation.P600));
			granulationQtyRepository.save(new GranulationQty(100L, true , 2200.00 ,Granulation.P800));
			
			granulationQtyRepository.save(new GranulationQty(101L, true , 1100.00 ,Granulation.P60));
			
			granulationQtyRepository.save(new GranulationQty(102L, true , 450.00 ,Granulation.P360));
			
			granulationQtyRepository.save(new GranulationQty(103L, true , 2250.00 ,Granulation.P80));
			granulationQtyRepository.save(new GranulationQty(104L, true , 1950.00 ,Granulation.P100));
			granulationQtyRepository.save(new GranulationQty(105L, true , 1950.00 ,Granulation.P120));
			granulationQtyRepository.save(new GranulationQty(106L, true , 1950.00 ,Granulation.P150));
			granulationQtyRepository.save(new GranulationQty(107L, true , 1950.00 ,Granulation.P180));
			granulationQtyRepository.save(new GranulationQty(108L, true , 1950.00 ,Granulation.P220));
			granulationQtyRepository.save(new GranulationQty(109L, true , 1950.00 ,Granulation.P240));
			granulationQtyRepository.save(new GranulationQty(110L, true , 1950.00 ,Granulation.P280));
			granulationQtyRepository.save(new GranulationQty(111L, true , 1950.00 ,Granulation.P320));
			granulationQtyRepository.save(new GranulationQty(112L, true , 1950.00 ,Granulation.P360));
			granulationQtyRepository.save(new GranulationQty(113L, true , 1950.00 ,Granulation.P400));
			
			granulationQtyRepository.save(new GranulationQty(114L, true , 2150.00 ,Granulation.P60));
			granulationQtyRepository.save(new GranulationQty(115L, true , 2000.00 ,Granulation.P80));
			granulationQtyRepository.save(new GranulationQty(116L, true , 1750.00 ,Granulation.P120));
			granulationQtyRepository.save(new GranulationQty(117L, true , 1750.00 ,Granulation.P150));
			granulationQtyRepository.save(new GranulationQty(118L, true , 1750.00 ,Granulation.P180));
			granulationQtyRepository.save(new GranulationQty(119L, true , 1750.00 ,Granulation.P220));
			granulationQtyRepository.save(new GranulationQty(120L, true , 1750.00 ,Granulation.P240));
			granulationQtyRepository.save(new GranulationQty(121L, true , 1750.00 ,Granulation.P320));
			granulationQtyRepository.save(new GranulationQty(122L, true , 1750.00 ,Granulation.P400));
			
            granulationQtyRepository.save(new GranulationQty(123L, true , 1950.00 ,Granulation.P80));
		    granulationQtyRepository.save(new GranulationQty(124L, true , 2150.00 ,Granulation.P120));
			granulationQtyRepository.save(new GranulationQty(125L, true , 2000.00 ,Granulation.P150));
			granulationQtyRepository.save(new GranulationQty(126L, true , 1750.00 ,Granulation.P180));
			granulationQtyRepository.save(new GranulationQty(127L, true , 1750.00 ,Granulation.P240));
			granulationQtyRepository.save(new GranulationQty(128L, true , 1750.00 ,Granulation.P320));
			granulationQtyRepository.save(new GranulationQty(129L, true , 1750.00 ,Granulation.P400));
			
			granulationQtyRepository.save(new GranulationQty(130L, true , 1750.00 ,Granulation.P360));
			granulationQtyRepository.save(new GranulationQty(131L, true , 1750.00 ,Granulation.P1500));
			
			List<GranulationQty> silverDisk = new ArrayList<>();
			
			silverDisk.add(granulationQtyRepository.findById(1L).get());
			silverDisk.add(granulationQtyRepository.findById(2L).get());
			silverDisk.add(granulationQtyRepository.findById(3L).get());
			silverDisk.add(granulationQtyRepository.findById(4L).get());
			silverDisk.add(granulationQtyRepository.findById(5L).get());
			silverDisk.add(granulationQtyRepository.findById(6L).get());
			silverDisk.add(granulationQtyRepository.findById(7L).get());
			silverDisk.add(granulationQtyRepository.findById(8L).get());
			
            List<GranulationQty> deflexDisk = new ArrayList<>();
			
            deflexDisk.add(granulationQtyRepository.findById(9L).get());
            deflexDisk.add(granulationQtyRepository.findById(10L).get());
            deflexDisk.add(granulationQtyRepository.findById(11L).get());
            deflexDisk.add(granulationQtyRepository.findById(12L).get());
            deflexDisk.add(granulationQtyRepository.findById(13L).get());
            deflexDisk.add(granulationQtyRepository.findById(14L).get());
            deflexDisk.add(granulationQtyRepository.findById(15L).get());
            deflexDisk.add(granulationQtyRepository.findById(16L).get());
            deflexDisk.add(granulationQtyRepository.findById(17L).get());
            
            List<GranulationQty> microStar150 = new ArrayList<>();
            
            microStar150.add(granulationQtyRepository.findById(18L).get());
            microStar150.add(granulationQtyRepository.findById(19L).get());
            microStar150.add(granulationQtyRepository.findById(20L).get());
            microStar150.add(granulationQtyRepository.findById(21L).get());
            
            List<GranulationQty> microStar80 = new ArrayList<>();
            
            microStar80.add(granulationQtyRepository.findById(18L).get());
            microStar80.add(granulationQtyRepository.findById(19L).get());
            microStar80.add(granulationQtyRepository.findById(20L).get());
            microStar80.add(granulationQtyRepository.findById(21L).get());
            
            List<GranulationQty> autonet150 = new ArrayList<>();
            
            autonet150.add(granulationQtyRepository.findById(25L).get());
            autonet150.add(granulationQtyRepository.findById(26L).get());
            autonet150.add(granulationQtyRepository.findById(27L).get());
            autonet150.add(granulationQtyRepository.findById(28L).get());
            autonet150.add(granulationQtyRepository.findById(29L).get());
            autonet150.add(granulationQtyRepository.findById(30L).get());
            autonet150.add(granulationQtyRepository.findById(31L).get());
            
            List<GranulationQty> autonet70x198 = new ArrayList<>();
            
            autonet70x198.add(granulationQtyRepository.findById(25L).get());
            autonet70x198.add(granulationQtyRepository.findById(26L).get());
            autonet70x198.add(granulationQtyRepository.findById(27L).get());
            autonet70x198.add(granulationQtyRepository.findById(28L).get());
            autonet70x198.add(granulationQtyRepository.findById(29L).get());
            autonet70x198.add(granulationQtyRepository.findById(30L).get());
            autonet70x198.add(granulationQtyRepository.findById(31L).get());
            
            List<GranulationQty> abranetDisk150 = new ArrayList<>();
			
            abranetDisk150.add(granulationQtyRepository.findById(39L).get());
            abranetDisk150.add(granulationQtyRepository.findById(40L).get());
            abranetDisk150.add(granulationQtyRepository.findById(41L).get());
            abranetDisk150.add(granulationQtyRepository.findById(42L).get());
            abranetDisk150.add(granulationQtyRepository.findById(43L).get());
            abranetDisk150.add(granulationQtyRepository.findById(44L).get());
            abranetDisk150.add(granulationQtyRepository.findById(45L).get());
            abranetDisk150.add(granulationQtyRepository.findById(46L).get());
            abranetDisk150.add(granulationQtyRepository.findById(47L).get());
            abranetDisk150.add(granulationQtyRepository.findById(48L).get());
            abranetDisk150.add(granulationQtyRepository.findById(49L).get());
            abranetDisk150.add(granulationQtyRepository.findById(50L).get());
            
            List<GranulationQty> abranetDisk80 = new ArrayList<>();
			
            abranetDisk80.add(granulationQtyRepository.findById(51L).get());
            abranetDisk80.add(granulationQtyRepository.findById(52L).get());
            abranetDisk80.add(granulationQtyRepository.findById(53L).get());
            abranetDisk80.add(granulationQtyRepository.findById(54L).get());
            
            List<GranulationQty> abralonDisk150 = new ArrayList<>();
            
            abralonDisk150.add(granulationQtyRepository.findById(55L).get());
            abralonDisk150.add(granulationQtyRepository.findById(56L).get());
            abralonDisk150.add(granulationQtyRepository.findById(57L).get());
            abralonDisk150.add(granulationQtyRepository.findById(58L).get());
            abralonDisk150.add(granulationQtyRepository.findById(59L).get());
            abralonDisk150.add(granulationQtyRepository.findById(60L).get());
            abralonDisk150.add(granulationQtyRepository.findById(61L).get());
            
            List<GranulationQty> abralonDisk80 = new ArrayList<>();
            abralonDisk150.add(granulationQtyRepository.findById(62L).get());
            
            List<GranulationQty> wpf = new ArrayList<>();
			
            wpf.add(granulationQtyRepository.findById(63L).get());
            wpf.add(granulationQtyRepository.findById(64L).get());
            wpf.add(granulationQtyRepository.findById(65L).get());
            wpf.add(granulationQtyRepository.findById(66L).get());
            wpf.add(granulationQtyRepository.findById(67L).get());
            wpf.add(granulationQtyRepository.findById(68L).get());
            wpf.add(granulationQtyRepository.findById(69L).get());
            wpf.add(granulationQtyRepository.findById(70L).get());
            wpf.add(granulationQtyRepository.findById(71L).get());
            wpf.add(granulationQtyRepository.findById(72L).get());
            wpf.add(granulationQtyRepository.findById(73L).get());
            
            List<GranulationQty> wpf1500 = new ArrayList<>();
            
            wpf1500.add(granulationQtyRepository.findById(74L).get());
            wpf1500.add(granulationQtyRepository.findById(75L).get());
            
            List<GranulationQty> iridiumDisk = new ArrayList<>();
			
            iridiumDisk.add(granulationQtyRepository.findById(76L).get());
            iridiumDisk.add(granulationQtyRepository.findById(77L).get());
            iridiumDisk.add(granulationQtyRepository.findById(78L).get());
            iridiumDisk.add(granulationQtyRepository.findById(79L).get());
            iridiumDisk.add(granulationQtyRepository.findById(80L).get());
            iridiumDisk.add(granulationQtyRepository.findById(81L).get());
            iridiumDisk.add(granulationQtyRepository.findById(82L).get());
            iridiumDisk.add(granulationQtyRepository.findById(83L).get());
            
            List<GranulationQty> qSilver = new ArrayList<>();
			
            qSilver.add(granulationQtyRepository.findById(84L).get());
            qSilver.add(granulationQtyRepository.findById(85L).get());
            qSilver.add(granulationQtyRepository.findById(86L).get());
            qSilver.add(granulationQtyRepository.findById(87L).get());
            qSilver.add(granulationQtyRepository.findById(88L).get());
            qSilver.add(granulationQtyRepository.findById(89L).get());
            
            List<GranulationQty> qIridium = new ArrayList<>();
			
            qIridium.add(granulationQtyRepository.findById(90L).get());
            qIridium.add(granulationQtyRepository.findById(91L).get());
            qIridium.add(granulationQtyRepository.findById(92L).get());
            qIridium.add(granulationQtyRepository.findById(93L).get());
            qIridium.add(granulationQtyRepository.findById(94L).get());
            qIridium.add(granulationQtyRepository.findById(95L).get());
            
            List<GranulationQty> goldSoft = new ArrayList<>();
			
            goldSoft.add(granulationQtyRepository.findById(96L).get());
            goldSoft.add(granulationQtyRepository.findById(97L).get());
            goldSoft.add(granulationQtyRepository.findById(98L).get());
            goldSoft.add(granulationQtyRepository.findById(99L).get());
            goldSoft.add(granulationQtyRepository.findById(100L).get());
            
            List<GranulationQty> goldDisk = new ArrayList<>();
			
            goldDisk.add(granulationQtyRepository.findById(101L).get());
            
            List<GranulationQty> mirlonDisk = new ArrayList<>();
			
            mirlonDisk.add(granulationQtyRepository.findById(102L).get());
            
            List<GranulationQty> silverRoll = new ArrayList<>();
			
            silverRoll.add(granulationQtyRepository.findById(103L).get());
            silverRoll.add(granulationQtyRepository.findById(104L).get());
            silverRoll.add(granulationQtyRepository.findById(105L).get());
            silverRoll.add(granulationQtyRepository.findById(106L).get());
            silverRoll.add(granulationQtyRepository.findById(107L).get());
            silverRoll.add(granulationQtyRepository.findById(108L).get());
            silverRoll.add(granulationQtyRepository.findById(109L).get());
            silverRoll.add(granulationQtyRepository.findById(110L).get());
            silverRoll.add(granulationQtyRepository.findById(111L).get());
            silverRoll.add(granulationQtyRepository.findById(112L).get());
            silverRoll.add(granulationQtyRepository.findById(113L).get());
            
            List<GranulationQty> goldRoll = new ArrayList<>();
			
            goldRoll.add(granulationQtyRepository.findById(114L).get());
            goldRoll.add(granulationQtyRepository.findById(115L).get());
            goldRoll.add(granulationQtyRepository.findById(116L).get());
            goldRoll.add(granulationQtyRepository.findById(117L).get());
            goldRoll.add(granulationQtyRepository.findById(118L).get());
            goldRoll.add(granulationQtyRepository.findById(119L).get());
            goldRoll.add(granulationQtyRepository.findById(120L).get());
            goldRoll.add(granulationQtyRepository.findById(121L).get());
            goldRoll.add(granulationQtyRepository.findById(122L).get());
            
            
            List<GranulationQty> autonetRoll = new ArrayList<>();
			
            autonetRoll.add(granulationQtyRepository.findById(123L).get());
            autonetRoll.add(granulationQtyRepository.findById(124L).get());
            autonetRoll.add(granulationQtyRepository.findById(125L).get());
            autonetRoll.add(granulationQtyRepository.findById(126L).get());
            autonetRoll.add(granulationQtyRepository.findById(127L).get());
            autonetRoll.add(granulationQtyRepository.findById(128L).get());
            autonetRoll.add(granulationQtyRepository.findById(129L).get());
            
            List<GranulationQty> mirlonRoll = new ArrayList<>();
			
            mirlonRoll.add(granulationQtyRepository.findById(130L).get());
            mirlonRoll.add(granulationQtyRepository.findById(131L).get());
            
            
            sanderRepository.save(new Sander(1L, "Silver Disk", 100, Dimension.D150, Type.PAPER, silverDisk , Condition.DRY, manufacturerRepository.findById(1L).get(), true ,ABRAZIVE + "silverDisk.jpg"));
			sanderRepository.save(new Sander(2L, "Deflex Disk", 50, Dimension.D125, Type.PAPER, deflexDisk , Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "deflexDisk.jpg"));
			sanderRepository.save(new Sander(3L, "Microstar Disk", 50, Dimension.D150, Type.PLASTIC, microStar150 , Condition.DRY, manufacturerRepository.findById(1L).get(),true ,ABRAZIVE +  "microStar.webp"));
			sanderRepository.save(new Sander(4L, "Microstar Disk", 50, Dimension.D77, Type.PLASTIC, microStar80, Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "microStar80.jpg"));
			sanderRepository.save(new Sander(5L, "Autonet Disk", 50, Dimension.D150, Type.WIRE ,autonet150, Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "autonet.webp"));
			sanderRepository.save(new Sander(6L, "Autonet Block", 50,Dimension.D70X198, Type.WIRE,autonet70x198, Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "autonet70x198.jpg"));
			sanderRepository.save(new Sander(7L, "Abranet Disk", 50,Dimension.D150, Type.WIRE,abranetDisk150, Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "abranet.jpg"));
			sanderRepository.save(new Sander(8L, "Abranet Disk", 50,Dimension.D77, Type.WIRE, abranetDisk80 , Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "abranet80.webp"));
            sanderRepository.save(new Sander(9L, "Abralon Disk", 20, Dimension.D150, Type.FOAM, abralonDisk150 , Condition.WET, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "abralon.jpg"));
			sanderRepository.save(new Sander(10L, "Abralon Disk", 20, Dimension.D77, Type.FOAM, abralonDisk80 , Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "abralon80.jpg"));
			sanderRepository.save(new Sander(11L, "WPF", 50,Dimension.DA4, Type.PAPER, wpf, Condition.WET, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "wpf220.jpg"));
			sanderRepository.save(new Sander(12L, "WPF", 50 ,Dimension.DA8, Type.PAPER, wpf1500 , Condition.WET, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "wpf1500.png"));
			sanderRepository.save(new Sander(13L, "Iridium Disk", 100, Dimension.D150, Type.PLASTIC, iridiumDisk , Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "iridium.jpg"));
			sanderRepository.save(new Sander(14L, "Silver Block", 50, Dimension.D70X420, Type.PAPER, qSilver , Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "rashpa.jpg"));
			sanderRepository.save(new Sander(15L, "Iridium Block", 100, Dimension.D70X400, Type.PLASTIC, qIridium , Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "iridiumRashpa.webp"));
			sanderRepository.save(new Sander(16L, "Gold Disk", 50 ,Dimension.D150, Type.PAPER,goldDisk, Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "goldDisk.jpg"));
			sanderRepository.save(new Sander(17L, "Mirlon Disk", 10,Dimension.D150, Type.WIRE,mirlonDisk, Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "mirlonDisk.jpg"));
			sanderRepository.save(new Sander(18L, "Goldflex Soft", 200 ,Dimension.D115X125, Type.FOAM, goldSoft , Condition.DRY, manufacturerRepository.findById(1L).get(), true ,ABRAZIVE + "goldSoft.jpg"));
			sanderRepository.save(new Sander(19L, "Silver Roll", 1, Dimension.D50X115, Type.PAPER, silverRoll , Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "silverRoll.webp"));
			sanderRepository.save(new Sander(20L, "Gold Roll", 1, Dimension.D50X115, Type.PAPER, goldRoll , Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "goldRoll.jpg"));
			sanderRepository.save(new Sander(21L, "Autonet Roll", 1, Dimension.D10X115, Type.WIRE, autonetRoll , Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "autonetRoll.jpg"));
			sanderRepository.save(new Sander(22L, "Mirlon Roll", 1, Dimension.D10X115, Type.WIRE, mirlonRoll, Condition.DRY, manufacturerRepository.findById(1L).get(),true , ABRAZIVE + "mirlonRoll.jpg"));

            
			hardenerRepository.save(new Hardener(1L, "SP2099 2K Hardener Medium" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 600.00 ,Weigth.ONE, true , HARDENERS + "2099.png" , manufacturerRepository.findById(3L).get()));
			hardenerRepository.save(new Hardener(2L, "SP2299 2K Hardener Very Fast" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 600.00 ,Weigth.ONE, true , HARDENERS + "2299.png" , manufacturerRepository.findById(3L).get()));
			
			hardenerRepository.save(new Hardener(3L, "SP2501 HS Hardener Medium" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1750.00 ,Weigth.TWO, true , HARDENERS + "2501.png" , manufacturerRepository.findById(3L).get()));
			hardenerRepository.save(new Hardener(4L, "SP2511 HS Hardener Fast" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1750.00 ,Weigth.TWO, true , HARDENERS + "2511.png" , manufacturerRepository.findById(3L).get()));
			
            hardenerRepository.save(new Hardener(5L, "47-50 2K Hardener Medium" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 800.00 ,Weigth.ONE, true , HARDENERS + "47-50.jpg" , manufacturerRepository.findById(2L).get()));
			hardenerRepository.save(new Hardener(6L, "47-40 2K Hardener Fast" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 800.00 ,Weigth.ONE, true , HARDENERS + "47-40.jpg" , manufacturerRepository.findById(2L).get()));
			hardenerRepository.save(new Hardener(7L, "47-30 2K Hardener Very Fast" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 800.00 , Weigth.ONE, true , HARDENERS + "47-30.jpg" , manufacturerRepository.findById(2L).get()));
			
			hardenerRepository.save(new Hardener(8L, "8-150 HS Hardener Medium" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1020.00 ,Weigth.ONE, true , HARDENERS + "8-150.jpg" , manufacturerRepository.findById(2L).get()));
			hardenerRepository.save(new Hardener(9L, "8-140 HS Hardener Fast" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1020.00 ,Weigth.ONE, true , HARDENERS + "8-140.png" , manufacturerRepository.findById(2L).get()));
			hardenerRepository.save(new Hardener(10L, "8-130 HS Hardener Very Fast" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1020.00 ,Weigth.ONE, true , HARDENERS + "8-130.jpg" , manufacturerRepository.findById(2L).get()));
			
			hardenerRepository.save(new Hardener(11L, "8-450 Air Dry HS420 Hardener Medium" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1300.00 ,Weigth.ONE, true , HARDENERS + "8-450.jpg" , manufacturerRepository.findById(2L).get()));
			hardenerRepository.save(new Hardener(12L, "8-440 Air Dry HS420 Hardener Fast" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1300.00 ,Weigth.ONE, true , HARDENERS + "8-440.jpg" , manufacturerRepository.findById(2L).get()));
			
			hardenerRepository.save(new Hardener(13L, "1-70 Epoxy Primer Hardener" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 800.00 ,Weigth.ONE, true , HARDENERS + "1-70.jpg" , manufacturerRepository.findById(2L).get()));
			
			hardenerRepository.save(new Hardener(14L, "1-10 Washprimer Hardener" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 570.00 ,Weigth.ONE, true , HARDENERS + "1-10.jpg" , manufacturerRepository.findById(2L).get()));
			
			List<Hardener> ms = new ArrayList<>();
			ms.add(hardenerRepository.findById(1L).get());
			ms.add(hardenerRepository.findById(2L).get());
			
			List<Hardener> spHs = new ArrayList<>();
			spHs.add(hardenerRepository.findById(3L).get());
			spHs.add(hardenerRepository.findById(4L).get());
			
			List<Hardener> k2 = new ArrayList<>();
			k2.add(hardenerRepository.findById(5L).get());
			k2.add(hardenerRepository.findById(6L).get());
			k2.add(hardenerRepository.findById(7L).get());
			
			List<Hardener> matt = new ArrayList<>();
			k2.add(hardenerRepository.findById(5L).get());
			k2.add(hardenerRepository.findById(6L).get());
			k2.add(hardenerRepository.findById(7L).get());
			
			List<Hardener> hs = new ArrayList<>();
			hs.add(hardenerRepository.findById(8L).get());
			hs.add(hardenerRepository.findById(9L).get());
			hs.add(hardenerRepository.findById(10L).get());
			
			List<Hardener> uhs = new ArrayList<>();
			uhs.add(hardenerRepository.findById(11L).get());
			uhs.add(hardenerRepository.findById(12L).get());
			
			List<Hardener> filler = new ArrayList<>();
			filler.add(hardenerRepository.findById(1L).get());
			filler.add(hardenerRepository.findById(2L).get());
			filler.add(hardenerRepository.findById(3L).get());
			filler.add(hardenerRepository.findById(4L).get());
			filler.add(hardenerRepository.findById(5L).get());
			filler.add(hardenerRepository.findById(6L).get());
			filler.add(hardenerRepository.findById(7L).get());
			filler.add(hardenerRepository.findById(8L).get());
			filler.add(hardenerRepository.findById(9L).get());
			filler.add(hardenerRepository.findById(10L).get());
			filler.add(hardenerRepository.findById(11L).get());
			filler.add(hardenerRepository.findById(12L).get());
			
			List<Hardener> epoxy = new ArrayList<>();
			epoxy.add(hardenerRepository.findById(13L).get());
			
			List<Hardener> washprimer = new ArrayList<>();
			washprimer.add(hardenerRepository.findById(14L).get());
			
			coatRepository.save(new Coat(1L, "SP4699 MS Clear Coat High Gloss" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 2150.00,Weigth.FIVE, true ,COATS + "4699.png" , ms , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(2L, "SP4501 HS Clear Coat 2:1" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 2450.00,Weigth.FIVE, true ,COATS + "4501.png" , spHs , manufacturerRepository.findById(2L).get() ));
			coatRepository.save(new Coat(3L, "SP4502 HS Anti Scratch Clear 2:1" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 2950.00,Weigth.FIVE, true ,COATS + "4502.png" , spHs , manufacturerRepository.findById(2L).get() ));
			
			coatRepository.save(new Coat(4L, "1-103 2K Clear Coat" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 2650.00,Weigth.FIVE, true ,COATS + "1-103.jpg" , k2 , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(5L, "1-204 MS Clear Coat" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 3050.00,Weigth.FIVE, true ,COATS + "1-204.jpg" , k2 , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(6L, "8-104 HS Clear Coat" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 2350.00,Weigth.FIVE, true ,COATS + "8-104.jpg" , hs , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(7L, "8-214 HS Scratch Resistant Clear Coat" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 4250.00,Weigth.FIVE, true ,COATS + "8-214.jpg" , hs , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(8L, "8-614 HS420 Air Dry Clear Coat" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 4950.00,Weigth.FIVE, true ,COATS + "8-614.jpg" , uhs , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(8L, "8-114 Scratch Resistant Fast Repair Clear" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 5050.00,Weigth.FIVE, true ,COATS + "8-114.jpg" , uhs , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(9L, "1-105 MS Clear Coat Matt" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1050.00,Weigth.FIVE, true ,COATS + "1-105.jpg" , matt , manufacturerRepository.findById(2L).get()));
			
			primerRepository.save(new Primer(1L, "8-14510 HS Surfacer, WHITE " ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 2500.00,Weigth.THREE, true , PRIMERS +  "8-14510.jpg" , filler , manufacturerRepository.findById(2L).get()));
			primerRepository.save(new Primer(2L, "8-14540 HS Surfacer, BLACK" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 2500.00,Weigth.THREE, true , PRIMERS + "8-14540.jpg" , filler , manufacturerRepository.findById(2L).get()));
			primerRepository.save(new Primer(3L, "SP5289 Universal Primer Filler White" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 2000.00,Weigth.THREE, true , PRIMERS + "5289.png" , filler , manufacturerRepository.findById(3L).get() ));
			primerRepository.save(new Primer(4L, "SP5279 Universal Primer Filler Black" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 2000.00, Weigth.THREE, true , PRIMERS + "5279.png" , filler , manufacturerRepository.findById(3L).get()));
			
			primerRepository.save(new Primer(5L, "1-7520 Epoxy Primer Grey" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1100.00,Weigth.ONE, true , PRIMERS + "epoxy.png" , epoxy , manufacturerRepository.findById(2L).get()));
			
			primerRepository.save(new Primer(6L, "1-15 Washprimer" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1000.00,Weigth.ONE, true , PRIMERS + "1-15.jpg" , washprimer , manufacturerRepository.findById(2L).get()));
			
			thinnerRepository.save(new Thinner(1L,"SP3099 FAST THINNER" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1250.00,Weigth.FIVE, true ,THINNERS + "3099.png" , manufacturerRepository.findById(3L).get()));
			thinnerRepository.save(new Thinner(2L,"SP3199 MEDIUM THINNER" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1250.00,Weigth.FIVE, true , THINNERS + "3199.png" , manufacturerRepository.findById(3L).get()));
			thinnerRepository.save(new Thinner(3L,"SP3299 SLOW THINNER" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1250.00,Weigth.FIVE, true , THINNERS + "3299.png" , manufacturerRepository.findById(3L).get()));
			thinnerRepository.save(new Thinner(4L,"1-151 Uni Thinner Medium" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1850.00,Weigth.FIVE, true , THINNERS + "1-151.jpg" , manufacturerRepository.findById(2L).get()));
			thinnerRepository.save(new Thinner(5L,"47-91 2K Spot Repair Thinner " ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 500.00,Weigth.FIVE, true , THINNERS + "47-91.jpg" , manufacturerRepository.findById(2L).get()));
			thinnerRepository.save(new Thinner(5L,"SP6499 SILICONE REMOVER" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1150.00,Weigth.FIVE, true , THINNERS + "6499.png" , manufacturerRepository.findById(3L).get()));
			
			puttyRepository.save(new Putty(1L,"SP7031 BodyWorks All-In-One" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,",570.00,Weigth.ONE, true, PUTTIES + "7031.png" , manufacturerRepository.findById(3L).get()));
			puttyRepository.save(new Putty(2L,"SP7011 BodyWorks Ultralight" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,",370.00,Weigth.ONE, true, PUTTIES + "7011.jpg" , manufacturerRepository.findById(3L).get()));
			puttyRepository.save(new Putty(3L,"1-909 Universal Body Filler" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,",660.00,Weigth.ONE, true, PUTTIES + "1-909.png" , manufacturerRepository.findById(2L).get()));
			puttyRepository.save(new Putty(4L,"6080 Spray Filler" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,",1000.00,Weigth.ONE, true, PUTTIES + "6080.png" , manufacturerRepository.findById(2L).get()));
			
			nozzleRepository.save(new Nozzle(1L, "Nozzle set for SPG 100" , NozzleSize.PRV, 1200.00,true, NOZZLES + "fin08.jpg", manufacturerRepository.findById(4L).get()));
			nozzleRepository.save(new Nozzle(2L, "Nozzle set for SPG 100" , NozzleSize.VTOR, 1200.00,true, NOZZLES + "fin1.jpg", manufacturerRepository.findById(4L).get()));
			
			nozzleRepository.save(new Nozzle(3L, "Nozzle set for SPG 500" , NozzleSize.TRET, 1500.00,true, NOZZLES + "fin13.jpg", manufacturerRepository.findById(4L).get()));
			nozzleRepository.save(new Nozzle(4L, "Nozzle set for SPG 500" , NozzleSize.PETTI, 1500.00,true, NOZZLES + "fin15.jpg", manufacturerRepository.findById(4L).get()));
			nozzleRepository.save(new Nozzle(5L, "Nozzle set for SPG 500" , NozzleSize.SEDMA, 1500.00,true, NOZZLES + "fin18.jpg", manufacturerRepository.findById(4L).get()));
			nozzleRepository.save(new Nozzle(6L, "Nozzle set for SPG 500" , NozzleSize.OSMA, 1500.00,true, NOZZLES + "fin2.jpg", manufacturerRepository.findById(4L).get()));
			nozzleRepository.save(new Nozzle(7L, "Nozzle set for SPG 500" , NozzleSize.DEVETA, 1500.00,true, NOZZLES + "fin25.jpg", manufacturerRepository.findById(4L).get()));
			
			nozzleRepository.save(new Nozzle(8L, "Nozzle set for SATA JET 100 RP" , NozzleSize.CETVRT, 6500.00,true, NOZZLES + "sata10014.jpg", manufacturerRepository.findById(5L).get()));
			nozzleRepository.save(new Nozzle(9L, "Nozzle set for SATA JET 100 RP" , NozzleSize.SESTI, 6500.00,true, NOZZLES + "sata10016.jpg", manufacturerRepository.findById(5L).get()));
			
			nozzleRepository.save(new Nozzle(10L, "Nozzle set for SATA JET 5500 RP" ,NozzleSize.TRET, 12500.00,true, NOZZLES + "sata5500rp13.jpg", manufacturerRepository.findById(5L).get()));
			nozzleRepository.save(new Nozzle(11L, "Nozzle set for SATA JET 5500 RP" ,NozzleSize.PETTI, 12500.00,true, NOZZLES + "sata5500rp15.jpg", manufacturerRepository.findById(5L).get()));
			
			nozzleRepository.save(new Nozzle(12L, "Nozzle set for SATA JET 5500 HVLP" ,NozzleSize.TRET, 13500.00,true,NOZZLES + "sata5500hvlp13.jpg", manufacturerRepository.findById(5L).get()));
			nozzleRepository.save(new Nozzle(13L, "Nozzle set for SATA JET 5500 HVLP" ,NozzleSize.PETTI, 13500.00,true,NOZZLES + "sata5500hvlp15.jpg", manufacturerRepository.findById(5L).get()));
			
			List<Nozzle> f100 = new ArrayList<>();
			f100.add(nozzleRepository.findById(1L).get());
			f100.add(nozzleRepository.findById(2L).get());
			
			List<Nozzle> f500 = new ArrayList<>();
			f500.add(nozzleRepository.findById(3L).get());
			f500.add(nozzleRepository.findById(4L).get());
			f500.add(nozzleRepository.findById(5L).get());
			f500.add(nozzleRepository.findById(7L).get());
			f500.add(nozzleRepository.findById(8L).get());
			
			List<Nozzle> sata100 = new ArrayList<>();
			sata100.add(nozzleRepository.findById(8L).get());
			sata100.add(nozzleRepository.findById(9L).get());
			
			List<Nozzle> satarp = new ArrayList<>();
			satarp.add(nozzleRepository.findById(10L).get());
			satarp.add(nozzleRepository.findById(11L).get());
			
			List<Nozzle> satahvlp= new ArrayList<>();
			satahvlp.add(nozzleRepository.findById(12L).get());
			satahvlp.add(nozzleRepository.findById(13L).get());
			
			gunRepository.save(new SprayGun(1L, "Finixa SPG 100" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 3500.00 , true , f100 , manufacturerRepository.findById(4L).get(), GUNS +  "finMini.jpg" ));
			gunRepository.save(new SprayGun(2L, "Finixa SPG 500" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 4400.00 , true , f500 , manufacturerRepository.findById(4L).get(), GUNS +  "finixaGun.jpg" ));
			
			gunRepository.save(new SprayGun(3L, "SATA JET 100" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1600.00 , true , sata100 , manufacturerRepository.findById(5L).get(), GUNS +  "sata100rp.png" ));
			gunRepository.save(new SprayGun(4L, "SATA JET 5500 RP" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 34000.00 , true , satarp , manufacturerRepository.findById(5L).get(), GUNS +  "sata5500rp.jpg" ));
			gunRepository.save(new SprayGun(5L, "SATA JET 5500 HVLP" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 35000.00 , true , satahvlp , manufacturerRepository.findById(5L).get(), GUNS +  "sata5500hvlp.jpg" ));
			
			extrasRepository.save(new Extras(1L , "Sata Gravity Cup" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 3200.00 , true , manufacturerRepository.findById(5L).get(), EXTRAS + "sataCup.png"));
			extrasRepository.save(new Extras(2L , "Finixa Gravity Cup" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 800.00 , true , manufacturerRepository.findById(4L).get(), EXTRAS + "finixaCup.jpg"));
			extrasRepository.save(new Extras(3L , "Finixa Regulator" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 2500.00 , true , manufacturerRepository.findById(4L).get(), EXTRAS + "gunMano.jpg"));
			extrasRepository.save(new Extras(4L , "Spiralflex 4011" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 90.00 , true , manufacturerRepository.findById(6L).get(), EXTRAS + "gunConnector.jpg"));
			extrasRepository.save(new Extras(5L , "Spiralflex 4003" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 350.00 , true , manufacturerRepository.findById(6L).get(), EXTRAS + "wallConnector.jpg"));
			extrasRepository.save(new Extras(6L , "Spiralflex Air Purifier" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 3700.00 , true , manufacturerRepository.findById(6L).get(), EXTRAS + "airPurifierMano.gif"));
			extrasRepository.save(new Extras(7L , "Spiralflex Air Purifier" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 2700.00 , true , manufacturerRepository.findById(6L).get(), EXTRAS + "airPurifier.gif"));
			extrasRepository.save(new Extras(8L , "SPIRALFLEX SPRAYGUN HOSE" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1900.00 , true , manufacturerRepository.findById(6L).get(), EXTRAS + "hose.jpg")); 
			
			padsRepository.save(new Pads(1L, "White Lambswool Pad 150mm" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 460.00 , true, manufacturerRepository.findById(1L).get(), PADS + "belo150.jpg"));
			padsRepository.save(new Pads(2L, "Yellow Lambswool Pad 150mm " ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1300.00 , true, manufacturerRepository.findById(1L).get(), PADS + "zolto150.jpg"));
			padsRepository.save(new Pads(3L, "Black Waffle 150mm " , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 850.00 , true, manufacturerRepository.findById(1L).get(), PADS + "blackWaffle.jpg"));
			padsRepository.save(new Pads(4L, "Yellow Waffle 150mm" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 880.00 , true, manufacturerRepository.findById(1L).get(), PADS + "yellowWaffle150.jpg"));
			padsRepository.save(new Pads(5L, "Yellow Waffle 85mm " , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 500.00 , true, manufacturerRepository.findById(1L).get(), PADS + "yellowWaffle80.jpg"));
			padsRepository.save(new Pads(6L, "Twisted Wool Pad 180mm " ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1100.00 , true, manufacturerRepository.findById(1L).get(), PADS + "belo180.jpg"));
			padsRepository.save(new Pads(7L, "Yellow Lambswool Pad 80mm" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 550.00 , true, manufacturerRepository.findById(1L).get(), PADS + "krzno80.jfif"));
			padsRepository.save(new Pads(8L, "White Polishing Felt Pad 125x6mm " , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 880.00 , true, manufacturerRepository.findById(1L).get(), PADS + "staklo.jfif"));
			padsRepository.save(new Pads(9L, "White Foam Pad 150mm " ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 620.00 , true, manufacturerRepository.findById(4L).get(), PADS + "white150.jpg"));
			padsRepository.save(new Pads(10L, "Black Foam Pad 150mm " ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 620.00 , true, manufacturerRepository.findById(4L).get(), PADS + "black150.jpg"));
			padsRepository.save(new Pads(11L, "Orange Foam Pad 150mm " ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 620.00 , true, manufacturerRepository.findById(4L).get(), PADS + "orange150.jpg"));
			
			List<Pads> first = new ArrayList<>();
			first.add(padsRepository.findById(1L).get());
			first.add(padsRepository.findById(2L).get());
			first.add(padsRepository.findById(6L).get());
			first.add(padsRepository.findById(7L).get());
			first.add(padsRepository.findById(9L).get());
			first.add(padsRepository.findById(11L).get());
			
			List<Pads> ten = new ArrayList<>();
			ten.add(padsRepository.findById(1L).get());
			ten.add(padsRepository.findById(2L).get());
			ten.add(padsRepository.findById(3L).get());
			ten.add(padsRepository.findById(4L).get());
			ten.add(padsRepository.findById(5L).get());
			ten.add(padsRepository.findById(6L).get());
			ten.add(padsRepository.findById(7L).get());
			ten.add(padsRepository.findById(9L).get());
		
			List<Pads> last = new ArrayList<>();
			last.add(padsRepository.findById(3L).get());
			last.add(padsRepository.findById(4L).get());
			last.add(padsRepository.findById(5L).get());
			last.add(padsRepository.findById(10L).get());
			
			List<Pads> glass = new ArrayList<>();
			glass.add(padsRepository.findById(8L).get());
			
			polishRepository.save(new Polish(1L, "Polarshine E3 Glass Polishing Componenet", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 3800.00 , true ,glass, manufacturerRepository.findById(1L).get(), POLISH + "glass.jpg" ));
			polishRepository.save(new Polish(2L, "Polarshine 5 Finishing Component", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 2350.00 , true ,last, manufacturerRepository.findById(1L).get(), POLISH + "5ka.jpg" ));
			polishRepository.save(new Polish(3L, "Polarshine 10 , 2 in 1","Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1750.00 , true ,ten, manufacturerRepository.findById(1L).get(), POLISH + "10ka.jpg" ));
			polishRepository.save(new Polish(4L, "Polarshine 25 Grip 1000", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1300.00 , true ,first, manufacturerRepository.findById(1L).get(), POLISH + "25ka.jpg" ));
			polishRepository.save(new Polish(5L, "Polarshine 35 Grip 800", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1600.00 , true ,first, manufacturerRepository.findById(1L).get(), POLISH + "35ka.jpg" ));
			
			toolRepository.save(new Tool(1L, "Mirka DEROS Central Vacuum Orbit 5,0" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 31000.00, true, Power.ELECTRIC, manufacturerRepository.findById(1L).get(), TOOLS + "deros.jpg"));
			toolRepository.save(new Tool(2L, "Mirka DEOS Central Vacuum Orbit 3,0" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 31000.00, true, Power.ELECTRIC, manufacturerRepository.findById(1L).get(), TOOLS +  "deos.jpg"));
			toolRepository.save(new Tool(3L, "Mirka PROS Central Vacuum Orbit 5.0" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 18000.00, true, Power.PNEUMATIC, manufacturerRepository.findById(1L).get(), TOOLS +  "pros.jpg"));
			toolRepository.save(new Tool(4L, "Mirka PS 1437 Polisher 150mm" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 24000.00, true, Power.ELECTRIC, manufacturerRepository.findById(1L).get(), TOOLS +  "polish.jpg"));
			toolRepository.save(new Tool(5L, "Mirka Dust Extractor" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 31000.00, true, Power.ELECTRIC, manufacturerRepository.findById(1L).get(), TOOLS +  "vacuum.jpg"));
			toolRepository.save(new Tool(6L, "Finixa Orbital Palm Sander" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 8800.00, true, Power.PNEUMATIC, manufacturerRepository.findById(4L).get(), TOOLS +  "miniSander.jpg"));
			toolRepository.save(new Tool(6L, "Finixa Electric Polisher" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 18000.00, true, Power.ELECTRIC, manufacturerRepository.findById(4L).get(), TOOLS +  "finPolisher.jpg"));
			
			sizeQtyRepository.save(new SizeQuantity( 1L , true , Size.L));
			sizeQtyRepository.save(new SizeQuantity( 2L , true , Size.XL));
			
			List<SizeQuantity> gloves = new ArrayList<>();
			gloves.add(sizeQtyRepository.findById(1L).get());
			gloves.add(sizeQtyRepository.findById(2L).get());
			
			sizeQtyRepository.save(new SizeQuantity( 3L , true , Size.S));
			sizeQtyRepository.save(new SizeQuantity( 4L , true ,  Size.M));
			sizeQtyRepository.save(new SizeQuantity( 5L , true , Size.L));
			sizeQtyRepository.save(new SizeQuantity( 6L , true , Size.XL));
			sizeQtyRepository.save(new SizeQuantity( 7L , true , Size.XXL));
			sizeQtyRepository.save(new SizeQuantity( 8L , true , Size.XXXL));
			
			List<SizeQuantity> overalls = new ArrayList<>();
			
			overalls.add(sizeQtyRepository.findById(3L).get());
			overalls.add(sizeQtyRepository.findById(4L).get());
			overalls.add(sizeQtyRepository.findById(5L).get());
			overalls.add(sizeQtyRepository.findById(6L).get());
			overalls.add(sizeQtyRepository.findById(7L).get());
			overalls.add(sizeQtyRepository.findById(8L).get());
		
			safetyRepository.save(new Safety(1L, "Finixa Nitril Disposable Gloves" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1300.00 , true , gloves ,manufacturerRepository.findById(4L).get(), SAFETIES + "glovesL.jpg" ));
			safetyRepository.save(new Safety(2L, "Finixa MAS 00 Spray mask A1 P2" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 2300.00 , true , null, manufacturerRepository.findById(4L).get(), SAFETIES + "MAS00.jpg" ));
			safetyRepository.save(new Safety(3L, "Finixa MAS 13 Carbon Dust Mask Safety Class FFP2." , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 250.00 , true , null, manufacturerRepository.findById(4L).get(), SAFETIES + "MAS13.jpg" ));
			safetyRepository.save(new Safety(4L, "Finixa Spray Overalls" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 1400.00 ,true , overalls, manufacturerRepository.findById(4L).get(), SAFETIES + "suit.jpg" ));
			safetyRepository.save(new Safety(11L, "SATA Air Star F Spray Mask A2 P3" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,", 5200.00 ,true , null ,manufacturerRepository.findById(5L).get(), SAFETIES + "sataMas.png" ));
			
			List <Sander> d70x400 = new ArrayList<>();
			d70x400.add(sanderRepository.findById(14L).get());
			d70x400.add(sanderRepository.findById(15L).get());
			
			List <Sander> d70x198 = new ArrayList<>();
			d70x198.add(sanderRepository.findById(14L).get());
			d70x198.add(sanderRepository.findById(15L).get());
			d70x198.add(sanderRepository.findById(20L).get());
			
			List <Sander> d115x230 = new ArrayList<>();
			d115x230.add(sanderRepository.findById(6L).get());
			
			List <Sander> wet = new ArrayList<>();
			wet.add(sanderRepository.findById(11L).get());
			wet.add(sanderRepository.findById(12L).get());
			
			List <Sander> dry = new ArrayList<>();
			wet.add(sanderRepository.findById(19L).get());
			wet.add(sanderRepository.findById(20L).get());
			wet.add(sanderRepository.findById(21L).get());

			helperRepository.save(new Helper(1L , "Mirka File Board Flexible Yellow" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis," , 5500.00 , true , Dimension.D70X400 ,manufacturerRepository.findById(1L).get(), BLOCKS + "flex.jpg", d70x400));
			helperRepository.save(new Helper(2L , "Mirka Sanding Block 36H Grey" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis," , 1300.00 , true , Dimension.D115X230 ,manufacturerRepository.findById(1L).get(), BLOCKS + "150x230.jpg", d115x230));
			helperRepository.save(new Helper(3L , "Mirka Sanding Block 22H Grey" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,",  1100.00 , true , Dimension.D70X198 ,manufacturerRepository.findById(1L).get(), BLOCKS + "70x198.jpg", d70x198));
			helperRepository.save(new Helper(4L , "Mirka Sanding Block 2-Sided Soft/Hard" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis," ,  500.00 , true , Dimension.D70X125 ,manufacturerRepository.findById(1L).get(), BLOCKS + "softHard.jpg", wet));
			helperRepository.save(new Helper(5L , "Mirka Rubber Sanding Block" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,",  1300.00 , true , Dimension.D70X125 ,manufacturerRepository.findById(1L).get(), BLOCKS + "redRubber.jpg",dry));
			helperRepository.save(new Helper(6L , "Mirka Curved Pad for 70x198mm Block 22H" ,"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,",  900.00 , true , Dimension.D70X198 ,manufacturerRepository.findById(1L).get(), BLOCKS + "convex.jpg",d70x198));
			helperRepository.save(new Helper(7L , "Finixa File Board" , "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis,",  3500.00 , true , Dimension.D70X400 ,manufacturerRepository.findById(4L).get(), BLOCKS + "flat.jpg",d70x400));
			
			
			} catch (Exception e) {
				System.out.println("Post construct NOT called");
		}
	}

}
