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
public class UtilsVisiteSite {
	
	/** id : Integer . est l'identifiant en base de donnée de la UtilsVisiteSite */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer nbVisiteSite;
	/** utilsLocalDateParHeure : UtilsLocalDateParHeure . est la date et l'heure (via un objet UtilsLocalDateParHeure) de l'UtilsVisiteSite */
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "utilsLocalDateParHeure_id")
	private UtilsLocalDateParHeure utilsLocalDateParHeure;
	
	public UtilsVisiteSite(UtilsLocalDateParHeure utilsLocalDateParHeure) {
		super();
		this.utilsLocalDateParHeure = utilsLocalDateParHeure;
		this.nbVisiteSite = 1;
	}
	
	

}
