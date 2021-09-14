package com.example.erafmak.abraziveMaterials.sander;



import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GranulationQty {
	
	@Id
	private Long id;
	
	private Boolean isAvailable;
	
	private Double price;
	
	@Enumerated(EnumType.STRING)
	private Granulation granulation;

}
