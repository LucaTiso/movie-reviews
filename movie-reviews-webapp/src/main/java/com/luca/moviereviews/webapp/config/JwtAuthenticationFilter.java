package com.luca.moviereviews.webapp.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.luca.moviereviews.core.security.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
private final JwtService jwtService;
	
	private final UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String username;
		System.out.println("parte il filtro");
		
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			System.out.println("Non inizia con bearer");
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt=authHeader.substring(7);
		
		username=jwtService.extractUsername(jwt);
		
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			System.out.println("userEmail not null");
			System.out.println(username);
			UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);	
			
			
			if(jwtService.isTokenValid(jwt, userDetails) && userDetails.isAccountNonExpired()) {
				
				
				System.out.println("token valido");
				System.out.println("username: "+userDetails.getUsername());
				
				
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
						);
			
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
			}
		}
		System.out.println("Fine filtro");
		filterChain.doFilter(request, response);
		
	}

}
