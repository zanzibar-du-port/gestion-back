package com.ol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.models.fromfront.CommandeFromFront;
import com.ol.models.fromfront.FournisseurFromFront;
import com.ol.services.CommandeService;

@RestController
@RequestMapping(path = "commande")
public class CommandeController {

	@Autowired
	CommandeService commandeService;
	
	@PostMapping("/getMailFromFrontByCommandeFromFront")
	public CommandeFromFront getMailFromFrontByCommandeFromFront(@RequestBody CommandeFromFront commandeFromFront) {
		return commandeService.getMailFromFrontByCommandeFromFront(commandeFromFront);
	}
	
	@PostMapping("/postCommandeFromFront")
	public CommandeFromFront postCommandeFromFront(@RequestBody CommandeFromFront commandeFromFront) {
		return commandeService.postCommandeFromFront(commandeFromFront);
	}
}
