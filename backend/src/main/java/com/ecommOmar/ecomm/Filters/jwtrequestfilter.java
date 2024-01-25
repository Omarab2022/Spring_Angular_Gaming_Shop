package com.ecommOmar.ecomm.Filters;

import com.ecommOmar.ecomm.Services.jwt.UserDetailsServiceImpl;
import com.ecommOmar.ecomm.Utils.JWTutils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;



@Component
@RequiredArgsConstructor
public class jwtrequestfilter extends OncePerRequestFilter {


	private final UserDetailsServiceImpl userDetailsService ;

	private final JWTutils jwTutils ;



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	                                FilterChain filterChain) throws ServletException,
			IOException {


		String authheader = request.getHeader("Authorization");
		String token = null;
		String username = null ;


		if (authheader != null && authheader.startsWith("Bearer ")) {

			token =authheader.substring(7);
			username = jwTutils.extractUsername(token);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if(jwTutils.validateToken(token , userDetails)){
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails , null);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}

		filterChain.doFilter(request,response);
	}
}
