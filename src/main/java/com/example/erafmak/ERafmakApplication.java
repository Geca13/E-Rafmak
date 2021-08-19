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
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
