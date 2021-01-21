package com.ol.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
import com.ol.models.tofrontadmin.CategorieDetailToFrontAdmin;
import com.ol.models.tofrontadmin.SousCategorieDetailToFrontAdmin;
import com.ol.services.CategorieService;
import com.ol.services.ProduitService;

@RestController
@RequestMapping(path = "categorie")
public class CategorieController {

	@Autowired
	CategorieService categorieService;
	
	
	@GetMapping("/getAllCategorie")
	public List<String> getAllCategorie() {
		return categorieService.getAll();
	}
	
	@GetMapping("/getAllSousCategoriesByLibelleCategorie/{libelleCategorie}")
	public Iterable<String> getAllSousCategoriesByLibelleCategorie(@PathVariable("libelleCategorie") String libelleCategorie) {
		return categorieService.getAllSousCategoriesByLibelleCategorie(libelleCategorie);
	}
	
	@GetMapping("/getAllCategorieDetailToFrontAdmin")
	public List<CategorieDetailToFrontAdmin> getAllCategorieToFrontAdmin() {
		return categorieService.getAllCategorieDetailToFrontAdmin();
	}
	
	@GetMapping("/getAllSousCategorieDetailToFrontAdminByIdCategorie/{idCategorie}")
	public List<SousCategorieDetailToFrontAdmin> getAllSousCategorieDetailToFrontAdminByLibelleCategorie(@PathVariable("idCategorie") Integer idCategorie) {
		return categorieService.getAllSousCategorieDetailToFrontAdminByIdCategorie(idCategorie);
	}
	
	@GetMapping("/changePositionDansLaListeCategorie/{idCategorie}/{positionDansLaListe}")
	public ResponseEntity<String> changePositionDansLaListeCategorie(@PathVariable("idCategorie") Integer idCategorie, 
			@PathVariable("positionDansLaListe") Integer positionDansLaListe) {
		categorieService.changePositionDansLaListeCategorie(idCategorie, positionDansLaListe);
		return new ResponseEntity<String>("modification position da la liste", HttpStatus.OK);
	}
	
	@GetMapping("/changePositionDansLaListeSousCategorie/{idCategorie}/{positionDansLaListe}")
	public ResponseEntity<String> changePositionDansLaListeSousCategorie(@PathVariable("idCategorie") Integer idSousCategorie, 
			@PathVariable("positionDansLaListe") Integer positionDansLaListe) {
		categorieService.changePositionDansLaListeSousCategorie(idSousCategorie, positionDansLaListe);
		return new ResponseEntity<String>("modification position da la liste", HttpStatus.OK);
	}
	
}
