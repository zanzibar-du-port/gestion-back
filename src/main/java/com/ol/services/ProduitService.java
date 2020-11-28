package com.ol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Produit;
import com.ol.repositories.ProduitRepoSitory;

@Service
public class ProduitService {
	
	@Autowired
	ProduitRepoSitory produitRepoSitory;

	public Produit saveProduit(Produit produitModel) {
		return produitRepoSitory.save(produitModel);
	}

	public Iterable<Produit> getAll() {
		return produitRepoSitory.findAll();
	}
}
