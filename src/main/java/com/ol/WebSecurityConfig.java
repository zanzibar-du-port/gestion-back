package com.ol;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Classe WebSecurityConfig Cette classe gère la méthode configure(HttpSecurity
 * http) qui définit le filtre de l'application et donc les urls accessibles par
 * les utilisateurs
 * 
 * @author Diginamic02
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Autowired
	JwtAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://localhost:4201"
				,"http://localhost:4202"
				,"http://146.59.154.95:4200"
				,"http://146.59.154.95:4201"
				,"http://146.59.154.95:4202"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
		// setAllowCredentials(true) is important, otherwise:
		// The value of the 'Access-Control-Allow-Origin' header in the response
		// must not be the wildcard '*' when the request's credentials mode is
		// 'include'.
		configuration.setAllowCredentials(true);
		// setAllowedHeaders is important! Without it, OPTIONS preflight request
		// will fail with 403 Invalid CORS request
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	/**
	 * Cette methode définit les urls disponibles (en GET ou POST) par
	 * l'utilisateur en fonction de son role (ADMIN et/ou USER) . Cette methode
	 * est activée à chaque url appellée par l'utilisateur et redirige ou nom
	 * vers les classes et méthodes concernées.
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
		http.headers().frameOptions().disable();

		http.authorizeRequests().antMatchers(HttpMethod.POST, "/authentification/auth").permitAll()
		.antMatchers(HttpMethod.GET, "/authentification/auth/user").permitAll()
		.antMatchers(HttpMethod.POST, "/utilisateur/addUtilisateur").permitAll()
		.antMatchers(HttpMethod.POST, "/stripe/**").permitAll()
		.antMatchers(HttpMethod.POST, "/footer/changeFooter").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/footer/getFooter").permitAll()
		.antMatchers(HttpMethod.POST, "/header/changeHeader").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/header/getHeader").permitAll()
		.antMatchers(HttpMethod.GET, "/image/delete/{imageId}").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/image/upload").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/image/get/{imageId}").permitAll()
		.antMatchers(HttpMethod.GET, "/image/getAll").permitAll()
		.antMatchers(HttpMethod.POST, "/infosGenerales/changeInfosGenerales").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/infosGenerales/**").permitAll()
		.antMatchers(HttpMethod.POST, "/produit/addProduit").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/produit/getAll").permitAll()
		.antMatchers(HttpMethod.GET, "/produit/get/{produitId}").permitAll()
		.antMatchers(HttpMethod.POST, "/produit/getProduitsActifs").permitAll()
		.antMatchers(HttpMethod.GET, "/produit/getAllByCategorie/{categorie}").permitAll()
		.antMatchers(HttpMethod.GET, "/categorie/getAll").permitAll()
		.anyRequest().authenticated();

		http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();
		http.logout().logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpStatus.OK.value()))
				.deleteCookies(TOKEN_COOKIE);
		
		
	}

}
