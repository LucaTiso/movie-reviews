package com.luca.moviereviews.core.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET_KEY = "c815c44f5b497cd677fad413488ce7859cb6d52be6ae3544c2a8c6a54b76aa72";
	
	private static final long ACCESS_TOKEN_EXPIRATION = 3_000_000l;
	
	private static final long REFRESH_TOKEN_EXPIRATION = 600_000_000l;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllCaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String generateToken( UserDetails userDetails) {
		return generateToken(new HashMap<>(),userDetails);
	}

	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return buildToken(extraClaims,userDetails,ACCESS_TOKEN_EXPIRATION);
	}
	
	public String generateRefreshToken(UserDetails userDetails) {
		return buildToken(new HashMap<>(),userDetails,REFRESH_TOKEN_EXPIRATION);
	}
	
	private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails,long expiration) {
		var authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.claim("authorities",authorities)
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username=extractUsername(token);
		return (username.equals(userDetails.getUsername()) ) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}

	private Claims extractAllCaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

		return Keys.hmacShaKeyFor(keyBytes);
	}

}
