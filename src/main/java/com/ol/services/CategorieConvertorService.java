package com.ol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Categorie;
import com.ol.models.SousCategorie;
import com.ol.models.tofrontadmin.CategorieDetailToFrontAdmin;
import com.ol.models.tofrontadmin.SousCategorieDetailToFrontAdmin;

@Service
public class CategorieConvertorService {
	
	@Autowired
	CategorieService categorieService;

	public SousCategorieDetailToFrontAdmin convertFromSousCategorieToSousCategorieDetailToFrontAdmin(SousCategorie sousCategorie) {
		return new SousCategorieDetailToFrontAdmin(
				sousCategorie.getId(),
				sousCategorie.getLibelle(),
				sousCategorie.getPositionDansLaListe(),
				sousCategorie.getActifInactif());
	}

	public CategorieDetailToFrontAdmin convertFromCategorieToSousCategorieDetailToFrontAdmin(Categorie categorie) {
		return new CategorieDetailToFrontAdmin(
				categorie.getId(),
				categorie.getLibelle(),
				categorie.getPositionDansLaListe(),
				categorieService.getAllSousCategoriesByLibelleCategorie(categorie.getLibelle()),
				categorie.getActifInactif());
	}
}
