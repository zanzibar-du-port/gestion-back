package com.ol.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.convertors.ProduitConvertor;
import com.ol.models.fromfront.FournisseurFromFront;
import com.ol.models.fromfront.ProduitFromFront;
import com.ol.models.tofront.ProduitToFront;
import com.ol.repositories.ProduitRepository;

@Service
public class ProduitService {

	@Autowired
	ProduitConvertor produitConvertor;

	@Autowired
	ProduitRepository produitRepository;
	
	public ProduitFromFront save(ProduitFromFront produitFromFront) {
		return produitConvertor.convertBddVersFromFront(
				produitRepository.save(
						produitConvertor.convertFromFrontVersBdd(produitFromFront)
						)
				);
	}

	public List<ProduitToFront> getAllProduitToFrontByIdFournisseur(Integer idFournisseur) {
		return produitRepository.getAllProduitToFrontByIdFournisseur(idFournisseur).stream().map(
				produit -> produitConvertor.convertBddVersToFront(produit))
				.collect(Collectors.toList());
	}

	public ProduitFromFront getProduitFromFrontByIdProduit(Integer idProduit) {
		// TODO Auto-generated method stub
		return produitConvertor.convertBddVersFromFront(produitRepository.findById(idProduit).get());
	}

	public Integer deleteProduitByIdProduit(Integer idProduit) {
		produitRepository.delete(produitRepository.findById(idProduit).get());
		return idProduit;
	}
	

}
