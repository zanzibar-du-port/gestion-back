package com.ol.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.models.auth.Utilisateur;
import com.ol.models.fromfront.FournisseurFromFront;
import com.ol.models.tofront.FournisseurToFront;
import com.ol.services.FournisseurService;
import com.ol.services.UtilisateurService;

@RestController
@RequestMapping(path = "fournisseur")
public class FournisseurController {

	@Autowired
	FournisseurService fournisseurService;
	
	@PostMapping("/postFournisseurFromFront")
	public FournisseurFromFront addUtilisateur(@RequestBody FournisseurFromFront fournisseurFromFront) {
		return fournisseurService.save(fournisseurFromFront);
	}
	
	@GetMapping("/getAllFournisseurToFront")
	public List<FournisseurToFront> getAllFournisseurToFront() {
		return fournisseurService.getAllFournisseurToFront();
	}
	
	@GetMapping("/deleteFournisseurByIdFournisseur/{idFournisseur}")
	public List<FournisseurToFront> deleteFournisseurByIdFournisseur(@PathVariable("idFournisseur") Integer idFournisseur) {
		return fournisseurService.deleteFournisseurByIdFournisseur(idFournisseur);
	}
	
	@GetMapping("/getFournisseurFromFrontByIdFournisseur/{idFournisseur}")
	public FournisseurFromFront getFournisseurFromFrontByIdFournisseur(@PathVariable("idFournisseur") Integer idFournisseur) {
		return fournisseurService.getFournisseurFromFrontByIdFournisseur(idFournisseur);
	}
	
}
