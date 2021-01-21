package com.ol.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "contact_client_id")
	private ContactClient contactClient;
	private Integer prixPanier;
	private Integer prixLivraison;
	private LocalDateTime dateHeure;
	private String idPaiementStripe;
	private String codeRecuperation;
	private String methodeDeReceptionProduits;
	@ManyToMany(cascade = {CascadeType.ALL})
	private List<Selection> listeSelection;
	
	public Commande(ContactClient contactClient, Integer prixPanier, Integer prixLivraison, LocalDateTime dateHeure,
			String idPaiementStripe, String codeRecuperation, String methodeDeReceptionProduits,
			List<Selection> listeSelection) {
		super();
		this.contactClient = contactClient;
		this.prixPanier = prixPanier;
		this.prixLivraison = prixLivraison;
		this.dateHeure = dateHeure;
		this.idPaiementStripe = idPaiementStripe;
		this.codeRecuperation = codeRecuperation;
		this.methodeDeReceptionProduits = methodeDeReceptionProduits;
		this.listeSelection = listeSelection;
	}
	

}
