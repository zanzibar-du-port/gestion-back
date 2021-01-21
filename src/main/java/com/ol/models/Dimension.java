package com.ol.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Objet Dimension représentant les dimensions d'un produit (hauteur, largeur, longueur, poid
 * Cet élément est principalement utilisé pour connaitre le prix d'envoi par la poste du produit
 *
 * @author lasbl
 *
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dimension {
	
	/** id : Integer . est l'identifiant en base de donnée de la Dimension */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** x : int . est la longueur du produit
	 * Cet élément est utile pour calculer le prix d'envoi par la poste du produit
	 */ 
	private int x;
	/** y : int . est la largeur du produit
	 * Cet élément est utile pour calculer le prix d'envoi par la poste du produit
	 */ 
	private int y;
	/** z : int . est la hauteur du produit
	 * Cet élément est utile pour calculer le prix d'envoi par la poste du produit
	 */ 
	private int z;
	/** x : int . est le poid en grammes du produit
	 * Cet élément est utile pour calculer le prix d'envoi par la poste du produit
	 */ 
	private int poid;

	/** Selection : Selection . est la Selection concernée par la dimension courante  */
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "selection_id")
	private Selection selection;
	
	public Dimension(int x, int y, int z, int poid, Selection selection) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.poid = poid;
		this.selection = selection;
	}
	

}
