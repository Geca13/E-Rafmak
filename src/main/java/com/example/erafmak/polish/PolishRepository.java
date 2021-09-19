package com.example.erafmak.polish;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolishRepository extends JpaRepository<Polish, Long> {

	List<Polish> findAllByPads_Id(Long id);

	Boolean existsByNameAndPads_Id(String name, Long id);
}
