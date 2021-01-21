package com.ol.models.tofrontadmin;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorieDetailToFrontAdmin {

	private Integer id;
	private String libelleCategorie;
	private Integer PositionDansLaListe;
	private List<String> libelleSousCategorie;
	private String actifInactif;
}
