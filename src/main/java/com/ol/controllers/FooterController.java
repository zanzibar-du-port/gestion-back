package com.ol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.models.Footer;
import com.ol.models.Header;
import com.ol.services.FooterService;
import com.ol.services.HeaderService;

@RestController
@RequestMapping(path = "footer")
public class FooterController {

	@Autowired
	FooterService footerService;
	
	@PostMapping("/changeFooter")
	public ResponseEntity<String> changeFooter(@RequestBody Footer footer) {
		String footerString = footerService.saveFooter(footer).toString();
		return new ResponseEntity<String>(footerString, HttpStatus.OK);
	}
	
	@GetMapping("/getFooter")
	public Footer getFooter() {
		return footerService.getFooter();
	}
}
