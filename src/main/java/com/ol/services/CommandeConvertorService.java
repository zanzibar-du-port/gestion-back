package com.ol.services;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Client;
import com.ol.models.Commande;
import com.ol.models.ContactClient;
import com.ol.models.fromfrontclient.CommandeFromFrontClient;
import com.ol.repositories.ClientRepository;
import com.ol.repositories.ContactClientRepository;
import com.ol.repositories.SelectionRepository;

@Service
public class CommandeConvertorService {

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	ContactClientRepository contactClientRepository;
	
	@Autowired
	SelectionRepository selectionRepository;
	
	public Commande convertFromCommandeFromFrontClientToCommande(CommandeFromFrontClient commandeFromFrontClient, String idPaiementStripe) {
		Client client = new Client();
		if(clientRepository.findByAdresseEmail(commandeFromFrontClient.getAdresseEmail()).isPresent()) {
			client = clientRepository.findByAdresseEmail(commandeFromFrontClient.getAdresseEmail()).get();
		}else {
			client = new Client(commandeFromFrontClient.getAdresseEmail(), commandeFromFrontClient.isAcceptePublicite());
			client = clientRepository.save(client);
		}
		ContactClient contactClient = contactClientRepository.save(
				new ContactClient(commandeFromFrontClient.getNomPrenom(),
						commandeFromFrontClient.getAdressePostale(),
						commandeFromFrontClient.getVille(),
						commandeFromFrontClient.getCodePostal(),
						commandeFromFrontClient.getTelephone(),
						commandeFromFrontClient.getPrecision(),
						client));
		return new Commande(contactClient,
				commandeFromFrontClient.getPrixPanier(),
				commandeFromFrontClient.getPrixLivraison(),
				LocalDateTime.now(),
				idPaiementStripe,
				RandomStringUtils.randomAlphabetic(10),
				commandeFromFrontClient.getMethodeDeReceptionProduits(),
				commandeFromFrontClient.getListeIdSelection().stream().map(
						idSelection -> selectionRepository.findById(idSelection).get()).collect(Collectors.toList()));
	}

}
