package com.ol.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ol.models.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer>{

	@Query(value = "SELECT * FROM commande WHERE date_heure > :dateCourante", 
			  nativeQuery = true)
	List<Commande> getAllDepuis(@Param("dateCourante") LocalDateTime dateCourante);

}
