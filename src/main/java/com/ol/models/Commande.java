package com.ol.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	private String nomPrenom;
	private String adresseEmail;
	private String telephone;
	private String precisionUtilisateur;
	private String adresse;
	private String ville;
	private String codePostal;
	private Integer prixTotal;
	private LocalDateTime dateHeure;
	private String idPaiementStripe;
	private String codeRecuperation;
	private String methodeDeReceptionProduits;
	@ManyToMany(cascade = {CascadeType.MERGE})
	private List<Produit> listeProduits;

}
