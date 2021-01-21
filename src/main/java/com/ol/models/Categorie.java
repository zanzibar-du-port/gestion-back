package com.ol.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Objet Categorie représentant une catégorie de produit
 * Cet élément est utile pour trier les produits dans le front
 * Une catégorie contient de 1 à n sous-catégories
 * @author lasbl
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categorie {
	
	/** id : Integer . est l'identifiant en base de donnée de la Categorie */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** libelle : String . est le libelle de la catégorie,
	 * exemple : Vêtements Hommes */
	private String libelle;
	private Integer positionDansLaListe;
	private String actifInactif;
	
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
		this.positionDansLaListe = 0;
		this.actifInactif = "ACTIF";
	}

}
