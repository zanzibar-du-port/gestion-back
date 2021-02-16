package com.ol.models.fromfront;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandeFromFront {
	
	private List<CommandeProduitFromFront> listeCommandeProduitFromFront;
	private MailFromFront mailFromFront;
	

}
