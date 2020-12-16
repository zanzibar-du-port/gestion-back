package com.ol.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ol.models.auth.InfosAuthentificationPost;
import com.ol.models.auth.Role;
import com.ol.models.auth.Utilisateur;
import com.ol.repositories.UtilisateurRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Cette classe est un service qui gère l'authentification de l'utilisateur et
 * la création ou non d'un cookie qui lui permettra d'être authentifié et donc
 * d'avoir accès aux differentes pages de l'application (selon son rôle)
 * 
 * @author Diginamic02
 *
 */
@Service
public class AuthentificationService {

	private UtilisateurRepository utilisateurRepository;
	private PasswordEncoder passwordEncoder;

	public AuthentificationService(UtilisateurRepository utilisateurRepository,
			PasswordEncoder passwordEncoder) {
		this.utilisateurRepository = utilisateurRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN;

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Value("${jwt.secret}")
	private String SECRET;
	/**
	 * Cette methode gère la création du cookie correspondant aux information
	 * postées par l'utilisateur dans le corps du POST correspondant. Elle
	 * retrouve l'utilisateur et ses infos dans la base de donnée s'il existe.
	 * Elle vérifie que le mot de passe fournit par l'utilisateur correspond à
	 * celui enregistré en BDD Elle crée un cookie contenant l'identifiant de
	 * l'utilisateur, son rôle, sa commune, sa date d'expiration (=date création
	 * + 86400 secondes soit 24h) puis le crypte avec la variable String SECRET
	 * renseignée dans le application.propertie
	 * 
	 * @param infos
	 * @return
	 */
	public ResponseEntity<?> authenticate(InfosAuthentificationPost infos) {
		Utilisateur efez = utilisateurRepository.findByIdentifiant(infos.getIdentifiant()).get();
		boolean jhvjh = passwordEncoder.matches(infos.getMotDePasse(), efez.getMotDePasse());
		System.out.println(this.passwordEncoder.encode(infos.getMotDePasse()));
		System.out.println(efez.getMotDePasse());
		return this.utilisateurRepository.findByIdentifiant(infos.getIdentifiant())
				.filter(utilisateur -> passwordEncoder.matches(infos.getMotDePasse(), utilisateur.getMotDePasse()))
				.map(utilisateur -> {
					Map<String, Object> infosSupplementaireToken = new HashMap<>();
					infosSupplementaireToken.put("roles",
							utilisateur.getRoles().stream().map(Role::getLibelle).collect(Collectors.joining(",")));
					
					String jetonJWT = Jwts.builder().setSubject(utilisateur.getAdresseMail())
							.addClaims(infosSupplementaireToken)
							.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
							.signWith(SignatureAlgorithm.HS512, SECRET).compact();

					ResponseCookie tokenCookie = ResponseCookie.from(TOKEN_COOKIE, jetonJWT).httpOnly(true)
							.maxAge(EXPIRES_IN).path("/").build();

					return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, tokenCookie.toString()).build();

				}).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

}
