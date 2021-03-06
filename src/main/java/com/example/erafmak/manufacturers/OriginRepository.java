package com.example.erafmak.manufacturers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Long> {

	Origin findByName(String origin);
	
	Boolean existsByName(String origin);

}
