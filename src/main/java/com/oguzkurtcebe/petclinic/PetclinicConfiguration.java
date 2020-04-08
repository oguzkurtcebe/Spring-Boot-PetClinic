package com.oguzkurtcebe.petclinic;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PetclinicConfiguration {
    @Autowired
 	private PetClinicProperties petClinicProperties;

    @PostConstruct
public void init() {
	System.out.println("Display Owner With Pets:"+petClinicProperties.isDisplayOwnersWithPets());
}
}
