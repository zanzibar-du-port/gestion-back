package com.ol.models.auth;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String adresseMail;
	private String motDePasse;
	
	/**
     * liste des roles de l’utilisateur
     */
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Role> roles;
	public Utilisateur(String adresseMail, String motDePasse,
			List<Role> role) {
		super();
		
		this.motDePasse = motDePasse;
		
		this.adresseMail = adresseMail;
		this.roles = role;
	}
	
}
