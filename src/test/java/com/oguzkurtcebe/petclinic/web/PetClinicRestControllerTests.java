package com.oguzkurtcebe.petclinic.web;

import java.net.URI;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.oguzkurtcebe.petclinic.model.Owner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class PetClinicRestControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate.withBasicAuth("user2", "secret2");

	}

	@Test
	public void testUpdateOwner() {
		Owner owner = restTemplate.getForObject("http://localhost:8080/rest/owner/3", Owner.class);

		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Eda"));

		owner.setFirstName("Edanur");
		restTemplate.put("http://localhost:8080/rest/owner/3", owner);
		owner = restTemplate.getForObject("http://localhost:8080/rest/owner/3", Owner.class);
		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Edanur"));
	}

	
	public void testDeleteOwner() {

		ResponseEntity<Void> responseEntity = restTemplate.exchange("http://localhost:8080/rest/owner/1",
				HttpMethod.DELETE, null, Void.class);
		restTemplate.delete("http://localhost:8080/rest/owner/2");
		try {
			restTemplate.getForEntity("http://localhost:8080/rest/owner/2", Owner.class);
			Assert.fail("should not have owner");
		} catch (RestClientException e) {

		}
	}

	@Test
	public void testCreateOwner() {
		Owner owner = new Owner();
		owner.setFirstName("Mehmet");
		owner.setLastName("Kaya");

		URI location = restTemplate.postForLocation("http://localhost:8080/rest/owner", owner);
		Owner owner2 = restTemplate.getForObject(location, Owner.class);
		MatcherAssert.assertThat(owner.getLastName(), Matchers.equalTo(owner.getLastName()));
	}
	

	@Test
	public void testGetOwnersByLastName() {
		ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owner?ln=Ferit",
				List.class);

		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		List<Map<String, String>> body = response.getBody();

		List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

		MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Kenan", "Hümeyra", "Salim"));
	}
	

	@Test
	public void testGetOwnerById() {
		ResponseEntity<Owner> response = restTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Ziya"));
	}

	@Test
	public void testServiceLevelValidation() {
		Owner owner = new Owner();
		//owner.setFirstName("k");
		//owner.setLastName("s");
		ResponseEntity<URI> responseEntity = restTemplate.postForEntity("http://localhost:8080/rest/owner", owner,URI.class);
				
		MatcherAssert.assertThat(responseEntity.getStatusCode(), Matchers.equalTo(HttpStatus.PRECONDITION_FAILED));

	}

}
