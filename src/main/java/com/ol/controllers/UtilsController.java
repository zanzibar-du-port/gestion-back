package com.ol.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.models.auth.Utilisateur;
import com.ol.services.UtilsService;

@RestController
@RequestMapping(path = "utils")
public class UtilsController {

	@Autowired
	UtilsService utilsService;
	
	@GetMapping("/addVisiteSite")
	public ResponseEntity<String> addVisiteSite() {
		utilsService.addVisiteSite();
		return new ResponseEntity<String>("addVisiteSite", HttpStatus.OK);
	}
	
	@GetMapping("/addvueProduit/{idProduit}")
	public ResponseEntity<String> addVisiteSite(@PathVariable("idProduit") Integer idProduit) {
		utilsService.addvueProduit(idProduit);
		return new ResponseEntity<String>("addvueProduit", HttpStatus.OK);
	}
	
	@GetMapping("/addPanierSelection/{idSelection}")
	public ResponseEntity<String> addPanierSelection(@PathVariable("idSelection") Integer idSelection) {
		utilsService.addPanierSelection(idSelection);
		return new ResponseEntity<String>("addPanierSelection", HttpStatus.OK);
	}
	
	@GetMapping("/addVueCategorie/{libelleCategorie}/{pagination}")
	public ResponseEntity<String> addVueCategorie(@PathVariable("libelleCategorie") String libelleCategorie,
			@PathVariable("pagination") Integer pagination) {
		utilsService.addVueCategorie(libelleCategorie, pagination);
		return new ResponseEntity<String>("addVueCategorie", HttpStatus.OK);
	}
	
	@GetMapping("/addVueSousCategorie/{libelleCategorie}/{libelleSousCategorie}/{pagination}")
	public ResponseEntity<String> addVueCategorie(@PathVariable("libelleCategorie") String libelleCategorie,
			@PathVariable("libelleSousCategorie") String libelleSousCategorie,
			@PathVariable("pagination") Integer pagination) {
		utilsService.addVueSousCategorie(libelleCategorie, pagination);
		return new ResponseEntity<String>("addVueSousCategorie", HttpStatus.OK);
	}
	
	@GetMapping("/getNbPaginations/{libelleCategorie}/{libelleSousCategorie}")
	public Integer getNbPaginations(
			@PathVariable("libelleCategorie") String libelleCategorie,
			@PathVariable("libelleSousCategorie") String libelleSousCategorie) {
		return utilsService.getNbPaginations(libelleCategorie, libelleSousCategorie);
	}
	
}
