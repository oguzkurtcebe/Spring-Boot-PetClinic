	package com.oguzkurtcebe.petclinic.service;

import java.util.List;

import javax.validation.Valid;

import com.oguzkurtcebe.petclinic.exception.OwnerNotFoundException;
import com.oguzkurtcebe.petclinic.exception.VetNotFoundException;
import com.oguzkurtcebe.petclinic.model.Owner;
import com.oguzkurtcebe.petclinic.model.Vet;

public interface PetClinicService {

	List<Owner> findOwners();
	List<Owner> findOwners(String lastname);
	Owner findOwner(Long id) throws OwnerNotFoundException;
	void createOwner(@Valid Owner owner);
	void deleteOwner(Long id);
	void updateOwner(Owner owner);
	
	 List<Vet>findVets();
	 Vet findVet(Long id) throws VetNotFoundException;

}
