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

/**
 * 
 * Objet SousCategorie représentant une sous-catégorie de produit
 * Cet élément est utile pour trier les produits dans le front
 * Une sous-catégorie est reliée à une et une seule catégorie
 * @author lasbl
 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SousCategorie {

	/** id : Integer . est l'identifiant en base de donnée de la SousCategorie */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** libelle : String . est le libelle de la SousCategorie 
	 * exemple : T-shirt Homme
	 */
	private String libelle;
	/** categorie : Categorie . est la catégorie à laquelle est reliée la SousCategorie */
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "id_categorie")
	private Categorie categorie;
	private Integer positionDansLaListe;
	private String actifInactif;
	
	public SousCategorie(String libelle, Categorie categorie) {
		super();
		this.libelle = libelle;
		this.categorie = categorie;
		this.positionDansLaListe = 0;
		this.actifInactif = "ACTIF";
	}
}
