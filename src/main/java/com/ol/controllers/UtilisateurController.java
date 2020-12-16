package com.ol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.models.Produit;
import com.ol.models.auth.Utilisateur;
import com.ol.repositories.UtilisateurRepository;
import com.ol.services.ProduitService;

@RestController
@RequestMapping(path = "utilisateur")
public class UtilisateurController {

	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	@PostMapping("/addUtilisateur")
	public ResponseEntity<String> addUtilisateur(@RequestBody Utilisateur utilisateur) {
		String produitBdd = utilisateurRepository.save(utilisateur).toString();
		return new ResponseEntity<String>(produitBdd, HttpStatus.OK);
	}
	
}
