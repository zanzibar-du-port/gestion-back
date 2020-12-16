package com.ol.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Commande;
import com.ol.repositories.CommandeRepository;

@Service
public class CommandeService {

	@Autowired
	CommandeRepository commandeRepository;
	
	public Commande save(Commande commande) {
		return commandeRepository.save(commande);
		
	}
	
	public List<Commande> getAllDepuis(Integer nbJours) {
		return commandeRepository.getAllDepuis(LocalDateTime.now().minusDays(nbJours) );
	}
}
