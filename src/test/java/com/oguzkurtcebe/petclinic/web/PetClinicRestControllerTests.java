package com.oguzkurtcebe.petclinic.web;

import java.net.URI;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.oguzkurtcebe.petclinic.model.Owner;
public class PetClinicRestControllerTests {
private RestTemplate restTemplate;


@Before
public void setUp() {
	 	restTemplate=new RestTemplate();
}

@Test
public void testUpdateOwner() {
   Owner owner=restTemplate.getForObject("http://localhost:8080/rest/owner/3", Owner.class);
	
	MatcherAssert.assertThat(owner.getFirstName(),Matchers.equalTo("Eda"));
	
	owner.setFirstName("Edanur");
	restTemplate.put("http://localhost:8080/rest/owner/3", owner);
	owner=restTemplate.getForObject("http://localhost:8080/rest/owner/3", Owner.class);
	MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Edanur"));
	}
@Test
public void testDeleteOwner() {
		restTemplate.delete("http://localhost:8080/rest/owner/2");
	try {
		restTemplate.getForEntity("http://localhost:8080/rest/owner/2", Owner.class);
		Assert.fail("should not have owner");
	} catch (RestClientException e) {
		
}}

@Test
public void testCreateOwner() {
	Owner owner=new Owner();
	owner.setFirstName("Mehmet");
    owner.setLastName("Kaya");
    
    URI location = restTemplate.postForLocation("http://localhost:8080/rest/owner",owner);
    Owner owner2=restTemplate.getForObject(location,Owner.class);
    MatcherAssert.assertThat(owner.getLastName(), Matchers.equalTo(owner.getLastName()));
    
}

@Test
public void testGetOwnerById() {
	ResponseEntity<Owner>response= restTemplate.getForEntity("http://localhost:8080/rest/owner/3",Owner.class);
	
	MatcherAssert.assertThat(response.getStatusCodeValue(),Matchers.equalTo(200));
	MatcherAssert.assertThat(response.getBody().getFirstName(),Matchers.equalTo("Eda"));
}
}
