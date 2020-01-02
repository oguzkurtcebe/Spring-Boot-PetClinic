package com.oguzkurtcebe.petclinic.security;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.securityContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.oguzkurtcebe.petclinic.service.PetClinicService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profies.active=dev")
public class PetClinicSecurityWithInvalidAuthTokenTests {

	@Autowired
	private PetClinicService petClinicService;

	@Before
	public void setUp() {
		TestingAuthenticationToken auth = new TestingAuthenticationToken("username", "secret", "ROLE_XXX");
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Test(expected=AccessDeniedException.class) 
	public void testFindOwners() {

		petClinicService.findOwners();
	}

	@After()
	public void tearDown() {
      SecurityContextHolder.clearContext();
	}
}
