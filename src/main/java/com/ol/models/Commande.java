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
public class Commande {
	
	/** id : Integer . est l'identifiant en base de donnée de la Commande */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDateTime dateHeureCommande;
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "id_fournisseur")
	private Fournisseur fournisseur;
	
	public Commande(LocalDateTime dateHeureCommande, Fournisseur fournisseur) {
		super();
		this.dateHeureCommande = dateHeureCommande;
		this.fournisseur = fournisseur;
	}

}
