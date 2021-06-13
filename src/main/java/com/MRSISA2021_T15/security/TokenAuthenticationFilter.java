package com.MRSISA2021_T15.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.MRSISA2021_T15.utils.TokenUtils;

import io.jsonwebtoken.ExpiredJwtException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private TokenUtils tokenUtils;

	private UserDetailsService userDetailsService;
	
	protected final Log LOGGER = LogFactory.getLog(getClass());

	public TokenAuthenticationFilter(TokenUtils tokenHelper, UserDetailsService userDetailsService) {
		this.tokenUtils = tokenHelper;
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {


		String username;
		
		var authToken = tokenUtils.getToken(request);
		
		try {
	
			if (authToken != null) {
				username = tokenUtils.getUsernameFromToken(authToken);
				
				if (username != null) {
					
					var userDetails = userDetailsService.loadUserByUsername(username);
					
					if (tokenUtils.validateToken(authToken, userDetails)) {
						
						var authentication = new TokenBasedAuthentication(userDetails);
						authentication.setToken(authToken);
						SecurityContextHolder.getContext().setAuthentication(authentication);
					}
				}
			}
			
		} catch (ExpiredJwtException ex) {
			LOGGER.debug("Token expired!");
		} 
		chain.doFilter(request, response);
	}

}
