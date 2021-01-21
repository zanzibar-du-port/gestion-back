package com.ol.models.utils;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilsVueProduit {

	/** id : Integer . est l'identifiant en base de donnée de la UtilsVueProduit */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer nbVueProduit;
	/** utilsLocalDateParHeure : UtilsLocalDateParHeure . est la date et l'heure (via un objet UtilsLocalDateParHeure) de vue du produit */
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "utilsLocalDateParHeure_id")
	private UtilsLocalDateParHeure utilsLocalDateParHeure;
	private Integer idProduit;
	
	public UtilsVueProduit(UtilsLocalDateParHeure utilsLocalDateParHeure, Integer idProduit) {
		super();
		this.utilsLocalDateParHeure = utilsLocalDateParHeure;
		this.idProduit = idProduit;
		this.nbVueProduit = 1;
	}
	
}
