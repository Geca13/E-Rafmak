package com.example.erafmak.safety;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafetyRepository extends JpaRepository<Safety, Long> {

}
