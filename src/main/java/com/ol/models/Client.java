package com.ol.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objet Client représentant un Client
 * Dans le front quelqu'un devient client à partir du moment où il renseigne son adresse e-mail
 * Quelqu'un qui reçoit un mail de publicité peut modifier l'attribut "acceptePublicite" en cliquant sur un lien
 * @author lasbl
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	
	/** id : Integer . est l'identifiant en base de donnée de la SousCategorie */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String adresseEmail;
	private boolean acceptePublicite;
	
	public Client(String adresseEmail, boolean acceptePublicite) {
		super();
		this.adresseEmail = adresseEmail;
		this.acceptePublicite = acceptePublicite;
	}
	
}
