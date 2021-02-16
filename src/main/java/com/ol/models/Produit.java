package com.ol.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String categorie;
	private String reference;
	private String nom;
	private Integer nbProduitParLot;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "id_fournisseur")
	private Fournisseur fournisseur;
	
}
