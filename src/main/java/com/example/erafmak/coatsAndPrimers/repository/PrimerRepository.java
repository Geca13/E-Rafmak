package com.example.erafmak.coatsAndPrimers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.erafmak.coatsAndPrimers.entity.Primer;

@Repository
public interface PrimerRepository extends JpaRepository<Primer, Long> {
	
	List<Primer> findAllByHardeners_Id(Long id);
	
	Boolean existsByNameAndHardeners_Id(String name , Long id);

}
