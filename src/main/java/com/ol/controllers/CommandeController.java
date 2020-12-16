package com.ol.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.exceptions.PaymentException;
import com.ol.models.Commande;
import com.ol.models.PaymentIntentDto;
import com.ol.services.CommandeService;
import com.ol.services.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping("/stripe")
public class CommandeController {

    @Autowired
    PaymentService paymentService;
    
    @Autowired
    CommandeService commandeService;

    @PostMapping("/paymentintent")
    public ResponseEntity<String> payment(@RequestBody PaymentIntentDto paymentIntentDto) throws StripeException {
        PaymentIntent paymentIntent = paymentService.paymentIntent(paymentIntentDto);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<String> confirm(@PathVariable("id") String id,@RequestBody Commande commande) throws  PaymentException {
    	PaymentIntent paymentIntent = new PaymentIntent();
    	try {
        	paymentIntent = paymentService.confirm(id);
        }catch (StripeException e) {
			throw new PaymentException();
		}
    	commande.setCodeRecuperation(RandomStringUtils.randomAlphabetic(10));
    	commandeService.save(commande);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable("id") String id) throws StripeException {
        PaymentIntent paymentIntent = paymentService.cancel(id);
        String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
    }
}
