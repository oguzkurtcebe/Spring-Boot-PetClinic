package com.oguzkurtcebe.petclinic.security;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.oguzkurtcebe.petclinic.model.Owner;
import com.oguzkurtcebe.petclinic.service.PetClinicService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties="spring.profiles.active=dev")

public class PetClinicSecurityWithoutValidAuthTokenTests {

	@Autowired
	private PetClinicService petClinicService;
      
	@Before
	public void setUp() {
		TestingAuthenticationToken auth=new TestingAuthenticationToken("user","secret","ROLE_USER");
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Test
	public void testFindOwners() {
		List<Owner> owners = petClinicService.findOwners();
		MatcherAssert.assertThat(owners.size(),Matchers.equalTo(10));
	}
	

	@After
	public void tearDown() {
		SecurityContextHolder.clearContext();
	}

}
