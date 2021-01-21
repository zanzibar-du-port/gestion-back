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
import com.ol.models.Image;
import com.ol.models.tofrontadmin.SousCategorieDetailToFrontAdmin;

@Repository
@Transactional
public interface CategorieRepository extends JpaRepository<Categorie, Integer>{

	Optional<Categorie> findByLibelle(String libelle);

	@Query(value = "select * from categorie order by position_dans_la_liste",
			nativeQuery = true)
	List<Categorie> getAllCategorie();

	@Modifying
	@Query(value = "update categorie set position_dans_la_liste = :positionDansLaListe where id = :idCategorie",
			nativeQuery = true)
	void changePositionDansLaListeCategorie(@Param("idCategorie") Integer idCategorie, @Param("positionDansLaListe") Integer positionDansLaListe);

}
