package com.ol.models.frombdd;

import javax.persistence.ColumnResult;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;

import com.ol.models.Image;
import com.ol.models.Produit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitBdd {

	private Integer id;
	private String libelle;
	private String description;
	private String detail;
	private Integer positionDansLaListe;
	private String prixNormalAv;
	private String prixBarreMilieu;
	private String prixNormalAp; 
	private String etat;
	private Integer imagePrincipaleId;
	
	public ProduitBdd(Integer id, String libelle, String description, String prixNormalAv, String prixBarreMilieu,
			String prixNormalAp, Integer imagePrincipaleId) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.description = description;
		this.prixNormalAv = prixNormalAv;
		this.prixBarreMilieu = prixBarreMilieu;
		this.prixNormalAp = prixNormalAp;
		this.imagePrincipaleId = imagePrincipaleId;
	}

	public ProduitBdd(Integer id, String libelle, String description, String prixNormalAv, String prixBarreMilieu,
			String prixNormalAp, Integer imagePrincipaleId, String etat) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.description = description;
		this.prixNormalAv = prixNormalAv;
		this.prixBarreMilieu = prixBarreMilieu;
		this.prixNormalAp = prixNormalAp;
		this.etat = etat;
		this.imagePrincipaleId = imagePrincipaleId;
	}

	public ProduitBdd(Integer id, String libelle, String description, String detail, String prixNormalAv,
			String prixBarreMilieu, String prixNormalAp, String etat, Integer imagePrincipaleId) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.description = description;
		this.detail = detail;
		this.prixNormalAv = prixNormalAv;
		this.prixBarreMilieu = prixBarreMilieu;
		this.prixNormalAp = prixNormalAp;
		this.etat = etat;
		this.imagePrincipaleId = imagePrincipaleId;
	}

	public ProduitBdd(Integer id, String libelle, String description, String detail, String prixNormalAv,
			String prixBarreMilieu, String prixNormalAp, Integer imagePrincipaleId) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.description = description;
		this.detail = detail;
		this.prixNormalAv = prixNormalAv;
		this.prixBarreMilieu = prixBarreMilieu;
		this.prixNormalAp = prixNormalAp;
		this.imagePrincipaleId = imagePrincipaleId;
	}
	
	
}
