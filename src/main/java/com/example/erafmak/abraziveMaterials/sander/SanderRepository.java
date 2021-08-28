package com.example.erafmak.abraziveMaterials.sander;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanderRepository extends JpaRepository<Sander, Long> {

	List<Sander> findByNameContaining(String discs);

}
