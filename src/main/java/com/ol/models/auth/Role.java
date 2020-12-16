package com.ol.models.auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    /**
     * id du role
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * nom du role
     */
    private String libelle;

    /**
     * constructeur
     * @param libelle nom du role
     */
    public Role(String libelle){
        this.libelle = libelle;

    }
	
	

}
