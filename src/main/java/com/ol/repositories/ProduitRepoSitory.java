package com.ol.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ol.models.Produit;

@Repository
public interface ProduitRepoSitory extends JpaRepository<Produit, Integer>{

	/**
     * 
     */
	@Query(value = "SELECT DISTINCT(T1.categorie) FROM (SELECT categorie, position_dans_la_liste FROM produit WHERE etat = 'ACTIF' ORDER BY position_dans_la_liste) AS T1", 
			  nativeQuery = true)
	List<String> getAllCategorie();

	@Query(value = "SELECT * FROM produit WHERE etat = 'ACTIF' AND categorie = :categorie ORDER BY position_dans_la_liste", 
			  nativeQuery = true)
	Iterable<Produit> findByCategorie(@Param("categorie") String categorie);
	
	@Query(value = "SELECT * FROM produit WHERE etat = 'ACTIF' ORDER BY position_dans_la_liste", 
			  nativeQuery = true)
	Iterable<Produit> findAllOrder();

	
}
