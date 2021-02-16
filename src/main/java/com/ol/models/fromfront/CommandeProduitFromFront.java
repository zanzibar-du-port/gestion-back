package com.ol.models.fromfront;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeProduitFromFront {

	private Integer idProduit;
	private Integer nbLots;
}
