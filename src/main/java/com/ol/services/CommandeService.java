package com.ol.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Commande;
import com.ol.models.CommandeProduit;
import com.ol.models.fromfront.CommandeFromFront;
import com.ol.models.fromfront.FournisseurFromFront;
import com.ol.repositories.CommandeProduitRepository;
import com.ol.repositories.CommandeRepository;
import com.ol.repositories.FournisseurRepository;
import com.ol.repositories.ProduitRepository;

@Service
public class CommandeService {

	@Autowired
	MailService mailService;
	
	@Autowired
	CommandeProduitRepository commandeProduitRepository;

	@Autowired
	CommandeRepository commandeRepository;
	
	@Autowired
	FournisseurRepository fournisseurRepository;

	@Autowired
	ProduitRepository produitRepository;
	
	public CommandeFromFront getMailFromFrontByCommandeFromFront(CommandeFromFront commandeFromFront) {
		commandeFromFront.setMailFromFront(mailService.getMailFromFrontByCommandeFromFront(commandeFromFront));
		return commandeFromFront;
	}

	public CommandeFromFront postCommandeFromFront(CommandeFromFront commandeFromFront) {
		mailService.sendMail(commandeFromFront.getMailFromFront());
		
		Commande commande = commandeRepository.save(
				new Commande(
						LocalDateTime.now(),
						fournisseurRepository.findByIdProduit(commandeFromFront.getListeCommandeProduitFromFront().get(0).getIdProduit()).get()
						));
		
		commandeFromFront.getListeCommandeProduitFromFront().stream().forEach(
				commandeProduitFromFront -> 
					commandeProduitRepository.save(
							new CommandeProduit(
									commandeProduitFromFront.getNbLots(),
									produitRepository.findById(commandeProduitFromFront.getIdProduit()).get(),
									commande
									)));
		
		return commandeFromFront;
	}
	

	public List<Integer> getDernieresCommandesByIdProduit(Integer idProduit){
		List<Integer> listeNbCommande = commandeProduitRepository.getDeuxDernieresCommandesByIdProduit(idProduit);
			while (listeNbCommande.size() < 2) {
				listeNbCommande.add(0, 0);
			}
		return listeNbCommande;
		
	}

}
