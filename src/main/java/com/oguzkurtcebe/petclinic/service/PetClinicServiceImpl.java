package com.oguzkurtcebe.petclinic.service;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oguzkurtcebe.petclinic.dao.OwnerRepository;
import com.oguzkurtcebe.petclinic.dao.PetRepository;
import com.oguzkurtcebe.petclinic.exception.OwnerNotFoundException;
import com.oguzkurtcebe.petclinic.model.Owner;

@Service
@Transactional(rollbackFor=Exception.class)
public class PetClinicServiceImpl implements PetClinicService {

	private OwnerRepository ownerRepository;
	private PetRepository petRepository;
	
	@Autowired
	public void setPetRepository(PetRepository petRepository) {
		this.petRepository = petRepository;
	}
	
	@Autowired
	public void setOwnerRepository(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Owner> findOwners() {
		return ownerRepository.findAll();
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Owner> findOwners(String lastname) {
		return ownerRepository.findByLastName(lastname);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
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
		petRepository.deleteByOwnerId(id);
		ownerRepository.delete(id);
	//if(true)throw new RuntimeException("testing rollback");

	}

	@Override
	public void updateOwner(Owner owner) {
		ownerRepository.update(owner);
	}

}
