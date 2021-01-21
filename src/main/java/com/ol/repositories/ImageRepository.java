package com.ol.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ol.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer>{

	Optional<Image> findByName(String name);

	@Query(value = "SELECT * FROM image_table i WHERE id in (select pli.liste_images_id from produit_liste_images pli where pli.produit_id = :idProduit)", 
			  nativeQuery = true)
	List<Image> getListeImageByIdProduit(Integer idProduit);
}
