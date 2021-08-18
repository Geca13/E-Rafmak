package com.example.erafmak.coatsAndPrimers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.erafmak.coatsAndPrimers.entity.Coat;

@Repository
public interface CoatRepository extends JpaRepository<Coat, Long> {

}
