package com.ol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Dimension;
import com.ol.models.Selection;
import com.ol.repositories.DimensionRepository;

@Service
public class DimensionService {

	@Autowired
	DimensionRepository dimensionRepository;
	
	public Dimension findDimensionByIdSelection(Integer idSelection) {
		return dimensionRepository.findByIdSelection(idSelection).get();
	}

	
}
