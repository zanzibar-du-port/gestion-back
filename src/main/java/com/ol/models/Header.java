package com.ol.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data
public class Header {
	
	@Id
	@Column(name = "id")
	private Integer id;
	private String titre;
	private String paragraph;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "IMAGE_ID")
	private Image image;
	
	public Header(Integer id, String titre, String paragraph, Image image) {
		super();
		this.id = new Integer(1);
		this.titre = titre;
		this.paragraph = paragraph;
		this.image = image;
	}
	
	

}
