package com.ol.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Categorie;
import com.ol.models.SousCategorie;
import com.ol.models.fromfrontadmin.CategorieFromFrontAdmin;
import com.ol.models.tofrontadmin.CategorieDetailToFrontAdmin;
import com.ol.models.tofrontadmin.CategorieToFrontAdmin;
import com.ol.models.tofrontadmin.SousCategorieDetailToFrontAdmin;
import com.ol.repositories.CategorieRepository;
import com.ol.repositories.SousCategorieRepository;


@Service
public class CategorieService {

	@Autowired
	ProduitService produitService;
	
	@Autowired
	CategorieRepository categorieRepository;
	
	@Autowired
	CategorieConvertorService categorieConvertorService;
	
	@Autowired
	SousCategorieRepository sousCategorieRepository;
	
	public List<String> getAll() {
		return categorieRepository.getAllCategorie().stream().map(c -> c.getLibelle()).collect(Collectors.toList());
	}
	//TODO gerer getAllProduitBySousCategorie
	public Iterable<String> getSousCategories(String categorie) {
		//return produitService.getSousCategories(categorie);
		return null;
	}
	
	public List<CategorieFromFrontAdmin> getListeCategorieFromFrontAdminByIdProduit(Integer idProduit) {
		List<SousCategorie> listeSousCategorie = sousCategorieRepository.findAllByIdProduit(idProduit);
		List<CategorieFromFrontAdmin> listeCategorieFromFrontAdmin =  
				listeSousCategorie
				.stream()
				.map(sCate -> new CategorieFromFrontAdmin(sCate.getCategorie().getLibelle(),
						sCate.getLibelle())).collect(Collectors.toList());
		return listeCategorieFromFrontAdmin;
		
	}
	public List<String> getAllSousCategoriesByLibelleCategorie(String libelleCategorie) {
		return sousCategorieRepository.getAllSousCategoriesByLibelleCategorie(libelleCategorie);
	}
	
	public List<CategorieToFrontAdmin> getAllCategoriesToFrontAdminByIdProduit(Integer idProduit) {
		return sousCategorieRepository.getAllSousCategoriesByIdProduit(idProduit)
				.stream()
				.map(sc -> new CategorieToFrontAdmin(sc.getId(), sc.getCategorie().getLibelle(), sc.getLibelle()))
				.collect(Collectors.toList());
	}
	
	public List<SousCategorieDetailToFrontAdmin> getAllSousCategorieDetailToFrontAdminByIdCategorie(Integer idCategorie) {
		return sousCategorieRepository.getAllSousCategoriesByIdCategorie(idCategorie)
				.stream()
				.map(sc -> categorieConvertorService.convertFromSousCategorieToSousCategorieDetailToFrontAdmin(sc))
				.collect(Collectors.toList());
	}
	public List<CategorieDetailToFrontAdmin> getAllCategorieDetailToFrontAdmin() {
		return categorieRepository.findAll()
				.stream()
				.sorted(Comparator.comparingInt(Categorie::getPositionDansLaListe))
				.map(sc -> categorieConvertorService.convertFromCategorieToSousCategorieDetailToFrontAdmin(sc))
				.collect(Collectors.toList());
	}
	
	public void changePositionDansLaListeCategorie(Integer idCategorie, Integer positionDansLaListe) {
		categorieRepository.changePositionDansLaListeCategorie(idCategorie, positionDansLaListe);
	}
	
	public void changePositionDansLaListeSousCategorie(Integer idSousCategorie, Integer positionDansLaListe) {
		sousCategorieRepository.changePositionDansLaListeSousCategorie(idSousCategorie, positionDansLaListe);
	}
	

}
