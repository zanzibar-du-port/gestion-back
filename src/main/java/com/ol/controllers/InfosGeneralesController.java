package com.ol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.models.Header;
import com.ol.models.InfosGenerales;
import com.ol.services.HeaderService;
import com.ol.services.InfosGeneralesService;

@RestController
@RequestMapping(path = "infosGenerales")
public class InfosGeneralesController {

	@Autowired
	InfosGeneralesService infosGeneralesService;
	
	@PostMapping("/changeInfosGenerales")
	public ResponseEntity<String> changeInfosGenerales(@RequestBody InfosGenerales infosGenerales) {
		String infosGeneralesString = infosGeneralesService.saveInfosGenerales(infosGenerales).toString();
		return new ResponseEntity<String>(infosGeneralesString, HttpStatus.OK);
	}
	
	@GetMapping("/getInfosGenerales")
	public InfosGenerales getInfosGenerales() {
		return infosGeneralesService.getInfosGenerales();
	}
	
}
