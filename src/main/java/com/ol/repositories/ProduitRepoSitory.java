package com.ol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ol.models.Produit;

@Repository
public interface ProduitRepoSitory extends JpaRepository<Produit, Integer>{

	
}
