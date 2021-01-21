package com.ol.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;

import com.ol.models.frombdd.ProduitBdd;
import com.ol.models.frombdd.SelectionBdd;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NamedNativeQuery(
		  name="Selection.findAllSelectionBddByIdProduit",
		  query="select s.id, " + 
		  		"s.libelle, " + 
		  		"s.quantite_disponible, " + 
		  		"s.prix " + 
		  		"FROM selection s " + 
		  		"WHERE s.produit_id = ?1 ",
		  resultSetMapping="find-All-SelectionBdd-By-IdProduit",
		  resultClass = Selection.class
		)
@SqlResultSetMapping(
		 name = "find-All-SelectionBdd-By-IdProduit", // If @Query does not specify name, the method name will be used by default.
		   classes = {
		      @ConstructorResult( 
		          targetClass = SelectionBdd.class,
		          columns = {
		               @ColumnResult( name = "id", type = Integer.class),  
		               @ColumnResult( name = "libelle", type = String.class),  
		               @ColumnResult( name = "quantite_disponible", type = Integer.class),    
		               @ColumnResult( name = "prix", type = Integer.class)
		          }
		      )
		   }
		)
@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Selection {
	
	/** id : Integer . est l'identifiant en base de donnée de la Selection */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** libelle : String . est le libelle de la Selection 
	 * exemple : Taille-M Couleur-noir
	 */
	private String libelle;
	/** quantiteDisponible : int . est la quantité disponible de produits de la Selection */
	private int quantiteDisponible;
	/** prix : int . est le prix de la Selection  */
	private int prix;
	/** produit : Produit . est le produit de la Selection  */
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "produit_id")
	private Produit produit;
	
	public Selection(String libelle, int quantiteDisponible, int prix, Produit produit) {
		super();
		this.libelle = libelle;
		this.quantiteDisponible = quantiteDisponible;
		this.prix = prix;
		this.produit = produit;
	}
}
