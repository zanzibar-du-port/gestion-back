package com.ol.models;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Fournisseur {
	
	/** id : Integer . est l'identifiant en base de donnée de la Categorie */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String entreprise;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	private String codeClient;
	
	public Fournisseur(Integer id, String entreprise, String nom, String prenom, String telephone, String email,
			String codeClient) {
		super();
		if(id == -1) {
			this.id = getRandomNumberInRange();
		}else {
			this.id = id;
		}
		this.entreprise = entreprise;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
		this.codeClient = codeClient;
	}
	
	private static int getRandomNumberInRange() {
        return (int)(Math.random() * Integer.MAX_VALUE);
    }

}
