package com.ol.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ol.models.Categorie;
import com.ol.models.SousCategorie;
import com.ol.models.tofrontadmin.CategorieToFrontAdmin;

@Repository
@Transactional
public interface SousCategorieRepository extends JpaRepository<SousCategorie, Integer>{

	Optional<SousCategorie> findByLibelleAndCategorie(String libelle, Categorie categorie);

	@Query(value = "select * from sous_categorie where actif_inactif = 'ACTIF' and id in (select liste_sous_categorie_id from produit_liste_sous_categorie where produit_id = :idProduit)",
			nativeQuery = true)
	List<SousCategorie> findAllByIdProduit(Integer idProduit);

	@Query(value = "select libelle from sous_categorie where actif_inactif = 'ACTIF' and id_categorie = (select id from categorie where libelle = :libelleCategorie) order by position_dans_la_liste",
			nativeQuery = true)
	List<String> getAllSousCategoriesByLibelleCategorie(String libelleCategorie);
	
	@Query(value = "select * from sous_categorie where id_categorie = :idCategorie order by position_dans_la_liste",
			nativeQuery = true)
	List<SousCategorie> getAllSousCategoriesByIdCategorie(Integer idCategorie);

	@Modifying
	@Query(value = "insert into produit_liste_sous_categorie values (:idProduit, :idSousCategorie)",
			nativeQuery = true)
	void relierProduitSousCategorie(@Param("idProduit") Integer idProduit, @Param("idSousCategorie") Integer idSousCategorie);

	@Query(value = "select * from sous_categorie where id in (select liste_sous_categorie_id from produit_liste_sous_categorie where produit_id = :idProduit)",
			nativeQuery = true)
	List<SousCategorie> getAllSousCategoriesByIdProduit(Integer idProduit);
	
	@Modifying
	@Query(value = "update sous_categorie set position_dans_la_liste = :positionDansLaListe where id = :idSousCategorie",
			nativeQuery = true)
	void changePositionDansLaListeSousCategorie(@Param("idSousCategorie") Integer idSousCategorie, 
			 @Param("positionDansLaListe") Integer positionDansLaListe);

	
}
