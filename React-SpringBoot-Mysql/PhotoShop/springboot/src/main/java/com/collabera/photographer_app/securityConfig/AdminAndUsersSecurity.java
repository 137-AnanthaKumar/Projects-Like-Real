package com.collabera.photographer_app.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.collabera.photographer_app.jwtfilter.JwtFilter;
import com.collabera.photographer_app.services.AdminAndUsersServices;

@EnableWebSecurity
public class AdminAndUsersSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AdminAndUsersServices adminAndUsersServices;
	
	@Autowired
	private JwtFilter filter;
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(adminAndUsersServices);
	}
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors();
		http.authorizeHttpRequests().antMatchers("/photographer/reg").permitAll();
		http.authorizeHttpRequests().antMatchers("/admin/login").permitAll();
		http.authorizeHttpRequests().antMatchers("/{userName}").permitAll();
		http.authorizeHttpRequests().antMatchers("/edit/{userName}").hasAnyRole("Admin","Photographer");
		http.authorizeHttpRequests().antMatchers("/remove/{id}").hasAnyRole("Admin","Photographer");
		http.authorizeHttpRequests().antMatchers("/get/{id}").permitAll();
		http.authorizeHttpRequests().antMatchers("/admin/allphotographers").hasRole("Admin");
		//http.authorizeHttpRequests().antMatchers("/admin/allphotographers").permitAll();

		http.authorizeHttpRequests().antMatchers("/photo/upload/{catagory}/{userId}").hasRole("Photographer");
		http.authorizeHttpRequests().antMatchers("/photo/download/{imageId}").permitAll();
		http.authorizeHttpRequests().antMatchers("/photo/delete/{imageId}").hasRole("Photographer");
		http.authorizeHttpRequests().antMatchers("/photo/getall/{photograoherId}").hasAnyRole("Admin","Photographer");
		http.authorizeHttpRequests().antMatchers("/search/get/{category}").permitAll();
		http.authorizeHttpRequests().anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}
	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}


	
	

}
