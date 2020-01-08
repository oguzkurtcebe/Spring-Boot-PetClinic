package com.oguzkurtcebe.petclinic.service;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oguzkurtcebe.petclinic.dao.OwnerRepository;
import com.oguzkurtcebe.petclinic.dao.PetRepository;
import com.oguzkurtcebe.petclinic.dao.jpa.VetRepository;
import com.oguzkurtcebe.petclinic.exception.OwnerNotFoundException;
import com.oguzkurtcebe.petclinic.exception.VetNotFoundException;
import com.oguzkurtcebe.petclinic.model.Owner;
import com.oguzkurtcebe.petclinic.model.Vet;

@Service
@Transactional(rollbackFor=Exception.class)
public class PetClinicServiceImpl implements PetClinicService {

	private OwnerRepository ownerRepository;
	private PetRepository petRepository;
	private VetRepository vetRepository;
	
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	public void setPetRepository(PetRepository petRepository) {
		this.petRepository = petRepository;
	}
	
	@Autowired
	public void setOwnerRepository(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}
	
	@Autowired
	public void setVetRepository(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}
	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	@Secured(value={"ROLE_USER","ROLE_EDITOR"})
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
	@CacheEvict(cacheNames="allOwners",allEntries=true)
	public void createOwner(Owner owner) {
		ownerRepository.create(owner);
		SimpleMailMessage msg=new SimpleMailMessage();
		msg.setFrom("o@k");
		msg.setTo("k@o");
		msg.setSubject("Owner oluşturuldu..");
		msg.setText("Owner id si:"+owner.getId()+" başarıyla oluşturuldu");
		mailSender.send(msg);

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

	@Override
	public List<Vet> findVets() {
		return vetRepository.findAll();
	}

	@Override
	public Vet findVet(Long id) throws VetNotFoundException {
		return vetRepository.findById(id).orElseThrow(()->{return new VetNotFoundException("Vet id Not found :"+id);});
		
	}

}
