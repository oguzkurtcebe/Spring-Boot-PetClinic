package com.oguzkurtcebe.petclinic.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.oguzkurtcebe.petclinic.dao.OwnerRepository;
import com.oguzkurtcebe.petclinic.model.Owner;

@Repository("ownerRepository")
public class OwnerRepositoryJpaImpl implements OwnerRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Owner> findAll() {
		return entityManager.createQuery("from Owner",Owner.class).getResultList();
	}

	@Override
	public Owner findById(Long id) {
		return entityManager.find(Owner.class, id);
	}

	@Override
	public List<Owner> findByLastName(String lastName) {
		return entityManager.createQuery("from Owner where lastName = :lastName",Owner.class).setParameter("Lastname",lastName)
				.getResultList();
	}

	@Override
	public void create(Owner owner) {
		entityManager.persist(owner);

	}

	@Override
	public Owner update(Owner owner) {
		return entityManager.merge(owner);
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(entityManager.getReference(Owner.class, id));

	}

}
