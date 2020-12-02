package com.ol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.models.Header;
import com.ol.models.Produit;
import com.ol.services.HeaderService;
import com.ol.services.ProduitService;

@RestController
@RequestMapping(path = "header")
public class HeaderController {
	
	@Autowired
	HeaderService headerService;
	
	@PostMapping("/changeHeader")
	public ResponseEntity<String> changeHeader(@RequestBody Header header) {
		String produitBdd = headerService.saveHeader(header).toString();
		return new ResponseEntity<String>(produitBdd, HttpStatus.OK);
	}
	
	@GetMapping("/getHeader")
	public Header getHeader() {
		return headerService.getHeader();
	}

}
