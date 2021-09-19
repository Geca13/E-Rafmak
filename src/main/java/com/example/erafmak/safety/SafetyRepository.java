package com.example.erafmak.safety;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafetyRepository extends JpaRepository<Safety, Long> {

	Boolean existsByIdAndSizeQty_Size(Long id,Size size);
	
	Safety findByIdAndSizeQty_Size(Long id,Size size);
	
	Safety findBySizeQty_Id(Long id);

	Boolean existsByIdAndSizeQty_IsAvailable(Long id, Boolean exists);
}
