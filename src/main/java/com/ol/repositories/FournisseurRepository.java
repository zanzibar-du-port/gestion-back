package com.ol.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ol.models.Fournisseur;
import com.ol.models.Produit;
import com.ol.models.tofront.FournisseurToFront;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur	, Integer>{

	@Query(value = "SELECT * FROM fournisseur WHERE id = (select id_fournisseur from produit where id = :idProduit)", 
			  nativeQuery = true)
	Optional<Fournisseur> findByIdProduit(@Param("idProduit") Integer idProduit);

	

}
