package com.ol;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ol.models.Footer;
import com.ol.models.Header;
import com.ol.models.Image;
import com.ol.models.InfosGenerales;
import com.ol.models.auth.Role;
import com.ol.models.auth.Utilisateur;
import com.ol.models.utils.UtilsLocalDateParHeure;
import com.ol.repositories.FooterRepository;
import com.ol.repositories.HeaderRepository;
import com.ol.repositories.InfosGeneralesRepository;
import com.ol.repositories.UtilisateurRepository;
import com.ol.repositories.utils.UtilsLocalDateParHeureRepository;
import com.ol.services.ScheduleService;


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
	
	private UtilsLocalDateParHeureRepository utilsLocalDateParHeureRepository;

	private ScheduleService scheduleService;
	
	public StartupDataInit(UtilisateurRepository utilisateurRepository, 
			UtilsLocalDateParHeureRepository utilsLocalDateParHeureRepository,
			ScheduleService scheduleService) {
		super();
		this.utilisateurRepository = utilisateurRepository;
		this.utilsLocalDateParHeureRepository = utilsLocalDateParHeureRepository;
		this.scheduleService = scheduleService;
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
			if(!utilisateurRepository.findByIdentifiant("lasbleis.olivier@yahoo.fr").isPresent()) {
				utilisateurRepository.save(new Utilisateur("lasbleis.olivier@yahoo.fr", 
						"$2a$10$RIaf88opNE8abA590j8NiOW/chpEn7q/mDdzBHFqboRJIF/fZ5gi2", 
						Arrays.asList(new Role("ROLE_USER"))));
			}
			this.scheduleService.ajoutQuotidienUtilsLocalDateParHeure();
		}
		

	}

}
