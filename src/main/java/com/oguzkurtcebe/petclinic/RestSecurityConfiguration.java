package com.oguzkurtcebe.petclinic;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Order(value=1)
@Configuration
public class RestSecurityConfiguration extends AbstractSecurityConfiguration {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//super.configure(http);
		http.antMatcher("/rest/**");
<<<<<<< HEAD
		http.authorizeRequests().antMatchers("/rest/**").access("hasRole('EDITOR')");
=======
	//	http.authorizeRequests().antMatchers("/rest/**").access("hasRole('EDITOR')");
>>>>>>> branch 'master' of https://github.com/oguzkurtcebe/Spring-Boot-PetClinic.git
		http.csrf().disable();
		http.httpBasic();
		

	}
}
