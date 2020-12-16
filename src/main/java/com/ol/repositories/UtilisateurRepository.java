package com.ol.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ol.models.auth.Utilisateur;


@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{

	/**
     * recupere un utilisateur grace à son identifiant
     * @param identifiant identifiant de l’utilisateur
     * @return un Optional<Utilisateur>
     */
	@Query("select u from Utilisateur u where u.adresseMail=?1")
    Optional<Utilisateur> findByIdentifiant(String adresseMail);


}
