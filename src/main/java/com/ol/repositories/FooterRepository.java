package com.ol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ol.models.Footer;

@Repository
public interface FooterRepository extends JpaRepository<Footer, Long>{
	
	

}
