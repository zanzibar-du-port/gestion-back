package com.ol.models.tofrontadmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorieToFrontAdmin {
	private Integer id;
	private String libelleCategorie;
	private String libelleSousCategorie;
}
