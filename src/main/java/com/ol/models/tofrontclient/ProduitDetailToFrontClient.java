package com.ol.models.tofrontclient;

import java.util.List;

import com.ol.models.Image;
import com.ol.models.tofrontadmin.CategorieToFrontAdmin;
import com.ol.models.tofrontadmin.SelectionToFrontAdmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDetailToFrontClient {

	private Integer id;
	private String libelle;
	private String description;
	private String detail;
	private List<Image> listeImages;
	private List<SelectionToFrontClient> listeSelectionsToFrontClient;
	
}
