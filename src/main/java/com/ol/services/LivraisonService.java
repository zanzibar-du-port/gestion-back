package com.ol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.repositories.DimensionRepository;
import com.ol.repositories.SelectionRepository;

@Service
public class LivraisonService {

	@Autowired
	SelectionService selectionService;
	
	@Autowired
	SelectionRepository selectionRepository;
	
	@Autowired
	DimensionRepository dimensionRepository;
	
	public Integer getPrixLivraison(List<Integer> listeIdSelection) {
		
		Integer prixLivraison = 0;
		
		Integer poidTotal = listeIdSelection.stream().map(
				id -> dimensionRepository.findByIdSelection(selectionRepository.findById(id).get().getId()).get().getPoid())
				.reduce(0, Integer::sum);
		while(poidTotal > 0) {
			if(poidTotal < 250) {
				prixLivraison = prixLivraison + 495;
			}else if(poidTotal < 500) {
				prixLivraison = prixLivraison + 645;
			}else if(poidTotal < 750) {
				prixLivraison = prixLivraison + 735;
			}else if(poidTotal < 1000) {
				prixLivraison = prixLivraison + 799;
			}else if(poidTotal < 2000) {
				prixLivraison = prixLivraison + 915;
			}else if(poidTotal < 5000) {
				prixLivraison = prixLivraison + 1410;
			}else if(poidTotal < 10000) {
				prixLivraison = prixLivraison + 2050;
			}else if(poidTotal < 15000) {
				prixLivraison = prixLivraison + 2600;
			}else if(poidTotal < 30000) {
				prixLivraison = prixLivraison + 3220;
			}
			poidTotal = poidTotal - 30000;
		}
		return prixLivraison;
		
	}
	
}
