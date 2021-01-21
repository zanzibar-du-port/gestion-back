package com.ol.models.tofrontclient;

import java.util.List;

import com.ol.models.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionToFrontClient {

	private Integer id;
	private String libelle;
	private String prix;
	
	public SelectionToFrontClient(Integer id, String libelle, Integer prix) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.prix = new Double(prix)/100 + " €";
	}
	
}
