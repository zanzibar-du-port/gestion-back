package com.ol.models;

import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;

import com.ol.models.frombdd.ProduitBdd;

import lombok.Data;
import lombok.NoArgsConstructor;


@NamedNativeQuery(
		  name="Produit.findProduitBddById",
		  query="select p.id, " + 
		  		"p.libelle, " + 
		  		"p.description, " + 
		  		"p.detail, " + 
		  		"p.position_dans_la_liste, " + 
		  		"p.prix_normal_av, " + 
		  		"p.prix_barre_milieu, " + 
		  		"p.prix_normal_ap, " + 
		  		"p.image_principale_id, " + 
		  		"p.etat " + 
		  		"FROM produit p " + 
		  		"WHERE p.id = ?1",
		  resultSetMapping="find-ProduitBdd-By-Id",
		  resultClass = Produit.class
		)
@SqlResultSetMapping(
		 name = "find-ProduitBdd-By-Id", // If @Query does not specify name, the method name will be used by default.
		   classes = {
		      @ConstructorResult( 
		          targetClass = ProduitBdd.class,
		          columns = {
		               @ColumnResult( name = "id", type = Integer.class),  
		               @ColumnResult( name = "libelle", type = String.class),  
		               @ColumnResult( name = "description", type = String.class), 
		               @ColumnResult( name = "detail", type = String.class), 
		               @ColumnResult( name = "position_dans_la_liste", type = Integer.class), 
		               @ColumnResult( name = "prix_normal_av", type = String.class),  
		               @ColumnResult( name = "prix_barre_milieu", type = String.class),  
		               @ColumnResult( name = "prix_normal_ap", type = String.class),   
		               @ColumnResult( name = "etat", type = String.class),   
		               @ColumnResult( name = "image_principale_id", type = Integer.class)
		          }
		      )
		   }
		)
@NamedNativeQuery(
		  name="Produit.getAllProduitBdd",
		  query="select p.id, " + 
		  		"p.libelle, " + 
		  		"p.description, " + 
		  		"p.detail, " + 
		  		"p.position_dans_la_liste, " + 
		  		"p.prix_normal_av, " + 
		  		"p.prix_barre_milieu, " + 
		  		"p.prix_normal_ap, " + 
		  		"p.etat, " + 
		  		"p.image_principale_id " + 
		  		"FROM produit p " + 
		  		"ORDER BY p.position_dans_la_liste " +
		  		"LIMIT 12 OFFSET ?1",
		  resultSetMapping="get-All-ProduitBdd",
		  resultClass = Produit.class
		)
@SqlResultSetMapping(
		 name = "get-All-ProduitBdd", // If @Query does not specify name, the method name will be used by default.
		   classes = {
		      @ConstructorResult( 
		          targetClass = ProduitBdd.class,
		          columns = {
		               @ColumnResult( name = "id", type = Integer.class),  
		               @ColumnResult( name = "libelle", type = String.class),  
		               @ColumnResult( name = "description", type = String.class),  
		               @ColumnResult( name = "detail", type = String.class),   
		               @ColumnResult( name = "position_dans_la_liste", type = Integer.class),   
		               @ColumnResult( name = "prix_normal_av", type = String.class),  
		               @ColumnResult( name = "prix_barre_milieu", type = String.class),  
		               @ColumnResult( name = "prix_normal_ap", type = String.class),   
		               @ColumnResult( name = "etat", type = String.class),   
		               @ColumnResult( name = "image_principale_id", type = Integer.class)
		          }
		      )
		   }
		)
@NamedNativeQuery(
		  name="Produit.getAllProduitBddByLibelleCategorie",
		  query="select p.id, " + 
		  		"p.libelle, " + 
		  		"p.description, " + 
		  		"p.detail, " + 
		  		"p.position_dans_la_liste, " + 
		  		"p.prix_normal_av, " + 
		  		"p.prix_barre_milieu, " + 
		  		"p.prix_normal_ap, " + 
		  		"p.etat, " + 
		  		"p.image_principale_id " +
		  		"FROM produit p " + 
		  		"where p.id in " + 
		  			"(select plsc.produit_id " + 
		  			"from produit_liste_sous_categorie plsc " + 
		  			"where plsc.liste_sous_categorie_id in " + 
		  				"(select sc.id " + 
		  				"from sous_categorie sc " +
		  				"where (select c.libelle " +
		  					"from categorie c "+
		  					"where c.id = sc.id_categorie) " +
		  				"= ?1)) " +
		  		"ORDER BY p.position_dans_la_liste " +
		  		"LIMIT 12 OFFSET ?2",
		  resultSetMapping="get-All-ProduitBdd-By-Libelle-Categorie",
		  resultClass = Produit.class
		)
@SqlResultSetMapping(
		 name = "get-All-ProduitBdd-By-Libelle-Categorie", // If @Query does not specify name, the method name will be used by default.
		   classes = {
		      @ConstructorResult( 
		          targetClass = ProduitBdd.class,
		          columns = {
		               @ColumnResult( name = "id", type = Integer.class),  
		               @ColumnResult( name = "libelle", type = String.class),  
		               @ColumnResult( name = "description", type = String.class),  
		               @ColumnResult( name = "detail", type = String.class),   
		               @ColumnResult( name = "position_dans_la_liste", type = Integer.class),   
		               @ColumnResult( name = "prix_normal_av", type = String.class),  
		               @ColumnResult( name = "prix_barre_milieu", type = String.class),  
		               @ColumnResult( name = "prix_normal_ap", type = String.class),   
		               @ColumnResult( name = "etat", type = String.class),   
		               @ColumnResult( name = "image_principale_id", type = Integer.class)
		          }
		      )
		   }
		)
@NamedNativeQuery(
		  name="Produit.getAllProduitBddByLibelleCategorieAndLibelleSousCategorie",
		  query="select p.id, " + 
		  		"p.libelle, " + 
		  		"p.description, " + 
		  		"p.detail, " + 
		  		"p.position_dans_la_liste, " + 
		  		"p.prix_normal_av, " + 
		  		"p.prix_barre_milieu, " + 
		  		"p.prix_normal_ap, " + 
		  		"p.etat, " + 
		  		"p.image_principale_id " +
		  		"FROM produit p " + 
		  		"where p.id in " + 
		  			"(select plsc.produit_id " + 
		  			"from produit_liste_sous_categorie plsc " + 
		  			"where plsc.liste_sous_categorie_id in " + 
		  				"(select sc.id " + 
		  				"from sous_categorie sc " +
		  				"where sc.libelle = ?2) " +
		  				"and (select c.libelle " + 
		  						"from categorie c " + 
		  						"where c.id = (select sc.id_categorie " + 
		  										"from sous_categorie sc " + 
		  										"where sc.libelle = ?2)) = ?1) " +
		  		"ORDER BY p.position_dans_la_liste " +
		  		"LIMIT 12 OFFSET ?3",
		  resultSetMapping="get-All-ProduitBdd-By-Libelle-Categorie-And-Libelle-SousCategorie",
		  resultClass = Produit.class
		)
@SqlResultSetMapping(
		 name = "get-All-ProduitBdd-By-Libelle-Categorie-And-Libelle-SousCategorie", // If @Query does not specify name, the method name will be used by default.
		   classes = {
		      @ConstructorResult( 
		          targetClass = ProduitBdd.class,
		          columns = {
		               @ColumnResult( name = "id", type = Integer.class),  
		               @ColumnResult( name = "libelle", type = String.class),  
		               @ColumnResult( name = "description", type = String.class),  
		               @ColumnResult( name = "detail", type = String.class),   
		               @ColumnResult( name = "position_dans_la_liste", type = Integer.class),   
		               @ColumnResult( name = "prix_normal_av", type = String.class),  
		               @ColumnResult( name = "prix_barre_milieu", type = String.class),  
		               @ColumnResult( name = "prix_normal_ap", type = String.class),   
		               @ColumnResult( name = "etat", type = String.class),   
		               @ColumnResult( name = "image_principale_id", type = Integer.class)
		          }
		      )
		   }
		)
@NamedNativeQuery(
		  name="Produit.getOneProduitBdd",
		  query="select p.id, " + 
		  		"p.libelle, " + 
		  		"p.description, " + 
		  		"p.detail, " + 
		  		"p.prix_normal_av, " + 
		  		"p.prix_barre_milieu, " + 
		  		"p.prix_normal_ap, " + 
		  		"p.image_principale_id " + 
		  		"FROM produit p " + 
		  		"WHERE p.id = ?1 ",
		  resultSetMapping="get-One-ProduitBdd",
		  resultClass = Produit.class
		)
@SqlResultSetMapping(
		 name = "get-One-ProduitBdd", // If @Query does not specify name, the method name will be used by default.
		   classes = {
		      @ConstructorResult( 
		          targetClass = ProduitBdd.class,
		          columns = {
		               @ColumnResult( name = "id", type = Integer.class),  
		               @ColumnResult( name = "libelle", type = String.class),  
		               @ColumnResult( name = "description", type = String.class), 
		               @ColumnResult( name = "detail", type = String.class),    
		               @ColumnResult( name = "prix_normal_av", type = String.class),  
		               @ColumnResult( name = "prix_barre_milieu", type = String.class),  
		               @ColumnResult( name = "prix_normal_ap", type = String.class),  
		               @ColumnResult( name = "image_principale_id", type = Integer.class)
		          }
		      )
		   }
		)
@Entity
@NoArgsConstructor
@Data
public class Produit {


	/** id : Integer . est l'identifiant en base de donnée du Produit */
	@Id
	private Integer id;
	/** libelle : String . est le libelle du Produit */
	private String libelle;
	/** description : String . est la description du Produit */
	private String description;
	/** detail : String . est la deuxième description du Produit */
	private String detail;
	/** etat : String . est l'état du Produit 
	 * Il peut être ACTIF ou INACTIF*/
	private String etat;
	/** prixNormalAv : String . est le prix affiché à gauche (sans être barré) sur la représentation du produit dans l'accueil du front
	 * Cet élément ne constitue pas un prix en soi, pour ceci il faut modifier l'élément prix de l'objet Selection
	*/
	private String prixNormalAv;
	/** prixBarreMilieu : String . est le prix affiché au milieu (barré) sur la représentation du produit dans l'accueil du front
	 * Cet élément ne constitue pas un prix en soi, pour ceci il faut modifier l'élément prix de l'objet Selection
	*/
	private String prixBarreMilieu;
	/** prixNormalAp : String . est le prix affiché à droite (sans être barré) sur la représentation du produit dans l'accueil du front
	 * Cet élément ne constitue pas un prix en soi, pour ceci il faut modifier l'élément prix de l'objet Selection
	*/
	private String prixNormalAp;
	/** imagePrincipale : Image . est l'image principale du Produit */
	@ManyToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "image_principale_id")
	private Image imagePrincipale;
	/** listeImages :  List<Image> . est la liste d'images du Produit */
	@ManyToMany(cascade = {CascadeType.MERGE})
	private List<Image> listeImages;
	/** listeSousCategorie : List<SousCategorie>  est la liste des sous-catégories du Produit */
	@ManyToMany(cascade = {CascadeType.MERGE})
	private List<SousCategorie> listeSousCategorie;
	/** positionDansLaListe : int . est la position Dans La Liste du Produit 
	 * Cet élément est utile pour attribuer un ordre d'importance pour l'affichage des produits
	 * En effet les éléments sont triés selon ce champ pour être envoyé au front
	 */
	private int positionDansLaListe;
	
	

	public Produit(Integer id, String libelle, String description, String detail, String etat, String prixNormalAv,
			String prixBarreMilieu, String prixNormalAp, Image imagePrincipale,
			List<Image> listeImages, List<SousCategorie> listeSousCategorie, int positionDansLaListe) {
		super();
		if(id.equals(new Integer(0))) {
			this.id = generateInteger();
		}else {
			this.id = id;
		}
		this.libelle = libelle;
		this.description = description;
		this.detail = detail;
		this.etat = etat;
		this.prixNormalAv = prixNormalAv;
		this.prixBarreMilieu = prixBarreMilieu;
		this.prixNormalAp = prixNormalAp;
		this.imagePrincipale = imagePrincipale;
		this.listeImages = listeImages;
		this.listeSousCategorie = listeSousCategorie;
		this.positionDansLaListe = positionDansLaListe;
	}

	public static Integer generateInteger() {
		return new Random().nextInt();
    }

	
}
