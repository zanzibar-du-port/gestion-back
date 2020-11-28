package com.ol.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduit;
	private String nomProduit;
	private String descriptionProduit;
	private int prixAvantReduction;
	private int prixApresReduction;
	
	public Produit(String nomProduit, String descriptionProduit, int prixAvantReduction, int prixApresReduction) {
		super();
		this.nomProduit = nomProduit;
		this.descriptionProduit = descriptionProduit;
		this.prixAvantReduction = prixAvantReduction;
		this.prixApresReduction = prixApresReduction;
	}
	
}
