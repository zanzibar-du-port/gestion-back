package com.ol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ol.models.Dimension;
import com.ol.models.Selection;

import java.util.Optional;

@Repository
public interface DimensionRepository extends JpaRepository<Dimension, Integer>{

	@Query(value = "SELECT * FROM dimension d WHERE d.id = (select s.id from selection s where s.id = :idSelection)", 
			  nativeQuery = true)
	Optional<Dimension> findByIdSelection(Integer idSelection);


	
}
