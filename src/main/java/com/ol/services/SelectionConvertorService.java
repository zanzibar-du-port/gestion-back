package com.ol.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Dimension;
import com.ol.models.Selection;
import com.ol.models.frombdd.SelectionBdd;
import com.ol.models.fromfrontadmin.DimensionFromFrontAdmin;
import com.ol.models.fromfrontadmin.SelectionFromFrontAdmin;
import com.ol.models.tofrontadmin.SelectionToFrontAdmin;
import com.ol.models.tofrontclient.SelectionPanierToFrontClient;
import com.ol.models.tofrontclient.SelectionToFrontClient;

@Service
public class SelectionConvertorService {
	
	@Autowired
	AchatSelectionService achatSelectionService;
	
	@Autowired
	DimensionService dimensionService;
	
	@Autowired
	ImageService imageService;

	public SelectionToFrontAdmin convertSelectionBddToSelectionToFrontAdmin(SelectionBdd sBdd) {
		return new SelectionToFrontAdmin(sBdd.getId(),
				sBdd.getLibelle(),
				sBdd.getPrix(),
				sBdd.getQuantiteDisponible(),
				achatSelectionService.getNbAchatsByIdProduit(sBdd.getId())
				
				);
	}
	
	public SelectionFromFrontAdmin convertSelectionBddToSelectionFromFrontAdmin(SelectionBdd sBdd) {
		
		Dimension dimension = dimensionService.findDimensionByIdSelection(sBdd.getId());
		return new SelectionFromFrontAdmin(
				sBdd.getId(),
				sBdd.getLibelle(),
				sBdd.getQuantiteDisponible(),
				sBdd.getPrix(),
				new DimensionFromFrontAdmin(
						dimension.getId(),
						dimension.getX(),
						dimension.getY(),
						dimension.getZ(),
						dimension.getPoid()))				
				;
		
	}

	public SelectionToFrontClient convertSelectionBddToSelectionToFrontClient(SelectionBdd sBdd) {
		return new SelectionToFrontClient(
				sBdd.getId(),
				sBdd.getLibelle(),
				sBdd.getPrix());
	}

	public SelectionPanierToFrontClient convertSelectionToSelectionPanierToFrontClient(Selection selection) {
		return new SelectionPanierToFrontClient(
				selection.getId(),
				selection.getLibelle(),
				selection.getPrix(),
				selection.getProduit().getImagePrincipale()
				);
	}

}
