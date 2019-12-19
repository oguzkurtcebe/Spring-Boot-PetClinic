package com.oguzkurtcebe.petclinic.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oguzkurtcebe.petclinic.dao.OwnerRepository;
import com.oguzkurtcebe.petclinic.exception.OwnerNotFoundException;
import com.oguzkurtcebe.petclinic.model.Owner;

@Service
public class PetClinicServiceImpl implements PetClinicService {

	private OwnerRepository ownerRepository;
	@Autowired
	public void setOwnerRepository(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}
	
	@Override
	public List<Owner> findOwners() {
		return ownerRepository.findAll();
	}

	@Override
	public List<Owner> findOwners(String lastname) {
		return ownerRepository.findByLastName(lastname);
	}

	@Override
	public Owner findOwner(Long id) throws OwnerNotFoundException {
		Owner owner=ownerRepository.findById(id);
		if(owner==null) throw new OwnerNotFoundException("Owner not Found");
		return owner;
	}
    
	@Override
	public void createOwner(Owner owner) {
		ownerRepository.create(owner);

	}

	@Override
	public void deleteOwner(Long id) {
		ownerRepository.delete(id);

	}

	@Override
	public void updateOwner(Owner owner) {
		ownerRepository.update(owner);
	}

}
