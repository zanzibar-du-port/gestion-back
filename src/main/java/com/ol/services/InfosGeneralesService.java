package com.ol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Header;
import com.ol.models.InfosGenerales;
import com.ol.repositories.HeaderRepository;
import com.ol.repositories.InfosGeneralesRepository;


@Service
public class InfosGeneralesService {

	
	@Autowired
	InfosGeneralesRepository infosGeneralesRepository;

	
	
	public InfosGenerales saveInfosGenerales(InfosGenerales infosGenerales) {
		return infosGeneralesRepository.save(infosGenerales);
	}

	public InfosGenerales getInfosGenerales() {
		InfosGenerales infosGenerales = infosGeneralesRepository.findById(new Integer(1)).get();
		return infosGenerales;
	}

}
