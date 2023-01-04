package com.collabera.photographer_app.jwtfilter;

import java.io.IOException;




import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.collabera.photographer_app.jwtutil.JwtUtil;
import com.collabera.photographer_app.services.AdminAndUsersServices;








@Configuration
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private AdminAndUsersServices adminAndUsersServices;
	


	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorizationHeader = request.getHeader("Authorization");

	
		String jwt = null;
		String userName = null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
           
			jwt = authorizationHeader.substring(7);
			

			userName = jwtUtil.extractUsername(jwt);

		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails details = this.adminAndUsersServices.loadUserByUsername(userName);
			
			if (jwtUtil.validateToken(jwt, details)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						details.getUsername(), details.getPassword(), details.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}

		}

		filterChain.doFilter(request, response);

	}

}
