package com.ol.models.tofrontclient;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ol.models.Image;
import com.ol.models.Selection;
import com.ol.models.SousCategorie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitToFrontClient {
	
	/** id : Integer . est l'identifiant en base de donnée du Produit */
	private Integer id;
	/** libelle : String . est le libelle du Produit */
	private String libelle;
	/** description : String . est la description du Produit */
	private String description;
	/** libellePrixAvant : String . est le libelle du prix à afficher avant le prix pour le Produit 
	 * exemple : à partir de 
	*/
	/** prixAvReduction : String . est le prix affiché à gauche (sans être barré) sur la représentation du produit dans l'accueil du front
	 * Cet élément ne constitue pas un prix en soi, pour ceci il faut modifier l'élément prix de l'objet Selection
	*/
	private String prixNormalAv;
	/** prixApReduction : String . est le prix affiché au milieu (barré) sur la représentation du produit dans l'accueil du front
	 * Cet élément ne constitue pas un prix en soi, pour ceci il faut modifier l'élément prix de l'objet Selection
	*/
	private String prixBarreMilieu;
	/** prixApReduction : String . est le prix affiché à droite (sans être barré) sur la représentation du produit dans l'accueil du front
	 * Cet élément ne constitue pas un prix en soi, pour ceci il faut modifier l'élément prix de l'objet Selection
	*/
	private String prixNormalAp;
	private Image imagePrincipale;

}
