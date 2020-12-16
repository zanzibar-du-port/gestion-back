package com.ol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ol.exceptions.UtilisateurNonTrouveException;
import com.ol.models.auth.InfosAuthentificationPost;
import com.ol.models.auth.UtilisateurVue;
import com.ol.services.AuthentificationService;
import com.ol.services.UtilisateurService;



/**
 * Cette classe gère l'authentifiaction des utilisateurs (afin qu'il puisse
 * obtenir le cookie qui leur permettra d'acceder aux urls de l'application.
 * Elle contient la methode authenticate(@RequestBody InfosAuthentificationPost
 * infos) accessible via l'url "/auth"
 * 
 * @author Diginamic02
 *
 */
@RestController
@RequestMapping("authentification")
public class AuthentificationController {

	@Autowired
	private AuthentificationService authentificationService;

	@Autowired
	private UtilisateurService utilisateurService;

	
	/**
	 * Cette methode gère le POST sur l'url /auth qui permet à l'utilisateur
	 * d'obtenir (ou non) le cookie qui lui permettra d'être authentifié.
	 * 
	 * @param infos information d’authentification
	 * @return une responseEntity
	 */
	@PostMapping("/auth")
	public ResponseEntity<?> authenticate(@RequestBody InfosAuthentificationPost infos) {
		return authentificationService.authenticate(infos);
	}

	/**
	 * permet de recuperer l’utilisateur courant
	 * @return un UtilisateurDto
	 * @throws UtilisateurNonTrouveException 
	 */
	@GetMapping("/auth/user")
	public UtilisateurVue getUtilisateurVue() throws UtilisateurNonTrouveException{
		return utilisateurService.convertUtilisateurToUtilisateurVue(utilisateurService.recupererUtilisateur());
	}

}
