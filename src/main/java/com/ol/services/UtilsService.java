package com.ol.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ol.models.Selection;
import com.ol.models.utils.UtilsAchatSelection;
import com.ol.models.utils.UtilsLocalDateParHeure;
import com.ol.models.utils.UtilsPanierSelection;
import com.ol.models.utils.UtilsVisiteSite;
import com.ol.models.utils.UtilsVueProduit;
import com.ol.repositories.ProduitRepoSitory;
import com.ol.repositories.utils.UtilsAchatSelectionRepository;
import com.ol.repositories.utils.UtilsLocalDateParHeureRepository;
import com.ol.repositories.utils.UtilsPanierSelectionRepository;
import com.ol.repositories.utils.UtilsVisiteSiteRepository;
import com.ol.repositories.utils.UtilsVueProduitRepository;

@Service
public class UtilsService {
	
	@Value("${front.produitParPage}")
	private Integer produitParPage;
	
	@Autowired
	ProduitRepoSitory produitRepoSitory;
	
	@Autowired
	UtilsVisiteSiteRepository utilsVisiteSiteRepoSitory;

	@Autowired
	UtilsVueProduitRepository utilsVueProduitRepository;

	@Autowired
	UtilsPanierSelectionRepository utilsPanierSelectionRepository;

	@Autowired
	UtilsAchatSelectionRepository utilsAchatSelectionRepository;
	
	@Autowired
	UtilsLocalDateParHeureRepository utilsLocalDateParHeureRepository;

	public Integer getNbPaginations(String libelleCategorie, String libelleSousCategorie) {
		
		Integer nbPagination = 0;
		
		if(!libelleCategorie.equals("undefined") && !libelleSousCategorie.equals("undefined")) {
			nbPagination = produitRepoSitory
					.getNbPaginations(libelleCategorie, libelleSousCategorie);
		}else if(!libelleCategorie.equals("undefined")) {
			nbPagination = produitRepoSitory
					.getNbPaginations(libelleCategorie) ;
		}else {
			nbPagination = produitRepoSitory
					.getNbPaginations();
		}
		return (int) Math.ceil(new Double(nbPagination) / produitParPage);
		
	}
	

	public void addVisiteSite() {
		UtilsVisiteSite utilsVisiteSite = new UtilsVisiteSite();
		if (utilsVisiteSiteRepoSitory.findByUtilsLocalDateParHeure(
				utilsLocalDateParHeureRepository.findByLocalDateHeure(
								LocalDateTime.now().of(
										LocalDateTime.now().getYear(),
										LocalDateTime.now().getMonth(),
										LocalDateTime.now().getDayOfMonth(),
										LocalDateTime.now().getHour(), 
										0)).get()).isPresent()) {
			
			utilsVisiteSite = utilsVisiteSiteRepoSitory.findByUtilsLocalDateParHeure(
					utilsLocalDateParHeureRepository.findByLocalDateHeure(
							LocalDateTime.now().of(
									LocalDateTime.now().getYear(),
									LocalDateTime.now().getMonth(),
									LocalDateTime.now().getDayOfMonth(),
									LocalDateTime.now().getHour(), 
									0)).get()).get();
			utilsVisiteSite.setNbVisiteSite(utilsVisiteSite.getNbVisiteSite() + 1);
			
		}else {
			utilsVisiteSite = new UtilsVisiteSite(utilsLocalDateParHeureRepository.findByLocalDateHeure(
					LocalDateTime.now().of(
							LocalDateTime.now().getYear(),
							LocalDateTime.now().getMonth(),
							LocalDateTime.now().getDayOfMonth(),
							LocalDateTime.now().getHour(), 
							0)).get());
		}
		utilsVisiteSiteRepoSitory.save(utilsVisiteSite);
	}

	public void addvueProduit(Integer idProduit) {
		UtilsVueProduit utilsVueProduit = new UtilsVueProduit();
		if (utilsVueProduitRepository.findByUtilsLocalDateParHeureAndIdProduit(
				utilsLocalDateParHeureRepository.findByLocalDateHeure(
								LocalDateTime.now().of(
										LocalDateTime.now().getYear(),
										LocalDateTime.now().getMonth(),
										LocalDateTime.now().getDayOfMonth(),
										LocalDateTime.now().getHour(), 
										0)).get(),
								idProduit).isPresent()) {
			
			utilsVueProduit = utilsVueProduitRepository.findByUtilsLocalDateParHeureAndIdProduit(
					utilsLocalDateParHeureRepository.findByLocalDateHeure(
							LocalDateTime.now().of(
									LocalDateTime.now().getYear(),
									LocalDateTime.now().getMonth(),
									LocalDateTime.now().getDayOfMonth(),
									LocalDateTime.now().getHour(), 
									0)).get(),
							idProduit).get();
			utilsVueProduit.setNbVueProduit(utilsVueProduit.getNbVueProduit() + 1);
			
		}else {
			utilsVueProduit = new UtilsVueProduit(utilsLocalDateParHeureRepository.findByLocalDateHeure(
					LocalDateTime.now().of(
							LocalDateTime.now().getYear(),
							LocalDateTime.now().getMonth(),
							LocalDateTime.now().getDayOfMonth(),
							LocalDateTime.now().getHour(), 
							0)).get(),
					idProduit);
		}
		utilsVueProduitRepository.save(utilsVueProduit);
	}

	public void addPanierSelection(Integer idSelection) {
		UtilsPanierSelection utilsPanierSelection = new UtilsPanierSelection();
		if (utilsPanierSelectionRepository.findByUtilsLocalDateParHeureAndIdSelection(
				utilsLocalDateParHeureRepository.findByLocalDateHeure(
								LocalDateTime.now().of(
										LocalDateTime.now().getYear(),
										LocalDateTime.now().getMonth(),
										LocalDateTime.now().getDayOfMonth(),
										LocalDateTime.now().getHour(), 
										0)).get(),
								idSelection).isPresent()) {
			
			utilsPanierSelection = utilsPanierSelectionRepository.findByUtilsLocalDateParHeureAndIdSelection(
					utilsLocalDateParHeureRepository.findByLocalDateHeure(
							LocalDateTime.now().of(
									LocalDateTime.now().getYear(),
									LocalDateTime.now().getMonth(),
									LocalDateTime.now().getDayOfMonth(),
									LocalDateTime.now().getHour(), 
									0)).get(),
							idSelection).get();
			utilsPanierSelection.setNbPanierSelection(utilsPanierSelection.getNbPanierSelection() + 1);
			
		}else {
			utilsPanierSelection = new UtilsPanierSelection(utilsLocalDateParHeureRepository.findByLocalDateHeure(
					LocalDateTime.now().of(
							LocalDateTime.now().getYear(),
							LocalDateTime.now().getMonth(),
							LocalDateTime.now().getDayOfMonth(),
							LocalDateTime.now().getHour(), 
							0)).get(),
					idSelection);
		}
		utilsPanierSelectionRepository.save(utilsPanierSelection);
	}

	public void addVueCategorie(String libelleCategorie, Integer pagination) {
		// TODO Auto-generated method stub
		
	}

	public void addVueSousCategorie(String libelleCategorie, Integer pagination) {
		// TODO Auto-generated method stub
		
	}


	public void addAchatSelection(Selection selection) {
		UtilsAchatSelection utilsAchatSelection = new UtilsAchatSelection();
		if (utilsAchatSelectionRepository.findByUtilsLocalDateParHeureAndIdSelection(
				utilsLocalDateParHeureRepository.findByLocalDateHeure(
								LocalDateTime.now().of(
										LocalDateTime.now().getYear(),
										LocalDateTime.now().getMonth(),
										LocalDateTime.now().getDayOfMonth(),
										LocalDateTime.now().getHour(), 
										0)).get(),
								selection.getId()).isPresent()) {
			
			utilsAchatSelection = utilsAchatSelectionRepository.findByUtilsLocalDateParHeureAndIdSelection(
					utilsLocalDateParHeureRepository.findByLocalDateHeure(
							LocalDateTime.now().of(
									LocalDateTime.now().getYear(),
									LocalDateTime.now().getMonth(),
									LocalDateTime.now().getDayOfMonth(),
									LocalDateTime.now().getHour(), 
									0)).get(),
							selection.getId()).get();
			utilsAchatSelection.setNbAchatSelection(utilsAchatSelection.getNbAchatSelection() + 1);
			
		}else {
			utilsAchatSelection = new UtilsAchatSelection(utilsLocalDateParHeureRepository.findByLocalDateHeure(
					LocalDateTime.now().of(
							LocalDateTime.now().getYear(),
							LocalDateTime.now().getMonth(),
							LocalDateTime.now().getDayOfMonth(),
							LocalDateTime.now().getHour(), 
							0)).get(),
					selection.getId());
		}
		utilsAchatSelectionRepository.save(utilsAchatSelection);
	}


}
