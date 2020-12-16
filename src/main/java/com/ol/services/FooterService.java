package com.ol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ol.models.Footer;
import com.ol.models.Header;
import com.ol.repositories.HeaderRepository;
import com.ol.repositories.FooterRepository;

@Service
public class FooterService {

	@Autowired
	FooterRepository footerRepository;

	@Autowired
	ImageService imageService;
	
	public Footer saveFooter(Footer footer) {
		
		return footerRepository.save(footer);
	}

	public Footer getFooter() {
		// TODO Auto-generated method stub
		Footer footer = footerRepository.findById(new Integer(1)).get();
		
		return footer;
	}
	
}
