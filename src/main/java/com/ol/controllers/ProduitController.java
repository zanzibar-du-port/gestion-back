package com.ol.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.models.fromfront.FournisseurFromFront;
import com.ol.models.fromfront.ProduitFromFront;
import com.ol.models.tofront.FournisseurToFront;
import com.ol.models.tofront.ProduitToFront;
import com.ol.services.ProduitService;

@RestController
@RequestMapping(path = "produit")
public class ProduitController {

	@Autowired
	ProduitService produitService;
	
	@PostMapping("/postProduitFromFront")
	public ProduitFromFront postProduitFromFront(@RequestBody ProduitFromFront produitFromFront) {
		return produitService.save(produitFromFront);
	}
	
	@GetMapping("/getAllProduitToFrontByIdFournisseur/{idFournisseur}")
	public List<ProduitToFront> getAllProduitToFrontByIdFournisseur(@PathVariable("idFournisseur") Integer idFournisseur) {
		return produitService.getAllProduitToFrontByIdFournisseur(idFournisseur);
	}
	
	@GetMapping("/getProduitFromFrontByIdProduit/{idProduit}")
	public ProduitFromFront getProduitFromFrontByIdProduit(@PathVariable("idProduit") Integer idProduit) {
		return produitService.getProduitFromFrontByIdProduit(idProduit);
	}
	
	@GetMapping("/deleteProduitByIdProduit/{idProduit}")
	public Integer deleteProduitByIdProduit(@PathVariable("idProduit") Integer idProduit) {
		return produitService.deleteProduitByIdProduit(idProduit);
	}
}
