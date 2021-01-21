package com.ol.repositories.utils;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ol.models.utils.UtilsLocalDateParHeure;

@Repository
public interface UtilsLocalDateParHeureRepository extends JpaRepository<UtilsLocalDateParHeure, Integer>{

	Optional<UtilsLocalDateParHeure> findByLocalDateHeure(LocalDateTime localDateHeure);

	
}
