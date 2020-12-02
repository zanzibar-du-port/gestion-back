package com.ol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Header;
import com.ol.models.Image;
import com.ol.models.Produit;
import com.ol.repositories.HeaderRepository;

@Service
public class HeaderService {
	
	@Autowired
	HeaderRepository headerRepository;

	@Autowired
	ImageService imageService;
	
	public Header saveHeader(Header header) {
		
		return headerRepository.save(header);
	}

	public Header getHeader() {
		// TODO Auto-generated method stub
		Header header = headerRepository.findById(new Long(1)).get();
		
		return header;
	}

}
