package com.ol.models.tofrontclient;

import com.ol.models.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionPanierToFrontClient extends SelectionToFrontClient{

	private Image image;
	
	public SelectionPanierToFrontClient(Integer id, String libelle, Integer prix, Image image) {
		super(id, libelle, prix);
		this.image = image;
	}
	
}
