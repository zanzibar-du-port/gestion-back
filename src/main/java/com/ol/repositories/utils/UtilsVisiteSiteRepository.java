package com.ol.repositories.utils;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ol.models.utils.UtilsLocalDateParHeure;
import com.ol.models.utils.UtilsVisiteSite;

@Repository
public interface UtilsVisiteSiteRepository extends JpaRepository<UtilsVisiteSite, Integer>{

	Optional<UtilsVisiteSite> findByUtilsLocalDateParHeure(UtilsLocalDateParHeure utilsLocalDateParHeure);

}
