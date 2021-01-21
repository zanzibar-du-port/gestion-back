package com.ol.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.exceptions.PaymentException;
import com.ol.exceptions.PrixNeCorrespondPasException;
import com.ol.models.Commande;
import com.ol.models.PaymentIntentDto;
import com.ol.models.fromfrontclient.CommandeFromFrontClient;
import com.ol.services.CommandeConvertorService;
import com.ol.services.CommandeService;
import com.ol.services.MailService;
import com.ol.services.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    PaymentService paymentService;
    
    @Autowired
    CommandeService commandeService;
    
    @Autowired
    CommandeConvertorService commandeConvertorService;
    
    @Autowired
    MailService mailService;

    @PostMapping("/paymentintent")
    public ResponseEntity<String> payment(@RequestBody PaymentIntentDto paymentIntentDto) throws StripeException {
        PaymentIntent paymentIntent = paymentService.paymentIntent(paymentIntentDto);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<String> confirm(@PathVariable("id") String id,
    										@RequestBody CommandeFromFrontClient commandeFromFrontClient) 
    												throws  PaymentException, PrixNeCorrespondPasException {
    	PaymentIntent paymentIntent = new PaymentIntent();
    	if (!commandeService.isPriceOk(commandeFromFrontClient)) {
    		throw new PrixNeCorrespondPasException("le prix reçu pour votre commande et celui en BDD ne correspondent pas");
		}
    	try {
        	paymentIntent = paymentService.confirm(id);
        }catch (StripeException e) {
			throw new PaymentException();
		}
    	Commande commande = commandeService.save(commandeConvertorService.convertFromCommandeFromFrontClientToCommande(commandeFromFrontClient,id));
    	mailService.sendMailCommande(commande);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.cancel(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }
    
    @GetMapping("/getAll")
	public Iterable<Commande> getAllCommande() {
		return commandeService.getAll();
	}
    
    @GetMapping("/getDepuis/{nJours}")
	public Iterable<Commande> getDepuis(@PathVariable("nJours") Integer nJours) {
		return commandeService.getDepuis(nJours);
	}
}
