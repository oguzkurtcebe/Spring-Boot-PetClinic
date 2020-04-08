package com.oguzkurtcebe.petclinic;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

	@Test
	public void PasswordEncodedTests() {
		
		BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		System.out.println("{bcrypt}" + passwordEncoder.encode("secret"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("secret2"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("secret3"));
		
	}
}
