package com.ol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ol.models.InfosGenerales;

@Repository
public interface InfosGeneralesRepository extends JpaRepository<InfosGenerales, Integer>{

}
