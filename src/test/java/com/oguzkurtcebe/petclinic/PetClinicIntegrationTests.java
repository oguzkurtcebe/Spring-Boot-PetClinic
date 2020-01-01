package com.oguzkurtcebe.petclinic;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.oguzkurtcebe.petclinic.model.Owner;
import com.oguzkurtcebe.petclinic.service.PetClinicService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties= {"spring.profiles.active=dev"})
public class PetClinicIntegrationTests {

	@Autowired
private	PetClinicService petClininService;

@Test
public void testFindOwners() {
	List<Owner> owners = petClininService.findOwners();
	MatcherAssert.assertThat(owners.size(),Matchers.equalTo(10));
}

}
