package com.example.erafmak.sprayGuns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.erafmak.sprayGuns.entity.SprayGun;

@Repository
public interface SprayGunRepository extends JpaRepository<SprayGun, Long> {

	boolean existsByNameAndNozzles_Id(String name, Long id);

}
