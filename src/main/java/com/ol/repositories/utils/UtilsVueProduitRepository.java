package com.ol.repositories.utils;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ol.models.utils.UtilsLocalDateParHeure;
import com.ol.models.utils.UtilsVisiteSite;
import com.ol.models.utils.UtilsVueProduit;

@Repository
public interface UtilsVueProduitRepository extends JpaRepository<UtilsVueProduit, Integer>{
	
	Optional<UtilsVueProduit> findByUtilsLocalDateParHeureAndIdProduit(UtilsLocalDateParHeure utilsLocalDateParHeure,
			Integer idProduit);
	
}
