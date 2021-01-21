package com.ol.models.tofrontadmin;

import java.util.List;

import com.ol.models.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitToFrontAdmin {

	private Integer id;
	private Image imagePrincipale;
	private String libelle;
	private String etat;
	private List<SelectionToFrontAdmin> listeSelectionsToFrontadmin;
	private List<CategorieToFrontAdmin> listeCategoriesToFrontadmin;
	private Integer nbVues;
}
