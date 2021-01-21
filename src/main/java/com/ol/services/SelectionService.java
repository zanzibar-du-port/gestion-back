package com.ol.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.frombdd.ProduitBdd;
import com.ol.models.fromfrontadmin.SelectionFromFrontAdmin;
import com.ol.models.tofrontclient.SelectionPanierToFrontClient;
import com.ol.models.tofrontclient.SelectionToFrontClient;
import com.ol.repositories.ProduitRepoSitory;
import com.ol.repositories.SelectionRepository;

@Service
public class SelectionService {

	@Autowired
	SelectionRepository selectionRepository;
	
	@Autowired
	ProduitService produitService;

	@Autowired
	ProduitRepoSitory produitRepoSitory;
	
	@Autowired
	SelectionConvertorService selectionConvertorService;

	@Autowired
	ProduitConvertorService produitConvertorService;
	
	public List<SelectionFromFrontAdmin> getListeSelectionFromFrontAdminByIdProduit(Integer idProduit) {
		return selectionRepository.findAllSelectionBddByIdProduit(idProduit)
				.stream()
				.map(sBdd -> selectionConvertorService.convertSelectionBddToSelectionFromFrontAdmin(sBdd))
				.collect(Collectors.toList());
	}

	public List<SelectionToFrontClient> getListeSelectionToFrontClientByIdProduit(Integer idProduit) {
		return selectionRepository.findAllSelectionBddByIdProduit(idProduit)
				.stream()
				.map(sBdd -> selectionConvertorService.convertSelectionBddToSelectionToFrontClient(sBdd))
				.collect(Collectors.toList());
	}

	public List<Integer> getSelectionsActifs(List<Integer> listeIdSelection) {
		return listeIdSelection
				.stream()
				.filter(idSelection -> produitService.isProduitActifByIdSelection(idSelection))
				.filter(idSelection -> selectionRepository.getQuantiteDisponible(idSelection) > 0)
				.collect(Collectors.toList());
	}

	public List<SelectionPanierToFrontClient> getListeSelectionPanierToFrontClient(List<Integer> listeIdSelection) {
		return listeIdSelection
				.stream()
				.map(idSelection -> getSelectionPanierToFrontClientByIdSelection(idSelection))
				.collect(Collectors.toList());
	}
	
	public SelectionPanierToFrontClient getSelectionPanierToFrontClientByIdSelection(Integer idSelection) {
		return selectionConvertorService.convertSelectionToSelectionPanierToFrontClient(selectionRepository.findById(idSelection).get());
	}

	public Integer getTotalPrixPanier(List<Integer> listeIdSelection) {
		return listeIdSelection.stream().map(id -> selectionRepository.getPrix(id))
				.reduce(0, Integer::sum)
				;
	}

	public Integer getPrixLivraison(List<Integer> listeIdSelection) {
		//TODO faire methode calcul prix livraison
		return 30;
	}
}
