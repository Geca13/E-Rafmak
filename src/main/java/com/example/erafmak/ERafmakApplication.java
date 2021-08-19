package com.example.erafmak;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.erafmak.abraziveMaterials.helpers.HelperRepository;
import com.example.erafmak.abraziveMaterials.sander.Condition;
import com.example.erafmak.abraziveMaterials.sander.Dimension;
import com.example.erafmak.abraziveMaterials.sander.Granulation;
import com.example.erafmak.abraziveMaterials.sander.Sander;
import com.example.erafmak.abraziveMaterials.sander.SanderRepository;
import com.example.erafmak.abraziveMaterials.sander.Type;
import com.example.erafmak.coatsAndPrimers.repository.CoatRepository;
import com.example.erafmak.coatsAndPrimers.repository.HardenerRepository;
import com.example.erafmak.coatsAndPrimers.repository.PrimerRepository;
import com.example.erafmak.coatsAndPrimers.repository.PuttyRepository;
import com.example.erafmak.coatsAndPrimers.repository.ThinnerRepository;
import com.example.erafmak.manufacturers.Manufacturer;
import com.example.erafmak.manufacturers.ManufacturerRepository;
import com.example.erafmak.manufacturers.Origin;
import com.example.erafmak.manufacturers.OriginRepository;
import com.example.erafmak.polish.PolishRepository;
import com.example.erafmak.sprayGuns.repository.ExtrasRepository;
import com.example.erafmak.sprayGuns.repository.NozzleRepository;
import com.example.erafmak.sprayGuns.repository.SprayGunRepository;
import com.example.erafmak.tools.ToolRepository;

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

	public static void main(String[] args) {
		SpringApplication.run(ERafmakApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		
		try {
			
			originRepository.save(new Origin(1L, "Finland"));
			originRepository.save(new Origin(2L, "Nederlands"));
			originRepository.save(new Origin(3L, "Italy"));
			originRepository.save(new Origin(4L, "Belgium"));
			originRepository.save(new Origin(5L, "Germany"));
			
			manufacturerRepository.save(new Manufacturer(1L, "MIRKA",originRepository.findById(1L).get() ,""));
			
			sanderRepository.save(new Sander(1L, "Silver Disk", 100, 10 , 2100.00 ,Dimension.VTORA, Type.PAPER,Granulation.TRETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://helcom-trade.hr/upload/catalog/product/228/thumb/mirka-qsilver-150mm-500x500_5eb2b12eb4c0d_300x300r.jpg"));
			sanderRepository.save(new Sander(2L, "Silver Disk", 100, 10 , 1910.00,Dimension.VTORA, Type.PAPER,Granulation.CHETVRTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://helcom-trade.hr/upload/catalog/product/228/thumb/mirka-qsilver-150mm-500x500_5eb2b12eb4c0d_300x300r.jpg"));
			sanderRepository.save(new Sander(3L, "Silver Disk", 100, 10 ,1910.00,Dimension.VTORA, Type.PAPER,Granulation.PETTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://helcom-trade.hr/upload/catalog/product/228/thumb/mirka-qsilver-150mm-500x500_5eb2b12eb4c0d_300x300r.jpg"));
			sanderRepository.save(new Sander(4L, "Silver Disk", 100, 10 ,1910.00,Dimension.VTORA, Type.PAPER,Granulation.SHESTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://helcom-trade.hr/upload/catalog/product/228/thumb/mirka-qsilver-150mm-500x500_5eb2b12eb4c0d_300x300r.jpg"));
			sanderRepository.save(new Sander(5L, "Silver Disk", 100, 10 ,1910.00,Dimension.VTORA, Type.PAPER,Granulation.SEDMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://helcom-trade.hr/upload/catalog/product/228/thumb/mirka-qsilver-150mm-500x500_5eb2b12eb4c0d_300x300r.jpg"));
			sanderRepository.save(new Sander(6L, "Silver Disk", 100, 10 ,1910.00,Dimension.VTORA, Type.PAPER,Granulation.OSMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://helcom-trade.hr/upload/catalog/product/228/thumb/mirka-qsilver-150mm-500x500_5eb2b12eb4c0d_300x300r.jpg"));
			sanderRepository.save(new Sander(7L, "Silver Disk", 100, 10 ,1910.00,Dimension.VTORA, Type.PAPER,Granulation.DESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://helcom-trade.hr/upload/catalog/product/228/thumb/mirka-qsilver-150mm-500x500_5eb2b12eb4c0d_300x300r.jpg"));
			sanderRepository.save(new Sander(8L, "Silver Disk", 100, 10 ,1910.00,Dimension.VTORA, Type.PAPER,Granulation.DVANAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://helcom-trade.hr/upload/catalog/product/228/thumb/mirka-qsilver-150mm-500x500_5eb2b12eb4c0d_300x300r.jpg"));
			
			sanderRepository.save(new Sander(9L, "Deflex Disk", 50, 10 ,900.00,Dimension.PRVA, Type.PAPER,Granulation.PRVA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://i.simpalsmedia.com/thumbor/0x350/i.simpalsmedia.com/marketplace/products/original/c7ffbaa08cd5826a9bdaf97fc36012df.jpg"));
			sanderRepository.save(new Sander(10L,"Deflex Disk", 50, 10 ,850.00,Dimension.PRVA, Type.PAPER,Granulation.VTORA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://i.simpalsmedia.com/thumbor/0x350/i.simpalsmedia.com/marketplace/products/original/c7ffbaa08cd5826a9bdaf97fc36012df.jpg"));
			sanderRepository.save(new Sander(11L,"Deflex Disk", 50, 10 ,800.00,Dimension.PRVA, Type.PAPER,Granulation.TRETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://i.simpalsmedia.com/thumbor/0x350/i.simpalsmedia.com/marketplace/products/original/c7ffbaa08cd5826a9bdaf97fc36012df.jpg"));
			sanderRepository.save(new Sander(12L, "Deflex Disk", 50, 10 ,700.00,Dimension.PRVA, Type.PAPER,Granulation.CHETVRTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://i.simpalsmedia.com/thumbor/0x350/i.simpalsmedia.com/marketplace/products/original/c7ffbaa08cd5826a9bdaf97fc36012df.jpg"));
			sanderRepository.save(new Sander(13L, "Deflex Disk", 50, 10 ,700.00,Dimension.PRVA, Type.PAPER,Granulation.PETTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://i.simpalsmedia.com/thumbor/0x350/i.simpalsmedia.com/marketplace/products/original/c7ffbaa08cd5826a9bdaf97fc36012df.jpg"));
			sanderRepository.save(new Sander(14L, "Deflex Disk", 50, 10 ,700.00,Dimension.PRVA, Type.PAPER,Granulation.SHESTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://i.simpalsmedia.com/thumbor/0x350/i.simpalsmedia.com/marketplace/products/original/c7ffbaa08cd5826a9bdaf97fc36012df.jpg"));
			sanderRepository.save(new Sander(15L, "Deflex Disk", 50, 10 ,700.00,Dimension.PRVA, Type.PAPER,Granulation.SEDMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://i.simpalsmedia.com/thumbor/0x350/i.simpalsmedia.com/marketplace/products/original/c7ffbaa08cd5826a9bdaf97fc36012df.jpg"));
			sanderRepository.save(new Sander(16L, "Deflex Disk", 50, 10 ,700.00,Dimension.PRVA, Type.PAPER,Granulation.OSMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://i.simpalsmedia.com/thumbor/0x350/i.simpalsmedia.com/marketplace/products/original/c7ffbaa08cd5826a9bdaf97fc36012df.jpg"));
			sanderRepository.save(new Sander(17L, "Deflex Disk", 50, 10 ,700.00,Dimension.PRVA, Type.PAPER,Granulation.DESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://i.simpalsmedia.com/thumbor/0x350/i.simpalsmedia.com/marketplace/products/original/c7ffbaa08cd5826a9bdaf97fc36012df.jpg"));
			
			sanderRepository.save(new Sander(18L, "Microstar Disk", 50, 10 ,1250.00,Dimension.VTORA, Type.PLASTIC,Granulation.SEDUMNAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://images-na.ssl-images-amazon.com/images/I/81v5vkI3q6L.__AC_SY300_SX300_QL70_FMwebp_.jpg"));
			sanderRepository.save(new Sander(19L, "Microstar Disk", 50, 10 ,1250.00,Dimension.VTORA, Type.PLASTIC,Granulation.OSUMNAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://images-na.ssl-images-amazon.com/images/I/81v5vkI3q6L.__AC_SY300_SX300_QL70_FMwebp_.jpg"));
			sanderRepository.save(new Sander(20L, "Microstar Disk", 50, 10 ,1250.00,Dimension.VTORA, Type.PLASTIC,Granulation.DEVETNAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://images-na.ssl-images-amazon.com/images/I/81v5vkI3q6L.__AC_SY300_SX300_QL70_FMwebp_.jpg"));
			sanderRepository.save(new Sander(21L, "Microstar Disk", 50, 10 ,1250.00,Dimension.VTORA, Type.PLASTIC,Granulation.DVAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://images-na.ssl-images-amazon.com/images/I/81v5vkI3q6L.__AC_SY300_SX300_QL70_FMwebp_.jpg"));
			
			sanderRepository.save(new Sander(22L, "Autonet Disk", 50, 10 ,1110.00,Dimension.VTORA, Type.WIRE ,Granulation.TRETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/mf_webp/jpg/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka-autonet-schuurschijven-125mm_1_2.webp"));
			sanderRepository.save(new Sander(23L, "Autonet Disk", 50, 10 ,1110.00,Dimension.VTORA, Type.WIRE ,Granulation.PETTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/mf_webp/jpg/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka-autonet-schuurschijven-125mm_1_2.webp"));
			sanderRepository.save(new Sander(24L, "Autonet Disk", 50, 10 ,1110.00,Dimension.VTORA, Type.WIRE ,Granulation.SHESTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/mf_webp/jpg/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka-autonet-schuurschijven-125mm_1_2.webp"));
			sanderRepository.save(new Sander(25L, "Autonet Disk", 50, 10 ,1110.00,Dimension.VTORA, Type.WIRE ,Granulation.SEDMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/mf_webp/jpg/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka-autonet-schuurschijven-125mm_1_2.webp"));
			sanderRepository.save(new Sander(26L, "Autonet Disk", 50, 10 ,1110.00,Dimension.VTORA, Type.WIRE ,Granulation.OSMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/mf_webp/jpg/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka-autonet-schuurschijven-125mm_1_2.webp"));
			sanderRepository.save(new Sander(27L, "Autonet Disk", 50, 10 ,1110.00,Dimension.VTORA, Type.WIRE ,Granulation.DESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/mf_webp/jpg/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka-autonet-schuurschijven-125mm_1_2.webp"));
			sanderRepository.save(new Sander(28L, "Autonet Disk", 50, 10 ,1110.00,Dimension.VTORA, Type.WIRE ,Granulation.DVANAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/mf_webp/jpg/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka-autonet-schuurschijven-125mm_1_2.webp"));
			
			sanderRepository.save(new Sander(29L, "Abranet Disk", 50, 10 ,2300.00,Dimension.VTORA, Type.WIRE,Granulation.VTORA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Abranet_6_Inch_Sanding_Disc_1_03145c28-da43-473e-8fbf-b55c19795dd2_360x.jpg?v=1616514796"));
			sanderRepository.save(new Sander(30L, "Abranet Disk", 50, 10 ,1900.00,Dimension.VTORA, Type.WIRE,Granulation.TRETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Abranet_6_Inch_Sanding_Disc_1_03145c28-da43-473e-8fbf-b55c19795dd2_360x.jpg?v=1616514796"));
			sanderRepository.save(new Sander(31L, "Abranet Disk", 50, 10 ,1900.00 ,Dimension.VTORA, Type.WIRE,Granulation.PETTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Abranet_6_Inch_Sanding_Disc_1_03145c28-da43-473e-8fbf-b55c19795dd2_360x.jpg?v=1616514796"));
			sanderRepository.save(new Sander(32L, "Abranet Disk", 50, 10 ,1900.00,Dimension.VTORA, Type.WIRE,Granulation.SHESTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Abranet_6_Inch_Sanding_Disc_1_03145c28-da43-473e-8fbf-b55c19795dd2_360x.jpg?v=1616514796"));
			sanderRepository.save(new Sander(33L, "Abranet Disk", 50, 10 ,1900.00,Dimension.VTORA, Type.WIRE,Granulation.SEDMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Abranet_6_Inch_Sanding_Disc_1_03145c28-da43-473e-8fbf-b55c19795dd2_360x.jpg?v=1616514796"));
			sanderRepository.save(new Sander(34L, "Abranet Disk", 50, 10 ,1900.00,Dimension.VTORA, Type.WIRE,Granulation.OSMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Abranet_6_Inch_Sanding_Disc_1_03145c28-da43-473e-8fbf-b55c19795dd2_360x.jpg?v=1616514796"));
			sanderRepository.save(new Sander(35L, "Abranet Disk", 50, 10 ,1900.00,Dimension.VTORA, Type.WIRE,Granulation.DESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Abranet_6_Inch_Sanding_Disc_1_03145c28-da43-473e-8fbf-b55c19795dd2_360x.jpg?v=1616514796"));
			sanderRepository.save(new Sander(36L, "Abranet Disk", 50, 10 ,1900.00,Dimension.VTORA, Type.WIRE,Granulation.DVANAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Abranet_6_Inch_Sanding_Disc_1_03145c28-da43-473e-8fbf-b55c19795dd2_360x.jpg?v=1616514796"));
			sanderRepository.save(new Sander(37L, "Abranet Disk", 50, 10 ,1900.00,Dimension.VTORA, Type.WIRE,Granulation.TRIENAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Abranet_6_Inch_Sanding_Disc_1_03145c28-da43-473e-8fbf-b55c19795dd2_360x.jpg?v=1616514796"));
			sanderRepository.save(new Sander(38L, "Abranet Disk", 50, 10 ,1900.00,Dimension.VTORA, Type.WIRE,Granulation.CHETIRINAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Abranet_6_Inch_Sanding_Disc_1_03145c28-da43-473e-8fbf-b55c19795dd2_360x.jpg?v=1616514796"));
			sanderRepository.save(new Sander(39L, "Abranet Disk", 50, 10 ,2200.00,Dimension.VTORA, Type.WIRE,Granulation.PETNAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Abranet_6_Inch_Sanding_Disc_1_03145c28-da43-473e-8fbf-b55c19795dd2_360x.jpg?v=1616514796"));
			sanderRepository.save(new Sander(40L, "Abranet Disk", 50, 10 ,2200.00,Dimension.VTORA, Type.WIRE,Granulation.SESTNAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Abranet_6_Inch_Sanding_Disc_1_03145c28-da43-473e-8fbf-b55c19795dd2_360x.jpg?v=1616514796"));
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
