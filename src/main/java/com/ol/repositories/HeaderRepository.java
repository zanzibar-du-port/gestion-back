package com.ol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ol.models.Header;

@Repository
public interface HeaderRepository extends JpaRepository<Header, Integer>{

	
}
