package com.ol.services;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ol.models.Commande;
import com.ol.models.Produit;
import com.ol.models.auth.Utilisateur;


@Service
public class MailService {
	
	@Autowired
	InfosGeneralesService infosGeneralesService;
	
	@Value("${admin.email}")
	private String emailAdmin;
	@Value("${admin.mdp}")
	private String mdpAdmin;
	@Value("${admin.urlsite}")
	private String urlSite;

	public void sendMailCommande(Commande commande) {
		
		sendMail(writeMailClient(commande),
				emailAdmin,
				commande.getContactClient().getClient().getAdresseEmail(),
				"votre commande sur " + urlSite
				);
		
		sendMail(writeMailAdmin(commande),
				emailAdmin,
				commande.getContactClient().getClient().getAdresseEmail(),
				"nouvelle commande d'un montant de : " + commande.getPrixPanier() + " en " + commande.getMethodeDeReceptionProduits());
	}
	
	public void sendMailNouveauContact(Utilisateur utilisateur) {
		sendMail(writeMailAdminNouveauContact(utilisateur),
				emailAdmin,
				utilisateur.getAdresseMail(),
				"votre commande sur " + urlSite
				);
	}
	
	private String writeMailClient(Commande commande) {
		String newLine = System.getProperty("line.separator");
		String listeProduits = getListeProduits(commande);
		return "Bonjour " + commande.getContactClient().getNomPrenom()
		+ newLine + "Merci pour votre commande dont voici le résumé"
		+ newLine + listeProduits
		+ newLine
		+ newLine + "Votre code de récupération est : " + commande.getCodeRecuperation()
		+ newLine 
		+ newLine + infosGeneralesService.getInfosGenerales().getTextMailCommande()
		+ newLine
		+ newLine
		+ newLine + "A bientôt";
					
	}
	private String writeMailAdmin(Commande commande) {
		String newLine = System.getProperty("line.separator");
		String listeProduits = getListeProduits(commande);
		return "Bonjour " + commande.getContactClient().getNomPrenom()
					+ newLine + "Voici une nouvelle commande :"
					+ newLine + listeProduits
					+ newLine
					+ newLine + "Informations sur la commande : "
					+ newLine + "Type decommande : " + commande.getMethodeDeReceptionProduits()
					+ newLine + "Nom et prénom : " + commande.getContactClient().getNomPrenom()
					+ newLine + "Téléphone : " + commande.getContactClient().getTelephone()
					+ newLine + "Adresse mail : " + commande.getContactClient().getClient().getAdresseEmail()
					+ newLine + "Nom et prénom : " + commande.getContactClient().getAdresse()
					+ newLine + "Nom et prénom : " + commande.getContactClient().getNomPrenom()
					+ newLine + "Nom et prénom : " + commande.getContactClient().getNomPrenom()
					+ newLine + "Nom et prénom : " + commande.getContactClient().getNomPrenom()
					+ newLine + "Le code de récupération est : " + commande.getCodeRecuperation()
					+ newLine
					+ newLine
					+ newLine + "A bientôt";
	}
	
	private String writeMailAdminNouveauContact(Utilisateur utilisateur) {
		String newLine = System.getProperty("line.separator");
		return "Bonjour " 
					+ newLine + "Un nouveau contact pour la newsletter : " + utilisateur.getAdresseMail()
					+ newLine
					+ newLine + "Bonne journée";
	}
	/**
	 * @param commande
	 * @return
	 */
	private String getListeProduits(Commande commande) {
		String newLine = System.getProperty("line.separator");
		String listeProduits = "";
		
		return listeProduits;
					
	}

	public void sendMail(String bodyMail, String expediteur, String destinataire, String objet) {
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
            message.setFrom(new InternetAddress(expediteur));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinataire)
            );
           
				message.setSubject(objet);

            	// Create the message part
                BodyPart messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setText(bodyMail);

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
