package com.ol.models.tofrontadmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionToFrontAdmin {

	private Integer id;
	private String libelle;
	private Integer prix;
	private Integer quantiteDisponible;
	private Integer nbAchats;

}
