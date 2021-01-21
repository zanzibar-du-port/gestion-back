package com.ol.repositories.utils;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ol.models.utils.UtilsLocalDateParHeure;
import com.ol.models.utils.UtilsPanierSelection;

@Repository
public interface UtilsPanierSelectionRepository extends JpaRepository<UtilsPanierSelection, Integer>{

	Optional<UtilsPanierSelection> findByUtilsLocalDateParHeureAndIdSelection(UtilsLocalDateParHeure utilsLocalDateParHeure,
			Integer idSelection);
	
}
