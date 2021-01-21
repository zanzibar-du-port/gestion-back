package com.ol.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Commande;
import com.ol.models.fromfrontclient.CommandeFromFrontClient;
import com.ol.repositories.CommandeRepository;
import com.ol.repositories.ProduitRepoSitory;

@Service
public class CommandeService {

	@Autowired
	CommandeRepository commandeRepository;
	
	@Autowired
	ProduitRepoSitory produitRepository;
	
	@Autowired
	ProduitService produitService;

	@Autowired
	SelectionService selectionService;

	@Autowired
	UtilsService utilsService;
	
	public Commande save(Commande commande) {
		commande.getListeSelection().stream().forEach(s -> utilsService.addAchatSelection(s));
		return commandeRepository.save(commande);
	}
	
	//TODO adapter calcul du prix selon selection et non produit
	public boolean isPriceOk(CommandeFromFrontClient commandeFromFrontClient){
		
		int prixListeSelectionBdd = selectionService.getTotalPrixPanier(commandeFromFrontClient.getListeIdSelection());
		
		int prixLivraisonBdd = 0;
		if(commandeFromFrontClient.getMethodeDeReceptionProduits().equals("LIVRAISON")) {
			prixLivraisonBdd = selectionService.getPrixLivraison(commandeFromFrontClient.getListeIdSelection());
		}
		
		if (prixListeSelectionBdd == commandeFromFrontClient.getPrixPanier()
				&& prixLivraisonBdd == commandeFromFrontClient.getPrixLivraison()) {
			return true;
		}else {
			return false;
		}
		
		
		
	}
	
	public List<Commande> getAllDepuis(Integer nbJours) {
		return commandeRepository.getAllDepuis(LocalDateTime.now().minusDays(nbJours) );
	}

	public Iterable<Commande> getAll() {
		return commandeRepository.findAll();
	}


	public List<Commande> getDepuis(Integer nJours) {
		LocalDateTime dateHeurePasse = LocalDateTime.now().minusDays(nJours);
		return commandeRepository.findDepuis(dateHeurePasse);
	}
}
