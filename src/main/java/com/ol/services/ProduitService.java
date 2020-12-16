package com.ol.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Image;
import com.ol.models.Produit;
import com.ol.repositories.ProduitRepoSitory;

@Service
public class ProduitService {
	
	@Autowired
	ProduitRepoSitory produitRepoSitory;

	public Produit saveProduit(Produit produit) {
		return produitRepoSitory.save(new Produit(
				produit.getId(),
				produit.getTitre(),
				produit.getDescription(),
				produit.getDetail(),
				produit.getEtat(),
				produit.getPrix(),
				produit.getImagePrincipale(),
				produit.getListeImages(),
				produit.getCategorie(),
				produit.getPositionDansLaListe(),
				produit.getListeSelections()));
	}

	public Iterable<Produit> getAll() {
		return produitRepoSitory.findAllOrder();
	}

	public Optional<Produit> findById(Integer produitId) {
		return produitRepoSitory.findById(produitId);
	}

	public Iterable<String> getAllCategorie() {
		return produitRepoSitory.getAllCategorie();
	}

	public Iterable<Produit> getAllByCategorie(String categorie) {
		return produitRepoSitory.findByCategorie(categorie);
	}

	public Boolean isProduitActif(Produit produit) {
		final Optional<Produit> retrievedProduit = findById(produit.getId());
		if (retrievedProduit.isPresent() && retrievedProduit.get().getEtat().equals("ACTIF")) {
			return true;
		}else {
			return false;
		}
	}
}
