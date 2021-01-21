package com.ol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ol.models.utils.UtilsAchatSelection;
import com.ol.repositories.utils.UtilsAchatSelectionRepository;

@Service
public class AchatSelectionService {

	@Autowired
	UtilsAchatSelectionRepository achatSelectionRepository;
	
	public Integer getNbAchatsByIdProduit(Integer id) {
		return achatSelectionRepository.getNbAchatsByIdProduit(id);
	}
	
	

}
