package com.ol.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ol.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

	Optional<Client> findByAdresseEmail(String adresseEmail);
	
}
