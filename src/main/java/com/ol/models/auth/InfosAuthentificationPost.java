package com.ol.models.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfosAuthentificationPost {

	/**
	 * identifiant : String représente l'identifiant (login) renseigné par
	 * l'utilisateur
	 */
	private String identifiant;
	/** mdp : String représente le mot de passe renseigné par l'utilisateur */
	private String motDePasse;
	
}
