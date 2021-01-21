package com.ol.models.tofrontadmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SousCategorieDetailToFrontAdmin {

	private Integer id;
	private String libelleSousCategorie;
	private Integer PositionDansLaListe;
	private String actifInactif;
}
