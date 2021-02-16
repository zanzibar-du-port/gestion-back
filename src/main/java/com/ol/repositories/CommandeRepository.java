package com.ol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ol.models.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer>{

}
