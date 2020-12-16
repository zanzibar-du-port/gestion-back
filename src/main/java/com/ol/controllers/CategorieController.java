package com.ol.controllers;

import java.io.IOException;
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
import com.ol.services.CategorieService;
import com.ol.services.ProduitService;

@RestController
@RequestMapping(path = "categorie")
public class CategorieController {

	@Autowired
	CategorieService categorieService;
	
	
	@GetMapping("/getAll")
	public Iterable<String> getAllCategorie() {
		return categorieService.getAll();
	}
	
	
}
