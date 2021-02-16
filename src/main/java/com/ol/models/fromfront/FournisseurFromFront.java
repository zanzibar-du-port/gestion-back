package com.ol.models.fromfront;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurFromFront {
	
	private Integer id;
	private String entreprise;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	private String codeClient;

}
