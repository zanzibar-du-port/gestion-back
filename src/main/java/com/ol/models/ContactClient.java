package com.ol.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lasbl
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactClient {

	/** id : Integer . est l'identifiant en base de donnée du ContactClient */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nomPrenom;
	private String adresse;
	private String ville;
	private String codePostal;
	private String telephone;
	private String precisionUtilisateur;
	/** client : Client . est le client concerné par ce contactClient */
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "client_id")
	private Client client;
	
	public ContactClient(String nomPrenom, String adresse, String ville, String codePostal, String telephone,
			String precisionUtilisateur, Client client) {
		super();
		this.nomPrenom = nomPrenom;
		this.adresse = adresse;
		this.ville = ville;
		this.codePostal = codePostal;
		this.telephone = telephone;
		this.precisionUtilisateur = precisionUtilisateur;
		this.client = client;
	}
}
