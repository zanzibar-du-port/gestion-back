package com.ol.models.fromfront;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailFromFront {
	
	private String destinataire;
	private String objet;
	private String texteMail;

}
