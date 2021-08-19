package com.example.erafmak.abraziveMaterials.helpers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelperRepository extends JpaRepository<Helper, Long> {

}
