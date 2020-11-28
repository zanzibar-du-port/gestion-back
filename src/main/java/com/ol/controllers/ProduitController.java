package com.ol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ol.models.Produit;
import com.ol.services.ProduitService;

@RestController
public class ProduitController {
	
	@Autowired
	ProduitService produitService;
	
	@PostMapping("/addProduit")
	public ResponseEntity<String> addProduit(@RequestBody Produit produit) {
		String produitBdd = produitService.saveProduit(produit).toString();
		return new ResponseEntity<String>(produitBdd, HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public Iterable<Produit> getAllProduitModel() {
		return produitService.getAll();
	}

}
