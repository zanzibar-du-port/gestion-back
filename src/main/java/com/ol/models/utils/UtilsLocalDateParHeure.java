package com.ol.models.utils;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilsLocalDateParHeure {

	/** id : Integer .id est l'identifiant en base de donnée de la UtilsLocalDateParHeure */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** localDateHeure : LocalDateTime est l'heure concernée */
	private LocalDateTime localDateHeure;
	
	public UtilsLocalDateParHeure(LocalDateTime localDateHeure) {
		super();
		this.localDateHeure = localDateHeure;
	}
	
}
