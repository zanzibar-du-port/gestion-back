package com.ol.models.tofront;

import com.ol.models.Fournisseur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitToFront {

	private Integer id;
	private String categorie;
	private String reference;
	private String nom;
	private Integer quantiteCommandeN2;
	private Integer quantiteCommandeN1;
	private Integer nbProduitParLot;
	
	
}
