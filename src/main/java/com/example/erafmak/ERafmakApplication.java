package com.example.erafmak;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.erafmak.abraziveMaterials.helpers.Helper;
import com.example.erafmak.abraziveMaterials.helpers.HelperRepository;
import com.example.erafmak.abraziveMaterials.sander.Condition;
import com.example.erafmak.abraziveMaterials.sander.Dimension;
import com.example.erafmak.abraziveMaterials.sander.Granulation;
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

	public static void main(String[] args) {
		SpringApplication.run(ERafmakApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		
		try {
			
			System.out.println("Post construct called");
			
			originRepository.save(new Origin(1L, "Finland"));
			originRepository.save(new Origin(2L, "Nederlands"));
			originRepository.save(new Origin(3L, "Italy"));
			originRepository.save(new Origin(4L, "Belgium"));
			originRepository.save(new Origin(5L, "Germany"));
			
			manufacturerRepository.save(new Manufacturer(1L, "MIRKA",originRepository.findById(1L).get() ,""));
			manufacturerRepository.save(new Manufacturer(2L, "DeBeer",originRepository.findById(2L).get() ,""));
			manufacturerRepository.save(new Manufacturer(3L, "Spralac",originRepository.findById(2L).get() ,""));
			manufacturerRepository.save(new Manufacturer(4L, "Finixa",originRepository.findById(4L).get() ,""));
			manufacturerRepository.save(new Manufacturer(5L, "Sata",originRepository.findById(5L).get() ,""));
			manufacturerRepository.save(new Manufacturer(6L, "Spiralflex",originRepository.findById(3L).get() ,""));
			
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
			
			sanderRepository.save(new Sander(41L, "Abralon Disk", 20, 10 , 2400.00 ,Dimension.VTORA, Type.FOAM,Granulation.SEDMA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://res.cloudinary.com/ecomsilver/image/upload/w_570,h_570,c_pad,q_auto/RestExpress/product-images/20698-1f047d04-0712-491a-912c-56199d1bda86-.jpg"));
			sanderRepository.save(new Sander(42L, "Abralon Disk", 20, 10 , 2400.00,Dimension.VTORA, Type.FOAM,Granulation.EDINAESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://res.cloudinary.com/ecomsilver/image/upload/w_570,h_570,c_pad,q_auto/RestExpress/product-images/20698-1f047d04-0712-491a-912c-56199d1bda86-.jpg"));
			sanderRepository.save(new Sander(43L, "Abralon Disk", 20, 10 ,2400.00,Dimension.VTORA, Type.FOAM,Granulation.TRIENAESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://res.cloudinary.com/ecomsilver/image/upload/w_570,h_570,c_pad,q_auto/RestExpress/product-images/20698-1f047d04-0712-491a-912c-56199d1bda86-.jpg"));
			sanderRepository.save(new Sander(44L, "Abralon Disk", 20, 10 ,2400.00,Dimension.VTORA, Type.FOAM,Granulation.SESTNAESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://res.cloudinary.com/ecomsilver/image/upload/w_570,h_570,c_pad,q_auto/RestExpress/product-images/20698-1f047d04-0712-491a-912c-56199d1bda86-.jpg"));
			sanderRepository.save(new Sander(45L, "Abralon Disk", 20, 10 ,2400.00,Dimension.VTORA, Type.FOAM,Granulation.DEVETNAESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://res.cloudinary.com/ecomsilver/image/upload/w_570,h_570,c_pad,q_auto/RestExpress/product-images/20698-1f047d04-0712-491a-912c-56199d1bda86-.jpg"));
			sanderRepository.save(new Sander(46L, "Abralon Disk", 20, 10 ,2400.00,Dimension.VTORA, Type.FOAM,Granulation.DVAESETIPRVA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://res.cloudinary.com/ecomsilver/image/upload/w_570,h_570,c_pad,q_auto/RestExpress/product-images/20698-1f047d04-0712-491a-912c-56199d1bda86-.jpg"));
			sanderRepository.save(new Sander(47L, "Abralon Disk", 20, 10 ,2400.00,Dimension.VTORA, Type.FOAM,Granulation.DVAESETIVTORA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://res.cloudinary.com/ecomsilver/image/upload/w_570,h_570,c_pad,q_auto/RestExpress/product-images/20698-1f047d04-0712-491a-912c-56199d1bda86-.jpg"));
			
			sanderRepository.save(new Sander(48L, "WPF", 50, 10 ,900.00,Dimension.PETTA, Type.PAPER,Granulation.DVAESETITRETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Waterproof_Sanding_Sheets_1_360x.jpg?v=1571262577"));
			sanderRepository.save(new Sander(49L, "WPF", 50, 10 ,900.00,Dimension.PETTA, Type.PAPER,Granulation.OSMA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Waterproof_Sanding_Sheets_1_360x.jpg?v=1571262577"));
			sanderRepository.save(new Sander(50L, "WPF", 50, 10 ,900.00,Dimension.PETTA, Type.PAPER,Granulation.DEVETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Waterproof_Sanding_Sheets_1_360x.jpg?v=1571262577"));
			sanderRepository.save(new Sander(51L, "WPF", 50, 10 ,900.00,Dimension.PETTA, Type.PAPER,Granulation.DESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Waterproof_Sanding_Sheets_1_360x.jpg?v=1571262577"));
			sanderRepository.save(new Sander(52L, "WPF", 50, 10 ,900.00,Dimension.PETTA, Type.PAPER,Granulation.EDINAESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Waterproof_Sanding_Sheets_1_360x.jpg?v=1571262577"));
			sanderRepository.save(new Sander(53L, "WPF", 50, 10 ,900.00,Dimension.PETTA, Type.PAPER,Granulation.DVANAESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Waterproof_Sanding_Sheets_1_360x.jpg?v=1571262577"));
			sanderRepository.save(new Sander(54L, "WPF", 50, 10 ,900.00,Dimension.PETTA, Type.PAPER,Granulation.TRIENAESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Waterproof_Sanding_Sheets_1_360x.jpg?v=1571262577"));
			sanderRepository.save(new Sander(55L, "WPF", 50, 10 ,900.00,Dimension.PETTA, Type.PAPER,Granulation.CHETIRINAESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Waterproof_Sanding_Sheets_1_360x.jpg?v=1571262577"));
			sanderRepository.save(new Sander(56L, "WPF", 50, 10 ,900.00,Dimension.PETTA, Type.PAPER,Granulation.PETNAESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Waterproof_Sanding_Sheets_1_360x.jpg?v=1571262577"));
			sanderRepository.save(new Sander(57L, "WPF", 50, 10 ,900.00,Dimension.PETTA, Type.PAPER,Granulation.SESTNAESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Waterproof_Sanding_Sheets_1_360x.jpg?v=1571262577"));
			sanderRepository.save(new Sander(58L, "WPF", 50, 10 ,900.00,Dimension.PETTA, Type.PAPER,Granulation.SEDUMNAESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Waterproof_Sanding_Sheets_1_360x.jpg?v=1571262577"));
			sanderRepository.save(new Sander(59L, "WPF", 50, 10 ,1350.00,Dimension.SESTA, Type.PAPER,Granulation.OSUMNAESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://www.svydis.lt/sites/default/files/styles/product_full/public/mirka_wpf.png?itok=qGhZpSSd"));
			sanderRepository.save(new Sander(60L, "WPF", 50, 10 ,1350.00,Dimension.SESTA, Type.PAPER,Granulation.DEVETNAESETA, Condition.WET, manufacturerRepository.findById(1L).get(),"https://www.svydis.lt/sites/default/files/styles/product_full/public/mirka_wpf.png?itok=qGhZpSSd"));
			
			sanderRepository.save(new Sander(61L, "Iridium Disk", 100, 10 ,3300.00,Dimension.VTORA, Type.PLASTIC,Granulation.TRETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.brightontools.co.uk/files/img_cache/8332/468_349___11549378041_a1.jpg?1549378247"));
			sanderRepository.save(new Sander(62L, "Iridium Disk", 100, 10 ,3300.00,Dimension.VTORA, Type.PLASTIC,Granulation.PETTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.brightontools.co.uk/files/img_cache/8332/468_349___11549378041_a1.jpg?1549378247"));
			sanderRepository.save(new Sander(63L, "Iridium Disk", 100, 10 ,3300.00,Dimension.VTORA, Type.PLASTIC,Granulation.SHESTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.brightontools.co.uk/files/img_cache/8332/468_349___11549378041_a1.jpg?1549378247"));
			sanderRepository.save(new Sander(64L, "Iridium Disk", 100, 10 ,3300.00,Dimension.VTORA, Type.PLASTIC,Granulation.SEDMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.brightontools.co.uk/files/img_cache/8332/468_349___11549378041_a1.jpg?1549378247"));
			sanderRepository.save(new Sander(65L, "Iridium Disk", 100, 10 ,3300.00,Dimension.VTORA, Type.PLASTIC,Granulation.OSMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.brightontools.co.uk/files/img_cache/8332/468_349___11549378041_a1.jpg?1549378247"));
			sanderRepository.save(new Sander(66L, "Iridium Disk", 100, 10 ,3300.00,Dimension.VTORA, Type.PLASTIC,Granulation.DESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.brightontools.co.uk/files/img_cache/8332/468_349___11549378041_a1.jpg?1549378247"));
			sanderRepository.save(new Sander(67L, "Iridium Disk", 100, 10 ,3300.00,Dimension.VTORA, Type.PLASTIC,Granulation.DVANAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.brightontools.co.uk/files/img_cache/8332/468_349___11549378041_a1.jpg?1549378247"));
			sanderRepository.save(new Sander(68L, "Iridium Disk", 100, 10 ,3300.00,Dimension.VTORA, Type.PLASTIC,Granulation.TRIENAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.brightontools.co.uk/files/img_cache/8332/468_349___11549378041_a1.jpg?1549378247"));
			
			sanderRepository.save(new Sander(69L, "Autonet Block", 50, 10 ,1200.00,Dimension.TRETA, Type.WIRE,Granulation.TRETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.finqu.com/users/11009/thumbnails/product/6251022-mirka-autonet-70-198-2-800-auto-jpg_1920_1080.jpg"));
			sanderRepository.save(new Sander(70L, "Autonet Block", 50, 10 ,1200.00,Dimension.TRETA, Type.WIRE,Granulation.PETTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.finqu.com/users/11009/thumbnails/product/6251022-mirka-autonet-70-198-2-800-auto-jpg_1920_1080.jpg"));
			sanderRepository.save(new Sander(71L, "Autonet Block", 50, 10 ,1200.00,Dimension.TRETA, Type.WIRE,Granulation.SEDMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.finqu.com/users/11009/thumbnails/product/6251022-mirka-autonet-70-198-2-800-auto-jpg_1920_1080.jpg"));
			sanderRepository.save(new Sander(72L, "Autonet Block", 50, 10 ,1200.00,Dimension.TRETA, Type.WIRE,Granulation.OSMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.finqu.com/users/11009/thumbnails/product/6251022-mirka-autonet-70-198-2-800-auto-jpg_1920_1080.jpg"));
			sanderRepository.save(new Sander(73L, "Autonet Block", 50, 10 ,1200.00,Dimension.TRETA, Type.WIRE,Granulation.DESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.finqu.com/users/11009/thumbnails/product/6251022-mirka-autonet-70-198-2-800-auto-jpg_1920_1080.jpg"));
			sanderRepository.save(new Sander(74L, "Autonet Block", 50, 10 ,1200.00,Dimension.TRETA, Type.WIRE,Granulation.DVANAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.finqu.com/users/11009/thumbnails/product/6251022-mirka-autonet-70-198-2-800-auto-jpg_1920_1080.jpg"));
			
			sanderRepository.save(new Sander(75L, "Q Silver", 50, 10 ,2000.00,Dimension.CHETVRTA, Type.PAPER,Granulation.TRETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://nonpaintpro.nl/wp-content/uploads/2018/12/mirka_q-silver_schuurstroken_70x420mm_met_14_gaten_1_1.jpg"));
			sanderRepository.save(new Sander(76L, "Q Silver", 50, 10 ,2000.00,Dimension.CHETVRTA, Type.PAPER,Granulation.PETTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://nonpaintpro.nl/wp-content/uploads/2018/12/mirka_q-silver_schuurstroken_70x420mm_met_14_gaten_1_1.jpg"));
			sanderRepository.save(new Sander(77L, "Q Silver", 100, 10 ,4000.00,Dimension.CHETVRTA, Type.PAPER,Granulation.SHESTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://nonpaintpro.nl/wp-content/uploads/2018/12/mirka_q-silver_schuurstroken_70x420mm_met_14_gaten_1_1.jpg"));
			sanderRepository.save(new Sander(78L, "Q Silver", 100, 10 ,4000.00,Dimension.CHETVRTA, Type.PAPER,Granulation.SEDMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://nonpaintpro.nl/wp-content/uploads/2018/12/mirka_q-silver_schuurstroken_70x420mm_met_14_gaten_1_1.jpg"));
			sanderRepository.save(new Sander(79L, "Q Silver", 100, 10 ,4000.00,Dimension.CHETVRTA, Type.PAPER,Granulation.OSMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://nonpaintpro.nl/wp-content/uploads/2018/12/mirka_q-silver_schuurstroken_70x420mm_met_14_gaten_1_1.jpg"));
			sanderRepository.save(new Sander(80L, "Q Silver", 100, 10 ,4000.00,Dimension.CHETVRTA, Type.PAPER,Granulation.DESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://nonpaintpro.nl/wp-content/uploads/2018/12/mirka_q-silver_schuurstroken_70x420mm_met_14_gaten_1_1.jpg"));
			
			sanderRepository.save(new Sander(81L, "Q IRIDIUM", 100, 10 ,4300.00,Dimension.OSMA, Type.PLASTIC,Granulation.TRETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://images.ua.prom.st/3069480467_w640_h640_abrazivnaya-polosa-mirka.jpg"));
			sanderRepository.save(new Sander(82L, "Q IRIDIUM", 100, 10 ,4300.00,Dimension.OSMA, Type.PLASTIC,Granulation.PETTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://images.ua.prom.st/3069480467_w640_h640_abrazivnaya-polosa-mirka.jpg"));
			sanderRepository.save(new Sander(83L, "Q IRIDIUM", 100, 10,4300.00,Dimension.OSMA, Type.PLASTIC,Granulation.SHESTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://images.ua.prom.st/3069480467_w640_h640_abrazivnaya-polosa-mirka.jpg"));
			sanderRepository.save(new Sander(84L, "Q IRIDIUM", 100, 10,4300.00,Dimension.OSMA, Type.PLASTIC,Granulation.SEDMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://images.ua.prom.st/3069480467_w640_h640_abrazivnaya-polosa-mirka.jpg"));
			sanderRepository.save(new Sander(85L, "Q IRIDIUM", 100, 10,4300.00,Dimension.OSMA, Type.PLASTIC,Granulation.OSMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://images.ua.prom.st/3069480467_w640_h640_abrazivnaya-polosa-mirka.jpg"));
			sanderRepository.save(new Sander(86L, "Q IRIDIUM", 100, 10,4300.00,Dimension.OSMA, Type.PLASTIC,Granulation.DESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://images.ua.prom.st/3069480467_w640_h640_abrazivnaya-polosa-mirka.jpg"));
			
			sanderRepository.save(new Sander(87L, "Goldflex Soft", 200, 10 ,2000.00,Dimension.DESETA, Type.FOAM,Granulation.OSMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.dtc-uk.com/media/catalog/product/cache/eaec396a7f4d3b0a500e60155ff13ed9/a/2/a291.jpg"));
			sanderRepository.save(new Sander(88L, "Goldflex Soft", 200, 10 ,2000.00,Dimension.DESETA, Type.FOAM,Granulation.DESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.dtc-uk.com/media/catalog/product/cache/eaec396a7f4d3b0a500e60155ff13ed9/a/2/a291.jpg"));
			sanderRepository.save(new Sander(89L, "Goldflex Soft", 200, 10 ,2000.00,Dimension.DESETA, Type.FOAM,Granulation.DVANAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.dtc-uk.com/media/catalog/product/cache/eaec396a7f4d3b0a500e60155ff13ed9/a/2/a291.jpg"));
			sanderRepository.save(new Sander(90L, "Goldflex Soft", 200, 10 ,2000.00,Dimension.DESETA, Type.FOAM,Granulation.CHETIRINAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.dtc-uk.com/media/catalog/product/cache/eaec396a7f4d3b0a500e60155ff13ed9/a/2/a291.jpg"));
			sanderRepository.save(new Sander(91L, "Goldflex Soft", 200, 10 ,2000.00,Dimension.DESETA, Type.FOAM,Granulation.PETNAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.dtc-uk.com/media/catalog/product/cache/eaec396a7f4d3b0a500e60155ff13ed9/a/2/a291.jpg"));
			
			sanderRepository.save(new Sander(92L, "Autonet Roll", 1, 10 ,2110.00,Dimension.SEDMA, Type.WIRE,Granulation.TRETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://m.media-amazon.com/images/I/51llyH3sxkL._AC_SX425_.jpg"));
			sanderRepository.save(new Sander(93L, "Autonet Roll", 1, 10 ,1910.00,Dimension.SEDMA, Type.WIRE,Granulation.PETTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://m.media-amazon.com/images/I/51llyH3sxkL._AC_SX425_.jpg"));
			sanderRepository.save(new Sander(94L, "Autonet Roll", 1, 10 ,1910.00,Dimension.SEDMA, Type.WIRE,Granulation.SHESTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://m.media-amazon.com/images/I/51llyH3sxkL._AC_SX425_.jpg"));
			sanderRepository.save(new Sander(95L, "Autonet Roll", 1, 10 ,1910.00,Dimension.SEDMA, Type.WIRE,Granulation.SEDMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://m.media-amazon.com/images/I/51llyH3sxkL._AC_SX425_.jpg"));
			sanderRepository.save(new Sander(96L, "Autonet Roll", 1, 10 ,1910.00,Dimension.SEDMA, Type.WIRE,Granulation.OSMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://m.media-amazon.com/images/I/51llyH3sxkL._AC_SX425_.jpg"));
			sanderRepository.save(new Sander(97L, "Autonet Roll", 1, 10 ,1910.00,Dimension.SEDMA, Type.WIRE,Granulation.DESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://m.media-amazon.com/images/I/51llyH3sxkL._AC_SX425_.jpg"));
			sanderRepository.save(new Sander(98L, "Autonet Roll", 1, 10 ,1910.00,Dimension.SEDMA, Type.WIRE,Granulation.DVANAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://m.media-amazon.com/images/I/51llyH3sxkL._AC_SX425_.jpg"));
			
			sanderRepository.save(new Sander(99L, "Mirlon Roll", 1, 10 ,1300.00,Dimension.SEDMA, Type.WIRE,Granulation.EDINAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://i.ebayimg.com/images/g/jz0AAOSw4Kpg8CgZ/s-l225.jpg"));
			sanderRepository.save(new Sander(100L,"Mirlon Roll", 1, 10 ,1300.00,Dimension.SEDMA, Type.WIRE,Granulation.OSUMNAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.2bar.lt/wp-content/uploads/2020/05/art-9114-lt-vaizdas-324x324.jpg"));
			
			sanderRepository.save(new Sander(101L, "Silver Roll", 1, 10 ,2100.00,Dimension.DEVETA, Type.PAPER,Granulation.TRETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.manomano.com/images/images_products/419258/P/13669990_1.jpg"));
			sanderRepository.save(new Sander(102L, "Silver Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.CHETVRTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.manomano.com/images/images_products/419258/P/13669990_1.jpg"));
			sanderRepository.save(new Sander(103L, "Silver Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.PETTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.manomano.com/images/images_products/419258/P/13669990_1.jpg"));
			sanderRepository.save(new Sander(104L, "Silver Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.SHESTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.manomano.com/images/images_products/419258/P/13669990_1.jpg"));
			sanderRepository.save(new Sander(105L, "Silver Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.SEDMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.manomano.com/images/images_products/419258/P/13669990_1.jpg"));
			sanderRepository.save(new Sander(106L, "Silver Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.OSMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.manomano.com/images/images_products/419258/P/13669990_1.jpg"));
			sanderRepository.save(new Sander(107L, "Silver Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.DEVETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.manomano.com/images/images_products/419258/P/13669990_1.jpg"));
			sanderRepository.save(new Sander(108L, "Silver Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.DESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.manomano.com/images/images_products/419258/P/13669990_1.jpg"));
			sanderRepository.save(new Sander(109L, "Silver Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.EDINAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.manomano.com/images/images_products/419258/P/13669990_1.jpg"));
			sanderRepository.save(new Sander(110L, "Silver Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.DVANAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.manomano.com/images/images_products/419258/P/13669990_1.jpg"));
			
			sanderRepository.save(new Sander(111L, "Gold Roll", 1, 10 ,2100.00,Dimension.DEVETA, Type.PAPER,Granulation.VTORA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka_gold_115mm_1.jpg"));
			sanderRepository.save(new Sander(112L, "Gold Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.TRETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka_gold_115mm_1.jpg"));
			sanderRepository.save(new Sander(113L, "Gold Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.PETTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka_gold_115mm_1.jpg"));
			sanderRepository.save(new Sander(114L, "Gold Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.SHESTA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka_gold_115mm_1.jpg"));
			sanderRepository.save(new Sander(115L, "Gold Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.SEDMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka_gold_115mm_1.jpg"));
			sanderRepository.save(new Sander(116L, "Gold Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.OSMA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka_gold_115mm_1.jpg"));
			sanderRepository.save(new Sander(117L, "Gold Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.DEVETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka_gold_115mm_1.jpg"));
			sanderRepository.save(new Sander(118L, "Gold Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.DESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka_gold_115mm_1.jpg"));
			sanderRepository.save(new Sander(119L, "Gold Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.EDINAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka_gold_115mm_1.jpg"));
			sanderRepository.save(new Sander(120L, "Gold Roll", 1, 10 ,1950.00,Dimension.DEVETA, Type.PAPER,Granulation.DVANAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka_gold_115mm_1.jpg"));
			
			sanderRepository.save(new Sander(121L, "Gold Disk", 50, 10 , 2200.00 ,Dimension.VTORA, Type.PAPER,Granulation.VTORA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.ultrimaxstore.com/images/ww/product/AVD1801.jpg"));
			sanderRepository.save(new Sander(122L, "Mirlon Disk", 10, 10 , 400.00,Dimension.VTORA, Type.WIRE,Granulation.EDINAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://cdn.shopify.com/s/files/1/0118/2262/products/Mirka_Mirlon_Discs_1_360x.jpg?v=1571262599"));
			
			sanderRepository.save(new Sander(123L, "Abralon Disk", 20, 10 ,1000.00,Dimension.EDINAESETA, Type.FOAM,Granulation.DEVETNAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.dtc-uk.com/media/catalog/product/cache/eaec396a7f4d3b0a500e60155ff13ed9/1/0/10267-A8A7500_C_1.jpg"));
			
			sanderRepository.save(new Sander(124L, "Abranet Disk", 50, 10 ,900.00,Dimension.EDINAESETA, Type.WIRE,Granulation.DESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/mf_webp/jpg/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka-abranet-schuurschijven-77mm_2.webp"));
			sanderRepository.save(new Sander(125L, "Abranet Disk", 50, 10 ,900.00,Dimension.EDINAESETA, Type.WIRE,Granulation.DVANAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/mf_webp/jpg/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka-abranet-schuurschijven-77mm_2.webp"));
			sanderRepository.save(new Sander(126L, "Abranet Disk", 50, 10 ,900.00,Dimension.EDINAESETA, Type.WIRE,Granulation.CHETIRINAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/mf_webp/jpg/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka-abranet-schuurschijven-77mm_2.webp"));
			sanderRepository.save(new Sander(127L, "Abranet Disk", 50, 10 ,900.00,Dimension.EDINAESETA, Type.WIRE,Granulation.PETNAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://www.nonpaints.com/media/mf_webp/jpg/media/catalog/product/cache/e3aef0ea27f589da506a087ef53e5914/m/i/mirka-abranet-schuurschijven-77mm_2.webp"));
			
			sanderRepository.save(new Sander(128L, "Microstar Disk", 50, 10 ,900.00,Dimension.EDINAESETA, Type.PLASTIC,Granulation.SESTNAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://poetsenpolijsten.nl/wp-content/uploads/2016/08/Mirka-Microstar-77mm-738x738.jpg"));
			sanderRepository.save(new Sander(129L, "Microstar Disk", 50, 10 ,900.00,Dimension.EDINAESETA, Type.PLASTIC,Granulation.SEDUMNAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://poetsenpolijsten.nl/wp-content/uploads/2016/08/Mirka-Microstar-77mm-738x738.jpg"));
			sanderRepository.save(new Sander(130L, "Microstar Disk", 50, 10 ,900.00,Dimension.EDINAESETA, Type.PLASTIC,Granulation.OSUMNAESETA, Condition.DRY, manufacturerRepository.findById(1L).get(),"https://poetsenpolijsten.nl/wp-content/uploads/2016/08/Mirka-Microstar-77mm-738x738.jpg"));
			
			hardenerRepository.save(new Hardener(1L, "2099" , "2K Hardener Medium" , 600.00 ,Weigth.ONE, 10 , "https://www.autolac.com.au/core/media/media.nl?id=233228&c=1048819&h=377326c8cbcfd51c3422" , manufacturerRepository.findById(3L).get()));
			hardenerRepository.save(new Hardener(2L, "2299" , "2K Hardener Medium Very Fast" , 600.00 ,Weigth.ONE, 10 , "https://www.autolac.com.au/core/media/media.nl?id=233228&c=1048819&h=377326c8cbcfd51c3422" , manufacturerRepository.findById(3L).get()));
			
			hardenerRepository.save(new Hardener(3L, "2501" , "HS Hardener Medium" , 1750.00 ,Weigth.TWO, 10 , "https://www.autolac.com.au/core/media/media.nl?id=233236&c=1048819&h=dd6d7b3dff26aa54f462" , manufacturerRepository.findById(3L).get()));
			hardenerRepository.save(new Hardener(4L, "2511" , "HS Hardener Fast" , 1750.00 ,Weigth.TWO, 10 , "https://www.autolac.com.au/core/media/media.nl?id=233229&c=1048819&h=a5fd8b37ed8e21c74613" , manufacturerRepository.findById(3L).get()));
			
            hardenerRepository.save(new Hardener(5L, "47-50" , "2K Hardener Medium" , 800.00 ,Weigth.ONE, 10 , "https://www.gakra.pl/1400-large_default/2k-47-50.jpg" , manufacturerRepository.findById(2L).get()));
			hardenerRepository.save(new Hardener(6L, "47-40" , "2K Hardener Fast" , 800.00 ,Weigth.ONE, 10 , "https://cdn.salla.sa/2AgkNHFJwjcFmUIKMpyTZK9GMjtP3z7VoD3AhrCp.jpg" , manufacturerRepository.findById(2L).get()));
			hardenerRepository.save(new Hardener(7L, "47-30" , "2K Hardener Very Fast" , 800.00 , Weigth.ONE, 10 , "https://www.gakra.pl/1401-large_default/2k-47-30.jpg" , manufacturerRepository.findById(2L).get()));
			
			hardenerRepository.save(new Hardener(8L, "8-150" , "HS Hardener Medium" , 1020.00 ,Weigth.ONE, 10 , "https://linkuponline.co.nz/imagecache/ii_1_1000532_6.jpg" , manufacturerRepository.findById(2L).get()));
			hardenerRepository.save(new Hardener(9L, "8-140" , "HS Hardener Fast" , 1020.00 ,Weigth.ONE, 10 , "https://grupaurban.pl/wp-content/uploads/2021/01/EA187-8-140.1-1L-323x140-HS-Hardener-Fast-DL-570x485.png" , manufacturerRepository.findById(2L).get()));
			hardenerRepository.save(new Hardener(10L, "8-130" , "HS Hardener Very Fast" , 1020.00 ,Weigth.ONE, 10 , "https://lakcdn.azureedge.net/media/2609/d8-1301.jpg?mode=pad&width=600&height=400&bgcolor=ffffff" , manufacturerRepository.findById(2L).get()));
			
			hardenerRepository.save(new Hardener(11L, "8-450" , "Air Dry HS420 Hardener Medium" , 1300.00 ,Weigth.ONE, 10 , "https://linkuponline.co.nz/imagecache/ii_1_1000537_6.jpg" , manufacturerRepository.findById(2L).get()));
			hardenerRepository.save(new Hardener(12L, "8-440" , "Air Dry HS420 Hardener Fast" , 1300.00 ,Weigth.ONE, 10 , "https://media.peinturevoiture.fr/6814-large_default/durcisseur-debeer-8-440.jpg" , manufacturerRepository.findById(2L).get()));
			
			hardenerRepository.save(new Hardener(13L, "1-70" , "Epoxy Primer Hardener" , 800.00 ,Weigth.ONE, 10 , "https://zvezda-udachi.ru/storage/thumbnail/286b3576c8665ca3ca9af4111b2e84bc-square-1000.jpg" , manufacturerRepository.findById(2L).get()));
			
			hardenerRepository.save(new Hardener(14L, "1-10" , "Washprimer Hardener" , 570.00 ,Weigth.ONE, 10 , "https://ressources.carross.eu/350x350/444f7e4f9512cef1167da4516fad5def/110%20image.jpg" , manufacturerRepository.findById(2L).get()));
			
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
			
			coatRepository.save(new Coat(1L, "4699" , "MS Clear Coat High Gloss" , 2150.00,Weigth.FIVE, 10 , "https://www.spralac.com/export/sites/spralac/images/products/SP4699.png_991685311.png" , ms , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(2L, "4501" , "HS Clear Coat 2:1" , 2450.00,Weigth.FIVE, 10 , "https://www.autolac.com.au/core/media/media.nl?id=233231&c=1048819&h=ade0e9a0f018d682df7d" , spHs , manufacturerRepository.findById(2L).get() ));
			coatRepository.save(new Coat(3L, "4502" , "HS Anti Scratch Clear 2:1" , 2950.00,Weigth.FIVE, 10 , "https://www.autolac.com.au/core/media/media.nl?id=233227&c=1048819&h=afe9dc457e47fcca63ba" , spHs , manufacturerRepository.findById(2L).get() ));
			
			coatRepository.save(new Coat(4L, "1-103" , "2K Clear Coat" , 2650.00,Weigth.FIVE, 10 , "https://www.autolac.com.au/core/media/media.nl?id=171&c=1048819&h=b0b361b09f4072e555f4" , k2 , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(5L, "1-204" , "MS Clear Coat" , 3050.00,Weigth.FIVE, 10 , "https://www.autolac.com.au/core/media/media.nl?id=171&c=1048819&h=b0b361b09f4072e555f4" , k2 , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(6L, "8-104" , "HS Clear Coat" , 2350.00,Weigth.FIVE, 10 , "https://www.autolac.com.au/core/media/media.nl?id=171&c=1048819&h=b0b361b09f4072e555f4" , hs , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(7L, "8-214" , "HS Scratch Resistant Clear Coat" , 4250.00,Weigth.FIVE, 10 , "https://www.autolac.com.au/core/media/media.nl?id=171&c=1048819&h=b0b361b09f4072e555f4" , hs , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(8L, "8-614" , "HS420 Air Dry Clear Coat" , 4950.00,Weigth.FIVE, 10 , "https://www.autolac.com.au/core/media/media.nl?id=171&c=1048819&h=b0b361b09f4072e555f4" , uhs , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(8L, "8-114" , "Scratch Resistant Fast Repair Clear" , 5050.00,Weigth.FIVE, 10 , "https://www.autolac.com.au/core/media/media.nl?id=171&c=1048819&h=b0b361b09f4072e555f4" , uhs , manufacturerRepository.findById(2L).get()));
			coatRepository.save(new Coat(9L, "1-105" , "MS Clear Coat Matt" , 1050.00,Weigth.FIVE, 10 , "https://cdn11.bigcommerce.com/s-g9bjiqgq3a/images/stencil/1280x1280/products/887/1137/Debeer_1-105__26776.1590089653.jpg?c=1" , matt , manufacturerRepository.findById(2L).get()));
			
			primerRepository.save(new Primer(1L, "8-14510" , "HS Surfacer, WHITE " , 2500.00,Weigth.THREE, 10 , "https://media.peinturevoiture.fr/9527-large_default/appret-blanc-hs-debeer-3l.jpg" , filler , manufacturerRepository.findById(2L).get()));
			primerRepository.save(new Primer(2L, "8-14540" , "HS Surfacer, BLACK" , 2500.00,Weigth.THREE, 10 , "https://media.peinturevoiture.fr/9527-large_default/appret-blanc-hs-debeer-3l.jpg" , filler , manufacturerRepository.findById(2L).get()));
			primerRepository.save(new Primer(3L, "5289" , "Universal Primer Filler White" , 2000.00,Weigth.THREE, 10 , "https://www.spralac.com/export/sites/spralac/images/products/SP5289.png_260582975.png" , filler , manufacturerRepository.findById(3L).get() ));
			primerRepository.save(new Primer(4L, "5279" , "Universal Primer Filler Black" , 2000.00, Weigth.THREE, 10 , "https://www.spralac.com/export/sites/spralac/images/products/SP5279.png_49232062.png" , filler , manufacturerRepository.findById(3L).get()));
			
			primerRepository.save(new Primer(5L, "1-7520" , "Epoxy Primer Grey" , 1100.00,Weigth.ONE, 10 , "https://www.provari.fi/assets/images/m_products/0/494/1/1-7520.web-1.png" , epoxy , manufacturerRepository.findById(2L).get()));
			
			primerRepository.save(new Primer(6L, "1-15" , "Washprimer" , 1000.00,Weigth.ONE, 10 , "https://lakcdn.azureedge.net/media/2584/d1-151.jpg?mode=pad&width=600&height=400&bgcolor=ffffff" , washprimer , manufacturerRepository.findById(2L).get()));
			
			thinnerRepository.save(new Thinner(1L,"3099", "FAST" , 1250.00,Weigth.FIVE, 10 , "https://www.spralac.com/export/sites/spralac/images/products/SP3099.png_350128767.png" , manufacturerRepository.findById(3L).get()));
			thinnerRepository.save(new Thinner(2L,"3199", "MEDIUM" , 1250.00,Weigth.FIVE, 10 , "https://www.spralac.com/export/sites/spralac/images/products/SP3199.png_350128767.png" , manufacturerRepository.findById(3L).get()));
			thinnerRepository.save(new Thinner(3L,"3299", "SLOW" , 1250.00,Weigth.FIVE, 10 , "https://www.spralac.com/export/sites/spralac/images/products/SP3299.png_772830593.png" , manufacturerRepository.findById(3L).get()));
			thinnerRepository.save(new Thinner(4L,"1-151", "Uni Thinner Medium" , 1850.00,Weigth.FIVE, 10 , "https://www.autolac.com.au/core/media/media.nl?id=232953&c=1048819&h=e23e1c73c2835d1e923e" , manufacturerRepository.findById(2L).get()));
			thinnerRepository.save(new Thinner(5L,"47-91", "2K Spot Repair Thinner " , 500.00,Weigth.FIVE, 10 , "https://www.autolaky1.sk/img_tovar/30056_ce33a095fdc5c49b386afacd2adad473.jpg" , manufacturerRepository.findById(2L).get()));
			thinnerRepository.save(new Thinner(5L,"6499", "SILICONE REMOVER" , 1150.00,Weigth.FIVE, 10 , "https://www.spralac.com/export/sites/spralac/images/products/SP6499.png_1695431327.png" , manufacturerRepository.findById(3L).get()));
			
			puttyRepository.save(new Putty(1L,"7031" , "BodyWorks All-In-One" ,570.00,Weigth.ONE, 10, "https://www.spralac.com/export/sites/spralac/images/products/SP7031.png_316497369.png" , manufacturerRepository.findById(3L).get()));
			puttyRepository.save(new Putty(2L,"7011" , "BodyWorks Ultralight" ,370.00,Weigth.ONE, 10, "https://www.drive4color.gr/photos/pro_sidirostoko_autokinitou_cilo_spralac_sp7011.jpg" , manufacturerRepository.findById(3L).get()));
			puttyRepository.save(new Putty(3L,"1-909" , "Universal Body Filler" ,660.00,Weigth.ONE, 10, "https://grupaurban.pl/wp-content/uploads/2021/01/EA027-1-909.1-1.02L-1.3kg-500x73-Universal-Body-Filler-Light-DL-570x463.png" , manufacturerRepository.findById(2L).get()));
			puttyRepository.save(new Putty(4L,"6080" , "Spray Filler" ,1000.00,Weigth.ONE, 10, "https://www.gmolton.com/dynimage/gallerylarge/3402/image.png" , manufacturerRepository.findById(2L).get()));
			
			nozzleRepository.save(new Nozzle(1L, "Nozzle set for SPG 100" , NozzleSize.PRV, 1200.00,5, "https://www.finixa.com/site/data/images/product/SPG100K1.jpg", manufacturerRepository.findById(4L).get()));
			nozzleRepository.save(new Nozzle(2L, "Nozzle set for SPG 100" , NozzleSize.VTOR, 1200.00,5, "https://www.finixa.com/site/data/images/product/SPG100K1.jpg", manufacturerRepository.findById(4L).get()));
			
			nozzleRepository.save(new Nozzle(3L, "Nozzle set for SPG 500" , NozzleSize.TRET, 1500.00,5, "https://www.finixa.com/site/data/images/product/SPG500K1.jpg", manufacturerRepository.findById(4L).get()));
			nozzleRepository.save(new Nozzle(4L, "Nozzle set for SPG 500" , NozzleSize.PETTI, 1500.00,5, "https://www.finixa.com/site/data/images/product/SPG500K1.jpg", manufacturerRepository.findById(4L).get()));
			nozzleRepository.save(new Nozzle(5L, "Nozzle set for SPG 500" , NozzleSize.SEDMA, 1500.00,5, "https://www.finixa.com/site/data/images/product/SPG500K1.jpg", manufacturerRepository.findById(4L).get()));
			nozzleRepository.save(new Nozzle(6L, "Nozzle set for SPG 500" , NozzleSize.OSMA, 1500.00,5, "https://www.finixa.com/site/data/images/product/SPG500K1.jpg", manufacturerRepository.findById(4L).get()));
			nozzleRepository.save(new Nozzle(7L, "Nozzle set for SPG 500" , NozzleSize.DEVETA, 1500.00,5, "https://www.finixa.com/site/data/images/product/SPG500K1.jpg", manufacturerRepository.findById(4L).get()));
			
			nozzleRepository.save(new Nozzle(8L, "Nozzle set for SATA JET 100 RP" , NozzleSize.CETVRT, 6500.00,5, "https://www.prodip.eu/image/cache/data/SATA/sata_5000_b_rp_tryskova_sada_10920-600x600.jpg", manufacturerRepository.findById(5L).get()));
			nozzleRepository.save(new Nozzle(9L, "Nozzle set for SATA JET 100 RP" , NozzleSize.SESTI, 6500.00,5, "https://www.prodip.eu/image/cache/data/SATA/sata_5000_b_rp_tryskova_sada_10920-600x600.jpg", manufacturerRepository.findById(5L).get()));
			
			nozzleRepository.save(new Nozzle(10L, "Nozzle set for SATA JET 5500 RP" ,NozzleSize.TRET, 12500.00,5, "https://www.prodip.eu/image/cache/data/SATA/sata_5000_b_rp_tryskova_sada_10920-600x600.jpg", manufacturerRepository.findById(5L).get()));
			nozzleRepository.save(new Nozzle(11L, "Nozzle set for SATA JET 5500 RP" ,NozzleSize.PETTI, 12500.00,5, "https://www.prodip.eu/image/cache/data/SATA/sata_5000_b_rp_tryskova_sada_10920-600x600.jpg", manufacturerRepository.findById(5L).get()));
			
			nozzleRepository.save(new Nozzle(12L, "Nozzle set for SATA JET 5500 HVLP" ,NozzleSize.TRET, 13500.00,5, "https://dkstatic.blob.core.windows.net/images/736326/500x500/mfrsat_sata_1063635.jpg", manufacturerRepository.findById(5L).get()));
			nozzleRepository.save(new Nozzle(13L, "Nozzle set for SATA JET 5500 HVLP" ,NozzleSize.PETTI, 13500.00,5, "https://dkstatic.blob.core.windows.net/images/736326/500x500/mfrsat_sata_1063635.jpg", manufacturerRepository.findById(5L).get()));
			
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
			
			gunRepository.save(new SprayGun(1L, "Finixa SPG 100" , 3500.00 , 10 , f100 , manufacturerRepository.findById(4L).get(), "https://www.finixa.com/site/data/images/product/SPG1001.jpg" ));
			gunRepository.save(new SprayGun(2L, "Finixa SPG 500" , 4400.00 , 10 , f500 , manufacturerRepository.findById(4L).get(), "https://www.finixa.com/site/data/images/product/SPG5001.jpg" ));
			
			gunRepository.save(new SprayGun(3L, "SATA JET 100" , 1600.00 , 10 , sata100 , manufacturerRepository.findById(5L).get(), "https://www.sata.com/assets/pim/assets/592x839/HERO-BILD-189613-SATAJET-100-B-F-RP.PNG" ));
			gunRepository.save(new SprayGun(4L, "SATA JET 5500 RP" , 34000.00 , 10 , satarp , manufacturerRepository.findById(5L).get(), "https://www.prpshop.it/3504-large_default/sata-satajet-x-5500-rp-o-professional-spray-gun.jpg" ));
			gunRepository.save(new SprayGun(5L, "SATA JET 5500 HVLP" , 35000.00 , 10 , satahvlp , manufacturerRepository.findById(5L).get(), "https://www.prpshop.it/3490-large_default/sata-satajet-x-5500-hvlp-o-professional-spray-gun.jpg" ));
			
			extrasRepository.save(new Extras(1L , "Sata Gravity Cup" , "reusable" , 3200.00 , 10 , manufacturerRepository.findById(5L).get(), "https://www.sata.com/assets/pim/assets/800x533/27243_KUNSTSTOFFBECHER.png"));
			extrasRepository.save(new Extras(2L , "Finixa Gravity Cup" , "reusable" , 800.00 , 10 , manufacturerRepository.findById(4L).get(), "https://www.finixa.com/site/data/images/product/SPG500C1.jpg"));
			extrasRepository.save(new Extras(3L , "Finixa Regulator" , "spraygun air regulator" , 2500.00 , 10 , manufacturerRepository.findById(4L).get(), "https://www.finixa.com/site/data/images/product/SPG9201.jpg"));
			extrasRepository.save(new Extras(4L , "Spiralflex 4011" , "CONNECTION FOR QUICK COUPLING 1/4" , 90.00 , 10 , manufacturerRepository.findById(6L).get(), "https://www.rp-tools.com/media/image/product/14163/lg/rp-sp-rcee4111_connection-for-quick-coupling-1-4-inch-cee.jpg"));
			extrasRepository.save(new Extras(5L , "Spiralflex 4003" , "CONNECTION FOR QUICK COUPLING 1/4" , 350.00 , 10 , manufacturerRepository.findById(6L).get(), "https://www.rp-tools.com/media/image/product/6016/lg/rp-sp-rcee4003_clutch-1-2-inch.jpg"));
			extrasRepository.save(new Extras(6L , "Spiralflex Air Purifier" , "primary with manometer" , 3700.00 , 10 , manufacturerRepository.findById(6L).get(), "http://www.fluxostore.com/image/cache/data/fx3130-340x340.gif"));
			extrasRepository.save(new Extras(7L , "Spiralflex Air Purifier" , "secondary" , 2700.00 , 10 , manufacturerRepository.findById(6L).get(), "http://www.fluxostore.com/image/cache/data/fx3120-340x340.gif"));
			extrasRepository.save(new Extras(8L , "SPIRALFLEX SPRAYGUN HOSE" , "PU RETINATO 12X8 M.10" , 1900.00 , 10 , manufacturerRepository.findById(6L).get(), "https://vigliettaguido.com/img_viglietta/prodotti_vg/tpr12x8!10.jpg"));
			
			padsRepository.save(new Pads(1L, "White Lambswool Pad", "150mm Grip" , 460.00 , 10, manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/7990150111_010.jpg?w=1200"));
			padsRepository.save(new Pads(2L, "Yellow Lambswool Pad", "150mm Grip" , 1300.00 , 10, manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/7991500211_010.jpg?w=1200"));
			padsRepository.save(new Pads(3L, "Black Waffle", "150mm Grip" , 850.00 , 10, manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/7993115022_001.jpg?w=1200"));
			padsRepository.save(new Pads(4L, "Yellow Waffle", "150mm Grip" , 880.00 , 10, manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/7993415021_010.jpg?w=306"));
			padsRepository.save(new Pads(5L, "Yellow Waffle", "85mm Grip" , 500.00 , 10, manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/7993408521_010.jpg?w=1200"));
			padsRepository.save(new Pads(6L, "Twisted Wool Pad", "180mm Grip" , 1100.00 , 10, manufacturerRepository.findById(1L).get(),""));
			padsRepository.save(new Pads(7L, "Yellow Lambswool Pad", "80mm Grip" , 550.00 , 10, manufacturerRepository.findById(1L).get(),""));
			padsRepository.save(new Pads(8L, "White Polishing Felt Pad", "125x6mm Grip" , 880.00 , 10, manufacturerRepository.findById(1L).get(),""));
			padsRepository.save(new Pads(9L, "White Foam Pad", "150mm Grip" , 620.00 , 10, manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/POP508-514-5183.jpg"));
			padsRepository.save(new Pads(10L, "Black Foam Pad", "150mm Grip" , 620.00 , 10, manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/POP908-914-9183.jpg"));
			padsRepository.save(new Pads(11L, "Orange Foam Pad", "150mm Grip" , 620.00 , 10, manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/POP708-714-7182.jpg"));
			
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
			
			polishRepository.save(new Polish(1L, "Polarshine E3" , "Glass Polishing Componenet", 3800.00 , 10 ,glass, manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/7990310111_011.jpg?w=1200" ));
			polishRepository.save(new Polish(2L, "Polarshine 5" , "Finishing Component", 2350.00 , 10 ,last, manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/7990500111_011.jpg?w=1200" ));
			polishRepository.save(new Polish(3L, "Polarshine 10" , "2 in 1", 1750.00 , 10 ,ten, manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/7995010111_011.jpg?w=1200" ));
			polishRepository.save(new Polish(4L, "Polarshine 25" , "Grip 1000", 1300.00 , 10 ,first, manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/7992710111_011.jpg?w=1200" ));
			polishRepository.save(new Polish(5L, "Polarshine 35" , "Grip 800", 1600.00 , 10 ,first, manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/7992810111_016.jpg?w=1200" ));
			
			toolRepository.save(new Tool(1L, "Mirka DEROS" , "650CV 150mm Central Vacuum Orbit 5,0" , 31000.00, 5, Power.ELECTRIC, manufacturerRepository.findById(1L).get(), "https://www.mirka.com/globalassets/pdm/mid6502022_002.jpg?w=1200"));
			toolRepository.save(new Tool(2L, "Mirka DEOS" , "383CV 70x198mm Central Vacuum Orbit 3,0" , 31000.00, 5, Power.ELECTRIC, manufacturerRepository.findById(1L).get(), "https://www.mirka.com/globalassets/pdm/mid3830201_001.jpg?w=1200"));
			toolRepository.save(new Tool(3L, "Mirka PROS" , "650CV 150mm Central Vacuum Orbit 5.0" , 18000.00, 5, Power.PNEUMATIC, manufacturerRepository.findById(1L).get(), "https://www.mirka.com/globalassets/pdm/8995650111_002.jpg?w=1200"));
			toolRepository.save(new Tool(4L, "Mirka PS 1437 " , "Polisher 150mm" , 24000.00, 5, Power.ELECTRIC, manufacturerRepository.findById(1L).get(), "https://www.mirka.com/globalassets/pdm/8991300111_002.jpg?w=1200"));
			toolRepository.save(new Tool(5L, "Mirka Dust Extractor" , "1025 L PC EU 230V" , 31000.00, 5, Power.ELECTRIC, manufacturerRepository.findById(1L).get(), "https://www.mirka.com/globalassets/pdm/8999000111_003.jpg?w=1200"));
			toolRepository.save(new Tool(6L, "Finixa Palm Sander" , "75mm/3 inch - 2.5mm orbital palm sander" , 8800.00, 5, Power.PNEUMATIC, manufacturerRepository.findById(4L).get(), "https://www.finixa.com/site/data/images/product/SAM021.jpg"));
			toolRepository.save(new Tool(6L, "Finixa Electric Polisher" , "1050W 700-2500RPM" , 18000.00, 5, Power.ELECTRIC, manufacturerRepository.findById(4L).get(), "https://www.finixa.com/site/data/images/product/POL552.jpg"));
			
			safetyRepository.save(new Safety(1L, "Finixa GCN 09" , "Nitril Disposable Gloves - 09 - 100pcs" , 1300.00 , 10 , Size.L,manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/GCN08-09-106.jpg" ));
			safetyRepository.save(new Safety(2L, "Finixa GCN 10" , "Nitril Disposable Gloves - 10 - 100pcs" , 1300.00 , 10 , Size.XL,manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/GCN08-09-106.jpg" ));
			safetyRepository.save(new Safety(3L, "Finixa MAS 00" , "Spray mask A1 P2" , 2300.00 , 10 , Size.STRECHABLE , manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/MAS002.jpg" ));
			safetyRepository.save(new Safety(4L, "Finixa MAS 13" , "Carbon Dust Mask Safety Class FFP2." , 250.00 , 10 , Size.STRECHABLE , manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/MAS135_339_mas-13_1599661775.jpg" ));
			safetyRepository.save(new Safety(5L, "Finixa Spray Overalls" , "Polyester spray overall with optional grey knee protection" , 1400.00 , 10 , Size.S,manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/SOG1.jpg" ));
			safetyRepository.save(new Safety(6L, "Finixa Spray Overalls" , "Polyester spray overall with optional grey knee protection" , 1400.00 , 10 , Size.M,manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/SOG1.jpg" ));
			safetyRepository.save(new Safety(7L, "Finixa Spray Overalls" , "Polyester spray overall with optional grey knee protection" , 1400.00 , 10 , Size.L,manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/SOG1.jpg" ));
			safetyRepository.save(new Safety(8L, "Finixa Spray Overalls" , "Polyester spray overall with optional grey knee protection" , 1400.00 , 10 , Size.XL,manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/SOG1.jpg" ));
			safetyRepository.save(new Safety(9L, "Finixa Spray Overalls" , "Polyester spray overall with optional grey knee protection" , 1400.00 , 10 , Size.XXL,manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/SOG1.jpg" ));
			safetyRepository.save(new Safety(10L, "Finixa Spray Overalls" , "Polyester spray overall with optional grey knee protection" , 1400.00 , 10 , Size.XXXL,manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/SOG1.jpg" ));
			safetyRepository.save(new Safety(11L, "SATA Air Star F" , "Spray Mask A2 P3" , 5200.00 , 10 , Size.STRECHABLE,manufacturerRepository.findById(5L).get(),"https://www.sata.com/assets/pim/assets/592x839/HERO-BILD-134353-AIR-STAR-F-FILTER.PNG" ));
			
			helperRepository.save(new Helper(1L , "Mirka File Board Flexible Yellow" ,  5500.00 , 5 , Dimension.OSMA ,manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/8391150111_001.jpg?w=306"));
			helperRepository.save(new Helper(2L , "Mirka Sanding Block 36H Grey" ,  1300.00 , 5 , Dimension.DVANAESETA ,manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/8391702011_003.jpg?w=306"));
			helperRepository.save(new Helper(3L , "Mirka Sanding Block 22H Grey" ,  1100.00 , 5 , Dimension.TRETA ,manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/8391502011_003.jpg?w=306"));
			helperRepository.save(new Helper(4L , "Mirka Sanding Block 2-Sided Soft/Hard" ,  500.00 , 5 , Dimension.TRINAESETA ,manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/8390900111_003.jpg?w=306"));
			helperRepository.save(new Helper(5L , "Mirka Rubber Sanding Block" ,  1300.00 , 5 , Dimension.TRINAESETA ,manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/8390100111_003.jpg?w=306"));
			helperRepository.save(new Helper(6L , "Mirka Curved Pad for 70x198mm Block 22H" ,  900.00 , 5 , Dimension.TRETA ,manufacturerRepository.findById(1L).get(),"https://www.mirka.com/globalassets/pdm/8391515011_001.jpg?w=306"));
			helperRepository.save(new Helper(7L , "Finixa File Board" ,  3500.00 , 5 , Dimension.OSMA ,manufacturerRepository.findById(4L).get(),"https://www.finixa.com/site/data/images/product/SAB323.jpg"));
			
			
			
			} catch (Exception e) {
				System.out.println("Post construct NOT called");
		}
	}

}
