package com.ol.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ol.models.Categorie;
import com.ol.models.Dimension;
import com.ol.models.Image;
import com.ol.models.Produit;
import com.ol.models.Selection;
import com.ol.models.SousCategorie;
import com.ol.models.frombdd.ProduitBdd;
import com.ol.models.fromfrontadmin.ProduitFromFrontAdmin;
import com.ol.models.tofrontadmin.ProduitToFrontAdmin;
import com.ol.models.tofrontclient.ProduitDetailToFrontClient;
import com.ol.models.tofrontclient.ProduitToFrontClient;
import com.ol.repositories.CategorieRepository;
import com.ol.repositories.DimensionRepository;
import com.ol.repositories.ImageRepository;
import com.ol.repositories.ProduitRepoSitory;
import com.ol.repositories.SelectionRepository;
import com.ol.repositories.SousCategorieRepository;
import com.sun.el.stream.Stream;

@Service
@Transactional
public class ProduitService {
	
	@Autowired
	ProduitConvertorService produitConvertorService;
	
	@Autowired
	ProduitRepoSitory produitRepoSitory;
	
	@Autowired
	SelectionRepository selectionRepoSitory;
	
	@Autowired
	CategorieRepository categorieRepoSitory;
	
	@Autowired
	SousCategorieRepository sousCategorieRepoSitory;
	
	@Autowired
	ImageRepository imageRepoSitory;
	
	@Autowired
	DimensionRepository dimensionRepoSitory;

	public List<ProduitToFrontClient> getAllProduitToFrontClient(int pagination) {
		return produitRepoSitory
				.getAllProduitBdd(pagination * 12)
				.stream()
				.map(p -> produitConvertorService.convertProduitBddToProduitToFrontClient(p))
				.collect(Collectors.toList());
	}
	
	public Produit saveProduit(Produit produit) {
		return produitRepoSitory.save(produit);
	}

	public List<Produit> getAll() {
		return produitRepoSitory.findAllOrder();
	}

	public Optional<Produit> findById(Integer produitId) {
		return produitRepoSitory.findById(produitId);
	}
	
	public void addVueById(Integer produitId) {
		produitRepoSitory.addVue(produitId);
	}

	public Iterable<String> getAllCategorie() {
		return produitRepoSitory.getAllCategorie();
	}

	public Iterable<String> getAllSousCategoriesFromCategorie(Categorie categorie) {
		//TODO
		return null;//produitRepoSitory.getAllSousCategoriesFromCategorie(categorie.getId());
	}
	
	public Iterable<Produit> getAllByCategorie(String categorie) {
		return produitRepoSitory.findByCategorie(categorie);
	}

	public Boolean isProduitActif(Produit produit) {
		final Optional<Produit> retrievedProduit = findById(produit.getId());
		if (retrievedProduit.isPresent() && retrievedProduit.get().getEtat().equals("ACTIF")) {
			return true;
		}else {
			return false;
		}
	}

	public List<Produit> getAllProduitsActifs() {
		return produitRepoSitory.findAllProduitsActifsOrder();
	}

	public void deleteProduit(Integer produitId) {
		produitRepoSitory.deleteById(produitId);;
	}


	public Produit saveProduitFromFrontAdmin(ProduitFromFrontAdmin produitFromFrontAdmin) {
		
		List<SousCategorie> listeSousCategories = new ArrayList<>();
		
		produitFromFrontAdmin.getListeCategories()
		.stream()
		.forEach(c -> {
			Categorie categorie = new Categorie();
			if(categorieRepoSitory.findByLibelle(c.getCategorie()).isPresent()) {
				categorie = categorieRepoSitory.findByLibelle(c.getCategorie()).get();
			}else {
				categorie = categorieRepoSitory.save(new Categorie(c.getCategorie()));
			}
			if(sousCategorieRepoSitory.findByLibelleAndCategorie(c.getSousCategorie(), categorie).isPresent()) {
				listeSousCategories.add(sousCategorieRepoSitory.findByLibelleAndCategorie(c.getSousCategorie(), categorie).get());
			}else {
				listeSousCategories.add(sousCategorieRepoSitory.save(new SousCategorie(c.getSousCategorie(),categorie)));
			}
			
		});
		
		List<Image> listeImages = new ArrayList<>();
		
		produitFromFrontAdmin.getListeIdImages()
		.stream()
		.forEach(id -> listeImages.add(imageRepoSitory.findById(id).get()));
		
		List<Selection> listeSelections = new ArrayList<>();
		
		
		
		Produit produit = new Produit(produitFromFrontAdmin.getId(),
				produitFromFrontAdmin.getLibelle(),
				produitFromFrontAdmin.getDescription(),
				produitFromFrontAdmin.getDetail(),
				produitFromFrontAdmin.getEtat(),
				produitFromFrontAdmin.getPrixNormalAv(),
				produitFromFrontAdmin.getPrixBarreMilieu(),
				produitFromFrontAdmin.getPrixNormalAp(),
				imageRepoSitory.findById(produitFromFrontAdmin.getIdImagePrincipale()).get(),
				listeImages,
				listeSousCategories,
				produitFromFrontAdmin.getPositionDansLaListe());
		
		produit = produitRepoSitory.save(produit);
		
		
		
		final Produit produitCourant = produit;
		
		produit.getListeSousCategorie().stream()
		.forEach(sc -> sousCategorieRepoSitory.relierProduitSousCategorie(produitCourant.getId(), sc.getId()));
		
		produitFromFrontAdmin.getListeSelections().stream()
		.forEach(
				s -> {
					Selection selectionCourante = new Selection();
					if(s.getId() != null && selectionRepoSitory.findById(s.getId()).isPresent()) {
						selectionCourante = 
								selectionRepoSitory.save(
										new Selection(
												s.getId(),
												s.getLibelle(),
												s.getQuantiteDisponible(),
												s.getPrix(),
												produitCourant)
										);
						Dimension dimension = dimensionRepoSitory.save(new Dimension(
								s.getDimension().getX(),
								s.getDimension().getY(),
								s.getDimension().getZ(),
								s.getDimension().getPoid(),
								selectionCourante)
								);
					}else {
						selectionCourante =
							selectionRepoSitory.save(
								new Selection(
										s.getLibelle(),
										s.getQuantiteDisponible(),
										s.getPrix(),
										produitCourant)
								);
						
						
					}
					if(s.getDimension().getId() != null && dimensionRepoSitory.findById(s.getDimension().getId()).isPresent()) {
						Dimension dimension = dimensionRepoSitory.save(new Dimension(
								s.getDimension().getId(),
								s.getDimension().getX(),
								s.getDimension().getY(),
								s.getDimension().getZ(),
								s.getDimension().getPoid(),
								selectionCourante)
								);
					}else {
						Dimension dimension = dimensionRepoSitory.save(new Dimension(
								s.getDimension().getX(),
								s.getDimension().getY(),
								s.getDimension().getZ(),
								s.getDimension().getPoid(),
								selectionCourante)
								);
					}
				});
		
		return produit;
	}

	public ProduitDetailToFrontClient getProduitDetailToFrontClientById(Integer idProduit) {
		return produitConvertorService.convertProduitBddToProduitDetailToFrontClient(produitRepoSitory
				.getOneProduitBdd(idProduit));
	}

	public List<ProduitToFrontAdmin> getAllProduitToFrontAdmin(Integer pagination) {
		return produitRepoSitory
				.getAllProduitBdd(pagination)
				.stream()
				.map(p -> produitConvertorService.convertProduitBddToProduitToFrontAdmin(p))
				.collect(Collectors.toList());
	}

	public ProduitFromFrontAdmin getOneProduitFromFrontAdmin(Integer idProduit) {
		return produitConvertorService.convertProduitBddToProduitFromFrontAdmin(produitRepoSitory
				.findProduitBddById(idProduit));
	}
	
	public List<ProduitToFrontClient> getAllProduitToFrontClientByLibelleCategorie(String categorie, int pagination) {
		return produitRepoSitory
				.getAllProduitBddByLibelleCategorie(categorie, pagination)
				.stream()
				.map(pBdd -> produitConvertorService.convertProduitBddToProduitToFrontClient(pBdd))
				.collect(Collectors.toList());
	}

	public List<ProduitToFrontClient> getAllProduitToFrontClientByLibelleCategorieAndLibelleSousCategorie(
			String categorie, String sousCategorie, Integer pagination) {
		
		return produitRepoSitory
				.getAllProduitBddByLibelleCategorieAndLibelleSousCategorie(categorie, sousCategorie, pagination)
				.stream()
				.map(pBdd -> produitConvertorService.convertProduitBddToProduitToFrontClient(pBdd))
				.collect(Collectors.toList());
	}

	public boolean isProduitActifByIdSelection(Integer idSelection) {
		return produitRepoSitory.getEtatProduitByIdSelection(idSelection).equals("ACTIF");
	}

	

}
