package com.ol.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ol.models.Produit;
import com.ol.models.frombdd.ProduitBdd;
import com.ol.models.tofrontadmin.ProduitToFrontAdmin;
import com.ol.models.tofrontclient.ProduitToFrontClient;

@Repository
public interface ProduitRepoSitory extends JpaRepository<Produit, Integer>{

	/**
     * 
     */
	@Query(value = "SELECT DISTINCT(T1.categorie) FROM (SELECT categorie, position_dans_la_liste FROM produit WHERE etat = 'ACTIF' ORDER BY position_dans_la_liste) AS T1", 
			  nativeQuery = true)
	List<String> getAllCategorie();

	/**
     * @Query(value = "SELECT DISTINCT(T1.sous_categorie) FROM (SELECT sous_categorie, position_dans_la_liste FROM produit WHERE etat = 'ACTIF' AND categorie = :categorie ORDER BY position_dans_la_liste) AS T1", 
			  nativeQuery = true)
	Iterable<String> getAllSousCategoriesFromCategorie(@Param("idCategorie") Integer idCategorie);
     */
	

	@Query(value = "SELECT * FROM produit WHERE etat = 'ACTIF' AND categorie = :categorie ORDER BY position_dans_la_liste", 
			  nativeQuery = true)
	List<Produit> findByCategorie(@Param("categorie") String categorie);
	
	@Query(value = "SELECT * FROM produit ORDER BY position_dans_la_liste", 
			  nativeQuery = true)
	List<Produit> findAllOrder();

	@Query(value = "SELECT * FROM produit WHERE etat = 'ACTIF' ORDER BY position_dans_la_liste", 
			  nativeQuery = true)
	List<Produit> findAllProduitsActifsOrder();

	@Modifying
	@Query(value = "UPDATE produit SET nb_vues = nb_vues + 1 WHERE id = :produitId ",
			nativeQuery = true)
	void addVue(@Param("produitId") Integer produitId);

	@Modifying
	@Query(value = "UPDATE produit SET nb_achats = nb_achats + 1 WHERE id = :produitId ",
			nativeQuery = true)
	void addAchat(@Param("produitId") Integer produitId);

	@Query(nativeQuery=true) 
	List<ProduitBdd> getAllProduitBdd(int pagination);

	@Query(nativeQuery=true)
	ProduitBdd getOneProduitBdd(Integer idProuit);

	@Query(nativeQuery=true)
	ProduitBdd findProduitBddById(Integer idProduit);

	@Query(value = "SELECT liste_images_id FROM produit_liste_images WHERE produit_id = :idProduit", 
			  nativeQuery = true)
	List<Integer> getlisteIdImages(@Param("idProduit") Integer idProduit);

	@Query(nativeQuery=true)
	List<ProduitBdd> getAllProduitBddByLibelleCategorieAndLibelleSousCategorie(String categorie,
			String sousCategorie, Integer pagination);
	
	@Query(nativeQuery=true)
	List<ProduitBdd> getAllProduitBddByLibelleCategorie(String categorie, Integer pagination);

	@Query(value = "SELECT etat FROM produit WHERE id = (select produit_id from selection where id = :idSelection)", 
			  nativeQuery = true)
	String getEtatProduitByIdSelection(Integer idSelection);

	@Query(value = "SELECT count(id) FROM produit WHERE etat = 'ACTIF'", 
			  nativeQuery = true)
	Integer getNbPaginations();

	@Query(value = "SELECT count(p.id) FROM produit p WHERE etat = 'ACTIF' and p.id in " + 
						"(select plsc.produit_id from produit_liste_sous_categorie plsc "+
						"where plsc.liste_sous_categorie_id in " + 
							"(select sc.id from sous_categorie sc "+ 
							"where (select c.libelle from categorie c where c.id = sc.id_categorie) = :libelleCategorie))", 
			  nativeQuery = true)
	Integer getNbPaginations(@Param("libelleCategorie") String libelleCategorie);

	@Query(value = "SELECT count(id) FROM produit WHERE etat = 'ACTIF'" +
						"and p.id in " + 
				  			"(select plsc.produit_id " + 
				  			"from produit_liste_sous_categorie plsc " + 
				  			"where plsc.liste_sous_categorie_id in " + 
				  				"(select sc.id " + 
				  				"from sous_categorie sc " +
				  				"where sc.libelle = :libelleSousCategorie) " +
				  				"and (select c.libelle " + 
				  						"from categorie c " + 
				  						"where c.id = (select sc.id_categorie " + 
				  										"from sous_categorie sc " + 
				  										"where sc.libelle = :libelleSousCategorie)) = :libelleCategorie) ", 
			  nativeQuery = true)
	Integer getNbPaginations(@Param("libelleCategorie") String libelleCategorie, 
			@Param("libelleSousCategorie") String libelleSousCategorie);

	
}
