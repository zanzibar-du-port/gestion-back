package com.ol.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.models.Produit;
import com.ol.models.tofrontclient.SelectionPanierToFrontClient;
import com.ol.services.LivraisonService;
import com.ol.services.ProduitService;
import com.ol.services.SelectionService;

@RestController
@RequestMapping(path = "selection")
public class SelectionController {
	
	@Autowired
	SelectionService selectionService;

	@Autowired
	LivraisonService livraisonService;
	
	@PostMapping("/getSelectionsActifs")
	public List<Integer> getSelectionsActifs(@RequestBody List<Integer>  listeIdSelection) {
		return selectionService.getSelectionsActifs(listeIdSelection);
	}

	@PostMapping("/getListeSelectionPanierToFrontClient")
	public List<SelectionPanierToFrontClient> getListeSelectionPanierToFrontClient(@RequestBody List<Integer>  listeIdSelection) {
		return selectionService.getListeSelectionPanierToFrontClient(listeIdSelection);
	}
	
	@PostMapping("/getTotalPrixPanier")
	public Integer getTotalPrixPanier(@RequestBody List<Integer>  listeIdSelection) {
		return selectionService.getTotalPrixPanier(listeIdSelection);
	}
	
	@PostMapping("/getPrixLivraison")
	public Integer getPrixLivraison(@RequestBody List<Integer>  listeIdSelection) {
		return livraisonService.getPrixLivraison(listeIdSelection);
	}
}
