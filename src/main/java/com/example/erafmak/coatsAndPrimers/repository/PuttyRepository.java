package com.example.erafmak.coatsAndPrimers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.erafmak.coatsAndPrimers.entity.Putty;

@Repository
public interface PuttyRepository extends JpaRepository<Putty, Long> {

}
