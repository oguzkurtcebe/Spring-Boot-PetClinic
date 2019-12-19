package com.oguzkurtcebe.petclinic.dao;

import java.util.List;

import com.oguzkurtcebe.petclinic.model.Owner;

public interface OwnerRepository {
	List<Owner> findAll();//bütün owner nesnesini döndüreek
	Owner findById(Long id);//id si verilen bir owner nesnesini döndürecek
	List<Owner> findByLastName(String lastname);	
	void create(Owner owner);	
	Owner update(Owner owner);
	void delete(Long id);
	
	
	
}
