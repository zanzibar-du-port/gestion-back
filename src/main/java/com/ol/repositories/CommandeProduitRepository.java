package com.ol.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ol.models.CommandeProduit;

@Repository
public interface CommandeProduitRepository extends JpaRepository<CommandeProduit, Integer>{

	@Query(value = "SELECT nb_lot FROM commande_produit " 
					+ "WHERE id_produit = :idProduit "
					+ "and id_commande in " 
								+ "(select id from commande "
											+ "where id_fournisseur = (select id_fournisseur from produit "
																					+ "where id = :idProduit) "
											+ "order by date_heure_commande asc)", 
			  nativeQuery = true)
	List<Integer> getDeuxDernieresCommandesByIdProduit(Integer idProduit);

}
