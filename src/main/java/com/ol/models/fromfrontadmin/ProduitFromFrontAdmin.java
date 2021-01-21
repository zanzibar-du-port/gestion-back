package com.ol.models.fromfrontadmin;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitFromFrontAdmin {

	private Integer id;
	
	private String libelle;
	
	private String description;

	private String detail;

	private String etat;

	private String prixNormalAv;

	private String prixBarreMilieu;

	private String prixNormalAp;

	private Integer idImagePrincipale;
	
	private List<Integer> listeIdImages;
	
	private List<CategorieFromFrontAdmin> listeCategories;
	
	private Integer positionDansLaListe;
	
	private List<SelectionFromFrontAdmin> listeSelections;
	
}
