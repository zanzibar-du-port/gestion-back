package com.ol.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.convertors.FournisseurConvertor;
import com.ol.models.fromfront.FournisseurFromFront;
import com.ol.models.tofront.FournisseurToFront;
import com.ol.repositories.FournisseurRepository;

@Service
public class FournisseurService {
	
	@Autowired
	FournisseurRepository fournisseurRepository;
	
	@Autowired
	FournisseurConvertor fournisseurConvertor;

	public FournisseurFromFront save(FournisseurFromFront fournisseurFromFront) {
		return fournisseurConvertor.convertBddVersFromFront(
				fournisseurRepository.save(
						fournisseurConvertor.convertFromFrontVersBdd(fournisseurFromFront)
						)
				);
	}

	public List<FournisseurToFront> getAllFournisseurToFront() {
		return fournisseurRepository.findAll().stream().map(
				fournisseur -> fournisseurConvertor.convertBddVersToFront(fournisseur))
				.collect(Collectors.toList());
	}

	public FournisseurFromFront getFournisseurFromFrontByIdFournisseur(Integer idFournisseur) {
		return fournisseurConvertor.convertBddVersFromFront(
				fournisseurRepository.findById(idFournisseur).get()
				);
	}

	public List<FournisseurToFront> deleteFournisseurByIdFournisseur(Integer idFournisseur) {
		fournisseurRepository.delete(fournisseurRepository.findById(idFournisseur).get());
		return getAllFournisseurToFront();
	}
}
