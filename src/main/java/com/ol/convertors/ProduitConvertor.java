package com.ol.convertors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Fournisseur;
import com.ol.models.Produit;
import com.ol.models.fromfront.FournisseurFromFront;
import com.ol.models.fromfront.ProduitFromFront;
import com.ol.models.tofront.FournisseurToFront;
import com.ol.models.tofront.ProduitToFront;
import com.ol.repositories.FournisseurRepository;
import com.ol.repositories.ProduitRepository;
import com.ol.services.CommandeService;
import com.ol.services.ProduitService;

@Service
public class ProduitConvertor {
	
	@Autowired
	FournisseurRepository fournisseurRepository;
	
	@Autowired
	ProduitRepository produitRepository;
	
	@Autowired
	CommandeService commandeService;

	public Produit convertFromFrontVersBdd(ProduitFromFront produitFromFront) {
		return new Produit(
				produitFromFront.getId(),
				produitFromFront.getCategorie(),
				produitFromFront.getReference(),
				produitFromFront.getNom(),
				produitFromFront.getNbProduitParLot(),
				fournisseurRepository.findById(produitFromFront.getIdFournisseur()).get()
				);
	}
	
	public ProduitFromFront convertBddVersFromFront(Produit produit) {
		return new ProduitFromFront(
				produit.getId(),
				produit.getCategorie(),
				produit.getReference(),
				produit.getNom(),
				produit.getNbProduitParLot(),
				produit.getFournisseur().getId()
				);
	}
	
	public Produit convertToFrontVersBdd(ProduitToFront produitToFront) {
		return new Produit(
				produitToFront.getId(),
				produitToFront.getCategorie(),
				produitToFront.getReference(),
				produitToFront.getNom(),
				produitToFront.getNbProduitParLot(),
				produitRepository.findById(produitToFront.getId()).get().getFournisseur()
				);
	}
	
	public ProduitToFront convertBddVersToFront(Produit produit) {
		List<Integer> listeDernieresCommandes = commandeService.getDernieresCommandesByIdProduit(produit.getId());
		return new ProduitToFront(
				produit.getId(),
				produit.getCategorie(),
				produit.getReference(),
				produit.getNom(),
				listeDernieresCommandes.get(listeDernieresCommandes.size()-2),
				listeDernieresCommandes.get(listeDernieresCommandes.size()-1),
				produit.getNbProduitParLot()
				);
	}
	
}
