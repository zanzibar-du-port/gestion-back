package com.ol.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.models.Image;
import com.ol.models.Produit;
import com.ol.models.fromfrontadmin.ProduitFromFrontAdmin;
import com.ol.models.tofrontadmin.ProduitToFrontAdmin;
import com.ol.models.tofrontclient.ProduitDetailToFrontClient;
import com.ol.models.tofrontclient.ProduitToFrontClient;
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
	
	@PostMapping("/addProduitFromFrontAdmin")
	public ResponseEntity<String> addProduitFromFrontAdmin(@RequestBody ProduitFromFrontAdmin produitFromFrontAdmin) {
		String produitBdd = produitService.saveProduitFromFrontAdmin(produitFromFrontAdmin).toString();
		return new ResponseEntity<String>(produitBdd, HttpStatus.OK);
	}
	
	@GetMapping("/getOneProduitFromFrontAdmin/{idProduit}")
	public ProduitFromFrontAdmin getOneProduitFromFrontAdmin(@PathVariable("idProduit") Integer idProduit) {
		return produitService.getOneProduitFromFrontAdmin(idProduit);
	}
	
	@GetMapping("/getAll")
	public Iterable<Produit> getAllProduits() {
		List<Image> listeVide = new ArrayList<>();
		List<Produit> listeProduits = produitService.getAll();
		for (Produit produit : listeProduits) {
			produit.setListeImages(listeVide);
		}
		return listeProduits;
	}
	
	@GetMapping("/getAllProduitToFrontClient/{pagination}")
	public List<ProduitToFrontClient> getAllProduitToFrontClient(@PathVariable("pagination") int pagination) {
		return produitService.getAllProduitToFrontClient(pagination);
	}
	
	@GetMapping("/getProduitDetailToFrontClientById/{idProduit}")
	public ProduitDetailToFrontClient getProduitDetailToFrontClientById(@PathVariable("idProduit") Integer idProduit) {
		return produitService.getProduitDetailToFrontClientById(idProduit);
	}
	
	@GetMapping("/getAllProduitToFrontAdmin/{pagination}")
	public List<ProduitToFrontAdmin> getAllProduitToFrontAdmin(@PathVariable("pagination") int pagination) {
		return produitService.getAllProduitToFrontAdmin(pagination);
	}
	
	@GetMapping(path = { "/getAllProduitToFrontClientByLibelleCategorie/{categorie}/{pagination}" })
	public List<ProduitToFrontClient> getAllProduitToFrontClientByLibelleCategorie(
			@PathVariable("categorie") String categorie,
			@PathVariable("pagination") int pagination) throws IOException {
		return produitService.getAllProduitToFrontClientByLibelleCategorie(categorie, pagination);
	}
	
	@GetMapping("/getAllProduitToFrontClientByLibelleCategorieAndLibelleSousCategorie/{categorie}/{sousCategorie}/{pagination}")
	public List<ProduitToFrontClient> getAllProduitToFrontAdminByLibelleCategorieAndLibelleSousCategorie(@PathVariable("categorie") String categorie,
			@PathVariable("sousCategorie") String sousCategorie,
			@PathVariable("pagination") int pagination) {
		return produitService.getAllProduitToFrontClientByLibelleCategorieAndLibelleSousCategorie(categorie, sousCategorie, pagination);
	}
	
	@GetMapping("/getProduitsActifs")
	public Iterable<Produit> getAllProduitsActifs() {
		List<Image> listeVide = new ArrayList<>();
		List<Produit> listeProduits = produitService.getAllProduitsActifs();
		for (Produit produit : listeProduits) {
			produit.setListeImages(listeVide);
		}
		return listeProduits;
	}
	
	
	
	@GetMapping(path = { "/get/{produitId}" })
	public Produit getProduit(@PathVariable("produitId") Integer produitId) throws IOException {
		final Optional<Produit> retrievedProduit = produitService.findById(produitId);
		return retrievedProduit.get();
	}
	
	
	/**
	 * @param listeProduits
	 * @return
	 * @PostMapping("/getProduitsActifs")
	public List<ProduitCommande> getProduitsActifs(@RequestBody List<ProduitCommande> listeProduits) {
		return listeProduits.stream().filter(p -> produitService.isProduitActif(p.getProduit()) 
				&& p.getProduit().getListeSelections().stream().anyMatch(s -> s.getId() == p.getSelection().getId()))
				.collect(Collectors.toList());
	}
	 */
	
	
	@DeleteMapping(path = { "/delete/{produitId}" })
	public ResponseEntity<Integer> deleteProduit(@PathVariable("produitId") Integer produitId) throws IOException {
		produitService.deleteProduit(produitId);
		return new ResponseEntity<>(produitId, HttpStatus.OK);
	}
}
