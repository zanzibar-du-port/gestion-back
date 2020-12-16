package com.ol.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.models.Image;
import com.ol.models.Produit;
import com.ol.services.ProduitService;

@RestController
@RequestMapping(path = "produit")
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
	
	@GetMapping(path = { "/getAllByCategorie/{categorie}" })
	public Iterable<Produit> getAllByCategorie(@PathVariable("categorie") String categorie) throws IOException {
		return produitService.getAllByCategorie(categorie);
	}
	
	@GetMapping(path = { "/get/{produitId}" })
	public Produit getProduit(@PathVariable("produitId") Integer produitId) throws IOException {
		final Optional<Produit> retrievedProduit = produitService.findById(produitId);
		return retrievedProduit.get();
	}
	
	@PostMapping("/getProduitsActifs")
	public List<Produit> getProduitsActifs(@RequestBody List<Produit> listeProduits) {
		return listeProduits.stream().filter(p -> produitService.isProduitActif(p)).collect(Collectors.toList());
	}
	
	
}
