package com.ol.models;

import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Produit {

	@Id
	private Integer id;
	private String titre;
	private String description;
	private String detail;
	private String etat;
	private int prix;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "IMAGE_PRINCIPALE_ID")
	private Image imagePrincipale;
	@ManyToMany(cascade = {CascadeType.MERGE})
	private List<Image> listeImages;
	private String categorie;
	private int positionDansLaListe;
	@OneToMany(cascade = {CascadeType.MERGE})
	private List<Selection> listeSelections;
	
	
	public Produit(Integer id, String titre, String description, String detail, String etat,
			int prix, Image imagePrincipale, List<Image> listeImages,String categorie,
			int positionDansLaListe,List<Selection> listeSelections) {
		super();
		if(id.equals(new Integer(0))) {
			this.id = generateString();
		}else {
			this.id = id;
		}
		this.titre = titre;
		this.description = description;
		this.detail = detail;
		this.etat = etat;
		this.prix = prix;
		this.imagePrincipale = imagePrincipale;
		this.listeImages = listeImages;
		this.categorie = categorie;
		this.positionDansLaListe = positionDansLaListe;
		this.listeSelections=listeSelections;
	}
	
	public static Integer generateString() {
		return new Random().nextInt();
    }
	
}
