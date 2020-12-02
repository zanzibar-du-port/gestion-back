package com.ol.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Footer {

	@Id
	@Column(name = "id")
	private Long id;
	private String titre;
	private String paragraph;
	private String textInput;
	private String textBouton;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "IMAGE_ID")
	private Image image;
	
	public Footer(Long id, String titre, String paragraph, Image image) {
		super();
		this.id = new Long(1);
		this.titre = titre;
		this.paragraph = paragraph;
		this.image = image;
	}
	
}
