package com.ol.models.fromfront;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ol.models.Fournisseur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitFromFront {

	private Integer id;
	private String categorie;
	private String reference;
	private String nom;
	private Integer nbProduitParLot;
	private Integer idFournisseur;
	
	
}
