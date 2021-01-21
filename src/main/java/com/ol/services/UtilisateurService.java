package com.ol.services;

import java.util.Arrays;

import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ol.exceptions.UtilisateurNonTrouveException;
import com.ol.models.auth.Utilisateur;
import com.ol.models.auth.UtilisateurVue;
import com.ol.repositories.UtilisateurRepository;
import com.ol.utils.UtilisateurConnecteUtils;



@Service
public class UtilisateurService {

	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MailService mailService;
	
	/**
	 * @param utilisateurCreationComptePost
	 * public Utilisateur convertUtilisateurCreationComptePostToUtilisateur(
			UtilisateurCreationComptePost utilisateurCreationComptePost) {
		
		return new Utilisateur(
				utilisateurCreationComptePost.getAdresseMail(),
				passwordEncoder.encode(utilisateurCreationComptePost.getMotDePasse()),
				Arrays.asList(new Role("ROLE_USER")));
		
	}
	 * @return
	 */
	

	public UtilisateurVue convertUtilisateurToUtilisateurVue(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return new UtilisateurVue(utilisateur.getAdresseMail());
	}

	public Utilisateur recupererUtilisateur() throws UtilisateurNonTrouveException {
		return utilisateurRepository.findByIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant())
				.orElseThrow(UtilisateurNonTrouveException::new);
	}

	public Utilisateur determinerUtilisateur() throws UtilisateurNonTrouveException {
		Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(UtilisateurConnecteUtils.recupererIdentifiant()).orElseThrow(UtilisateurNonTrouveException::new);
		
		return utilisateur;
	}

	public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);
	}
	
	public Utilisateur saveContact(Utilisateur utilisateur) {
		mailService.sendMailNouveauContact(utilisateur);
		return utilisateurRepository.save(utilisateur);
	}

}
