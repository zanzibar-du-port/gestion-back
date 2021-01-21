package com.ol.repositories.utils;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ol.models.utils.UtilsAchatSelection;
import com.ol.models.utils.UtilsLocalDateParHeure;
import com.ol.models.utils.UtilsPanierSelection;

@Repository
public interface UtilsAchatSelectionRepository extends JpaRepository<UtilsAchatSelection, Integer>{

	@Query(value = "SELECT count(a.id) FROM achat_selection a WHERE a.selection_id = :id", 
			  nativeQuery = true)
	Integer getNbAchatsByIdProduit(Integer id);

	Optional<UtilsAchatSelection> findByUtilsLocalDateParHeureAndIdSelection(
			UtilsLocalDateParHeure utilsLocalDateParHeure,
			Integer idSelection);
}
