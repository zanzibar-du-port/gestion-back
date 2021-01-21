package com.ol.models.utils;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ol.models.Client;
import com.ol.models.Selection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilsAchatSelection {

	/** id : Integer . est l'identifiant en base de donnée de la VueProduit */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer nbAchatSelection;
	/** utilsLocalDateParHeure : UtilsLocalDateParHeure . est la date et l'heure (via un objet UtilsLocalDateParHeure) de vue du produit */
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "utilsLocalDateParHeure_id")
	private UtilsLocalDateParHeure utilsLocalDateParHeure;
	private Integer idSelection;
	
	public UtilsAchatSelection(UtilsLocalDateParHeure utilsLocalDateParHeure, Integer idSelection) {
		super();
		this.utilsLocalDateParHeure = utilsLocalDateParHeure;
		this.idSelection = idSelection;
		this.nbAchatSelection = 1;
	}
}
