package com.ol.convertors;

import org.springframework.stereotype.Service;

import com.ol.models.Fournisseur;
import com.ol.models.fromfront.FournisseurFromFront;
import com.ol.models.tofront.FournisseurToFront;

@Service
public class FournisseurConvertor {

	public Fournisseur convertFromFrontVersBdd(FournisseurFromFront fournisseurFromFront) {
		return new Fournisseur(
				fournisseurFromFront.getId(),
				fournisseurFromFront.getEntreprise(),
				fournisseurFromFront.getNom(),
				fournisseurFromFront.getPrenom(),
				fournisseurFromFront.getTelephone(),
				fournisseurFromFront.getEmail(),
				fournisseurFromFront.getCodeClient()
				);
	}
	
	public FournisseurFromFront convertBddVersFromFront(Fournisseur fournisseur) {
		return new FournisseurFromFront(
				fournisseur.getId(),
				fournisseur.getEntreprise(),
				fournisseur.getNom(),
				fournisseur.getPrenom(),
				fournisseur.getTelephone(),
				fournisseur.getEmail(),
				fournisseur.getCodeClient()
				);
	}
	
	public Fournisseur convertToFrontVersBdd(FournisseurToFront fournisseurToFront) {
		return new Fournisseur(
				fournisseurToFront.getId(),
				fournisseurToFront.getEntreprise(),
				fournisseurToFront.getNom(),
				fournisseurToFront.getPrenom(),
				fournisseurToFront.getTelephone(),
				fournisseurToFront.getEmail(),
				fournisseurToFront.getCodeClient()
				);
	}
	
	public FournisseurToFront convertBddVersToFront(Fournisseur fournisseur) {
		return new FournisseurToFront(
				fournisseur.getId(),
				fournisseur.getEntreprise(),
				fournisseur.getNom(),
				fournisseur.getPrenom(),
				fournisseur.getTelephone(),
				fournisseur.getEmail(),
				fournisseur.getCodeClient()
				);
	}
}
