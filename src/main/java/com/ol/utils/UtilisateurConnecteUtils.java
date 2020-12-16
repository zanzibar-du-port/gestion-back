package com.ol.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ol.models.auth.UtilisateurConnecte;



@Component
public class UtilisateurConnecteUtils {

	/**
	 * Cette methode permet de récupérer l'identifiant de l'utilisateur connecté
	 * 
	 * @return
	 */
	public static String recupererIdentifiant() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		System.out.println(((UtilisateurConnecte) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		return ((UtilisateurConnecte) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAdresseMail();
	}
}

