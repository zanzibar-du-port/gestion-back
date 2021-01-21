package com.ol.models.fromfrontadmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionFromFrontAdmin {

	private Integer id;
	private String libelle;
	private Integer quantiteDisponible;
	private Integer prix;
	private DimensionFromFrontAdmin dimension;
	
}
