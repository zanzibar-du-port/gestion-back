package com.ol.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ol.models.Produit;
import com.ol.models.tofront.ProduitToFront;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer>{

	@Query(value = "SELECT * FROM produit WHERE id_fournisseur = :idFournisseur order by categorie", 
			  nativeQuery = true)
	List<Produit> getAllProduitToFrontByIdFournisseur(@Param("idFournisseur") Integer idFournisseur);

}
