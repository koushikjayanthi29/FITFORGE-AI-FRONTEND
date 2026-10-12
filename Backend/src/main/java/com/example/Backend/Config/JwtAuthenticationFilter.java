package com.example.Backend.Config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Backend.Service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        String token = null;
        String email = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);

            try {
                email = jwtService.extractEmail(token);
            } catch (Exception e) {
                // Invalid token
            }
        }

        if (email != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

        	if (jwtService.isTokenValid(token, email)) {

        	    UsernamePasswordAuthenticationToken authentication =
        	            new UsernamePasswordAuthenticationToken(
        	                    email,
        	                    null,
        	                    java.util.Collections.emptyList()
        	            );

        	    authentication.setDetails(
        	            new WebAuthenticationDetailsSource()
        	                    .buildDetails(request)
        	    );

        	    SecurityContextHolder.getContext()
        	            .setAuthentication(authentication);

        	    System.out.println("JWT AUTHENTICATED USER: " + email);
        	}
        }

        filterChain.doFilter(request, response);
    }
}