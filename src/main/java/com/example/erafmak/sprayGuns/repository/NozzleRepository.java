package com.example.erafmak.sprayGuns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.erafmak.sprayGuns.entity.Nozzle;

@Repository
public interface NozzleRepository extends JpaRepository<Nozzle, Long> {

}
