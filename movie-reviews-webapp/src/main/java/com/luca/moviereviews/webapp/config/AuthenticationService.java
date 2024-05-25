package com.luca.moviereviews.webapp.config;


import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;


import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luca.moviereviews.core.model.EmailTemplateName;
import com.luca.moviereviews.core.security.AuthenticationRequest;
import com.luca.moviereviews.core.security.AuthenticationResponse;
import com.luca.moviereviews.core.security.JwtService;
import com.luca.moviereviews.core.security.RegisterRequest;
import com.luca.moviereviews.core.security.UserDetailsImpl;
import com.luca.moviereviews.core.service.EmailService;
import com.luca.moviereviews.jpa.entities.SecurityUser;
import com.luca.moviereviews.jpa.entities.Token;
import com.luca.moviereviews.jpa.enums.Role;
import com.luca.moviereviews.jpa.repository.SecurityUserRepository;
import com.luca.moviereviews.jpa.repository.TokenRepository;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	
	private final SecurityUserRepository securityUserRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;

	private final AuthenticationManager authenticationManager;
	
	private final EmailService emailService;
	
	private final TokenRepository tokenRepository;
	
	private final static String activationUrl="http://localhost:3000/activate-account";
	
	public AuthenticationResponse register(RegisterRequest request) throws MessagingException {
		var user = UserDetailsImpl.builder().firstName(request.getFirstname()).lastName(request.getLastname())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).username(request.getUsername())
				.role(Role.USER)
				.enabled(false)
				.build();
		
		SecurityUser securityUser = new SecurityUser();
		
		securityUser.setUsername(user.getUsername());
		securityUser.setCountry(user.getCountry());
		securityUser.setEmail(user.getEmail());
		securityUser.setFirstName(user.getFirstName());
		securityUser.setLastName(user.getLastName());
		securityUser.setPassword(user.getPassword());
		securityUser.setRole(user.getRole());
		securityUser.setEnabled(user.isEnabled());
		
		// aggiungere enabled a false nel salvataggio
		
		securityUserRepository.save(securityUser);
		
		sendValidationEmail(securityUser);
		
		var jwtRefreshToken = jwtService.generateRefreshToken(user);
		
		var jwtToken = jwtService.generateToken(user);
		
		return AuthenticationResponse.builder()
				.token(jwtToken).refreshToken(jwtRefreshToken).build();
		
	}

	@Transactional
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		Authentication auth=authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		//var claims = new HashMap<String,Object>();
		
		
		// un altro modo per recuperare UserDetails è utilizzando il metodo getPrincipal dell'istanza Authentication
		SecurityUser securityUser = securityUserRepository.findByUsername(request.getUsername()).orElseThrow();
		
		//claims.put("lastName", securityUser.getLastName());
	
		UserDetails user=UserDetailsImpl.builder().firstName(securityUser.getFirstName()).lastName(securityUser.getLastName())
		.email(securityUser.getEmail()).password(securityUser.getPassword())
		.role(securityUser.getRole()).username(securityUser.getUsername())
		.build();
		
		
		
		var jwtToken = jwtService.generateToken(user);
		var jwtRefreshToken = jwtService.generateRefreshToken(user);
		return AuthenticationResponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
	}
	
	
	@Transactional
	public void editProfile(RegisterRequest request ) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		SecurityUser securityUser = securityUserRepository.findByUsername(userDetails.getUsername()).orElseThrow();
		if(request.getCountry()!=null) {
			securityUser.setCountry(request.getCountry());
		}
		if(request.getEmail()!=null) {
			securityUser.setEmail(request.getEmail());
		}
		if(request.getFirstname()!=null) {
			securityUser.setFirstName(request.getFirstname());
		}
		if(request.getLastname()!=null) {
			securityUser.setLastName(request.getLastname());
		}
		if(request.getPassword()!=null) {
			securityUser.setPassword(request.getPassword());
		}
		if(request.getUsername()!=null) {
			securityUser.setUsername(request.getUsername());
		}
		
		
	}
	
	
	public void refreshToken(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String username;
		System.out.println("parte il refresh");
		
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			System.out.println("Non inizia con bearer");
			return;
		}
		
		refreshToken=authHeader.substring(7);
		
		username=jwtService.extractUsername(refreshToken);
		
		
		if(username!=null) {
			
			SecurityUser securityUser=this.securityUserRepository.findByUsername(username).orElseThrow();
			
			
			UserDetailsImpl userDetails=new UserDetailsImpl();
			
			userDetails.setEmail(securityUser.getEmail());
			userDetails.setPassword(securityUser.getPassword());
			userDetails.setRole(securityUser.getRole());
			userDetails.setUsername(securityUser.getUsername());
			userDetails.setCountry(securityUser.getCountry());
			userDetails.setFirstName(securityUser.getFirstName());
			userDetails.setLastName(securityUser.getLastName());
			userDetails.setEnabled(securityUser.getEnabled());
			
			
			if(jwtService.isTokenValid(refreshToken, userDetails)) {
				
				var accessToken = jwtService.generateToken(userDetails);
				var authResponse = AuthenticationResponse.builder().token(accessToken).refreshToken(refreshToken).build();
				
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			}
		}
		
		
	}
	
	//@Transactional
	public void activateAccount(String token) throws MessagingException {
		
		Token savedToken = tokenRepository.findByToken(token).orElseThrow(()->new RuntimeException("Invalid token"));
		if(LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
			sendValidationEmail(savedToken.getSecurityUser());
			throw new RuntimeException("Activation Token has expired. A new token has been sent");
		}
		
		var user=securityUserRepository.findById(savedToken.getSecurityUser().getId())
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
		user.setEnabled(true);
		securityUserRepository.save(user);
		savedToken.setValidatedAt(LocalDateTime.now());
		tokenRepository.save(savedToken);
		
	}
	
	private void sendValidationEmail(SecurityUser user) throws MessagingException {
		var token=generateAndSaveActivationToken(user);
		
		emailService.sendMail(user.getEmail(), user.getUsername(), EmailTemplateName.ACTIVATE_ACCOUNT, activationUrl, token, "Account activation");
		
	}
	
	private String generateAndSaveActivationToken(SecurityUser user) {
		// generate a token
		String generatedToken= generateActivationCode(6);
		
		// salva il token in una tabella per i token
		var token= Token.builder().token(generatedToken).createdAt(LocalDateTime.now()).expiresAt(LocalDateTime.now().plusMinutes(15)).securityUser(user).build();
		
		tokenRepository.save(token);
		
		return generatedToken;
	}
	
	private String generateActivationCode(int length) {
		
		String characters= "0123456789";
		StringBuilder codeBuilder = new StringBuilder();
		SecureRandom secureRandom = new SecureRandom();
		
		for(int i=0;i<length;i++) {
			int randomIndex=secureRandom.nextInt(characters.length()); //0..9
			codeBuilder.append(characters.charAt(randomIndex));
		}
		
		return codeBuilder.toString();
	}
}
