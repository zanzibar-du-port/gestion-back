package com.ol.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class InfosGenerales {

	@Id
	@Column(name = "id")
	private Integer id;
	private String textPrePaiement;
	private String textPostPaiement;
	private String textMailCommande;
	private String etatRecuperationProduit;
	private int prixLivraison;
	
	public InfosGenerales(Integer id, 
			String textPrePaiement, 
			String textPostPaiement, 
			String textMailCommande,
			String etatRecuperationProduit,
			int prixLivraison) {
		super();
		this.id = new Integer(1);
		this.textPrePaiement = textPrePaiement;
		this.textPostPaiement = textPostPaiement;
		this.textMailCommande = textMailCommande;
		this.etatRecuperationProduit = etatRecuperationProduit;
		this.prixLivraison=prixLivraison;
	}
	
}
