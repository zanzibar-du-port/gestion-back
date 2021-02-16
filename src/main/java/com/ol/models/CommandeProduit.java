package com.ol.models;

import java.time.LocalDateTime;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CommandeProduit {

	/** id : Integer . est l'identifiant en base de donnée de la Commande */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer nbLot;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "id_produit")
	private Produit produit;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "id_commande")
	private Commande commande;
	
	public CommandeProduit(Integer nbLot, Produit produit, Commande commande) {
		super();
		this.nbLot = nbLot;
		this.produit = produit;
		this.commande = commande;
	}
}
