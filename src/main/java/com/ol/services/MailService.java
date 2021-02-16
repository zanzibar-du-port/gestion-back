package com.ol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ol.models.Fournisseur;
import com.ol.models.Produit;
import com.ol.models.fromfront.CommandeFromFront;
import com.ol.models.fromfront.CommandeProduitFromFront;
import com.ol.models.fromfront.MailFromFront;
import com.ol.repositories.FournisseurRepository;
import com.ol.repositories.ProduitRepository;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
public class MailService {
	
	@Autowired
	ProduitRepository produitRepository;

	@Autowired
	FournisseurRepository fournisseurRepository;
	
	@Value("${admin.email}")
	private String emailAdmin;
	@Value("${admin.mdp}")
	private String mdpAdmin;

	public MailFromFront getMailFromFrontByCommandeFromFront(CommandeFromFront commandeFromFront) {
		
		String textMail = "Bonjour, \n" +
						"Voici la commande pour l'Epicerie du port \n\n";
		
		for (CommandeProduitFromFront commandeProduitFromFront : commandeFromFront.getListeCommandeProduitFromFront()) {
			Produit produit = produitRepository.findById(commandeProduitFromFront.getIdProduit()).get();
			if(commandeProduitFromFront.getNbLots() != 0) {
				if(produit.getNbProduitParLot() > 1) {
					textMail = textMail
							+ commandeProduitFromFront.getNbLots() 
							+ " lot(s) de " + produit.getNbProduitParLot() + " " + produit.getNom() 
							+ " (référence : " + produit.getReference() + ") \n";
				}else {
					textMail = textMail
							+ commandeProduitFromFront.getNbLots() 
							+ " " + produit.getNom() 
							+ " (référence : " + produit.getReference() + ") \n";
				}
			}
			
			
		}
		textMail = textMail
				+ "\n" 
				+ "Cordialement \n"
				+ "\n"
				+ "Melanie Jacq";
		
		Fournisseur fournisseur = fournisseurRepository.findByIdProduit(commandeFromFront.getListeCommandeProduitFromFront().get(0).getIdProduit()).get();
		
		return new MailFromFront(fournisseur.getEmail(), "Commande Epicerie du Port", textMail);
	}

	public void sendMail(MailFromFront mailFromFront) {
		String username = emailAdmin;
        String password = mdpAdmin;

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Epicerie-du-port"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailFromFront.getDestinataire())
            );
           
				message.setSubject(mailFromFront.getObjet());

            	// Create the message part
                BodyPart messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setText(mailFromFront.getTexteMail());

                // Create a multipar message
                Multipart multipart = new MimeMultipart();

                // Set text message part
                multipart.addBodyPart(messageBodyPart);

                // Send the complete message parts
                message.setContent(multipart);
                
			
            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}

}
