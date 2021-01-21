package com.ol.models.fromfrontclient;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeFromFrontClient {

	private Integer prixPanier;
	private Integer prixLivraison;
	private String methodeDeReceptionProduits;
	private String nomPrenom;
	private String telephone;
	private String adresseEmail;
	private String adressePostale;
	private String ville;
	private String codePostal;
	private String precision;
	private boolean acceptePublicite;
	private List<Integer> listeIdSelection;
	
}
