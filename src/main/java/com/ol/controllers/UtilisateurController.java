package com.ol.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.models.Produit;
import com.ol.models.auth.Utilisateur;
import com.ol.repositories.UtilisateurRepository;
import com.ol.services.ProduitService;
import com.ol.services.UtilisateurService;

@RestController
@RequestMapping(path = "utilisateur")
public class UtilisateurController {

	@Autowired
	UtilisateurService utilisateurService;
	
	@PostMapping("/addUtilisateur")
	public ResponseEntity<String> addUtilisateur(@RequestBody Utilisateur utilisateur) {
		String produitBdd = utilisateurService.saveUtilisateur(utilisateur).toString();
		return new ResponseEntity<String>(produitBdd, HttpStatus.OK);
	}
	
	@GetMapping("/addContact/{email}")
	public ResponseEntity<String> addContact(@PathVariable("email") String email) {
		String produitBdd = utilisateurService.saveContact(new Utilisateur(email,"",Arrays.asList())).toString();
		return new ResponseEntity<String>(produitBdd, HttpStatus.OK);
	}
}
