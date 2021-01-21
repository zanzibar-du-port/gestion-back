package com.ol.models.frombdd;

import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionBdd {

	private Integer id;
	private String libelle;
	private Integer quantiteDisponible;
	private Integer prix;
	
}
