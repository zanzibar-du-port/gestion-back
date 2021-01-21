package com.ol.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ol.models.Selection;
import com.ol.models.SousCategorie;
import com.ol.models.frombdd.SelectionBdd;
import com.ol.models.tofrontadmin.SelectionToFrontAdmin;

@Repository
public interface SelectionRepository extends JpaRepository<Selection, Integer>{

	@Modifying
	@Query(value = "UPDATE selection SET quantite_disponible = quantite_disponible - 1 WHERE id = :selectionId ",
			nativeQuery = true)
	void addAchat(@Param("selectionId") Integer selectionId);

	@Modifying
	@Query(value = "UPDATE selection SET quantite_disponible = quantite_disponible - 1 WHERE id = :selectionId ",
			nativeQuery = true)
	void decreaseSelectionProduit(@Param("selectionId") Integer selectionId);

	@Query(nativeQuery=true)
	List<SelectionBdd> findAllSelectionBddByIdProduit(Integer id);

	@Query(value = "select quantite_disponible from selection where id = :idSelection",
			nativeQuery = true)
	Integer getQuantiteDisponible(Integer idSelection);

	@Query(value = "select produit_id from selection where id = :idSelection",
			nativeQuery = true)
	Integer getIdProduitByIdSelection(Integer idSelection);

	@Query(value = "select prix from selection where id = :idSelection",
			nativeQuery = true)
	Integer getPrix(Integer idSelection);
	
}
