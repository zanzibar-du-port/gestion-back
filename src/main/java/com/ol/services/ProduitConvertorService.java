package com.ol.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Image;
import com.ol.models.Produit;
import com.ol.models.frombdd.ProduitBdd;
import com.ol.models.fromfrontadmin.ProduitFromFrontAdmin;
import com.ol.models.tofrontadmin.ProduitToFrontAdmin;
import com.ol.models.tofrontclient.ProduitDetailToFrontClient;
import com.ol.models.tofrontclient.SelectionPanierToFrontClient;
import com.ol.models.tofrontclient.ProduitToFrontClient;
import com.ol.models.tofrontclient.SelectionToFrontClient;
import com.ol.repositories.ImageRepository;
import com.ol.repositories.ProduitRepoSitory;
import com.ol.repositories.SelectionRepository;

@Service
public class ProduitConvertorService {

	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	SelectionRepository selectionRepository;
	
	@Autowired
	SelectionConvertorService selectionConvertorService;
	
	@Autowired
	CategorieService categorieService;

	@Autowired
	SelectionService selectionService;
	
	@Autowired
	ProduitRepoSitory produitRepoSitory;
	
	@Autowired
	ImageService imageService;
	
	public ProduitToFrontClient convertProduitBddToProduitToFrontClient(ProduitBdd produitBdd) {
		return new ProduitToFrontClient(produitBdd.getId(),
				produitBdd.getLibelle(),
				produitBdd.getDescription(),
				produitBdd.getPrixNormalAv(),
				produitBdd.getPrixBarreMilieu(),
				produitBdd.getPrixNormalAp(),
				imageRepository.findById(produitBdd.getImagePrincipaleId()).get());
	}

	public ProduitToFrontAdmin convertProduitBddToProduitToFrontAdmin(ProduitBdd produitBdd) {
		return new ProduitToFrontAdmin(produitBdd.getId(),
				imageRepository.findById(produitBdd.getImagePrincipaleId()).get(),
				produitBdd.getLibelle(),
				produitBdd.getEtat(),
				selectionRepository.findAllSelectionBddByIdProduit(produitBdd.getId())
				.stream()
				.map(sBdd -> selectionConvertorService.convertSelectionBddToSelectionToFrontAdmin(sBdd))
				.collect(Collectors.toList()),
				categorieService.getAllCategoriesToFrontAdminByIdProduit(produitBdd.getId()),
				5 // TODO vueProduitService.getNbVuesByIdProduit(produitBdd.getId())
				);
	}

	public ProduitFromFrontAdmin convertProduitBddToProduitFromFrontAdmin(ProduitBdd produitBdd) {
		return new ProduitFromFrontAdmin(
				produitBdd.getId(),
				produitBdd.getLibelle(),
				produitBdd.getDescription(),
				produitBdd.getDetail(),
				produitBdd.getEtat(),
				produitBdd.getPrixNormalAv(),
				produitBdd.getPrixBarreMilieu(),
				produitBdd.getPrixNormalAp(),
				produitBdd.getImagePrincipaleId(),
				produitRepoSitory.getlisteIdImages(produitBdd.getId()),
				categorieService.getListeCategorieFromFrontAdminByIdProduit(produitBdd.getId()),
				produitBdd.getPositionDansLaListe(),
				selectionService.getListeSelectionFromFrontAdminByIdProduit(produitBdd.getId())
				);
	}

	public ProduitDetailToFrontClient convertProduitBddToProduitDetailToFrontClient(ProduitBdd produitBdd) {
		
		List<Image> listeImages = new ArrayList<>();
				
		listeImages.addAll(imageService.getListeImageByIdProduit(produitBdd.getId()));
		
		listeImages.add(0, imageRepository.findById(produitBdd.getImagePrincipaleId()).get());
		
		List<SelectionToFrontClient> listeSelectionToFrontClient = 
				selectionService.getListeSelectionToFrontClientByIdProduit(produitBdd.getId());
		
		return new ProduitDetailToFrontClient(
				produitBdd.getId(),
				produitBdd.getLibelle(),
				produitBdd.getDescription(),
				produitBdd.getDetail(),
				listeImages,
				listeSelectionToFrontClient);
	}

	
}
