package com.ol.models.tofront;

import com.ol.models.fromfront.FournisseurFromFront;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurToFront {
	
	private Integer id;
	private String entreprise;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	private String codeClient;
}
