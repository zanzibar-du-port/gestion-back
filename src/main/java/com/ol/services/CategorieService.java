package com.ol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CategorieService {

	@Autowired
	ProduitService produitService;
	
	public Iterable<String> getAll() {
		// TODO Auto-generated method stub
		return produitService.getAllCategorie();
	}


}
