package com.example.erafmak.polish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PadsRepository extends JpaRepository<Pads, Long> {

}
