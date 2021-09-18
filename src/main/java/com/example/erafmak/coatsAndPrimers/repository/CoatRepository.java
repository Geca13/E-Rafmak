package com.example.erafmak.coatsAndPrimers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.erafmak.coatsAndPrimers.entity.Coat;

@Repository
public interface CoatRepository extends JpaRepository<Coat, Long> {
	
	List<Coat> findAllByHardeners_Id(Long id);
	
	Boolean existsByNameAndHardeners_Id(String name , Long id);
}
