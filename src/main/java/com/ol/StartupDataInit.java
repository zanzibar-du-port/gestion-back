package com.ol;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ol.models.auth.Role;
import com.ol.models.auth.Utilisateur;
import com.ol.repositories.UtilisateurRepository;


/**
 * Cette Classe initialise la bdd. Au demmarage de l'application, elle fait
 * appel à la methode init().
 * 
 */
@Component
public class StartupDataInit {


	@Value("${data.init}")
	private Boolean isDataInit;

	private UtilisateurRepository utilisateurRepository;
	
	public StartupDataInit(UtilisateurRepository utilisateurRepository) {
		super();
		this.utilisateurRepository = utilisateurRepository;
	}



	/**
	 * initialise la bdd Cette méthode init va être invoquée au démarrage de
	 * l'application. via la methode insererLaListeDesCommunes() issue de la
	 * classe InsertionEnBasDeDonneeService, elle fait des appels aux APIs et
	 * récupère la liste des stations metéo, des stations de pollutions, des
	 * communes des mesures de pollution et de météo puis elle les lie entre
	 * elles et les insère en BDD
	 */
	// La méthode init va être invoquée au démarrage de l'application.
	@EventListener(ContextRefreshedEvent.class)
	public void init() throws Exception {

		if(isDataInit) {
			if(!utilisateurRepository.findByIdentifiant("epicerieduportcommandes@gmail.com").isPresent()) {
				utilisateurRepository.save(new Utilisateur("epicerieduportcommandes@gmail.com", 
						"$2a$10$X5ByNfZa0/8G.qy4hAQ07OZODumBbYwsoa4KaSrhI1JwqMjxXAgtC", 
						Arrays.asList(new Role("ROLE_USER"))));
			}
		}
		

	}

}
